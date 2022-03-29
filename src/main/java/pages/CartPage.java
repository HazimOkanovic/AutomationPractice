package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static utils.Utils.waitPresent;

public class CartPage {
    private WebDriver driver;
    private By dressNameInCart = By.xpath("//td//p[@class='product-name']");
    private By quantityField = By.xpath("//td//input[@type= 'text']");
    private By totalPrice = By.xpath("//tr//td//span[@id='total_price']");
    private By proceedToCheckOutButton = By.xpath("//p//a[@class='button btn btn-default standard-checkout button-medium']");
    private By emptyCartMessage = By.xpath("//div//p[@class='alert alert-warning']");
    private By shippingCartContains = By.xpath("//div//h1//span[@class='heading-counter']");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public String getDressName(){
        waitPresent(dressNameInCart, driver);
        return driver.findElement(dressNameInCart).getText();
    }
    public void clickTrashButtonCart(String buttonNumber){
        String trashButton = "/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr[%s]/td[7]/div/a/i";
        trashButton = String.format(trashButton, buttonNumber);
        waitPresent(By.xpath(trashButton), driver);
        driver.findElement(By.xpath(trashButton)).click();
    }
    public void increaseQuantity(String quantity) {
        waitPresent(quantityField, driver);
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(quantity);
    }
    public String checkEmptyCart(){
        waitPresent(emptyCartMessage, driver);
        return driver.findElement(emptyCartMessage).getText();
    }
    public String getTotalPriceInCart(){
        waitPresent(totalPrice, driver);
        return driver.findElement(totalPrice).getText();
    }
    public String checkShippingCart(){
        waitPresent(shippingCartContains, driver);
        return driver.findElement(shippingCartContains).getText();
    }
    public String checkColourAndSizeInCart(String itemWanted){
        String colourAndSizeInCart = "/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr[%s]/td[2]/small[2]/a";
        colourAndSizeInCart = String.format(colourAndSizeInCart, itemWanted);
        waitPresent(By.xpath(colourAndSizeInCart), driver);
        return driver.findElement(By.xpath(colourAndSizeInCart)).getText();
    }
    public OrderCheckOutPage clickCheckOutCart(){
        waitPresent(proceedToCheckOutButton, driver);
        driver.findElement(proceedToCheckOutButton).click();
        return new OrderCheckOutPage(driver);
    }
}
