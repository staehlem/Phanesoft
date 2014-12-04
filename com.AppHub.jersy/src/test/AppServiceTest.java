package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import model.App;
import model.AppComments;
import model.Platform;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class AppServiceTest extends JerseyTest {
	String name = "root";
	String password = "password";
	String JerseyLocation = "http://localhost:8080/com.AppHub.jersy/rest/AppService";
	Platform platform = null;
	
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
 
	@Test
	public void getAppTest() throws JSONException,
			URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		App app = createApp();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(app)));
		json = makeWebResource(name, password, JerseyLocation, app.getAppID())
				.get(JSONObject.class);
		makeWebResource(name, password, JerseyLocation+"/remove",
				app.getAppID()).delete();
		assertEquals(app.getAppID(), json.get("appID"));
		assertEquals(app.getAppName(), json.get("appName"));
		assertEquals(app.getAppUrl(), json.get("appUrl"));
		assertEquals(app.getAppDeveloper(), json.get("appDeveloper"));
		assertEquals(app.getDescription(), json.get("description"));
		assertEquals(app.getAppPlatform(), json.get("appPlatform"));
		assertEquals(app.getAppRating(), json.get("appRating"));
		assertEquals(app.getAppCost(), json.get("appCost"));
		assertEquals(app.getAppVersion(), json.get("appVersion"));
		assertEquals(app.isAppAvailable(), json.get("appAvailable"));
	}
	
	@Test
	public void getAllAppsTest() throws JsonMappingException, JsonGenerationException, UniformInterfaceException, JSONException, IOException {
		App app = null;
		ArrayList<String> ids = new ArrayList<String>();
		int numberOfApps = 2;
		for(int temp = 0; temp < numberOfApps; temp++) {
			app = createApp();
			ids.add(app.getAppID());
			makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(app)));
		}
		JSONArray json = makeWebResource(name, password, JerseyLocation, null)
				.get(JSONArray.class);
		assertTrue(json.length() >= numberOfApps);
		for(String id: ids) {
			makeWebResource(name, password, JerseyLocation+"/remove",
					id).delete();
		}
		
	}
	
	@Test
	public void createAppTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		App app = createApp();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(app)));
		makeWebResource(name, password, JerseyLocation+"/remove",
				app.getAppID()).delete();
		assertEquals(app.getAppID(), json.get("appID"));
		assertEquals(app.getAppName(), json.get("appName"));
		assertEquals(app.getAppUrl(), json.get("appUrl"));
		assertEquals(app.getAppDeveloper(), json.get("appDeveloper"));
		assertEquals(app.getDescription(), json.get("description"));
		assertEquals(app.getAppPlatform(), json.get("appPlatform"));
		assertEquals(app.getAppRating(), json.get("appRating"));
		assertEquals(app.getAppCost(), json.get("appCost"));
		assertEquals(app.getAppVersion(), json.get("appVersion"));
		assertEquals(app.isAppAvailable(), json.get("appAvailable"));
	}
	
	@Test
	public void updateAppTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		App app = createApp();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(app)));
		json.put("appName", "New App Name");
		json = makeWebResource(name, password, JerseyLocation+"/update", null)
				.put(JSONObject.class, json);
		makeWebResource(name, password, JerseyLocation+"/remove",
				app.getAppID()).delete();
		assertEquals(app.getAppID(), json.get("appID"));
		assertEquals("New App Name", json.get("appName"));
		assertEquals(app.getAppUrl(), json.get("appUrl"));
		assertEquals(app.getAppDeveloper(), json.get("appDeveloper"));
		assertEquals(app.getDescription(), json.get("description"));
		assertEquals(app.getAppPlatform(), json.get("appPlatform"));
		assertEquals(app.getAppRating(), json.get("appRating"));
		assertEquals(app.getAppCost(), json.get("appCost"));
		assertEquals(app.getAppVersion(), json.get("appVersion"));
		assertEquals(app.isAppAvailable(), json.get("appAvailable"));
	}
	
	@Test
	public void deleteAppTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		App app = createApp();
		makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(app)));
		makeWebResource(name, password, JerseyLocation+"/remove", 
				app.getAppID()).delete();
	}
	
	public static String convertToJson(Object object) throws JsonMappingException, JsonGenerationException, IOException {
		return (new ObjectMapper()).writeValueAsString(object);
	}
	
	private App createApp() {
		return new App("TestApp", "testUrl.com",
				"TestDeveloper", "TestDescription", platform.getPlatformName(),
				true, 5.0, 1.99,
				2.0, new ArrayList<AppComments>(), "testUrl");
	}
	
	@Before
	public void createPlatform() throws JsonMappingException, JsonGenerationException, UniformInterfaceException, JSONException, IOException {
		platform = new Platform((int)(System.currentTimeMillis()), "TestPlatform");
		makeWebResource(name, password, "http://localhost:8080/com.AppHub.jersy/rest/PlatformService/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
	}
	
	@After
	public void deletePlatform() {
		makeWebResource(name, password, "http://localhost:8080/com.AppHub.jersy/rest/PlatformService/remove",
				platform.getIdPlatform()+"").delete();
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
