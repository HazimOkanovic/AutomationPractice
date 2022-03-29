package login;

import base.BaseTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LogInTests extends BaseTest {
    String invalidEmail = "private.outlook@outlook.com";
    String email = "private.pitanje@outlook.com";
    String password = "Hazim123";
    String emailPath = "email";
    String passwordPath = "passwd";
    String failedAuthentication = "Authentication failed.";
    String invalidEmailError = "Invalid email address.";
    String noInputs = "An email address required.";
    String noAccountRegisteredError = "There is no account registered for this email address.";
    String messageConfirmationEmailIsSent = "A confirmation email has been sent to your address: private.pitanje@outlook.com";

    @Test
    public void signInNoAccountTest(){
        var accountPage = homePage.clickSignIn();
        accountPage.enterLoginData(invalidEmail, emailPath);
        accountPage.enterLoginData(password, passwordPath);
        accountPage.clickSignIn();
        assertEquals(accountPage.checkErrorCreateAccount(), failedAuthentication, "Authentication does not work!");
    }
    @Test
    public void invalidEmailTest(){
        var accountPage = homePage.clickSignIn();
        accountPage.enterLoginData(password,emailPath);
        accountPage.clickSignIn();
        assertEquals(accountPage.checkErrorCreateAccount(), invalidEmailError, "Email verification did not fail!");
    }
    @Test
    public void noInputsTest(){
        var accountPage = homePage.clickSignIn();
        accountPage.clickSignIn();
        assertEquals(accountPage.checkErrorForgotPassNoAccount(), noInputs, "Works with no inputs!");
    }
    @Test
    public void forgotPassNoAccountTest(){
        var accountPage = homePage.clickSignIn();
        accountPage.clickForgotPass();
        accountPage.enterEmailRetrievePass(invalidEmail);
        accountPage.clickRetrieveButton();
        assertEquals(accountPage.checkErrorForgotPassNoAccount(), noAccountRegisteredError, "There is registered account");
    }
    @Test
    public void createAccountInvalidEmailTest(){
        var accountPage = homePage.clickSignIn();
        accountPage.enterEmailCreateAccount(password);
        accountPage.clickCreateAccount();
        assertEquals(accountPage.checkErrorCreateAccount(), invalidEmailError, "You can create an account with no email");
    }
    @Test
    public void forgotPasswordWithAccountTest(){
        var accountPage = homePage.clickSignIn();
        accountPage.clickForgotPass();
        accountPage.enterEmailRetrievePass(email);
        accountPage.clickRetrieveButton();
        assertEquals(accountPage.checkMessageForgotPasswdWithAccount(), messageConfirmationEmailIsSent, "Forgot password does not work");
    }
    @Test
    public void logInWithValidCredentials(){
        var accountPage = homePage.clickSignIn();
        accountPage.enterLoginData(email, emailPath);
        accountPage.enterLoginData(password, passwordPath);
    }
}
