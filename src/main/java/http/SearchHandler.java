package http;

import com.alibaba.fastjson.JSON;
import contract.ContractManager;
import contract.DocumentManager;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;
import model.OfficialDocument;

import java.util.Map;
import java.util.Set;

/**
 * @author guyue
 * @date 2018/4/25
 */
public class SearchHandler implements IHttpHandler {
    @Override
    public void run(RoutingContext routingContext) {
        String documentId = routingContext.request().getParam("documentId");
        WebServer.getLog().info("some one try to search document id {}.", documentId);
        routingContext.vertx().executeBlocking(future -> {
            try {
                DocumentManager documentManager = ContractManager.getContract();
                String documentStr = documentManager.getDocument(documentId).send();
                WebServer.getLog().debug("get document from blockchain success : {}", documentStr);
                OfficialDocument document = JSON.parseObject(documentStr, OfficialDocument.class);
                future.complete(document);
            } catch (Exception e) {
                WebServer.getLog().error(e, "try to get the document {} error.", documentId);
                future.fail(e);
            }
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                if (asyncResult.result() == null) {
                    routingContext.response().end("search error: nonexistence");
                    return;
                }
                WebServer.getLog().debug("search document id {} success, result is {}.", documentId, asyncResult.result().toString());
                Set<Map.Entry<String, Object>> entries = JSON
                        .parseObject(JSON.toJSONString(asyncResult.result())).entrySet();
                ThymeleafTemplateEngine templateEngine = ThymeleafTemplateEngine.create();
                routingContext.put("msg", "success search document in block-chain.");
                routingContext.put("entries", entries);
                templateEngine.render(routingContext, "templates/", "result.html", bufferAsyncResult -> {
                    if (bufferAsyncResult.succeeded()) {
                        routingContext.response().putHeader("content-type", "Content-Type: text/html; charset=utf-8")
                                .end(bufferAsyncResult.result());
                    } else {
                        routingContext.fail(asyncResult.cause());
                    }
                });
            } else {
                routingContext.fail(asyncResult.cause());
            }
        });
    }
}
