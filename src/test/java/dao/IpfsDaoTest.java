package dao;

import cn.hutool.log.LogFactory;
import org.junit.Test;

public class IpfsDaoTest {

    @Test
    public void test() {
        String file = "Bitcoin: A Peer-to-Peer Electronic Cash System";
        String name = "test";
        IpfsDao.save(name, file.getBytes());
        byte[] bytes = IpfsDao.query(name);
        assert bytes != null;
        LogFactory.get().info("success, result is \"{}\".", new String(bytes));
    }
}