package dressesTests;
import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DressesPage;
import pages.LogInPage;
import pages.MyAccountPage;
import static org.testng.Assert.assertEquals;

public class SelectDressesDeleteInCartTest extends BaseTest {
    String email = "private.pitanje@outlook.com";
    String password = "Hazim123";
    String emailPath = "email";
    String passwordPath = "passwd";
    String logInMessage = "Welcome to your account. Here you can manage all of your personal information and orders.";
    String casualDress = "casual dress";
    String dressNumber = "1";
    String wantedColour = "Blue";
    String wantedSize = "L";
    String wantedQuantity = "7";
    String trashButtonNumber = "1";
    String summerDressName = "Printed Summer Dress";
    String emptyCartMessage = "Your shopping cart is empty.";
    String shippingCartContains = "Your shopping cart contains: 7 Products";
    String numberOfDresses = "7";
    String dressesPrice = "$204.86";
    String casualDressColourAndSize = "Blue, L";
    String dressColourAndSizeInCart = "Color : Blue, Size : L";
    String searchCasualDressResult = "4 results have been found.";
    MyAccountPage accountPage;
    LogInPage logInPage;
    CartPage cartPage;
    DressesPage dressesPage;
    @Test(priority = 0)
    public void checkLogIn(){
        logInPage = homePage.clickSignIn();
        logInPage.enterLoginData(email, emailPath);
        logInPage.enterLoginData(password, passwordPath);
        accountPage = logInPage.clickSignIn();
        assertEquals(accountPage.checkLogIn(), logInMessage, "Log in was not successful");
    }
    @Test(priority = 1)
    public void checkSearch(){
        homePage.enterTextInSearch(casualDress);
        dressesPage = homePage.clickSearchButton();
        assertEquals(homePage.checkSearchResult(), searchCasualDressResult, "The search was not successful");
    }
    @Test(priority = 2)
    public void CheckSelectFirstDress(){
        dressesPage.chooseTheDress(dressNumber);
        assertEquals(dressesPage.checkIfDressIsSelected(), summerDressName, "Names are not the same");
    }
    @Test(priority = 3)
    public void checkSizeAndColour(){
        dressesPage.chooseTheDressColour(wantedColour);
        dressesPage.setTheSize(wantedSize);
    }
    @Test(priority = 4)
    public void checkQuantity(){
        dressesPage.enterWantedQuantity(wantedQuantity);
        dressesPage.clickAddToCart();
        assertEquals(dressesPage.checkTheNumberOfDresses(), numberOfDresses, "Numbers do not match");
    }
    @Test(priority = 5)
    public void checkColourAndSize(){
        assertEquals(dressesPage.checkTheColourAndSize(), casualDressColourAndSize, "Colour and size do not match");
    }
    @Test(priority = 6)
    public void checkProductNumberInCart(){
        cartPage = dressesPage.proceedToCheckOut();
        assertEquals(cartPage.checkShippingCart(), shippingCartContains, "Shipping carts are not the same");
    }
    @Test(priority = 7)
    public void checkPriceInCart(){
        assertEquals(cartPage.getTotalPriceInCart(), dressesPrice, "The prices are not the same");
    }
    @Test(priority = 8)
    public void checkColourAndSizeInCart(){
        assertEquals(cartPage.checkColourAndSizeInCart("1"), dressColourAndSizeInCart);
    }
    @Test(priority = 9)
    public void checkEmptyCart(){
        cartPage.clickTrashButtonCart(trashButtonNumber);
        assertEquals(cartPage.checkEmptyCart(), emptyCartMessage, "Cart is not empty");
    }
}
