package features.steps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class WhenAUserGetAllProjects {

    public static String user = "jnavarro";
    public static String pass = "jnavarro";

    @Steps
    RedmineUserStep userSteps;

    @Test
    public void userGetAllProjects(){
        userSteps.userLoginInRedmine(user,pass);
        userSteps.getAllProjects();
    }
}
