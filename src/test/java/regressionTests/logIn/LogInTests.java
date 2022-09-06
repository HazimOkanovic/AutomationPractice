package regressionTests.logIn;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.MyAccountPage;

import static org.testng.Assert.assertEquals;

public class LogInTests extends BaseTest {
    LogInPage logInPage;
    MyAccountPage myAccountPage;
    private String email = "private.pitanje@outlook.com";
    private String invalidEmail = "private.outlook@outlook.com";
    private String password = "Hazim123";
    private String emailPath = "email";
    private String passwordPath = "passwd";
    private String logInMessage = "Welcome to your account. Here you can manage all of your personal information and orders.";
    private String signOutText = "Sign in";
    private String failedAuthentication = "Authentication failed.";
    private String messageConfirmationEmailIsSent = "A confirmation email has been sent to your address: private.pitanje@outlook.com";
    private String invalidEmailError = "Invalid email address.";

    @Test(priority = 0)
    public void logInWithValidCredentials(){
        logInPage = homePage.clickSignIn();
        logInPage.enterLoginData(email, emailPath);
        logInPage.enterLoginData(password, passwordPath);
        myAccountPage = logInPage.clickSignIn();
        assertEquals(myAccountPage.checkLogIn(), logInMessage, "Log in was not successful");
    }
    @Test(priority = 1)
    public void signingOutTest(){
        myAccountPage.clickSignOut();
        assertEquals(myAccountPage.checkSignOut(), signOutText, "Sign out does not work");
    }
    @Test(priority = 2)
    public void signInNoAccountTest(){
        logInPage.enterLoginData(invalidEmail, emailPath);
        logInPage.enterLoginData(password, passwordPath);
        logInPage.clickSignIn();
        assertEquals(logInPage.checkErrorCreateAccount(), failedAuthentication, "Authentication does not work!");
    }
    @Test (priority = 3)
    public void noInputsTest(){
        logInPage.clickSignIn();
        assertEquals(logInPage.checkErrorForgotPassNoAccount(), failedAuthentication, "Works with no inputs!");
    }
    @Test (priority = 4)
    public void invalidEmailTest(){
        logInPage.enterLoginData(password,emailPath);
        logInPage.clickSignIn();
        assertEquals(logInPage.checkErrorCreateAccount(), failedAuthentication, "Email verification did not fail!");
    }
    @Test (priority = 5)
    public void forgotPasswordWithAccountTest(){
        logInPage.clickForgotPass();
        logInPage.enterEmailRetrievePass(email);
        logInPage.clickRetrieveButton();
        assertEquals(logInPage.checkMessageForgotPasswdWithAccount(), messageConfirmationEmailIsSent, "Forgot password does not work");
    }
}
