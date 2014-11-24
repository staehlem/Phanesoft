package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import model.User;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class UserServiceTest extends JerseyTest {
	
	WebResource webResource = client().resource("http://localhost:8080/");
	
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
 
	@Test
	public void getUserTest() throws JSONException,
			URISyntaxException {
		
		JSONObject json = webResource.path("com.AppHub.jersy/rest/UserService/root")
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
		JSONObject json = webResource.path("com.AppHub.jersy/rest/UserService/create")
				.post(JSONObject.class, new JSONObject(convertToJson(user)));
		webResource.path("com.AppHub.jersy/rest/UserService/remove/" + 
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
		JSONObject json = webResource.path("com.AppHub.jersy/rest/UserService/create")
				.post(JSONObject.class, new JSONObject(convertToJson(user)));
		json.put("password", "passwordChanged");
		json = webResource.path("com.AppHub.jersy/rest/UserService/update")
				.put(JSONObject.class, json);
		webResource.path("com.AppHub.jersy/rest/UserService/remove/" + 
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
		JSONObject json = webResource.path("com.AppHub.jersy/rest/UserService/create")
				.post(JSONObject.class, new JSONObject(convertToJson(user)));
		webResource.path("com.AppHub.jersy/rest/UserService/remove/" + 
				user.getUserName()).delete();
	}
	
	public static String convertToJson(Object object) throws JsonMappingException, JsonGenerationException, IOException {
		return (new ObjectMapper()).writeValueAsString(object);
	}
	
	private User createUsers() {
		return new User("testUser", "Test", "Test", "Test", "Test", "Admin");
	}
}
