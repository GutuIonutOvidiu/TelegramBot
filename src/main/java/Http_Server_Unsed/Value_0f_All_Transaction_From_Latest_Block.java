package Http_Server_Unsed;

import Etereum.Ethereum_Blockchain;
import com.google.gson.*;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Value_0f_All_Transaction_From_Latest_Block extends HttpServlet {

    public String getResponse() throws Exception {
        String block = new Eth_getBlockByNumberPage().getResponse();
        return Ethereum_Blockchain.getValueOfAllTransactionsInGivenBlock(block);
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

    public Value_0f_All_Transaction_From_Latest_Block() throws ExecutionException, InterruptedException, TimeoutException {
    }
}
