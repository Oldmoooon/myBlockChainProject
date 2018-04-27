package base;

import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;

public class BlockChainUtilsTest {

    @Test
    public void getAdmin() {
        Admin admin = BlockChainUtils.getAdmin();
        System.out.println(String.format("create a admin instance success, this is accounts id: %s.", admin.personalListAccounts().getId()));
    }
}