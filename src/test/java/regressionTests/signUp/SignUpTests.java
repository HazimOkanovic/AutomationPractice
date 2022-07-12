package regressionTests.signUp;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LogInPage;
import pages.NewAccountPage;
import utils.Utils;

import static org.testng.Assert.assertEquals;
public class SignUpTests extends BaseTest {
    LogInPage logInPage;
    NewAccountPage newAccountPage;
    String email = Utils.getAlphaNumericString(7) + "@gmail.com";
    String [] personalInfo = {"Hazim", "Okanovic", "Hazim123"};
    String [] personalInfoPaths = {"customer_firstname", "customer_lastname", "passwd"};
    String [] timeVariables = {"10", "1", "1992"};
    String [] timePaths = {"days", "months", "years"};
    String [] companyAddressesCityZip = {"OS Abdulvehab Ilhamija", "Sahmani bb", "Bosnia and Herzegovina", "Zepce", "72236"};
    String [] companyAddressesCityZipPaths = {"company", "address1", "address2", "city", "postcode"};
    String addInfo = "There is nothing more to add";
    String [] phonesAndAddressForReference = {"032670389", "062258766", "Zeljezno Polje"};
    String [] phonesAndAddressForReferencePath = {"phone", "phone_mobile", "alias"};
    String nameSurnameAndPasswordError = "There are 3 errors\n" + "lastname is required.\n" +
            "firstname is required.\n" + "passwd is required.";
    String addressInfoError = "There are 4 errors\n" + "address1 is required.\n" + "city is required.\n" +
            "The Zip/Postal code you've entered is invalid. It must follow this format: 00000\n" +
            "This country requires you to choose a State.";
    String allRequiredInfoError = "There are 8 errors\n" + "You must register at least one phone number.\n"
            + "lastname is required.\n" + "firstname is required.\n" + "passwd is required.\n" +
            "address1 is required.\n" + "city is required.\n" +
            "The Zip/Postal code you've entered is invalid. It must follow this format: 00000\n" +
            "This country requires you to choose a State.";
    String [] addressVariablesForChecking = {"OS Abdulvehab Ilhamija", "032670389", "062258766"};
    String [] addressVariablesFOrCheckingPaths = {"company", "phone", "phone_mobile"};
    @Test
    public void createAccountWithAllInfoTest(){
        logInPage = homePage.clickSignIn();
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
        newAccountPage.clickRegister();
        assertEquals(newAccountPage.checkSuccess(), true, "Registration failed!");
    }
    @Test
    public void notProvidingAnyInfoTest() {
        logInPage = homePage.clickSignIn();
        logInPage.enterEmailCreateAccount(email);
        newAccountPage = logInPage.clickCreateAccount();
        newAccountPage.clickRegister();
        assertEquals(newAccountPage.checkError(), allRequiredInfoError, "Registration failed!");
    }
    @Test
    public void notEnteringNameSurnamePasswordTest(){
        logInPage = homePage.clickSignIn();
        logInPage.enterEmailCreateAccount(email);
        newAccountPage = logInPage.clickCreateAccount();
        newAccountPage.clickMr();
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
        newAccountPage.clickRegister();
        assertEquals(newAccountPage.checkError(), nameSurnameAndPasswordError, "Registration succeeded!");
    }
    @Test
    public void notEnteringAddressesInfoTest(){
        logInPage = homePage.clickSignIn();
        logInPage.enterEmailCreateAccount(email);
        var newAccountPage = logInPage.clickCreateAccount();
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
    @Test
    public void checkInfoWhenAccountCreated(){
        logInPage = homePage.clickSignIn();
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
        var myAccountPage = newAccountPage.clickRegister();
        myAccountPage.clickAddressButton();
        for (int i=0; i<addressVariablesForChecking.length; i++){
            assertEquals(myAccountPage.getInfo(addressVariablesFOrCheckingPaths[i]), addressVariablesForChecking[i]);
        }
    }
}
