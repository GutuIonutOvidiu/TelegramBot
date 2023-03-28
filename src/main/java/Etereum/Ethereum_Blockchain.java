package Etereum;

import api.JsonRpcCommand;
import com.google.gson.*;
import utils.Utilities;
import java.math.BigInteger;
import java.util.Arrays;
public class Ethereum_Blockchain {
    public static String getValueOfAllTransactionsInGivenBlock(String block){
        String transactions_str = Utilities.getFieldValueFromResult(block,"transactions");

        JsonArray transactions = new JsonParser().parse(transactions_str).getAsJsonArray();
        BigInteger value_of_transactions = new BigInteger("0",10);

        for (JsonElement transaction : transactions) {
            String value = transaction.getAsString();

            String content = new JsonRpcCommand("https://eth-mainnet.public.blastapi.io/","eth_getTransactionByHash",Arrays.asList("\""+ value + "\"")).execute();
            String fieldValueAsJsonElemet = Utilities.getFieldValueFromResult(content,"value");
            String fieldValue = new JsonParser().parse(fieldValueAsJsonElemet).getAsString();
            BigInteger aux = new BigInteger(fieldValue.substring(2),16);
            value_of_transactions = value_of_transactions.add(aux);
        }
        BigInteger[] bigIntegerValue0fTransactions = value_of_transactions.divideAndRemainder(new BigInteger("1000000000000000000"));
        return bigIntegerValue0fTransactions[0].toString() + "." + bigIntegerValue0fTransactions[1];
    }
    public static String  getValueOfAllTransactionsInLastMinute() throws Exception {

        double valueOfAllTransactions = 0;
        int counter = 0;
        String initialBlock =  new JsonRpcCommand("https://eth-mainnet.public.blastapi.io/","eth_blockNumber", Arrays.asList()).execute();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(initialBlock, JsonObject.class);
        String initialBlockNumber;
            if(jsonObject.has("result")){
            initialBlockNumber = jsonObject.get("result").getAsString();
            }else {
            return "The big Json does not have a \"result\" field";
            }

        int blockNumberAsDecimal = Integer.parseInt(initialBlockNumber.substring(2), 16);

        while(counter < 4 ){
            System.out.println("Counter:" + counter);
            System.out.println("InitialBLock number: " + initialBlockNumber);
            String fullBlock = new JsonRpcCommand("https://eth-mainnet.public.blastapi.io/","eth_getBlockByNumber", Arrays.asList("\"" + initialBlockNumber + "\"","false")).execute();
            valueOfAllTransactions += Double.parseDouble(getValueOfAllTransactionsInGivenBlock(fullBlock));
            counter++;
            blockNumberAsDecimal--;
            initialBlockNumber = "0x"+Integer.toString(blockNumberAsDecimal, 16);
        }
         return valueOfAllTransactions + "";

    }
}
