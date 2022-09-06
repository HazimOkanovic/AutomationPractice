package dressesTests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;
import java.util.Arrays;
import static org.testng.Assert.assertEquals;

public class BuyingTwoDifferentDressesTest extends BaseTest {
    LogInPage logInPage;
    MyAccountPage accountPage;
    DressesPage dressesPage;
    CartPage cartPage;
    OrderCheckOutPage orderCheckOutPage;

    private String email = "private.pitanje@outlook.com";
    private String password = "Hazim123";
    private String emailPath = "email";
    private String passwordPath = "passwd";
    private String logInMessage = "Welcome to your account. Here you can manage all of your personal information and orders.";
    private String categoryName = "DRESSES ";
    private String sortByName = "Product Name: Z to A";
    private String sortByPrice = "Price: Lowest first";
    private String firstDressNumber = "2";
    private String secondDressNumber = "4";
    private String[] dressesNamesAfterFirstSorting = {"Printed Chiffon Dress", "Printed Dress", "Printed Dress", "Printed Summer Dress", "Printed Summer Dress"};
    private String[] dressesNamesAfterSecondSorting = {"Printed Chiffon Dress", "Printed Dress", "Printed Summer Dress", "Printed Summer Dress", "Printed Dress"};
    private String firstDressName = "Printed Dress";
    private String secondDressName = "Printed Summer Dress";
    private String firstDressSize = "Orange, L";
    private String totalPrice = "$341.00";
    private String wantedColour = "White";
    private String wantedSize = "L";
    private String secondDressColorAndSize = "White, L";
    private String firstDressColourAndSizeInCart = "Color : Orange, Size : L";
    private String secondDressColourAndSizeInCart = "Color : White, Size : L";
    private String wantedQuantity = "6";
    private String shippingCartContains = "Your shopping cart contains: 12 Products";
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
    public void checkLogIn(){
        logInPage = homePage.clickSignIn();
        logInPage.enterLoginData(email, emailPath);
        logInPage.enterLoginData(password, passwordPath);
        accountPage = logInPage.clickSignIn();
        assertEquals(accountPage.checkLogIn(), logInMessage, "Log in was not successful");
    }
    @Test(priority = 1)
    public void checkClickOnDresses(){
        dressesPage = homePage.clickDresses();
        assertEquals(dressesPage.checkCategoryName(), categoryName, "The click did not work");
    }
    @Test(priority = 2)
    public void checkSortBy() throws InterruptedException {
        dressesPage.sortBy(sortByName);
        Thread.sleep(Long.parseLong("5000"));
        assertEquals(dressesPage.checkDressesOrder().toString(), Arrays.toString(dressesNamesAfterFirstSorting), "Sorting was not successful");
    }
    @Test(priority = 3)
    public void checkDressSelecting(){
        dressesPage.chooseTheDress(firstDressNumber);
        assertEquals(dressesPage.checkIfDressIsSelected(), firstDressName, "Names do not match");
    }
    @Test(priority = 4)
    public void checkSelectingSizeAndQuantity(){
        dressesPage.setTheSize(wantedSize);
        dressesPage.enterWantedQuantity(wantedQuantity);
        dressesPage.clickAddToCart();
        assertEquals(dressesPage.checkTheNumberOfDresses(), wantedQuantity, "Numbers do not match");
    }
    @Test(priority = 5)
    public void checkTheSize(){
        assertEquals(dressesPage.checkTheColourAndSize(), firstDressSize, "Sizes do not match");
    }
    @Test(priority = 6)
    public void checkContinueShopping(){
        dressesPage.clickContinueShopping();
        homePage.clickDresses();
        assertEquals(dressesPage.checkCategoryName(), categoryName, "Category name is not the same");
    }
    @Test(priority = 7)
    public void checkSecondSortBy() throws InterruptedException {
        dressesPage.sortBy(sortByPrice);
        Thread.sleep(Long.parseLong("5000"));
        assertEquals(dressesPage.checkDressesOrder().toString(), Arrays.toString(dressesNamesAfterSecondSorting), "Sorting was not successful");
    }
    @Test(priority = 8)
    public void checkSelectionSecondDress(){
        dressesPage.chooseTheDress(secondDressNumber);
        assertEquals(dressesPage.checkIfDressIsSelected(), secondDressName, "Dress names do not match");
    }
    @Test(priority = 9)
    public void checkSelectingColourAndSize(){
        dressesPage.setTheSize(wantedSize);
        dressesPage.chooseTheDressColour(wantedColour);
    }
    @Test(priority = 10)
    public void checkSecondDressQuantity(){
        dressesPage.enterWantedQuantity(wantedQuantity);
        dressesPage.clickAddToCart();
        assertEquals(dressesPage.checkTheNumberOfDresses(), wantedQuantity, "Quantity does not match");
    }
    @Test(priority = 11)
    public void checkSecondDressColourAndSize(){
        assertEquals(dressesPage.checkTheColourAndSize(), secondDressColorAndSize, "Dress color and size do not match");
    }
    @Test(priority = 12)
    public void checkTotalPriceCart(){
        cartPage = dressesPage.proceedToCheckOut();
        assertEquals(cartPage.getTotalPriceInCart(), totalPrice, "Prices are not the same");
    }
    @Test(priority = 13)
    public void checkProductNumber(){
        assertEquals(cartPage.checkShippingCart(), shippingCartContains, "Shipping cart numbers do not match");
    }
    @Test(priority = 14)
    public void checkSizeAndColourFirstDressInCart(){
        assertEquals(cartPage.checkColourAndSizeInCart("1"), firstDressColourAndSizeInCart, "Colour and size do not match");
    }
    @Test(priority = 15)
    public void checkSizeAndColorSecondDressInCart(){
        assertEquals(cartPage.checkColourAndSizeInCart("2"), secondDressColourAndSizeInCart, "Color and size do not match");
    }
    @Test(priority = 16)
    public void checkBillingAddress(){
        orderCheckOutPage = cartPage.clickCheckOutCart();
        assertEquals(orderCheckOutPage.checkBillingAddress(), billingAddress, "Billing address is not the same");
    }
    @Test(priority = 17)
    public void checkSelectingAcceptTerms(){
        orderCheckOutPage.clickProceed();
        orderCheckOutPage.clickAcceptTerms();
        orderCheckOutPage.clickProceed();
        assertEquals(orderCheckOutPage.checkTotalPriceBeforeChoosingPayment(), totalPrice, "Total price is not the same");
    }
    @Test(priority = 18)
    public void checkSelectingBankWire(){
        orderCheckOutPage.clickBankWire();
        assertEquals(orderCheckOutPage.getOrderConfirmationBankWire(), orderSummary, "Order confirmation is not the same");
    }
    @Test(priority = 19)
    public void checkCompleteOrder(){
        orderCheckOutPage.clickSubmitOrder();
        assertEquals(orderCheckOutPage.checkOrderSummary(), orderConfirmation, "Order summary is not the same");
    }
}
