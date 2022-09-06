package dressesTests;
import base.BaseTest;
import org.testng.annotations.Test;
import pages.*;
import java.util.Arrays;
import static org.testng.Assert.assertEquals;

public class LogInHoverSelectDressCompleteOrderTest extends BaseTest {
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
    private String summerDressesTitle = "SUMMER DRESSES ";
    private String[] dressesNamesAfterSorting= {"Printed Chiffon Dress", "Printed Summer Dress", "Printed Summer Dress"};
    private String dressName = "Printed Chiffon Dress";
    private String dressColourAndSize = "Green, L";
    private String numberOfDresses = "9";
    private String priceOfTenDresses = "$149.60";
    private String sortByLowestFirst = "Price: Lowest first";
    private String dressNumber = "1";
    private String wantedColour = "Green";
    private String wantedSize = "L";
    private String dressColourAndSizeInCart = "Color : Green, Size : L";
    private String wantedQuantity = "9";
    private String shippingCartContains = "Your shopping cart contains: 9 Products";
    private String billingAddress = "YOUR BILLING ADDRESS\n" +
                            "Hazim Okanovic\n" +
                            "OS AbdulvehabIlhamija\n" +
                            "Sahmani bb\n" +
                            "zepce, Connecticut 72236\n" +
                            "United States\n" +
                            "062258766\n" +
                            "Update";
    private String orderSummary = "You have chosen to pay by check. Here is a short summary of your order:";
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
    public void checkHoverAndSelectSummerDress(){
        accountPage.hoverOverDresses();
        dressesPage = accountPage.clickSummerDressesAfterHover();
        assertEquals(dressesPage.checkCategoryName(), summerDressesTitle, "The title is not the same");
    }
    @Test(priority = 2)
    public void checkSortBy() throws InterruptedException {
        dressesPage.sortBy(sortByLowestFirst);
        Thread.sleep(Long.parseLong("5000"));
        assertEquals(dressesPage.checkDressesOrder().toString(), Arrays.toString(dressesNamesAfterSorting), "The dresses are not in the same order");
    }
    @Test(priority = 3)
    public void checkSelectDress(){
        dressesPage.chooseTheDress(dressNumber);
        assertEquals(dressesPage.checkIfDressIsSelected(), dressName, "Dress names are not the same");
    }
    @Test(priority = 4)
    public void checkSelectingColourAndSize(){
        dressesPage.chooseTheDressColour(wantedColour);
        dressesPage.setTheSize(wantedSize);
    }
    @Test(priority = 5)
    public void checkQuantity(){
        dressesPage.enterWantedQuantity(wantedQuantity);
        dressesPage.clickAddToCart();
        assertEquals(dressesPage.checkTheNumberOfDresses(), numberOfDresses, "Numbers are not the same");
    }
    @Test(priority = 6)
    public void checkColourAndSize(){
        assertEquals(dressesPage.checkTheColourAndSize(), dressColourAndSize, "Colour and Size are not the same");
    }
    @Test(priority = 7)
    public void checkPriceInCart(){
        cartPage = dressesPage.proceedToCheckOut();
        assertEquals(cartPage.getTotalPriceInCart(), priceOfTenDresses, "Prices are not the same");
    }
    @Test(priority = 8)
    public void checkProductNumber(){
        assertEquals(cartPage.checkShippingCart(), shippingCartContains, "Shipping cart number is not same");
    }
    @Test(priority = 9)
    public void checkColourAndSizeInCart(){
        assertEquals(cartPage.checkColourAndSizeInCart("1"), dressColourAndSizeInCart, "Colour and size do not match");
    }
    @Test(priority = 10)
    public void checkBillingAddress(){
        orderCheckOutPage = cartPage.clickCheckOutCart();
        assertEquals(orderCheckOutPage.checkBillingAddress(), billingAddress, "Billing address is not the same");
    }
    @Test(priority = 11)
    public void checkPriceBeforePayment(){
        orderCheckOutPage.clickProceed();
        orderCheckOutPage.clickAcceptTerms();
        orderCheckOutPage.clickProceed();
        assertEquals(orderCheckOutPage.checkTotalPriceBeforeChoosingPayment(), priceOfTenDresses, "The prices are not the same");
    }
    @Test(priority = 12)
    public void checkColourAndSizeBeforePayment(){
        assertEquals(orderCheckOutPage.checkColourAndSizeBeforePayment(), dressColourAndSizeInCart, "Colour and size do not match");
    }
    @Test(priority = 13)
    public void checkChequePaymentOption(){
        orderCheckOutPage.clickCheckPayment();
        assertEquals(orderCheckOutPage.checkOrderSummary(), orderSummary, "Order summary is not the same");
    }
    @Test(priority = 14)
    public void checkOrderConfirmation(){
        orderCheckOutPage.clickSubmitOrder();
        assertEquals(orderCheckOutPage.getOrderConfirmationCheck(), orderConfirmation, "Order confirmation is not the same");
    }
}
