package Http_Server_Unsed;

import api.JsonRpcCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Eth_getBlockByNumberPage extends HttpServlet {
    public  String getResponse() throws Exception {
        String eth_BlockNumber = String.valueOf(new RequestLatestBlock().getResponse());
        return getResponse(eth_BlockNumber);
    }

    public  String getResponse(String eth_BlockNumber) throws Exception {

        return new JsonRpcCommand("https://eth-mainnet.public.blastapi.io/","eth_getBlockByNumber", Arrays.asList("\"" + eth_BlockNumber + "\"","false")).execute();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        try {
            out.println(getResponse());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
