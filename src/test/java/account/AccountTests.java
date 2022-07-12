package account;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.MyAccountPage;

import static org.testng.Assert.assertEquals;

public class AccountTests extends BaseTest {
    LogInPage logInPage;
    MyAccountPage myAccountPage;
    String email = "private.pitanje@outlook.com";
    String password = "Hazim123";
    String emailPath = "email";
    String passwordPath = "passwd";
    String signOutText = "Sign in";
    @Test
    public void logInTest(){
        logInPage = homePage.clickSignIn();
        logInPage.enterLoginData(email, emailPath);
        logInPage.enterLoginData(password, passwordPath);
        myAccountPage = logInPage.clickSignIn();
        myAccountPage.clickSignOut();
        assertEquals(myAccountPage.checkSignOut(), signOutText, "Sign out does not work");
    }
}
