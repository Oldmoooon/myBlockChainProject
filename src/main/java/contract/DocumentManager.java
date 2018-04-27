package contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 * @author guyue
 */
public class DocumentManager extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506106ec8061005e6000396000f300606060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806338f532bf146100595780636519fac2146100f75780637ccb6a6414610148575b005b6100f5600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190505061021e565b005b341561010257600080fd5b61012e600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610400565b604051808215151515815260200191505060405180910390f35b341561015357600080fd5b6101a3600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506104bb565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101e35780820151818401526020810190506101c8565b50505050905090810190601f1680156102105780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60008033915060009050600083141515610236578290505b6002805480600101828161024a91906105db565b9160005260206000209001600084909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506040805190810160405280858152602001828152506001866040518082805190602001908083835b6020831015156102e657805182526020820191506020810190506020830392506102c1565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000820151816000019080519060200190610335929190610607565b5060208201518160010155905050846040518082805190602001908083835b6020831015156103795780518252602082019150602081019050602083039250610354565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390208273ffffffffffffffffffffffffffffffffffffffff167fc00bccde8d51b66adb4ca94f231b00967d8d9caecf935ab8cbde7df9d2eacd936001604051808215151515815260200191505060405180910390a35050505050565b6000806000600280549050141561041a57600091506104b5565b600090505b6002805490508110156104b05760028181548110151561043b57fe5b906000526020600020900160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614156104a357600191506104b5565b808060010191505061041f565b600091505b50919050565b6104c3610687565b6104cc33610400565b506001826040518082805190602001908083835b60208310151561050557805182526020820191506020810190506020830392506104e0565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105cf5780601f106105a4576101008083540402835291602001916105cf565b820191906000526020600020905b8154815290600101906020018083116105b257829003601f168201915b50505050509050919050565b81548183558181151161060257818360005260206000209182019101610601919061069b565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061064857805160ff1916838001178555610676565b82800160010185558215610676579182015b8281111561067557825182559160200191906001019061065a565b5b509050610683919061069b565b5090565b602060405190810160405280600081525090565b6106bd91905b808211156106b95760008160009055506001016106a1565b5090565b905600a165627a7a7230582084411fa4721222276e37343fcaee1bd378171f5768d7298a1066c1e9fc166aa90029";

    protected static final HashMap<String, String> ADDRESSES;

    static {
        ADDRESSES = new HashMap<>();
    }

    protected DocumentManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DocumentManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<DocumentStoreUpdatedEventResponse> getDocumentStoreUpdatedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("DocumentStoreUpdated",
                Arrays.asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}),
                Collections.singletonList(new TypeReference<Bool>() {
                }));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<DocumentStoreUpdatedEventResponse> responses = new ArrayList<>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DocumentStoreUpdatedEventResponse typedResponse = new DocumentStoreUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._key = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._success = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<DocumentStoreUpdatedEventResponse> documentStoreUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("DocumentStoreUpdated",
                Arrays.asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}),
                Collections.singletonList(new TypeReference<Bool>() {
                }));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(log -> {
            EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
            DocumentStoreUpdatedEventResponse typedResponse = new DocumentStoreUpdatedEventResponse();
            typedResponse.log = log;
            typedResponse._sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._key = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._success = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            return typedResponse;
        });
    }

    public static RemoteCall<DocumentManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DocumentManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DocumentManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DocumentManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public RemoteCall<Boolean> userExisted(String user) {
        final Function function = new Function("userExisted",
                Collections.singletonList(new Address(user)),
                Collections.singletonList(new TypeReference<Bool>() {
                }));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> getDocument(String key) {
        final Function function = new Function("getDocument",
                Collections.singletonList(new Utf8String(key)),
                Collections.singletonList(new TypeReference<Utf8String>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setDocument(String key, String document, BigInteger secret, BigInteger weiValue) {
        final Function function = new Function(
                "setDocument",
                Arrays.asList(new org.web3j.abi.datatypes.Utf8String(key),
                        new org.web3j.abi.datatypes.Utf8String(document),
                        new org.web3j.abi.datatypes.generated.Uint256(secret)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public static DocumentManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DocumentManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static DocumentManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DocumentManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    @Override
    protected String getStaticDeployedAddress(String networkId) {
        return ADDRESSES.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return ADDRESSES.get(networkId);
    }

    public static class DocumentStoreUpdatedEventResponse {
        public Log log;

        public String _sender;

        public byte[] _key;

        public Boolean _success;
    }
}
