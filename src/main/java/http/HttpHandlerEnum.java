package http;

import io.vertx.core.http.HttpMethod;

/**
 * @author guyue
 * @date 2018/4/21
 */
public enum HttpHandlerEnum {

    /**
     * base handler for home page
     */
    DEMO_INDEX(HttpMethod.GET, "/*", new DemoHandler()),
    /**
     * add a official document to blockchain
     */
    ADD_DOCUMENT(HttpMethod.POST, "/submit.html", new AddDocumentHandler()),
    /**
     * search a official document in database and blockchain then return to browser
     */
    SEARCH_DOCUMENT(HttpMethod.POST, "/", new SearchHandler()),
    ;

    HttpHandlerEnum(HttpMethod method, String path, IHttpHandler handler) {
        this.method = method;
        this.path = path;
        this.handler = handler;
    }

    private HttpMethod method;

    private String path;

    private IHttpHandler handler;

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public IHttpHandler getHandler() {
        return handler;
    }
}
