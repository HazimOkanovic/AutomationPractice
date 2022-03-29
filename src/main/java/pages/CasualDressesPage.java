package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static utils.Utils.*;

public class CasualDressesPage {
    private WebDriver driver;
    private By theOnlyCasualDressToCart = By.xpath("//div//h5//a[@title='Printed Dress']");
    private By addToCartButton = By.xpath("//div//p//button[@name='Submit']");
    private By selectDropDown = By.xpath("//div//div[@id='uniform-group_1']");
    private By chooseTheSize = By.xpath("//div//div/select[@name='group_1']");
    private By proceedToCheckOutButton = By.xpath("//div//a[@title='Proceed to checkout']");
    private By casualDressesHeading = By.xpath("//div//div[@class='rte']");
    private By casualDressAdded = By.xpath("//div//h2//span[@class='ajax_cart_product_txt ']");
    private By quantityField = By.xpath("//p//input[@id= 'quantity_wanted']");
    private By checkTheColourAndSize = By.xpath("//div//span[@id='layer_cart_product_attributes']");
    private By continueShoppingButton = By.xpath("//div//span[@title='Continue shopping']");

    public CasualDressesPage(WebDriver driver){
        this.driver = driver;
    }

    public void selectTheOnlyDress(){
        waitPresent(theOnlyCasualDressToCart, driver);
        driver.findElement(theOnlyCasualDressToCart).click();
    }
    public void enterWantedQuantity(String quantity) {
        waitPresent(quantityField, driver);
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(quantity);
    }
    public void setTheSize(String sizeValue){
        waitPresent(selectDropDown, driver);
        driver.findElement(selectDropDown).click();
        Select select = new Select(driver.findElement(chooseTheSize));
        select.selectByValue(sizeValue);
    }
    public void clickAddToCart(){
        waitPresent(addToCartButton, driver);
        addToCart(addToCartButton, driver);
    }
    public void clickContinueShopping(){
        waitPresent(continueShoppingButton, driver);
        driver.findElement(continueShoppingButton).click();
    }
    public String checkAddingToCart(){
        waitPresent(casualDressAdded, driver);
        return driver.findElement(casualDressAdded).getText();
    }
    public String checkTheColourAndSize(){
        waitPresent(checkTheColourAndSize,driver);
        return driver.findElement(checkTheColourAndSize).getText();
    }
    public String checkCasualDressesHeading(){
        waitPresent(casualDressesHeading, driver);
        return driver.findElement(casualDressesHeading).getText();
    }
    public CartPage proceedToCheckOut(){
        waitPresent(proceedToCheckOutButton, driver);
        driver.findElement(proceedToCheckOutButton).click();
        return new CartPage(driver);
    }
}
