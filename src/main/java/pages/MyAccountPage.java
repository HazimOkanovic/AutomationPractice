package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.Utils.hoverOverElements;
import static utils.Utils.waitPresent;

public class MyAccountPage {
    private static WebDriver driver;
    private By myAddressButton = By.xpath("//div//li//a[@title='Addresses']");
    private By logoButton = By.xpath("//div//a[@title='My Store']//img[@class='logo img-responsive']");
    private By cartButton = By.xpath("//div//a[@title='View my shopping cart']");
    private By dressesButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a");
    private By casualDresses = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[1]/a");
    private By eveningDresses = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[2]/a");
    private By summerDresses = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[3]/a");
    private By logOutButton = By.xpath("//div//a[@title='Log me out']");
    private By signInButton = By.xpath("//div//a[@class='login']");
    private By welcomeToAccount = By.xpath("//div//p[@class='info-account']");

    public MyAccountPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickAddressButton(){
        waitPresent(myAddressButton, driver);
        driver.findElement(myAddressButton).click();
    }
    public String getInfo(String path){
        String elementLocation = "//div//ul//li//span[@class='address_%s']";
        elementLocation = String.format(elementLocation, path);
        waitPresent(By.xpath(elementLocation), driver);
        return driver.findElement(By.xpath(elementLocation)).getText();
    }
    public void clickLogoToGoHome(){
        waitPresent(logoButton, driver);
        driver.findElement(logoButton).click();
    }
    public void clickSignOut(){
        waitPresent(logOutButton, driver);
        driver.findElement(logOutButton).click();
    }
    public void hoverOverDresses(){
        waitPresent(dressesButton, driver);
        hoverOverElements(dressesButton, driver);
    }
    public String checkSignOut(){
        waitPresent(signInButton, driver);
        return driver.findElement(signInButton).getText();
    }
    public String checkLogIn(){
        waitPresent(welcomeToAccount, driver);
        return driver.findElement(welcomeToAccount).getText();
    }
    public DressesPage clickEveningDressesAfterHover(){
        waitPresent(eveningDresses, driver);
        driver.findElement(eveningDresses).click();
        return new DressesPage(driver);
    }
    public DressesPage clickSummerDressesAfterHover(){
        waitPresent(summerDresses, driver);
        driver.findElement(summerDresses).click();
        return new DressesPage(driver);
    }
}
