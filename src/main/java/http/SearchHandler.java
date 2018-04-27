package http;

import com.alibaba.fastjson.JSON;
import contract.ContractManager;
import contract.DocumentManager;
import io.vertx.ext.web.RoutingContext;
import model.OfficialDocument;

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
                routingContext.response().end(JSON.toJSONString(asyncResult.result()));
            } else {
                routingContext.fail(asyncResult.cause());
            }
        });
    }
}
