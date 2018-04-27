package contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 */
public class SaveInfo extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b6103658061001e6000396000f300606060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806341adb3f51461005c5780634ed3885e146100b95780636d4ce63c14610116575b600080fd5b341561006757600080fd5b6100b7600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506101a4565b005b34156100c457600080fd5b610114600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506101be565b005b341561012157600080fd5b6101296101d8565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561016957808201518184015260208101905061014e565b50505050905090810190601f1680156101965780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b80600090805190602001906101ba929190610280565b5050565b80600090805190602001906101d4929190610280565b5050565b6101e0610300565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102765780601f1061024b57610100808354040283529160200191610276565b820191906000526020600020905b81548152906001019060200180831161025957829003601f168201915b5050505050905090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102c157805160ff19168380011785556102ef565b828001600101855582156102ef579182015b828111156102ee5782518255916020019190600101906102d3565b5b5090506102fc9190610314565b5090565b602060405190810160405280600081525090565b61033691905b8082111561033257600081600090555060010161031a565b5090565b905600a165627a7a723058204b5da09fbd2dd55296a9b2fcccd3364296d9d3ae79fd3cdb005daf775f171ff00029";

    private static final HashMap<String, String> ADDRESSES;

    static {
        ADDRESSES = new HashMap<>();
    }

    private SaveInfo(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private SaveInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> saveInfo(String str) {
        final Function function = new Function(
                "saveInfo",
                Collections.singletonList(new Utf8String(str)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> set(String str) {
        final Function function = new Function(
                "set",
                Collections.singletonList(new Utf8String(str)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> get() {
        final Function function = new Function("get",
                Collections.emptyList(),
                Collections.singletonList(new TypeReference<Utf8String>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<SaveInfo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SaveInfo.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SaveInfo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SaveInfo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static SaveInfo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SaveInfo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SaveInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SaveInfo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    @Override
    protected String getStaticDeployedAddress(String networkId) {
        return ADDRESSES.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return ADDRESSES.get(networkId);
    }
}
