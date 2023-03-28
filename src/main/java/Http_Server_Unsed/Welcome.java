package Http_Server_Unsed;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Welcome extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            // Set response content type
            response.setContentType("text/html");

            // Actual logic goes here.
            PrintWriter out = response.getWriter();
            out.println("<h1>Http_Server_Unsed.Welcome to my Jetty server!</h1>");
        }
}
