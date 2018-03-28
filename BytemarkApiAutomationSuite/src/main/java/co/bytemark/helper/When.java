package co.bytemark.helper;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class When {
	static ObjectMapper objectMapper;
	static Response response;
	static When whenActions = null;
	public static RequestSpecification given;

	private When() {

	}

	static {
		objectMapper = new ObjectMapper();

	}

	public static When whenActions() {
		// whenActions=null;
		if (whenActions == null) {
			whenActions = new When();
		}
		return whenActions;
	}

	public RequestSpecification whenP() {
		// given=null;
		if (given == null) {
			given = Given.givenP();
			return given;
		}
		return given;
	}

	public Response getRequest(String resourceURI) {
		whenActions = null;
		given = null;
		response = When.whenActions().whenP().get(resourceURI);
		return response;
	}

	public Response getRequest() {
		Response response = When.whenActions().whenP().get();
		return response;
	}

	public Response postRequuest(String resources) {
		whenActions = null;
		Response post = When.whenActions().whenP().post(resources);
		return post;
	}

	public Response putRequest(String resources) {
		Response response = When.whenActions.whenP().put(resources);
		return response;
	}

	public String postRequest_ResponseGetter(String resources) {
		String asString = When.whenActions().whenP().post(resources).asString();
		System.out.println("Response is: " + asString);

		return asString;
	}

	public String putRequest_StringResponseGetter(String resources) {
		return When.whenActions().whenP().put(resources).asString();
	}

	public void content_Negotition(String contentType) {
		switch (contentType) {
		case "JSON":
			When.whenActions().whenP().when().contentType(ContentType.JSON);
			break;
		case "XML":
			When.whenActions().whenP().contentType(ContentType.XML);
			break;
		}
	}

	public <T> When postBody(T cls) {
		@SuppressWarnings("unused")
		RequestSpecification body = When.whenActions().whenP().body(cls);
		return whenActions;
	}

	public String getStringResponse(String resourceURI) {
		return When.whenActions().getRequest(resourceURI).asString();
	}

	public void pojoToJsonConverter(File file, Object pojo) {
		try {
			objectMapper.writeValue(file, pojo);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public <T> T jsonToPojoMapper(File file, Class<T> className) {
		T cls = null;
		try {
			cls = objectMapper.readValue(file, className);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cls;
	}

	public Object stringToJsonConverter(String json) {
		JsonPath js = new JsonPath(json);
		Object object = js.get();
		return object;
	}

	public <T> T getReposnse_PojoMapper(String resources, String jsonFile, Class<T> className) {
		String stringResponse = When.whenActions().getStringResponse(resources);
		Object stringToJsonConverter = When.whenActions().stringToJsonConverter(stringResponse);
		When.whenActions().pojoToJsonConverter(new File(Utility.jsonFilePathExtracter(jsonFile)),
				stringToJsonConverter);
		T jsonToPojoMapper = When.whenActions().jsonToPojoMapper(new File(Utility.jsonFilePathExtracter(jsonFile)),
				className);
		return jsonToPojoMapper;
	}

	public <T> T postResponse_PojoMapper(Object requestBodyObject, String resources, String jsonFileName,
			Class<T> className) {
		When.whenActions().content_Negotition(ConstantVariables.RESPONSE_TYPE);
		String postRequest_ResponseGetter = When.whenActions().postBody(requestBodyObject)
				.postRequest_ResponseGetter(resources);
		Object stringToJsonConverter = When.whenActions().stringToJsonConverter(postRequest_ResponseGetter);
		When.whenActions().pojoToJsonConverter(new File(Utility.jsonFilePathExtracter(jsonFileName)),
				stringToJsonConverter);
		T jsonToPojoMapper = When.whenActions().jsonToPojoMapper(new File(Utility.jsonFilePathExtracter(jsonFileName)),
				className);
		return jsonToPojoMapper;
	}

	public <T> T putResponse_PojoMapper(String contentType, Object requestBodyObject, String resources,
			String jsonFileName, Class<T> classname) {
		When.whenActions().content_Negotition(contentType);
		String putRequest_StringResponseGetter = When.whenActions().postBody(requestBodyObject)
				.putRequest_StringResponseGetter(resources);
		Object stringToJsonConverter = When.whenActions().stringToJsonConverter(putRequest_StringResponseGetter);
		When.whenActions().pojoToJsonConverter(new File(Utility.jsonFilePathExtracter(jsonFileName)),
				stringToJsonConverter);
		T putResponse_PojoMapper = When.whenActions()
				.jsonToPojoMapper(new File(Utility.jsonFilePathExtracter(jsonFileName)), classname);
		return putResponse_PojoMapper;

	}

	public String getResponseSingleValueJsonParser(String resourceURI, String jsonPath) {
		return When.whenActions().getRequest(resourceURI).then().extract().response().path(jsonPath);
	}

	public String[] getResponseJsonParserWithMultiValues(String resourceURI, int numberValues, String[] jsonPath) {
		String[] jsonparsedValues = new String[numberValues];
		Response response = When.whenActions().getRequest(resourceURI);
		for (int i = 0; i < numberValues; i++) {
			String jsonToParse = jsonPath[i];
			String path = response.then().extract().response().path(jsonToParse);
			jsonparsedValues[i] = path;
		}
		return jsonparsedValues;
	}

	public String postResponseSingleValueExtracter(Object requestBodyObject, String resources, String ContenetType,
			String jsonPath) {
		When.whenActions().content_Negotition(ContenetType);
		return When.whenActions().postBody(requestBodyObject).postRequuest(resources).then().extract().path(jsonPath);

	}

	public Integer postResponseJsonSizeGetter(Object requestBodyObject, String resources, String ContenetType,
			String jsonPath) {
		When.whenActions().content_Negotition(ContenetType);
		return When.whenActions().postBody(requestBodyObject).postRequuest(resources).path(jsonPath);
	}

	public Integer getResponseJsonSizeGetter(String resourceURI, String jsonPath) {
		return When.whenActions().getRequest(resourceURI).path(jsonPath);
	}

	public String[] postResponseJsonParserWithMultiValues(String resourceURI, int numberValues, String[] jsonPath,
			String contentType) {
		String[] jsonparsedValues = new String[numberValues];
		When.whenActions().content_Negotition(contentType);
		Response response = When.whenActions().postRequuest(resourceURI);
		for (int i = 0; i < numberValues; i++) {
			String jsonToParse = jsonPath[i];
			String path = response.then().extract().response().path(jsonToParse);
			jsonparsedValues[i] = path;
		}
		return jsonparsedValues;
	}
}
