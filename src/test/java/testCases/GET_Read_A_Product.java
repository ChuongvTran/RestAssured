package testCases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GET_Read_A_Product {
	
	@Test
	public void readAProduct() throws InterruptedException {
		
		SoftAssert softAssert = new SoftAssert(); // soft assert requires a object
		
//		HashMap<String,String> queryParams = new HashMap<String,String>();
//		queryParams.put("id", "1930");
//		queryParams.put("id", "1801");
		
		Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json; charset=UTF-8") // (key,value)
					.queryParam("id", "1801") // only passing one query
//					.queryParams(queryParams) // passing query from HashMap object (multiple query)
				.when()
					.get("/read_one.php")
				.then().extract().response();
		
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
//		Assert.assertEquals(statusCode, 200);
		softAssert.assertEquals(statusCode, 200, "Did not match");

		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("ResposeTime " + actualResponseTime);
		if (actualResponseTime <= 2000) {
			System.out.println("Response time in within range");
		}else {
			System.out.println("Response time out of range");
		}
		
//		response.getBody().prettyPrint();
		String responseBody = response.getBody().asString();
		System.out.println("ResponseBody: " + responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		
		String productName = jp.getString("name");
		String productDescripton = jp.getString("description");
		String productPrice = jp.getString("price");
		
		softAssert.assertEquals(productName, "The Tony movie");
		System.out.println("ProductName: " + productName);
		
		softAssert.assertEquals(productDescripton, "Tony's Story animation");
		System.out.println("productDescripton: " + productDescripton);
		
		softAssert.assertEquals(productPrice, "185");
		System.out.println("productPrice: " + productPrice);
		
		softAssert.assertAll();
	}

}
