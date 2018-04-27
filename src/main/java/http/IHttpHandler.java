package http;

import io.vertx.ext.web.RoutingContext;

/**
 * @author guyue
 * @date 2018/4/21
 */
public interface IHttpHandler {
    /**
     * use the method to handle http request
     * @param routingContext routing context of http request
     */
    void run(RoutingContext routingContext);
}
