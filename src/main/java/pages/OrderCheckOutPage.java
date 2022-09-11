package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static utils.Utils.waitPresent;

public class OrderCheckOutPage {
    private WebDriver driver;
    private By acceptProceedCheckOutButton = By.xpath("//p//button[@type='submit']");
    private By billingAddress = By.xpath("//div//ul[@id='address_invoice']");
    private By termsOfServiceCheckBox = By.xpath("//p//div[@id='uniform-cgv']");
    private By bankWirePayment = By.xpath("//div//p//a[@class='bankwire']");
    private By checkPayment = By.xpath("//div//p//a[@class='cheque']");
    private By confirmOrderButton = By.xpath("//p//button[@type = 'submit']");
    private By totalPriceBeforePayment = By.xpath("//tr//td//span[@id= 'total_price']");
    private By orderConfirmationCheck = By.xpath("//div//p[@class= 'alert alert-success']");
    private By orderConfirmationBankWire = By.xpath("//div//p//strong[@class= 'dark']");
    private By alreadyRegistered = By.xpath("//*[@id=\"login_form\"]/h3");
    private By submitButton = By.xpath("//p//button[@id='SubmitLogin']");
    private By orderSummary = By.xpath("//div//p//strong[@class='dark']");
    private By colourAndSizeBeforePayment = By.xpath("//td//small//a");

    public OrderCheckOutPage(WebDriver driver){
        this.driver = driver;
    }

    public String checkBillingAddress(){
        waitPresent(billingAddress, driver);
       return driver.findElement(billingAddress).getText();
    }
    public void enterLoginData(String data, String path) {
        String elementPath = "//input[@id = '%s']";
        elementPath = String.format(elementPath, path);
        waitPresent(By.xpath(elementPath), driver);
        driver.findElement(By.xpath(elementPath)).sendKeys(data);
    }
    public void clickProceed(){
        waitPresent(acceptProceedCheckOutButton, driver);
        driver.findElement(acceptProceedCheckOutButton).click();
    }
    public void clickAcceptTerms(){
        waitPresent(termsOfServiceCheckBox, driver);
        driver.findElement(termsOfServiceCheckBox).click();
    }
    public void clickSignIn(){
        waitPresent(submitButton, driver);
        driver.findElement(submitButton).click();
    }
    public void clickBankWire(){
        waitPresent(bankWirePayment, driver);
        driver.findElement(bankWirePayment).click();
    }
    public void clickCheckPayment(){
        waitPresent(checkPayment, driver);
        driver.findElement(checkPayment).click();
    }
    public String checkOrderSummary(){
        waitPresent(orderSummary, driver);
        return driver.findElement(orderSummary).getText();
    }
    public String checkColourAndSizeBeforePayment(){
        waitPresent(colourAndSizeBeforePayment, driver);
        return driver.findElement(colourAndSizeBeforePayment).getText();
    }
    public void clickSubmitOrder(){
        waitPresent(confirmOrderButton, driver);
        driver.findElement(confirmOrderButton).click();
    }
    public String checkTotalPriceBeforeChoosingPayment(){
        waitPresent(totalPriceBeforePayment, driver);
        return driver.findElement(totalPriceBeforePayment).getText();
    }
    public String checkAlreadyRegistered(){
        waitPresent(alreadyRegistered, driver);
        return driver.findElement(alreadyRegistered).getText();
    }
    public String getOrderConfirmationCheck(){
        waitPresent(orderConfirmationCheck, driver);
        return driver.findElement(orderConfirmationCheck).getText();
    }
    public String getOrderConfirmationBankWire(){
        waitPresent(orderConfirmationBankWire, driver);
        return driver.findElement(orderConfirmationBankWire).getText();
    }
}
