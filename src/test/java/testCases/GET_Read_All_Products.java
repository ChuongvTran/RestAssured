package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GET_Read_All_Products {
	
	@Test
	public void readAllProducts() throws InterruptedException {
			
		Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json; charset=UTF-8") // (key,type)
				.when()
					.get("/read.php")
				.then().extract().response();
		
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code:" + statusCode);
		Assert.assertEquals(statusCode, 200);
			
		String responseBody = response.getBody().prettyPrint();
		System.out.println("ResponseBody:" +responseBody);
		
	}

}
