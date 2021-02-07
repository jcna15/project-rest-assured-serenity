package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.Base64;

import static io.restassured.RestAssured.given;

@RunWith(SerenityRunner.class)
public class RedmineConfig {
    public static String user = "jnavarro";
    public static String pass = "jnavarro";
    public static String api_key;

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;


    public static void Login(){

        String encodedString = Base64.getEncoder().encodeToString((user+":"+pass).getBytes());

        Response response =
                given()
                        .header("Authorization","Basic "+encodedString).
                when()
                        .get("http://localhost:81/my/account.json").
                then()
                        .assertThat().statusCode(200)
                        .extract().response();

        api_key = response.getBody().path("user.api_key").toString();
    }

    @Before
    public void setup(){
        Login();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setBasePath("/") //redmine
                .setPort(81)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/json")
                .addHeader("X-Redmine-API-Key",api_key)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
}
