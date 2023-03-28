package Http_Server_Unsed;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Web_Server {

    public static void main(String[] args) throws Exception,InterruptedException {

        Server server = new Server(15800);

        ServletHolder servletHolder1 = new ServletHolder(Welcome.class);
        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(servletHolder1, "/*");

        ServletHolder servletHolder2 = new ServletHolder(RequestLatestBlock.class);
        context.addServlet(servletHolder2, "/latest_block");

        ServletHolder servletHolder3 = new ServletHolder(Eth_getBlockByNumberPage.class);
        context.addServlet(servletHolder3, "/eth_getBlockByNumber");
        server.setHandler(context);

        ServletHolder servletHolder4 = new ServletHolder(Value_0f_All_Transaction_From_Latest_Block.class);
        context.addServlet(servletHolder4, "/value_0f_All_Transaction_From_Latest_Block");
        server.setHandler(context);

        server.start();
        server.join();


    }
}
