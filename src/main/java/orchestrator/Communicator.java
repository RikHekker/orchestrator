package orchestrator;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class Communicator {
	String url;
	
	public void Communicator(String url) {
		url = url;
	}
	
	public String post(String body){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost request = new HttpPost(url);
		request.setEntity(body)
        CloseableHttpResponse httpResponse = httpClient.execute(request);
		if (httpResponse.contains(201)) {
			return "ACTIVE";
		}else if (httpResponse.contains(404)||httpResponse.contains(409))
			return "INACTIVE";
	}


}
