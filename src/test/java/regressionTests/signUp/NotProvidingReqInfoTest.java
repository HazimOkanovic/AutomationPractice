package regressionTests.signUp;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.NewAccountPage;
import utils.Utils;

import static org.testng.Assert.assertEquals;

public class NotProvidingReqInfoTest extends BaseTest {
    LogInPage logInPage;
    NewAccountPage newAccountPage;

    private String authentication = "AUTHENTICATION";
    private String email = Utils.getAlphaNumericString(15) + "@gmail.com";
    private String allRequiredInfoError = "There are 8 errors\n" + "You must register at least one phone number.\n"
            + "lastname is required.\n" + "firstname is required.\n" + "passwd is required.\n" +
            "address1 is required.\n" + "city is required.\n" +
            "The Zip/Postal code you've entered is invalid. It must follow this format: 00000\n" +
            "This country requires you to choose a State.";

    @Test(priority = 0)
    public void checkSignInButton(){
        logInPage = homePage.clickSignIn();
        assertEquals(logInPage.checkAuthentication(), authentication, "The message is not the same");
    }
    @Test(priority = 1)
    public void notProvidingAnyInfoTest() {
        logInPage.enterEmailCreateAccount(email);
        newAccountPage = logInPage.clickCreateAccount();
        newAccountPage.clickRegister();
        assertEquals(newAccountPage.checkError(), allRequiredInfoError, "Registration failed!");
    }
}
