package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utils.Utils.hoverOverElements;
import static utils.Utils.waitPresent;

public class HomePage {
    private WebDriver driver;
    private By signInButton = By.xpath("//div[@class='header_user_info']//a[@class='login']");
    private By searchButton = By.xpath("//form//button[@name= 'submit_search']");
    private By searchField = By.xpath("//div//form//input[@id= 'search_query_top']");
    private By cartButtonOnHome = By.xpath("//div//a[@title='View my shopping cart']");
    private By xButtonInCart = By.xpath("//dt//span[@class='remove_link']");
    private By searchCasualDressResult = By.xpath("//div//h1//span[@class='heading-counter']");
    private By popularOnHome = By.xpath("//div//ul//li//a[@data-toggle= 'tab']");
    private By dressesButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a");

    public HomePage(WebDriver driver){
            this.driver = driver;
        }

    public LogInPage clickSignIn(){
        waitPresent(signInButton, driver);
        driver.findElement(signInButton).click();
        return new LogInPage(driver);
    }
    public void clickToEnterText(){
        waitPresent(searchField, driver);
        WebElement element = driver.findElement(searchField);
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", element);
    }
    public void enterTextInSearch(String searchFor){
        waitPresent(searchField, driver);
        WebElement element = driver.findElement(searchField);
        String js = "arguments[0].setAttribute('value','"+searchFor+"')";
        ((JavascriptExecutor) driver).executeScript(js, element);
    }
    public void hoverOverCart(){
        waitPresent(cartButtonOnHome, driver);
        hoverOverElements(cartButtonOnHome, driver);
    }
    public void clickOnXInCart(){
        waitPresent(xButtonInCart, driver);
        driver.findElement(xButtonInCart).click();
    }
    public String checkIfOnHome(){
        waitPresent(popularOnHome, driver);
        return driver.findElement(popularOnHome).getText();
    }
    public String checkEmptyCart(){
        waitPresent(cartButtonOnHome, driver);
        return driver.findElement(cartButtonOnHome).getText();
    }
    public String checkSearchResult(){
        waitPresent(searchCasualDressResult, driver);
        return driver.findElement(searchCasualDressResult).getText();
    }
    public DressesPage clickDresses(){
        waitPresent(dressesButton, driver);
        driver.findElement(dressesButton).click();
        return new DressesPage(driver);
    }
    public DressesPage clickSearchButton(){
        waitPresent(searchButton, driver);
        WebElement element = driver.findElement(searchButton);
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", element);
        return new DressesPage(driver);
    }
}



