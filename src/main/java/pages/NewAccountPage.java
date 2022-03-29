package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static utils.Utils.waitPresent;

public class NewAccountPage {
    private WebDriver driver;
    private By maleGender = By.xpath("//div[@id='uniform-id_gender1']");
    private By statesClick = By.xpath("//p//div[@id='uniform-id_state']");
    private By statesSelect = By.xpath("//p//div//select[@id='id_state']");
    private By additionalInfo = By.xpath("//p//textarea[@name='other']");
    private By registerButton = By.xpath("//div//button[@name='submitAccount']");
    private By myAccountLocator = By.xpath("//div[@class='col-xs-12 col-sm-6 col-lg-4']");
    private By errorTextField = By.xpath("//div[@class='alert alert-danger']");

    public NewAccountPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickMr(){
        waitPresent(maleGender, driver);
        driver.findElement(maleGender).click();
    }
    public void enterNameSurnamePassword(String nameSurnamePassword, String path){
        String elementLocation = "//div//input[@name='%s']";
        elementLocation = String.format(elementLocation, path);
        waitPresent(By.xpath(elementLocation), driver);
        driver.findElement(By.xpath(elementLocation)).sendKeys(nameSurnamePassword);
    }
    public void selectDateOfBirth(String time, String path){
        String clickElementLocation = "//div[@class='row']//div[@id='uniform-%s']";
        clickElementLocation = String.format(clickElementLocation, path);
        waitPresent(By.xpath(clickElementLocation), driver);
        driver.findElement(By.xpath(clickElementLocation)).click();
        String dropDownSelection = "//div[@class='row']//div//select[@id='%s']";
        dropDownSelection = String.format(dropDownSelection, path);
        Select select = new Select(driver.findElement(By.xpath(dropDownSelection)));
        select.selectByValue(time);
    }
    public void enterCompanyAddressesCityZip(String data, String path){
        String elementLocation = "//div//p//input[@name='%s']";
        elementLocation = String.format(elementLocation, path);
        waitPresent(By.xpath(elementLocation), driver);
        driver.findElement(By.xpath(elementLocation)).sendKeys(data);
    }
    public void selectState(){
        waitPresent(statesClick, driver);
        driver.findElement(statesClick).click();
        Select select = new Select(driver.findElement(statesSelect));
        select.selectByValue("9");
    }
    public void addAdditionalInfo(String addInfo){
        waitPresent(additionalInfo, driver);
        driver.findElement(additionalInfo).sendKeys(addInfo);
    }
    public void addPhonesAndAssignAddress(String data, String path){
        String elementLocation = "//p//input[@name='%s']";
        elementLocation = String.format(elementLocation, path);
        waitPresent(By.xpath(elementLocation), driver);
        driver.findElement(By.xpath(elementLocation)).sendKeys(data);
    }
    public String checkError(){
        waitPresent(errorTextField, driver);
        return driver.findElement(errorTextField).getText();
    }
    public boolean checkSuccess() {
        waitPresent(myAccountLocator, driver);
        if (driver.findElement(myAccountLocator).isDisplayed()) {
            return true;
        }
        else{
            return false;
        }
    }
    public MyAccountPage clickRegister(){
        waitPresent(registerButton, driver);
        driver.findElement(registerButton).click();
        return new MyAccountPage(driver);
    }
}
