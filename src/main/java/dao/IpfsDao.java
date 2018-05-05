package dao;

import base.Constants;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.IOException;
import java.util.List;

/**
 * @author guyue
 * @date 2018/5/3
 */
public class IpfsDao {
    private final static String IPFS_REDIS_KEY = "ipfs";

    private volatile static IPFS ipfs = new IPFS(Constants.IPFS_PATH);

    private static Log log = LogFactory.get();

    public static String save(String name, byte[] bytes) {
        MerkleNode merkleNode = null;
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(bytes);
        try {
            merkleNode = ipfs.add(file).get(0);
        } catch (IOException e) {
            log.error(e, "save file {} into IPFS error.", name);
        }
        if (merkleNode != null) {
            Multihash hash = merkleNode.hash;
            String base58 = hash.toBase58();
            log.info("save file of {} into IPFS success, multi hash base58 is {}.", name,
                    base58);
            return base58;
        } else {
            log.error("something error when save file into IPFS.");
            return null;
        }
    }

    public static byte[] query(String base58) {
        Multihash multihash = Multihash.fromBase58(base58);
        try {
            return ipfs.cat(multihash);
        } catch (IOException e) {
            log.error(e, "get file {} from IPFS error.", base58);
        }
        return null;
    }
}
