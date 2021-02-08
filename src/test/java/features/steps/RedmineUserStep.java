package features.steps;

import config.RedmineEndpoints;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.Base64;

public class RedmineUserStep {

    public static String api_key;

    @Step("#User login with username {0} and password {1}")
    public void userLoginInRedmine(String user, String pass){
        String encodedString = Base64.getEncoder().encodeToString((user+":"+pass).getBytes());

        Response response =
                SerenityRest.
                given()
                        .header("Authorization","Basic "+encodedString).
                when()
                        .get(  RedmineEndpoints.BASE_URI + RedmineEndpoints.MY_LOGIN_ACCOUNT_JSON).
                then()
                        .assertThat().statusCode(200)
                        .extract().response();

        api_key = response.getBody().path("user.api_key").toString();
    }

    @Step
    public void getAllProjects(){
        SerenityRest.
                given().
                        header("X-Redmine-API-Key",api_key).
                when()
                        .get(RedmineEndpoints.BASE_URI + RedmineEndpoints.ALL_REDMINE_PROJECT_JSON).
                then()
                        .statusCode(200);
    }
}
