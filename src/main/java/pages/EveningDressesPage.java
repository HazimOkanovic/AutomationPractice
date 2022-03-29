package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import static utils.Utils.*;
import static utils.Utils.waitPresent;

public class EveningDressesPage {
    private WebDriver driver;
    private By theOnlyEveningDressToCart = By.xpath("//div//h5//a[@title='Printed Dress']");
    private By addToCartButton = By.xpath("//div//p//button[@name='Submit']");
    private By selectDropDown = By.xpath("//div//div[@id='uniform-group_1']");
    private By chooseTheSize = By.xpath("//div//div/select[@name='group_1']");
    private By proceedToCheckOutButton = By.xpath("//div//a[@title='Proceed to checkout']");
    private By eveningDressesHeading = By.xpath("//div//h1//span[@class= 'cat-name']");
    private By casualDressAdded = By.xpath("//div//h2//span[@class='ajax_cart_product_txt ']");
    private By quantityField = By.xpath("//p//input[@id= 'quantity_wanted']");
    private By checkTheColourAndSize = By.xpath("//div//span[@id='layer_cart_product_attributes']");
    private By continueShoppingButton = By.xpath("//div//span[@title='Continue shopping']");
    private By checkDressName = By.xpath("//div//h1[@itemprop='name']");
    private By numberOfDresses = By.xpath("//div//div//span[@id='layer_cart_product_quantity']");

    public EveningDressesPage(WebDriver driver){
        this.driver = driver;
    }

    public void selectTheOnlyDress(){
        waitPresent(theOnlyEveningDressToCart, driver);
        driver.findElement(theOnlyEveningDressToCart).click();
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
    public String checkIfDressIsSelected(){
        waitPresent(checkDressName, driver);
        return driver.findElement(checkDressName).getText();
    }
    public String checkTheNumberOfDresses(){
        waitPresent(numberOfDresses, driver);
        return driver.findElement(numberOfDresses).getText();
    }
    public String checkTheColourAndSize(){
        waitPresent(checkTheColourAndSize,driver);
        return driver.findElement(checkTheColourAndSize).getText();
    }
    public String checkCasualDressesHeading(){
        waitPresent(eveningDressesHeading, driver);
        return driver.findElement(eveningDressesHeading).getText();
    }
    public CartPage proceedToCheckOut(){
        waitPresent(proceedToCheckOutButton, driver);
        driver.findElement(proceedToCheckOutButton).click();
        return new CartPage(driver);
    }
}
