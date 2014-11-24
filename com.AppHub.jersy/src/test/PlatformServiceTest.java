package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import model.Platform;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class PlatformServiceTest extends JerseyTest {
	WebResource webResource = client().resource("http://localhost:8080/");
	
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
 
	@Test
	public void getPlatformTest() throws JSONException,
			URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		Platform platform = createPlatform();
		JSONObject json = webResource.path("com.AppHub.jersy/rest/PlatformService/create")
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		json = webResource.path("com.AppHub.jersy/rest/PlatformService/" + platform.getIdPlatform())
				.get(JSONObject.class);
		webResource.path("com.AppHub.jersy/rest/PlatformService/remove/" + 
				platform.getIdPlatform()).delete();
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
			webResource.path("com.AppHub.jersy/rest/PlatformService/create")
			.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		}
		JSONArray json = webResource.path("com.AppHub.jersy/rest/PlatformService")
				.get(JSONArray.class);
		assertTrue(json.length() >= numberOfPlatforms);
		for(String id: ids) {
			webResource.path("com.AppHub.jersy/rest/PlatformService/remove/" + 
					id).delete();
		}
		
	}
	
	@Test
	public void createPlatformTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		Platform platform = createPlatform();
		JSONObject json = webResource.path("com.AppHub.jersy/rest/PlatformService/create")
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		webResource.path("com.AppHub.jersy/rest/PlatformService/remove/" + 
				platform.getIdPlatform()).delete();
		assertEquals(platform.getIdPlatform(), json.get("idPlatform"));
		assertEquals(platform.getPlatformName(), json.get("platformName"));
	}
	
	@Test
	public void updatePlatformTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		Platform platform = createPlatform();
		JSONObject json = webResource.path("com.AppHub.jersy/rest/PlatformService/create")
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		json.put("platformName", "New Platform Name");
		json = webResource.path("com.AppHub.jersy/rest/PlatformService/update")
				.put(JSONObject.class, json);
		webResource.path("com.AppHub.jersy/rest/PlatformService/remove/" + 
				platform.getIdPlatform()).delete();
		assertEquals(platform.getIdPlatform(), json.get("idPlatform"));
		assertEquals("New Platform Name", json.get("platformName"));
	}
	
	@Test
	public void deletePlatformTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		Platform platform = createPlatform();
		JSONObject json = webResource.path("com.AppHub.jersy/rest/PlatformService/create")
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		webResource.path("com.AppHub.jersy/rest/PlatformService/remove/" + 
				platform.getIdPlatform()).delete();
	}
	
	public static String convertToJson(Object object) throws JsonMappingException, JsonGenerationException, IOException {
		return (new ObjectMapper()).writeValueAsString(object);
	}
	
	private Platform createPlatform() {
		return new Platform((int)(System.currentTimeMillis()), "TestStore");
	}
}
