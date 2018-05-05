package http;

import base.Constants;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author guyue
 * @date 2018/4/21
 */
public class WebServer extends AbstractVerticle {
    private static Log log = LogFactory.get();

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        for (HttpHandlerEnum per : HttpHandlerEnum.values()) {
            router.route(per.getMethod(), per.getPath()).blockingHandler(routingContext -> {
                log.info("http request method is {} and path is {}.", routingContext.request().method(), routingContext.request().path());
                per.getHandler().run(routingContext);
            });
        }

        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(request -> request.setExpectMultipart(true));
        httpServer.requestHandler(router::accept);
        httpServer.listen(Constants.HTTP_PORT, Constants.HTTP_HOST, httpServerAsyncResult -> {
            if (httpServerAsyncResult.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(httpServerAsyncResult.cause());
            }
        });
        super.start();
    }

    public static Log getLog() {
        return log;
    }
}
