package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import model.User;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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

public class UserServiceTest extends JerseyTest {
	
	String name = "root";
	String password = "password";
	String JerseyLocation = "http://localhost:8080/com.AppHub.jersy/rest/UserService";
	
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
 
	@Test
	public void getUserTest() throws JSONException,
			URISyntaxException {
		
		JSONObject json = makeWebResource(name, password, JerseyLocation, "root")
				.get(JSONObject.class);
		assertEquals("root@root.com", json.get("email"));
		assertEquals("password", json.get("password"));
		assertEquals("root", json.get("first"));
		assertEquals("root", json.get("last"));
		assertEquals("Admin", json.get("userType"));
	}
	
	@Test
	public void createUserTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		User user = createUsers();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(user)));
		makeWebResource(name, password, JerseyLocation+"/remove",
				user.getUserName()).delete();
		assertEquals("testUser", json.get("userName"));
		assertEquals("Test", json.get("password"));
		assertEquals("Test", json.get("first"));
		assertEquals("Test", json.get("last"));
		assertEquals("Admin", json.get("userType"));
	}
	
	@Test
	public void updateUserTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		User user = createUsers();
		JSONObject json = makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(user)));
		json.put("password", "passwordChanged");
		json = makeWebResource(name, password, JerseyLocation+"/update", null)
				.put(JSONObject.class, json);
		makeWebResource(name, password, JerseyLocation+"/remove",
				user.getUserName()).delete();
		assertEquals("testUser", json.get("userName"));
		assertEquals("Test", json.get("first"));
		assertEquals("Test", json.get("last"));
		assertEquals("Admin", json.get("userType"));
		assertEquals("passwordChanged", json.get("password"));
	}
	
	@Test
	public void deleteUserTest() throws JSONException, URISyntaxException, JsonMappingException, JsonGenerationException, UniformInterfaceException, IOException {
		User user = createUsers();
		makeWebResource(name, password, JerseyLocation+"/create", null)
				.post(JSONObject.class, new JSONObject(convertToJson(user)));
		makeWebResource(name, password, JerseyLocation+"/remove",
				user.getUserName()).delete();
	}
	
	public static String convertToJson(Object object) throws JsonMappingException, JsonGenerationException, IOException {
		return (new ObjectMapper()).writeValueAsString(object);
	}
	
	private User createUsers() {
		return new User("testUser", "Test", "Test", "Test", "Test", "Admin");
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
