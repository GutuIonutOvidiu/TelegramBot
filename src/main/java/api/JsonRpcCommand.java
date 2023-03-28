package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.util.List;
public class JsonRpcCommand implements Command {
    String endpoint_url;
    String method;
    List<String> params;
    public JsonRpcCommand(String endpoint_url, String method, List<String> params) {
        this.endpoint_url = endpoint_url;
        this.method = method;
        this.params = params;
    }
    @Override
    public String execute() {
        HttpClient httpClient = new HttpClient(new SslContextFactory());
        try {
            httpClient.start();
            StringBuilder params_str = new StringBuilder();
            for(String p:params){
                 if (!params_str.isEmpty()){
                params_str.append(",");
                }
                params_str.append(p);
            }

        String body = "{\"jsonrpc\":\"2.0\",\"method\":\""+this.method +"\",\"params\":[" + params_str + "],\"id\":0}";
        ContentResponse response = httpClient.newRequest(this.endpoint_url) .method(HttpMethod.POST) .header("Content-Type", "application/json").content(new StringContentProvider(body)).send();
        int statusCode = response.getStatus();
        String content = response.getContentAsString();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(content);
        String prettyJsonString = gson.toJson(jsonElement);

        return prettyJsonString;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
