package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import model.Platform;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class PlatformServiceTest extends JerseyTest {
	String name = "root";
	String password = "password";
	String JerseyLocation = "http://localhost:8080/com.AppHub.jersy/rest/PlatformService";
	
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
 
	@Test
	public void getPlatformTest() throws JSONException,
			URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		Platform platform = createPlatform();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		json = makeWebResource(name, password, JerseyLocation, platform.getIdPlatform()+"")
				.get(JSONObject.class);
		makeWebResource(name, password, JerseyLocation+"/remove",
				platform.getIdPlatform()+"").delete();
		assertEquals(platform.getIdPlatform(), json.get("idPlatform"));
		assertEquals(platform.getPlatformName(), json.get("platformName"));
	}
	
	@Test
	public void getAllPlatformsTest() throws JsonMappingException, JsonGenerationException, UniformInterfaceException, JSONException, IOException {
		Platform platform = null;
		ArrayList<String> ids = new ArrayList<String>();
		int numberOfPlatforms = 2;
		for(int temp = 0; temp < numberOfPlatforms; temp++) {
			platform = createPlatform();
			ids.add(platform.getIdPlatform()+"");
			makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		}
		JSONArray json = makeWebResource(name, password, JerseyLocation, null)
				.get(JSONArray.class);
		assertTrue(json.length() >= numberOfPlatforms);
		for(String id: ids) {
			makeWebResource(name, password, JerseyLocation+"/remove",
					id).delete();
		}
		
	}
	
	@Test
	public void createPlatformTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		Platform platform = createPlatform();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		makeWebResource(name, password, JerseyLocation+"/remove",
				platform.getIdPlatform()+"").delete();
		assertEquals(platform.getIdPlatform(), json.get("idPlatform"));
		assertEquals(platform.getPlatformName(), json.get("platformName"));
	}
	
	@Test
	public void updatePlatformTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		Platform platform = createPlatform();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		json.put("platformName", "New Platform Name");
		json = makeWebResource(name, password, JerseyLocation+"/update", null)
				.put(JSONObject.class, json);
		makeWebResource(name, password, JerseyLocation+"/remove",
				platform.getIdPlatform()+"").delete();
		assertEquals(platform.getIdPlatform(), json.get("idPlatform"));
		assertEquals("New Platform Name", json.get("platformName"));
	}
	
	@Test
	public void deletePlatformTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		Platform platform = createPlatform();
		makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		makeWebResource(name, password, JerseyLocation+"/remove",
				platform.getIdPlatform()+"").delete();
	}
	
	public static String convertToJson(Object object) throws JsonMappingException, JsonGenerationException, IOException {
		return (new ObjectMapper()).writeValueAsString(object);
	}
	
	private Platform createPlatform() {
		return new Platform((int)(System.currentTimeMillis()), "TestStore");
	}
	
	public static WebResource.Builder makeWebResource(String username, String password, String baseUrl, String id) {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(username, password));
		String uri = UriBuilder.fromUri(baseUrl).build().toString();
		if (id != null) {
			uri += "/" + id;
		}
		return client.resource(uri).type(MediaType.APPLICATION_JSON);
	}
	
}
