package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static utils.Utils.waitPresent;

public class LogInPage {
    private WebDriver driver;
    private By submitButton = By.xpath("//p//button[@id='SubmitLogin']");
    private By errorMessageForgotPasswd = By.xpath("//div[@class='alert alert-danger']//ol//li");
    private By messageForgotPassWithAccount = By.xpath("//div//p[@class='alert alert-success']");
    private By forgotPassButton = By.xpath("//div//p[@class='lost_password form-group']//a[@title='Recover your forgotten password']");
    private By forgotPassEnterEmail = By.xpath("//fieldset//div//input[@id='email']");
    private By retrievePassButton = By.xpath("//fieldset//p//button[@type='submit']");
    private By emailCreateAccount = By.xpath("//div//input[@id='email_create']");
    private By emailCreateErrorMessage = By.xpath("//div//ol//li");
    private By createAccountButton = By.xpath("//div//button[@id='SubmitCreate']");

    public LogInPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterLoginData(String data, String path) {
        String elementPath = "//input[@id = '%s']";
        elementPath = String.format(elementPath, path);
        waitPresent(By.xpath(elementPath), driver);
        driver.findElement(By.xpath(elementPath)).sendKeys(data);
    }
    public String checkErrorForgotPassNoAccount(){
        waitPresent(errorMessageForgotPasswd, driver);
        return driver.findElement(errorMessageForgotPasswd).getText();
    }
    public String checkMessageForgotPasswdWithAccount() {
        waitPresent(messageForgotPassWithAccount, driver);
        return driver.findElement(messageForgotPassWithAccount).getText();
    }
    public void clickForgotPass(){
        waitPresent(forgotPassButton, driver);
        driver.findElement(forgotPassButton).click();
    }
    public void enterEmailRetrievePass(String email){
        waitPresent(forgotPassEnterEmail, driver);
        driver.findElement(forgotPassEnterEmail).sendKeys(email);
    }
    public void clickRetrieveButton(){
        waitPresent(retrievePassButton, driver);
        driver.findElement(retrievePassButton).click();
    }
    public void enterEmailCreateAccount(String email){
        waitPresent(emailCreateAccount, driver);
        driver.findElement(emailCreateAccount).sendKeys(email);
    }
    public String checkErrorCreateAccount(){
        waitPresent(emailCreateErrorMessage, driver);
        return driver.findElement(emailCreateErrorMessage).getText();
    }
    public NewAccountPage clickCreateAccount(){
        waitPresent(createAccountButton, driver);
        driver.findElement(createAccountButton).click();
        return new NewAccountPage(driver);
    }
    public MyAccountPage clickSignIn(){
        waitPresent(submitButton, driver);
        driver.findElement(submitButton).click();
        return new MyAccountPage(driver);
    }
}
