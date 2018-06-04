package http;

import base.BlockChainUtils;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import contract.ContractManager;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import model.Account;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;

/**
 * @author guyue
 * @date 2018/5/23
 */
public class LoginHandler implements IHttpHandler {
    private static Log log = LogFactory.get();
    @Override
    public void run(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        String id = request.getParam("id");
        String password = request.getParam("password");
        log.info("user {} try to login, enter password is {}", id, password);
        routingContext.vertx().executeBlocking(future -> {
            try {
                Tuple4<String, String, BigInteger, Boolean> user = ContractManager.getContract()
                        .getUser(id)
                        .send();
                if (!user.getValue4()) {
                    boolean result = BlockChainUtils.createAccount(new Account(user.getValue1(), user.getValue2(), user.getValue3()), new Account("admin", "admin", Integer.MAX_VALUE));
                    future.complete(result);
                    return;
                }
                log.debug("user {}'s real password is {}, and authority is {}", user.getValue2(), user.getValue3());
                String realPassword = user.getValue2();
                future.complete(realPassword.equals(password));
            } catch (Exception e) {
                log.error(e, "get user info or create account in blockchain error.");
            }
        }, asyncResult -> {
            if (!asyncResult.succeeded()) {
                log.error(asyncResult.cause(), "something error happened.");
                routingContext.fail(asyncResult.cause());
            } else {
                log.debug("rerouting.");
                routingContext.reroute(HttpMethod.GET, "/index.html");
            }
        });
    }
}
