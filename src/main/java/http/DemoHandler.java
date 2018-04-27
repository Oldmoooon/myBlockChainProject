package http;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;

/**
 * @author guyue
 * @date 2018/4/21
 */
public class DemoHandler implements IHttpHandler {
    @Override
    public void run(RoutingContext routingContext) {
        String page = routingContext.request().path();
        ThymeleafTemplateEngine templateEngine = ThymeleafTemplateEngine.create();
        templateEngine.render(routingContext, "templates", page, bufferAsyncResult -> {
            if (bufferAsyncResult.succeeded()) {
                routingContext.response().putHeader("content-type", "Content-Type: text/html; charset=utf-8")
                        .end(bufferAsyncResult.result());
            } else {
                routingContext.reroute("/index.html");
            }
        });
    }
}
