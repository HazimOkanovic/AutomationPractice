package regressionTests.dressesTests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DressesPage;
import pages.OrderCheckOutPage;
import java.util.Arrays;
import static org.testng.Assert.assertEquals;

public class ChooseDressThenLoginTest extends BaseTest {
    DressesPage dressesPage;
    CartPage cartPage;
    OrderCheckOutPage orderCheckOutPage;

    private By dressesButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a");
    private String[] dressesNamesAfterSorting= {"Printed Chiffon Dress", "Printed Dress", "Printed Dress", "Printed Summer Dress", "Printed Summer Dress"};
    private String categoryName = "DRESSES ";
    private String dressName = "Printed Dress";
    private String dressColourAndSize = "Beige, M";
    private String totalPriceOfDresses = "$409.92";
    private String shippingCartContains = "Your shopping cart contains: 8 Products";
    private String alreadyRegistered = "ALREADY REGISTERED?";
    private String email = "private.pitanje@outlook.com";
    private String password = "Hazim123";
    private String emailPath = "email";
    private String passwordPath = "passwd";
    private String sortByName = "Product Name: Z to A";
    private String dressNumber = "3";
    private String wantedColour = "Beige";
    private String wantedSize = "M";
    private String colourAndSizeInCart = "Color : Beige, Size : M";
    private String wantedQuantity = "8";
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
    public void checkClickOnDresses(){
        dressesPage = homePage.clickDresses();
        assertEquals(dressesPage.checkCategoryName(), categoryName, "The click did not work");
    }
    @Test(priority = 1)
    public void checkSortBy() throws InterruptedException {
        dressesPage.sortBy(sortByName);
        Thread.sleep(Long.parseLong("8000"));
        assertEquals(dressesPage.checkDressesOrder().toString(), Arrays.toString(dressesNamesAfterSorting), "Sorting was not successful");
    }
    @Test(priority = 2)
    public void checkSelectTheDress(){
        dressesPage.chooseTheDress(dressNumber);
        assertEquals(dressesPage.checkIfDressIsSelected(), dressName, "The name is not correct");
    }
    @Test(priority = 3)
    public void checkSettingColourAndSize(){
        dressesPage.chooseTheDressColour(wantedColour);
        dressesPage.setTheSize(wantedSize);
    }
    @Test(priority = 4)
    public void checkQuantity(){
        dressesPage.enterWantedQuantity(wantedQuantity);
        dressesPage.clickAddToCart();
        assertEquals(dressesPage.checkTheNumberOfDresses(), wantedQuantity, "Numbers are not the same");
    }
    @Test(priority = 5)
    public void checkColourAndSize(){
        assertEquals(dressesPage.checkTheColourAndSize(), dressColourAndSize, "Colour and size do not match");
    }
    @Test(priority = 6)
    public void checkPriceInCart(){
        cartPage = dressesPage.proceedToCheckOut();
        assertEquals(cartPage.getTotalPriceInCart(), totalPriceOfDresses);
    }
    @Test(priority = 7)
    public void checkTheProductNumberInCart(){
        assertEquals(cartPage.checkShippingCart(), shippingCartContains, "The product numbers are not the same");
    }
    @Test(priority = 8)
    public void checkColourAndSizeInCart(){
        assertEquals(cartPage.checkColourAndSizeInCart("1"), colourAndSizeInCart, "Colour and Size are not the same");
    }
    @Test(priority = 8)
    public void checkLogInPage(){
        orderCheckOutPage = cartPage.clickCheckOutCart();
        assertEquals(orderCheckOutPage.checkAlreadyRegistered(), alreadyRegistered, "The message is not there");
    }
    @Test(priority = 9)
    public void checkLogIn(){
        orderCheckOutPage.enterLoginData(email, emailPath);
        orderCheckOutPage.enterLoginData(password, passwordPath);
        orderCheckOutPage.clickSignIn();
        assertEquals(orderCheckOutPage.checkBillingAddress(), billingAddress, "Billing address is not correct");
    }
    @Test(priority = 10)
    public void checkPriceBeforePayment(){
        orderCheckOutPage.clickProceed();
        orderCheckOutPage.clickAcceptTerms();
        orderCheckOutPage.clickProceed();
        assertEquals(orderCheckOutPage.checkTotalPriceBeforeChoosingPayment(), totalPriceOfDresses, "Prices do not match");
    }
    @Test(priority = 11)
    public void checkDressColourAndSizeBeforePayment(){
        assertEquals(orderCheckOutPage.checkColourAndSizeBeforePayment(), colourAndSizeInCart, "Size and colour do not match");
    }
    @Test(priority = 12)
    public void checkBankWireInfoBeforePayment(){
        orderCheckOutPage.clickBankWire();
        assertEquals(orderCheckOutPage.checkOrderSummary(), orderSummary, "Order summary is not the same");
    }
    @Test(priority = 13)
    public void checkCompletedOrder(){
        orderCheckOutPage.clickSubmitOrder();
        assertEquals(orderCheckOutPage.getOrderConfirmationBankWire(), orderConfirmation, "Order confirmation is not the same");
    }
}
