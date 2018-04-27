import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import contract.ContractManager;
import http.WebServer;
import io.vertx.core.Vertx;

import java.util.concurrent.TimeUnit;

/**
 * @author guyue
 * @date 2018/4/21
 */
public class MyServerAgent {
    private static Log log = LogFactory.get();

    private static Vertx vertx;

    private static String deploymentId;

    public static void init() {
        ContractManager.init();
        log.info("contract init OK !");
        addShutdownHook();
    }

    public static void start() {
        vertx = Vertx.vertx();
        vertx.deployVerticle(new WebServer(), stringAsyncResult -> {
            if (stringAsyncResult.succeeded()) {
                deploymentId = stringAsyncResult.result();
                log.info("web server verticle deploy OK !");
            } else {
                log.error(stringAsyncResult.cause(), "web server verticle deploy error.");
            }
        });
    }

    public static void stop() {
        vertx.undeploy(deploymentId, voidAsyncResult -> {
            if (voidAsyncResult.succeeded()) {
                log.info("undeploy web server verticle success.");
            } else {
                log.error(voidAsyncResult.cause(), "undeploy web server verticle error.");
            }
            ContractManager.onExit();
            log.info("stop contract manager success.");
            System.exit(0);
        });
    }

    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("shutdown hook execute start...");
                stop();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error(e, "wait server shutdown error.");
                }
                log.info("stop server success.");
            }
        }));
    }
}
