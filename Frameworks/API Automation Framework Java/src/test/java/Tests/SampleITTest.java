package Tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.*;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import Utilities.Util;
import DomainObjects.*;

public class SampleITTest {
	private static RequestSpecification requestSpec;
	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
				setBaseUri(Util.BASE_URL).
				setContentType(ContentType.JSON).
				build();
	}
	@Test
	public void verifyDummyObjectCall() {
		System.out.println(DummyModel.builder().withDefaultAttributes().build());
		given().
				spec(requestSpec).
				and().
				body( DummyModel.builder().withDefaultAttributes().toString() ).
				when().
				post("/api/v1/test/").
				then().
				assertThat().equals("A response object");
	}
	}