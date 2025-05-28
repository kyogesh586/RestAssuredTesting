package day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPRequests {
	
	/*given()
		content type, set cookies, add auth, add param, set headers info etc....
		
	when()
		get, post, put, delete
	
	then()
		validate status code, extract response, extract headers cookies & repsonse body....*/
	
	int id;
	
	@Test(priority=1)
	void getUser() {
		
		when()
			.get("https://reqres.in/api/users?page=2")
			
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
		
	}
	
	@Test(priority=2)
	void createUser() {
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "yogesh");
		data.put("job", "QA");
		
		
		id=given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		/*.then()
			.statusCode(201)
			.log().all();	*/
	}
	
	@Test(priority=3, dependsOnMethods= {"createUser"})
	void updateUser() {
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "puneet");
		data.put("job", "Sr. QA");
		
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();	
	}
	
	@Test(priority=4)
	void deleteUser() {
		
		given()
			
		.when()
			.delete("https://reqres.in/api/users"+id)
			
		.then()
			.statusCode(204)
			.log().all();	
	}
	
}