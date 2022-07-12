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



    String noAccountRegisteredError = "There is no account registered for this email address.";





    @Test
    public void forgotPassNoAccountTest(){
        var accountPage = homePage.clickSignIn();
        accountPage.clickForgotPass();
        accountPage.enterEmailRetrievePass(invalidEmail);
        accountPage.clickRetrieveButton();
        assertEquals(accountPage.checkErrorForgotPassNoAccount(), noAccountRegisteredError, "There is registered account");
    }


}
