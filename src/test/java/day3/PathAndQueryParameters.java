package day3;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParameters {
 	
	//https://reqres.in/api/users?page=2&id=5
	
	@Test
	void testQueryAndParameters() {
		
		given()
			.pathParam("mypath", "users")	//path parameters
			.queryParam("page", 2) 	//query parameters
			.queryParam("id", 5) 	//query parameters
			
		.when()
			.get("https://reqres.in/api/{mypath}")
			
		.then()
			.statusCode(200)
			.log().all();
	}

}
