package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*Different ways to create POST request body
1. Post Request body using Hashmap
2. Post request body creation using Org.JSON
3. Post request body creation using POJO class
4. Post request using external JSON file data
*/

public class DiffWaysToCreatePostRequestBody {
	
	//1. Post request body using Hashmap
	String token;
	
	//@Test(priority=1)
	void testPostUsingHashMap() {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("firstname", "Scott");
		data.put("lastname", "Brown");
		data.put("totalprice", 1111);
		data.put("depositpaid", true);
		
		HashMap<String, String> bookingDates = new HashMap<String, String>();
		bookingDates.put("checkin", "2018-01-01");
		bookingDates.put("checkout", "2019-01-01");
		data.put("bookingdates", bookingDates);
		data.put("additionalneeds", "Breakfast");
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
		.when()
			.post("https://restful-booker.herokuapp.com/booking")
			
		.then()
			.statusCode(200) 
			.body("booking.firstname", equalTo("Scott"))
		    .body("booking.lastname", equalTo("Brown")) 
		    .body("booking.totalprice", equalTo(1111)) 
		    .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
		    .header("Content-Type", "application/json; charset=utf-8") 
		    .log().all();
		 
	}
	
	//2. Post request body using org.json library
	
	//@Test(priority=1)
	void testPostUsingJsonLibrary() {
		
		JSONObject data =new JSONObject();
		data.put("firstname", "Scott");
		data.put("lastname", "Brown");
		data.put("totalprice", 1111);
		data.put("depositpaid", true);
		
		JSONObject bookingDates = new JSONObject();
		bookingDates.put("checkin", "2018-01-01");
		bookingDates.put("checkout", "2019-01-01");
		data.put("bookingdates", bookingDates);
		data.put("additionalneeds", "Breakfast");
		
		given()
			.contentType(ContentType.JSON)
			.body(data.toString())
		.when()
			.post("https://restful-booker.herokuapp.com/booking")
			
		.then()
			.statusCode(200) 
			.body("booking.firstname", equalTo("Scott"))
		    .body("booking.lastname", equalTo("Brown")) 
		    .body("booking.totalprice", equalTo(1111)) 
		    .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
		    .header("Content-Type", "application/json; charset=utf-8") 
		    .log().all();
		 
	}
	
	//3. Post request body using POJO Class
	
		//@Test(priority=1)
		void testPostUsingPOJOClass() {
			
			BookingDates bookingDates = new BookingDates();
			bookingDates.setCheckin("2018-01-01");
			bookingDates.setCheckout("2019-01-01");
			
			Pojo_PostRequest data = new Pojo_PostRequest();
			
			data.setFirstname("Scott");
			data.setLastname("Brown");
			data.setTotalprice(1111);
			data.setDepositpaid(true);
			data.setBookingdates(bookingDates);
			data.setAdditionalneeds("Breakfast");
			
			given()
				.contentType(ContentType.JSON)
				.body(data)
			.when()
				.post("https://restful-booker.herokuapp.com/booking")
				
			.then()
				.statusCode(200) 
				.body("booking.firstname", equalTo("Scott"))
			    .body("booking.lastname", equalTo("Brown")) 
			    .body("booking.totalprice", equalTo(1111))
			    .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
			    .header("Content-Type", "application/json; charset=utf-8") 
			    .log().all();
			 
		}
		
		//4. Post request body using External JSON File
		
			@Test(priority=1)
			void testPostUsingExternalJsonFile() throws FileNotFoundException {
				
				File f = new File(".\\body.json"); //dot is representing location of your current project
				
				FileReader fr = new FileReader(f);
				
				JSONTokener jt = new JSONTokener(fr);
				
				JSONObject data = new JSONObject(jt);
				
				given()
					.contentType(ContentType.JSON)
					.body(data.toString())
				.when()
					.post("https://restful-booker.herokuapp.com/booking")
					
				.then()
					.statusCode(200) 
					.body("booking.firstname", equalTo("Scott"))
				    .body("booking.lastname", equalTo("Brown")) 
				    .body("booking.totalprice", equalTo(1111))
				    .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
				    .header("Content-Type", "application/json; charset=utf-8") 
				    .log().all();
				 
			}
		
		
	
	@Test(priority=2)
	void testDelete() {
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/2")
		
		.then()
			.statusCode(204);
	}

}
