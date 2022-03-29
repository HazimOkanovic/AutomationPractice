package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import static utils.Utils.addToCart;
import static utils.Utils.waitPresent;

public class SummerDressesPage {
    private WebDriver driver;
    private By addToCartButton = By.xpath("//div//p//button[@name='Submit']");
    private By selectDropDown = By.xpath("//div//div[@id='uniform-group_1']");
    private By chooseTheSize = By.xpath("//div//div/select[@name='group_1']");
    private By proceedToCheckOutButton = By.xpath("//div//a[@title='Proceed to checkout']");
    private By summerDressesHeading = By.xpath("//h1//span[@class='cat-name']");
    private By quantityField = By.xpath("//p//input[@id= 'quantity_wanted']");
    private By checkTheColourAndSize = By.xpath("//div//span[@id='layer_cart_product_attributes']");
    private By continueShoppingButton = By.xpath("//div//span[@title='Continue shopping']");
    private By checkName = By.xpath("//div//h1[@itemprop='name']");
    private By numberOfDresses = By.xpath("//div//div//span[@id='layer_cart_product_quantity']");

    public SummerDressesPage(WebDriver driver){
        this.driver = driver;
    }

    public void chooseTheDress(String dressNumber){
        String dresses = "//*[@id=\"center_column\"]/ul/li[%s]/div/div[2]/h5/a";
        dresses = String.format(dresses, dressNumber);
        waitPresent(By.xpath(dresses), driver);
        driver.findElement(By.xpath(dresses)).click();
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
    public void chooseTheColour(String wantedColour){
        String colour = "//div//ul//li//a[@title='%s']";
        colour = String.format(colour, wantedColour);
        waitPresent(By.xpath(colour), driver);
        driver.findElement(By.xpath(colour)).click();
    }
    public void clickAddToCart(){
        waitPresent(addToCartButton, driver);
        addToCart(addToCartButton, driver);
    }
    public void clickContinueShopping(){
        waitPresent(continueShoppingButton, driver);
        driver.findElement(continueShoppingButton).click();
    }
    public String checkIfDressIsSelected() {
        waitPresent(checkName, driver);
        return driver.findElement(checkName).getText();
    }
    public String checkTheNumberOfDresses(){
        waitPresent(numberOfDresses, driver);
        return driver.findElement(numberOfDresses).getText();
    }
    public String checkTheColourAndSize(){
        waitPresent(checkTheColourAndSize,driver);
        return driver.findElement(checkTheColourAndSize).getText();
    }
    public String checkSummerDressesHeading(){
      waitPresent(summerDressesHeading, driver);
       return driver.findElement(summerDressesHeading).getText();
    }
    public CartPage proceedToCheckOut(){
        waitPresent(proceedToCheckOutButton, driver);
        driver.findElement(proceedToCheckOutButton).click();
        return new CartPage(driver);
    }
}
