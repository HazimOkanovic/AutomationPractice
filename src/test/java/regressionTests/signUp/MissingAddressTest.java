package regressionTests.signUp;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.NewAccountPage;
import utils.Utils;

import static org.testng.Assert.assertEquals;

public class MissingAddressTest extends BaseTest {
    LogInPage logInPage;
    NewAccountPage newAccountPage;

    private String authentication = "AUTHENTICATION";
    private String email = Utils.getAlphaNumericString(15) + "@gmail.com";
    private String [] personalInfo = {"Hazim", "Okanovic", "Hazim123"};
    private String [] personalInfoPaths = {"customer_firstname", "customer_lastname", "passwd"};
    private String [] timeVariables = {"10", "1", "1992"};
    private String [] timePaths = {"days", "months", "years"};
    private String [] companyAddressesCityZip = {"OS Abdulvehab Ilhamija", "Sahmani bb", "Bosnia and Herzegovina", "Zepce", "72236"};
    private String [] companyAddressesCityZipPaths = {"company", "address1", "address2", "city", "postcode"};
    private String addInfo = "There is nothing more to add";
    private String [] phonesAndAddressForReference = {"032670389", "062258766", "Zeljezno Polje"};
    private String [] phonesAndAddressForReferencePath = {"phone", "phone_mobile", "alias"};
    private String addressInfoError = "There are 4 errors\n" + "address1 is required.\n" + "city is required.\n" +
            "The Zip/Postal code you've entered is invalid. It must follow this format: 00000\n" +
            "This country requires you to choose a State.";

    @Test(priority = 0)
    public void checkSignInButton(){
        logInPage = homePage.clickSignIn();
        assertEquals(logInPage.checkAuthentication(), authentication, "The message is not the same");
    }
    @Test(priority = 1)
    public void notEnteringAddressesInfoTest(){
        homePage.clickSignIn();
        logInPage.enterEmailCreateAccount(email);
        newAccountPage = logInPage.clickCreateAccount();
        newAccountPage.clickMr();
        for (int i = 0; i < personalInfo.length; i++){
            newAccountPage.enterNameSurnamePassword(personalInfo[i], personalInfoPaths[i]);
        }
        for (int i = 0; i < timeVariables.length; i++){
            newAccountPage.selectDateOfBirth(timeVariables[i], timePaths[i]);
        }
        for (int i = 0; i == 0; i++){
            newAccountPage.enterCompanyAddressesCityZip(companyAddressesCityZip[i], companyAddressesCityZipPaths[i]);
        }
        newAccountPage.addAdditionalInfo(addInfo);
        for (int i = 0; i < phonesAndAddressForReference.length; i++){
            newAccountPage.addPhonesAndAssignAddress(phonesAndAddressForReference[i], phonesAndAddressForReferencePath[i]);
        }
        newAccountPage.clickRegister();
        assertEquals(newAccountPage.checkError(), addressInfoError, "Registration succeeded!");
    }
}
