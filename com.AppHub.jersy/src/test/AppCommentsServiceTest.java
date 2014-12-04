package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import model.App;
import model.AppComments;
import model.Platform;
import model.User;

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
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class AppCommentsServiceTest extends JerseyTest {
	String name = "root";
	String password = "password";
	String JerseyLocation = "http://localhost:8080/com.AppHub.jersy/rest/AppCommentsService";
	private App app = null;
	private User user = null;
	private Platform platform = null;
	
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
 
	@Test
	public void getAllAppComments() throws JSONException,
			URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		AppComments appComment = null;
		int numberOfComments = 2;
		ArrayList<String> ids = new ArrayList<String>();
		for(int temp = 0; temp < numberOfComments; temp++) {
			appComment = createAppComment();
			ids.add(appComment.getCommentId());
			makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(appComment)));
		}
		JSONArray json = makeWebResource(name, password, JerseyLocation, app.getAppID())
				.get(JSONArray.class);
		for(String id: ids) {
			makeWebResource(name, password, JerseyLocation+"/remove", id).delete();
		}
		assertTrue(json.length() >= 2);
	}
	
	@Test
	public void createAppCommentTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		AppComments appComment = createAppComment();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
			.post(JSONObject.class, new JSONObject(convertToJson(appComment)));
		makeWebResource(name, password, JerseyLocation+"/remove", appComment.getCommentId())
			.delete();
		assertEquals(appComment.getAppComment(), json.get("appComment"));
		assertEquals(appComment.getAppId(), json.get("appId"));
		assertEquals(appComment.getCommentId(), json.get("commentId"));
		assertEquals(appComment.getUserName(), json.get("userName"));
		assertEquals(appComment.isApprovalComment(), json.get("approvalComment"));
	}
	
	@Test
	public void updateAppCommentTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		AppComments appComment = createAppComment();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
			.post(JSONObject.class, new JSONObject(convertToJson(appComment)));
		json.put("appComment", "New App Comment");
		json = makeWebResource(name, password, JerseyLocation+"/update", null)
				.put(JSONObject.class, json);
		makeWebResource(name, password, JerseyLocation+"/remove", appComment.getCommentId())
			.delete();
		assertEquals("New App Comment", json.get("appComment"));
		assertEquals(appComment.getAppId(), json.get("appId"));
		assertEquals(appComment.getCommentId(), json.get("commentId"));
		assertEquals(appComment.getUserName(), json.get("userName"));
		assertEquals(appComment.isApprovalComment(), json.get("approvalComment"));
	}
	
	@Test
	public void deleteAppCommentTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		AppComments appComment = createAppComment();
		makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(appComment)));
		makeWebResource(name, password, JerseyLocation+"/remove", appComment.getCommentId())
			.delete();
	}
	
	@Before
	public void setUp() throws JsonMappingException, JsonGenerationException, UniformInterfaceException, JSONException, IOException {
		this.user = new User("testUser", "Test", "Test", "Test", "Test", "Admin");
		makeWebResource(name, password, "http://localhost:8080/com.AppHub.jersy/rest/UserService/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(user)));
		this.platform = new Platform((int)(System.currentTimeMillis()), "TestPlatform");
		makeWebResource(name, password, "http://localhost:8080/com.AppHub.jersy/rest/PlatformService/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(platform)));
		this.app = new App("TestApp", "testUrl.com",
				"TestDeveloper", "TestDescription", platform.getPlatformName(),
				true, 5.0, 1.99,
				2.0, new ArrayList<AppComments>(), null);
		makeWebResource(name, password, "http://localhost:8080/com.AppHub.jersy/rest/AppService/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(app)));
	}
	
	@After
	public void tearDown() {
		makeWebResource(name, password, "http://localhost:8080/com.AppHub.jersy/rest/UserService/remove", 
				user.getUserName()).delete();
		makeWebResource(name, password, "http://localhost:8080/com.AppHub.jersy/rest/AppService/remove",
				app.getAppID()).delete();
		makeWebResource(name, password, "http://localhost:8080/com.AppHub.jersy/rest/PlatformService/remove",
				platform.getIdPlatform()+"").delete();
	}
	
	public static String convertToJson(Object object) throws JsonMappingException, JsonGenerationException, IOException {
		return (new ObjectMapper()).writeValueAsString(object);
	}
	
	private AppComments createAppComment() {
		return new AppComments(user.getUserName(), app.getAppID(),
				"Test App Comment", ((int)(System.currentTimeMillis()))+"", false);
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
