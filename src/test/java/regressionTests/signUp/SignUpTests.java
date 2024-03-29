package regressionTests.signUp;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.MyAccountPage;
import pages.NewAccountPage;
import utils.Utils;

import static org.testng.Assert.assertEquals;
public class SignUpTests extends BaseTest {
    LogInPage logInPage;
    NewAccountPage newAccountPage;
    MyAccountPage myAccountPage;

    private String authentication = "AUTHENTICATION";
    private String email = Utils.getAlphaNumericString(15) + "@gmail.com";
    private String signOutText = "Sign in";
    private String [] personalInfo = {"Hazim", "Okanovic", "Hazim123"};
    private String [] personalInfoPaths = {"customer_firstname", "customer_lastname", "passwd"};
    private String [] timeVariables = {"10", "1", "1992"};
    private String [] timePaths = {"days", "months", "years"};
    private String [] companyAddressesCityZip = {"OS Abdulvehab Ilhamija", "Sahmani bb", "Bosnia and Herzegovina", "Zepce", "72236"};
    private String [] companyAddressesCityZipPaths = {"company", "address1", "address2", "city", "postcode"};
    private String addInfo = "There is nothing more to add";
    private String [] phonesAndAddressForReference = {"032670389", "062258766", "Zeljezno Polje"};
    private String [] phonesAndAddressForReferencePath = {"phone", "phone_mobile", "alias"};
    private String nameSurnameAndPasswordError = "There are 3 errors\n" + "lastname is required.\n" +
            "firstname is required.\n" + "passwd is required.";
    private String addressInfoError = "There are 4 errors\n" + "address1 is required.\n" + "city is required.\n" +
            "The Zip/Postal code you've entered is invalid. It must follow this format: 00000\n" +
            "This country requires you to choose a State.";
    private String allRequiredInfoError = "There are 8 errors\n" + "You must register at least one phone number.\n"
            + "lastname is required.\n" + "firstname is required.\n" + "passwd is required.\n" +
            "address1 is required.\n" + "city is required.\n" +
            "The Zip/Postal code you've entered is invalid. It must follow this format: 00000\n" +
            "This country requires you to choose a State.";
    private String [] addressVariablesForChecking = {"OS Abdulvehab Ilhamija", "032670389", "062258766"};
    private String [] addressVariablesFOrCheckingPaths = {"company", "phone", "phone_mobile"};

    @Test(priority = 0)
    public void checkSignInButton(){
        logInPage = homePage.clickSignIn();
        assertEquals(logInPage.checkAuthentication(), authentication, "The message is not the same");
    }
    @Test(priority = 1)
    public void createAccountWithAllInfoTest(){
        logInPage.enterEmailCreateAccount(email);
        newAccountPage = logInPage.clickCreateAccount();
        newAccountPage.clickMr();
        for (int i = 0; i < personalInfo.length; i++){
            newAccountPage.enterNameSurnamePassword(personalInfo[i], personalInfoPaths[i]);
        }
        for (int i = 0; i < timeVariables.length; i++){
            newAccountPage.selectDateOfBirth(timeVariables[i], timePaths[i]);
        }
        for (int i = 0; i < companyAddressesCityZip.length; i++){
            newAccountPage.enterCompanyAddressesCityZip(companyAddressesCityZip[i], companyAddressesCityZipPaths[i]);
        }
        newAccountPage.selectState();
        newAccountPage.addAdditionalInfo(addInfo);
        for (int i = 0; i < phonesAndAddressForReference.length; i++){
            newAccountPage.addPhonesAndAssignAddress(phonesAndAddressForReference[i], phonesAndAddressForReferencePath[i]);
        }
        myAccountPage = newAccountPage.clickRegister();
        assertEquals(newAccountPage.checkSuccess(), true, "Registration failed!");
    }
    @Test (priority = 2)
    public void checkInfoTest(){
        myAccountPage.clickAddressButton();
        for (int i=0; i<addressVariablesForChecking.length; i++){
            assertEquals(myAccountPage.getInfo(addressVariablesFOrCheckingPaths[i]), addressVariablesForChecking[i]);
        }
    }
    @Test(priority = 3)
    public void signingOutTest(){
        myAccountPage.clickSignOut();
        assertEquals(myAccountPage.checkSignOut(), signOutText, "Sign out does not work");
    }
}
