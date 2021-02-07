package features.projects;

import config.RedmineConfig;
import config.RedmineEndpoints;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

public class RedmineProjectTest extends RedmineConfig {

    @Test
    public void getAllProjects(){
        SerenityRest.
                given().
                when()
                    .get(RedmineEndpoints.ALL_REDMINE_PROJECT_JSON).
                then()
                    .statusCode(200);
    }
}
