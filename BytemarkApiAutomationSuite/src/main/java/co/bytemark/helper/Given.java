package co.bytemark.helper;

import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;

//Class contains all low level keywords belongs to given gherkin
public class Given {
	public static RequestSpecification given;
	public static Given give;
    
	private Given() {

	}

	public static Given preRequisites() {
		 //give=null;
		if (give == null) {
			give = new Given();
			return give;
		}
		return give;
	}

	public static RequestSpecification givenP() {
		 //given=null;
		if (given == null) {
			given = given();
			return given;
		}

		return given;
	}

	@SuppressWarnings("static-access")
	public RequestSpecification parameters(int paramNumber, String[] paramKey, String[] paramValue) {
		give = null;
		given = null;
		given = Given.preRequisites().givenP();
		for (int i = 0; i < paramNumber; i++) {
			System.out.println(paramKey[i]+","+ paramValue[i]);
			given.param(paramKey[i], paramValue[i]);
		}
		return given;
	}

	public void headers(String header) {
		Given.preRequisites().headers(header);
	}
	
	public void pathParam(String key , String value) {
		give = null;
		given = null;
		RequestSpecification pathParam = Given.givenP().pathParam(key, value);
	}
	

}
