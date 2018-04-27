package contract;

import base.BlockChainUtils;
import base.Constants;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.tx.ManagedTransaction;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author guyue
 * @date 2018/4/21
 */
public class ContractManager {

    private static Log log = LogFactory.get();

    private static DocumentManager contract;

    public static void init() {
        log.info("create a wallet file by private key...");
        String walletFile = null;
        try {
            walletFile = WalletUtils.generateWalletFile(Constants.WALLET_PASSWORD,
                    ECKeyPair.create(new BigInteger(Constants.PRIVATE_KEY, 16)), new File(Constants.WALLET_FILE_DICT), true);
            log.info("create walletFile success, file is " + walletFile);
        } catch (CipherException | IOException e) {
            log.error(e, "create wallet file by private error.");
        }
        File wallet = new File(Constants.WALLET_FILE_DICT + walletFile);
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(Constants.WALLET_PASSWORD, wallet);
            log.info("load ganache wallet success, credentials loaded.");
        } catch (IOException | CipherException e) {
            log.error(e, "load ganache wallet error.");
        }
        log.info("address of credential is " + Objects.requireNonNull(credentials).getAddress());
        log.info("deploying smart contract...");
        try {
            contract = DocumentManager.deploy(BlockChainUtils.getAdmin(), credentials,
                    ManagedTransaction.GAS_PRICE, org.web3j.tx.Contract.GAS_LIMIT).send();
            log.info("deploy smart contract success, contract address is {}.", contract.getContractAddress());
        } catch (Exception e) {
            log.error(e, "deploy smart contract error.");
        }
    }

    public static DocumentManager getContract() {
        if (contract == null) {
            init();
        }
        return contract;
    }

    public static void onExit() {

    }
}
