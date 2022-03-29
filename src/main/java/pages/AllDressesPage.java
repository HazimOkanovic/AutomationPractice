package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static utils.Utils.waitPresent;

public class AllDressesPage {
    private WebDriver driver;
    private By checkDresses = By.xpath("//div//h1//span[@class='heading-counter']");
    private By selectSortBy = By.xpath("//form//div//div[@id='uniform-selectProductSort']");
    private By sortByOptions = By.xpath("//form//div//div//select[@id='selectProductSort']");
    private By continueShoppingButton = By.xpath("//div//span[@title='Continue shopping']");
    private By addToCartButton = By.xpath("//div//p//button[@name='Submit']");
    private By proceedToCheckOutButton = By.xpath("//div//a[@title='Proceed to checkout']");
    private By checkName = By.xpath("//div//h1[@itemprop='name']");
    private By checkFirstAdding = By.xpath("//div//h2//span[@class='ajax_cart_product_txt ']");
    private By checkSecondAdding = By.xpath("//div//h2//span[@style='display: inline;']");


    public AllDressesPage(WebDriver driver){
        this.driver = driver;
    }

    public void sortBy(String value){
        waitPresent(selectSortBy, driver);
        driver.findElement(selectSortBy).click();
        WebElement element = driver.findElement(sortByOptions);
        Select select = new Select(element);
        select.selectByValue(value);
    }
    public void selectDress(String dressNumber){
        String dresses = "//*[@id=\"center_column\"]/ul/li[%s]/div/div[2]/h5/a";
        dresses = String.format(dresses, dressNumber);
        waitPresent(By.xpath(dresses), driver);
        driver.findElement(By.xpath(dresses)).click();
    }
    public void clickAddToCart(){
        waitPresent(addToCartButton, driver);
        driver.findElement(addToCartButton).click();
    }
    public void clickContinueShopping(){
        waitPresent(continueShoppingButton, driver);
        driver.findElement(continueShoppingButton).click();
    }
    public String checkIfDressesClicked(){
        waitPresent(checkDresses, driver);
        return driver.findElement(checkDresses).getText();
    }
    public String checkNameOfSelectedDress() {
        waitPresent(checkName, driver);
        return driver.findElement(checkName).getText();
    }
    public String checkIfFirstAddedToCart(){
        waitPresent(checkFirstAdding, driver);
        return driver.findElement(checkFirstAdding).getText();
    }
    public String checkIfSecondAddedToCart(){
        waitPresent(checkSecondAdding, driver);
        return driver.findElement(checkSecondAdding).getText();
    }
}
