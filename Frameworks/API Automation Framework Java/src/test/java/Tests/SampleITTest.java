package Tests;

import static io.restassured.RestAssured.*;
import static java.util.Arrays.asList;

import java.util.Collection;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.junit.*;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import Utilities.Util;
import DomainObjects.*;

@RunWith(JUnitParamsRunner.class)
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
	public void verifyDummyObjectCallWithDefautName() {
		System.out.println(DummyModel.builder().withDefaultAttributes().build());
		given().
				spec(requestSpec).
				and().
				body( DummyModel.builder().withDefaultAttributes().toString() ).
				when().
				post("/api/v1/test/").
				then().
				assertThat().
				statusCode(200);
	}

	@Test
	@Parameters(method = "parametersForValidateValidMessages")
	public void verifyDummyObjectCallWithName(String name, String response) {
		System.out.println(DummyModel.builder().withName(name).build());
		given().
				spec(requestSpec).
				and().
				body( DummyModel.builder().withDefaultAttributes().toString() ).
				when().
				post("/api/v1/test/").
				then().
				assertThat().equals(response);
	}

	public static Collection<Object[]> parametersForValidateValidMessages() {
		return asList(
				new Object[][]{
						{ "Test1", "response"},
						{ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "response" },
						{ "", "response"},
				});
	}

	}