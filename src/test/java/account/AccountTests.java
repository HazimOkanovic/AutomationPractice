package account;

import base.BaseTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AccountTests extends BaseTest {
    String email = "private.pitanje@outlook.com";
    String password = "Hazim123";
    String emailPath = "email";
    String passwordPath = "passwd";
    String signOutText = "\n" +
                         "\t\t\tSign in\n" +
                         "\t\t";
    @Test
    public void signOutTest(){
        var logInPage = homePage.clickSignIn();
        logInPage.enterLoginData(email, emailPath);
        logInPage.enterLoginData(password, passwordPath);
        var myAccountPage = logInPage.clickSignIn();
        myAccountPage.clickSignOut();
        assertEquals(myAccountPage.checkSignOut(), signOutText, "Sign out does not work");
    }
}
