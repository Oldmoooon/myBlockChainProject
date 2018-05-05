package base;

/**
 * @author guyue
 * @date 2018/4/20
 */
public class Constants {
    /**
     * the http url of ganache test block-chain
     */
    public final static String ETH_URL = "http://127.0.0.1:7545";

    /**
     * the password of wallet
     */
    public final static String WALLET_PASSWORD = "ganache";

    public final static String WALLET_FILE_DICT = "/data/scripts/wallets/";

    /**
     * first private key in ganache
     */
    public final static String PRIVATE_KEY = "18f91ccecbe798f5c05e90e0da7a8b0494e8f587915d944df223d2e66fbea898";

    /**
     * http web server bind host
     */
    public final static String HTTP_HOST = "127.0.0.1";

    /**
     * http web server listen port
     */
    public final static int HTTP_PORT = 8080;

    /**
     * redis server host
     */
    public final static String REDIS_HOST = "127.0.0.1";

    /**
     * redis server port
     */
    public final static int REDIS_PORT = 6379;

    /**
     * ipfs path
     */
    public final static String IPFS_PATH = "/ip4/127.0.0.1/tcp/5001";
}
