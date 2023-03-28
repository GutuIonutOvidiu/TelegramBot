package Http_Server_Unsed;

import api.JsonRpcCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class RequestLatestBlock extends HttpServlet {
    public  String getResponse() throws Exception {
      return new JsonRpcCommand("https://eth-mainnet.public.blastapi.io/","eth_blockNumber", Arrays.asList()).execute();
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
    public RequestLatestBlock() throws ExecutionException, InterruptedException, TimeoutException {
    }
}


