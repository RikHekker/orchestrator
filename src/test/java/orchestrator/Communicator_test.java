package orchestrator;

import java.io.IOException;

import org.junit.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.client.WireMock.*;




import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;



public class Communicator_test {
	/*
	 * I saw to late that this project was supposed to be done with WireMock.
	 * This and the communicator is pseudo code for how I think it should be made.
	 * Due to time constraints I could not finish it.
	 */
	public void test() {
		/*
		 * Make stubs for the tree given calls
		 */
		
		WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
	    wireMockServer.start();
	    wireMockServer.stubFor(post(urlPathMatching("/activate"))
	    		.withRequestBody(matches({"customerId: 12345, macAddress: AA:BB:CC:DD:EE:FF"}))
				  .willReturn(aResponse()
				  .withStatus(201)));
	    wireMockServer.stubFor(post(urlPathMatching("/activate"))
	    		.withRequestBody(matches({"customerId: 12345 macAddress: AA:BB:CC:DD:EE:AA"}))
				  .willReturn(aResponse()
				  .withStatus(404)));
	    wireMockServer.stubFor(post(urlPathMatching("/activate"))
	    		.withRequestBody(matches({"customerId: 11111, macAddress: AA:BB:CC:DD:EE:FF"}))
				  .willReturn(aResponse()
				  .withStatus(409)));
	    
	    Communicator communicator = new Communicator("http://localhost:8080/activate");
	    
	    communicator.post("customerId: 12345, macAddress: AA:BB:CC:DD:EE:FF");
	    communicator.post("customerId: 12345, macAddress: AA:BB:CC:DD:EE:AA");
	    communicator.post("customerId: 11111, macAddress: AA:BB:CC:DD:EE:FF");


		
	}
	
}
