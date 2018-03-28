package co.bytemark.helper;

import org.testng.Assert;

import io.restassured.response.ValidatableResponse;

public class Then {
	boolean flag;

	private Then() {

	}

	public static Then thenValidation() {
		return new Then();
	}

	public ValidatableResponse thenP() {
		ValidatableResponse response = When.response.then();
		return response;
	}

	public ValidatableResponse statusCodeCheck(int statusCode) {
		ValidatableResponse status = Then.thenValidation().thenP().statusCode(statusCode);
		return status;
	}

	public int getStatusCode() {
		return When.response.getStatusCode();
	}

	public boolean bodyEqualsValidation(String jsonPath, String value) {
		Object path = Then.thenValidation().thenP().extract().path(jsonPath);
		if (path.equals(value)) {
			return true;
		}
		return false;
	}

	public boolean pojoBodyEqualsValidator(String pojoPath, String value) {
		if (pojoPath.equals(value)) {
			return true;
		}
		return false;
	}

	public <T> boolean assertEquals(T jsonPath, T value) {
		Assert.assertEquals(jsonPath, value);
		flag = true;
		return true;

	}
	
	public <T> boolean assertEquals(T[] jsonPath, T[] value) {
		Assert.assertEquals(jsonPath, value);
		flag = true;
		return true;

	}


	public <T>void assertNotEquals(T jsonPath, T value) {
		Assert.assertNotEquals(jsonPath, value);
	}

	public <T> void assertNull(T jsonPath) {
		Assert.assertNull(jsonPath);
	}

}
