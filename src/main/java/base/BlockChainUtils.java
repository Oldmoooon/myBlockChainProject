package base;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import contract.ContractManager;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @author guyue
 * @date 2018/4/19
 */
public class BlockChainUtils {

    private static Log log = LogFactory.get();

    private volatile static Admin admin;
    /**
     * create a web3j admin instance and return it.
     * @return web3j admin
     */
    public static Admin getAdmin() {
        if (admin == null) {
            synchronized (BlockChainUtils.class) {
                if (admin == null) {
                    try {
                        admin = Admin.build(new HttpService(Constants.ETH_URL));
                        log.info("Connected to Ethereum by admin module, client version: "
                                + admin.ethProtocolVersion().send().getProtocolVersion());
                    } catch (IOException e) {
                        log.error(e, "Connect to Ethereum by admin module error. ");
                    }
                }
            }
        }
        return admin;
    }

    public static String createDocumentId() {
        try {
            return ContractManager.getContract().getDocumentCount().send().add(BigInteger
                    .valueOf(1)).toString();
        } catch (Exception e) {
            log.error(e, "create DocumentId error.");
        }
        return String.valueOf(-1);
    }
}
