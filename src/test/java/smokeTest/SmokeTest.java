package smokeTest;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;

public class SmokeTest extends BaseTest {
    LogInPage logInPage;
    MyAccountPage myAccountPage;
    DressesPage dressesPage;
    CartPage cartPage;
    OrderCheckOutPage orderCheckOutPage;

    private String email = "private.pitanje@outlook.com";
    private String invalidEmail = "private.outlook@outlook.com";
    private String password = "Hazim123";
    private String emailPath = "email";
    private String passwordPath = "passwd";
    private String logInMessage = "Welcome to your account. Here you can manage all of your personal information and orders.";
    private String categoryName = "DRESSES ";
    private String firstDressNumber = "2";
    private String firstDressName = "Printed Dress";
    private String wantedSize = "L";
    private String wantedQuantity = "6";
    private String firstDressSize = "Beige, L";
    private String totalPrice = "$307.94";
    private String shippingCartContains = "Your shopping cart contains: 6 Products";
    private String firstDressColourAndSizeInCart = "Color : Beige, Size : L";
    private String billingAddress = "YOUR BILLING ADDRESS\n" +
            "Hazim Okanovic\n" +
            "OS AbdulvehabIlhamija\n" +
            "Sahmani bb\n" +
            "zepce, Connecticut 72236\n" +
            "United States\n" +
            "062258766\n" +
            "Update";
    private String orderSummary = "You have chosen to pay by bank wire. Here is a short summary of your order:";
    private String orderConfirmation = "Your order on My Store is complete.";

    @Test(priority = 0)
    public void logInWithValidCredentials(){
        logInPage = homePage.clickSignIn();
        logInPage.enterLoginData(email, emailPath);
        logInPage.enterLoginData(password, passwordPath);
        myAccountPage = logInPage.clickSignIn();
        assertEquals(myAccountPage.checkLogIn(), logInMessage, "Log in was not successful");
    }
    @Test(priority = 1)
    public void checkClickOnDresses(){
        dressesPage = homePage.clickDresses();
        assertEquals(dressesPage.checkCategoryName(), categoryName, "The click did not work");
    }
    @Test(priority = 2)
    public void checkDressSelecting(){
        dressesPage.chooseTheDress(firstDressNumber);
        assertEquals(dressesPage.checkIfDressIsSelected(), firstDressName, "Names do not match");
    }
    @Test(priority = 3)
    public void checkSelectingSizeAndQuantity(){
        dressesPage.setTheSize(wantedSize);
        dressesPage.enterWantedQuantity(wantedQuantity);
        dressesPage.clickAddToCart();
        assertEquals(dressesPage.checkTheNumberOfDresses(), wantedQuantity, "Numbers do not match");
    }
    @Test(priority = 4)
    public void checkTheSize(){
        assertEquals(dressesPage.checkTheColourAndSize(), firstDressSize, "Sizes do not match");
    }
    @Test(priority = 5)
    public void checkTotalPriceCart(){
        cartPage = dressesPage.proceedToCheckOut();
        assertEquals(cartPage.getTotalPriceInCart(), totalPrice, "Prices are not the same");
    }
    @Test(priority = 6)
    public void checkProductNumber(){
        assertEquals(cartPage.checkShippingCart(), shippingCartContains, "Shipping cart numbers do not match");
    }
    @Test(priority = 7)
    public void checkSizeAndColourFirstDressInCart(){
        assertEquals(cartPage.checkColourAndSizeInCart("1"), firstDressColourAndSizeInCart, "Colour and size do not match");
    }
    @Test(priority = 8)
    public void checkBillingAddress(){
        orderCheckOutPage = cartPage.clickCheckOutCart();
        assertEquals(orderCheckOutPage.checkBillingAddress(), billingAddress, "Billing address is not the same");
    }
    @Test(priority = 9)
    public void checkSelectingAcceptTerms(){
        orderCheckOutPage.clickProceed();
        orderCheckOutPage.clickAcceptTerms();
        orderCheckOutPage.clickProceed();
        assertEquals(orderCheckOutPage.checkTotalPriceBeforeChoosingPayment(), totalPrice, "Total price is not the same");
    }
    @Test(priority = 10)
    public void checkSelectingBankWire(){
        orderCheckOutPage.clickBankWire();
        assertEquals(orderCheckOutPage.getOrderConfirmationBankWire(), orderSummary, "Order confirmation is not the same");
    }
    @Test(priority = 11)
    public void checkCompleteOrder(){
        orderCheckOutPage.clickSubmitOrder();
        assertEquals(orderCheckOutPage.checkOrderSummary(), orderConfirmation, "Order summary is not the same");
    }
 }   