package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LogginDemo {
	
	
	@Test(priority=1)
	void testlogs() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			//.log().body()
			//.log().cookies()
			//.log().headers()
		.log().all();
	}

}
