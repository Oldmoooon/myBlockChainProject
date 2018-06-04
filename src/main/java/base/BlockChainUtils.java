package base;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import contract.ContractManager;
import model.Account;
import model.OfficialDocument;
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

    public static String createDocumentId(OfficialDocument document) {
        try {
            return document.getTitle();
        } catch (Exception e) {
            log.error(e, "create DocumentId error.");
        }
        return String.valueOf(-1);
    }

    public static boolean createAccount(Account user, Account creator) {
        if (creator.getAuthority().intValue() <= user.getAuthority().intValue()) {
            return false;
        }
        try {
            ContractManager.getContract().addUser(user.getId(), user.getPassword(),
                    user.getAuthority()).send();
        } catch (Exception e) {
            log.error(e, "create account error.");
            return false;
        }
        return true;
    }
}
