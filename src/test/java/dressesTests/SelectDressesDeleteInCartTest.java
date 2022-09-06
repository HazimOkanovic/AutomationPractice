package dressesTests;
import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DressesPage;
import pages.LogInPage;
import pages.MyAccountPage;
import static org.testng.Assert.assertEquals;

public class SelectDressesDeleteInCartTest extends BaseTest {
    MyAccountPage accountPage;
    LogInPage logInPage;
    CartPage cartPage;
    DressesPage dressesPage;

    private String email = "private.pitanje@outlook.com";
    private String password = "Hazim123";
    private String emailPath = "email";
    private String passwordPath = "passwd";
    private String logInMessage = "Welcome to your account. Here you can manage all of your personal information and orders.";
    private String casualDress = "casual dress";
    private String dressNumber = "1";
    private String wantedColour = "Blue";
    private String wantedSize = "L";
    private String wantedQuantity = "7";
    private String trashButtonNumber = "1";
    private String summerDressName = "Printed Summer Dress";
    private String emptyCartMessage = "Your shopping cart is empty.";
    private String shippingCartContains = "Your shopping cart contains: 7 Products";
    private String numberOfDresses = "7";
    private String dressesPrice = "$204.86";
    private String casualDressColourAndSize = "Blue, L";
    private String dressColourAndSizeInCart = "Color : Blue, Size : L";
    private String searchCasualDressResult = "4 results have been found.";

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
