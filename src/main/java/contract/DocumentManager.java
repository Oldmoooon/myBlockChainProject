package contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class DocumentManager extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610e338061005e6000396000f30060606040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806331feb6711461006f57806338f532bf146101c35780633d1c2273146102615780637ccb6a641461028a578063964f593714610360575b005b341561007a57600080fd5b6100ca600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610421565b60405180806020018060200185815260200184151515158152602001838103835287818151815260200191508051906020019080838360005b8381101561011e578082015181840152602081019050610103565b50505050905090810190601f16801561014b5780820380516001836020036101000a031916815260200191505b50838103825286818151815260200191508051906020019080838360005b83811015610184578082015181840152602081019050610169565b50505050905090810190601f1680156101b15780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b61025f600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091908035906020019091905050610667565b005b341561026c57600080fd5b61027461080e565b6040518082815260200191505060405180910390f35b341561029557600080fd5b6102e5600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190505061081b565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561032557808201518184015260208101905061030a565b50505050905090810190601f1680156103525780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561036b57600080fd5b610407600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091908035906020019091905050610931565b604051808215151515815260200191505060405180910390f35b610429610b5f565b610431610b5f565b60008060008090505b60038054905081101561062a5761047060038281548110151561045957fe5b9060005260206000209060030201600001876109d6565b1561061d5760038181548110151561048457fe5b90600052602060002090600302016000016003828154811015156104a457fe5b90600052602060002090600302016001016003838154811015156104c457fe5b9060005260206000209060030201600201546000838054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561056d5780601f106105425761010080835404028352916020019161056d565b820191906000526020600020905b81548152906001019060200180831161055057829003601f168201915b50505050509350828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106095780601f106105de57610100808354040283529160200191610609565b820191906000526020600020905b8154815290600101906020018083116105ec57829003601f168201915b50505050509250945094509450945061065f565b808060010191505061043a565b600080602060405190810160405280600081525091906020604051908101604052806000815250919081915094509450945094505b509193509193565b6000339050600280548060010182816106809190610b73565b9160005260206000209001600086909190915090805190602001906106a6929190610b9f565b50506040805190810160405280848152602001838152506001856040518082805190602001908083835b6020831015156106f557805182526020820191506020810190506020830392506106d0565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000820151816000019080519060200190610744929190610c1f565b5060208201518160010155905050836040518082805190602001908083835b6020831015156107885780518252602082019150602081019050602083039250610763565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390208173ffffffffffffffffffffffffffffffffffffffff167fc00bccde8d51b66adb4ca94f231b00967d8d9caecf935ab8cbde7df9d2eacd936001604051808215151515815260200191505060405180910390a350505050565b6000600280549050905090565b610823610b5f565b6001826040518082805190602001908083835b60208310151561085b5780518252602082019150602081019050602083039250610836565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109255780601f106108fa57610100808354040283529160200191610925565b820191906000526020600020905b81548152906001019060200180831161090857829003601f168201915b50505050509050919050565b600061093b610c9f565b6060604051908101604052808681526020018581526020018481525090506003805480600101828161096d9190610ccd565b91600052602060002090600302016000839091909150600082015181600001908051906020019061099f929190610c1f565b5060208201518160010190805190602001906109bc929190610c1f565b506040820151816002015550505060019150509392505050565b6000806109e1610cff565b6000859250849150815183805460018160011615610100020316600290049050141515610a115760009350610b56565b600090505b82805460018160011615610100020316600290049050811015610b51578181815181101515610a4157fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191683828154600181600116156101000203166002900481101515610acd57fe5b815460011615610aec5790600052602060002090602091828204019190065b9054901a7f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916141515610b445760009350610b56565b8080600101915050610a16565b600193505b50505092915050565b602060405190810160405280600081525090565b815481835581811511610b9a57818360005260206000209182019101610b999190610d13565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610be057805160ff1916838001178555610c0e565b82800160010185558215610c0e579182015b82811115610c0d578251825591602001919060010190610bf2565b5b509050610c1b9190610d3f565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610c6057805160ff1916838001178555610c8e565b82800160010185558215610c8e579182015b82811115610c8d578251825591602001919060010190610c72565b5b509050610c9b9190610d3f565b5090565b606060405190810160405280610cb3610d64565b8152602001610cc0610d64565b8152602001600081525090565b815481835581811511610cfa57600302816003028360005260206000209182019101610cf99190610d78565b5b505050565b602060405190810160405280600081525090565b610d3c91905b80821115610d385760008181610d2f9190610dbf565b50600101610d19565b5090565b90565b610d6191905b80821115610d5d576000816000905550600101610d45565b5090565b90565b602060405190810160405280600081525090565b610dbc91905b80821115610db85760008082016000610d979190610dbf565b600182016000610da79190610dbf565b600282016000905550600301610d7e565b5090565b90565b50805460018160011615610100020316600290046000825580601f10610de55750610e04565b601f016020900490600052602060002090810190610e039190610d3f565b5b505600a165627a7a72305820e1f7a52082e2e8a04b3e3d2313462ff6771d000f5fe09ebf532d85997eec23740029";

    public static final String FUNC_GETDOCUMENT = "getDocument";

    public static final String FUNC_GETDOCUMENTCOUNT = "getDocumentCount";

    public static final String FUNC_ADDUSER = "addUser";

    public static final String FUNC_GETUSER = "getUser";

    public static final String FUNC_SETDOCUMENT = "setDocument";

    public static final Event DOCUMENTSTOREUPDATED_EVENT = new Event("DocumentStoreUpdated",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    protected DocumentManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DocumentManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<DocumentManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DocumentManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DocumentManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DocumentManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<DocumentStoreUpdatedEventResponse> getDocumentStoreUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DOCUMENTSTOREUPDATED_EVENT, transactionReceipt);
        ArrayList<DocumentStoreUpdatedEventResponse> responses = new ArrayList<DocumentStoreUpdatedEventResponse>(valueList.size());
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

    public Observable<DocumentStoreUpdatedEventResponse> documentStoreUpdatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, DocumentStoreUpdatedEventResponse>() {
            @Override
            public DocumentStoreUpdatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DOCUMENTSTOREUPDATED_EVENT, log);
                DocumentStoreUpdatedEventResponse typedResponse = new DocumentStoreUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse._sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._key = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._success = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<DocumentStoreUpdatedEventResponse> documentStoreUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DOCUMENTSTOREUPDATED_EVENT));
        return documentStoreUpdatedEventObservable(filter);
    }

    public RemoteCall<String> getDocument(String _key) {
        final Function function = new Function(FUNC_GETDOCUMENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_key)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getDocumentCount() {
        final Function function = new Function(FUNC_GETDOCUMENTCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addUser(String _id, String _password, BigInteger _authority) {
        final Function function = new Function(
                FUNC_ADDUSER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_id),
                        new org.web3j.abi.datatypes.Utf8String(_password),
                        new org.web3j.abi.datatypes.generated.Uint256(_authority)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple4<String, String, BigInteger, Boolean>> getUser(String _id) {
        final Function function = new Function(FUNC_GETUSER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple4<String, String, BigInteger, Boolean>>(
                new Callable<Tuple4<String, String, BigInteger, Boolean>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, Boolean>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (Boolean) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setDocument(String _key, String _document, BigInteger _secret, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SETDOCUMENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_key),
                        new org.web3j.abi.datatypes.Utf8String(_document),
                        new org.web3j.abi.datatypes.generated.Uint256(_secret)),
                Collections.<TypeReference<?>>emptyList());
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
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class DocumentStoreUpdatedEventResponse {
        public Log log;

        public String _sender;

        public byte[] _key;

        public Boolean _success;
    }
}
