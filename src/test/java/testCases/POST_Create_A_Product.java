package testCases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class POST_Create_A_Product {
	
	@Test
	public void createAProduct() throws InterruptedException {
		
		String payloadPath = ".\\src\\main\\java\\Data\\payload.json"; // gets data from payload data file
				
//		HashMap<String,String> payload = new HashMap<String, String>();
//		payload.put("name", "The Tony movie");
//		payload.put("price", "185");
//		payload.put( "description", "Tony's Story animation");
//		payload.put( "category_id", "5");
		
		Response response = 
				given()
					.log().all() // will log and print the whole process in the console 
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json;") // (key,value)
					.body(new File (payloadPath)) // need new File()
				.when()
					.post("/create.php") // changed to post from get to create
				.then()
					.log().headers() // will long and print all the response headers
					.extract().response();
		
		
//		int statusCode = response.getStatusCode();
//		System.out.println("Status code: " + statusCode);
//		Assert.assertEquals(statusCode, 201);
//		
//		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
//		System.out.println("ResposeTime " + actualResponseTime);
//		if (actualResponseTime <= 2000) {
//			System.out.println("Response time in within range");
//		}else {
//			System.out.println("Response time out of range");
//		}
//		
////	response.getBody().prettyPrint();
//		String responseBody = response.getBody().asString();
//		System.out.println("ResponseBody: " + responseBody);
//		
//		JsonPath jp = new JsonPath(responseBody);
//		
//		String successMessage = jp.getString("message");
//		
//		System.out.println("successMessage: " + successMessage);
//		Assert.assertEquals(successMessage, "Product was created.");
//		

	}

}
