package http;

import com.alibaba.fastjson.JSONArray;
import dao.RedisDao;
import com.alibaba.fastjson.JSON;
import contract.ContractManager;
import contract.DocumentManager;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;
import model.OfficialDocument;

import java.util.Map;
import java.util.Objects;
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
        Set<String> hashKeys = RedisDao.getRedis().smembers(documentId);
        if (hashKeys == null || hashKeys.size() == 0) {
            routingContext.response().end("search error: nonexistence | redis.");
            return;
        }
        String key = Objects.requireNonNull(hashKeys).iterator().next();
        routingContext.vertx().executeBlocking(future -> {
            try {
                DocumentManager documentManager = ContractManager.getContract();
                String documentStr = documentManager.getDocument(key).send();
                WebServer.getLog().debug("get document from blockchain success : {}", documentStr);
                JSONArray jsonArray = JSONArray.parseArray(documentStr);
                future.complete(jsonArray);
            } catch (Exception e) {
                WebServer.getLog().error(e, "try to get the document {} error, key is {}.", documentId, key);
                future.fail(e);
            }
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                if (asyncResult.result() == null) {
                    routingContext.response().end("search error: nonexistence | block-chain.");
                    return;
                }
                JSONArray jsonArray = (JSONArray) asyncResult.result();
                WebServer.getLog().debug("search document id {} success, key is {}, result is {}.", documentId, key, asyncResult.result().toString());
                Set<Map.Entry<String, Object>> entries = jsonArray.getJSONObject(jsonArray.size() - 1).entrySet();
                ThymeleafTemplateEngine templateEngine = ThymeleafTemplateEngine.create();
                routingContext.put("msg", "success search document in block-chain, json array is " + jsonArray.toString() + ".");
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
