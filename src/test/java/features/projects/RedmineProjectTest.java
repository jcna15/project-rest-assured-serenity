package features.projects;

import config.RedmineConfig;
import config.RedmineEndpoints;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
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
