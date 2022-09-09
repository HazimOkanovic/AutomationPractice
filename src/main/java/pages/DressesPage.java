package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.addToCart;
import static utils.Utils.waitPresent;

public class DressesPage {
    private WebDriver driver;
    private By addToCartButton = By.xpath("//div//p//button[@name='Submit']");
    private By selectDropDown = By.xpath("//div//div[@id='uniform-group_1']");
    private By chooseTheSize = By.xpath("//div//div/select[@name='group_1']");
    private By proceedToCheckOutButton = By.xpath("//div//a[@title='Proceed to checkout']");
    private By casualDressAdded = By.xpath("//div//h2//span[@class='ajax_cart_product_txt ']");
    private By quantityField = By.xpath("//p//input[@id= 'quantity_wanted']");
    private By checkTheColourAndSize = By.xpath("//div//span[@id='layer_cart_product_attributes']");
    private By continueShoppingButton = By.xpath("//div//span[@title='Continue shopping']");
    private By theOnlyEveningDressToCart = By.xpath("//div//h5//a[@title='Printed Dress']");
    private By checkDressName = By.xpath("//div//h1[@itemprop='name']");
    private By numberOfDresses = By.xpath("//div//div//span[@id='layer_cart_product_quantity']");
    private By categoryName = By.xpath("//h1//span[@class='cat-name']");
    private By summerDressesHeading = By.xpath("//h1//span[@class='cat-name']");
    private By selectSortBy = By.xpath("//form//div//div[@id='uniform-selectProductSort']");
    private By sortByOptions = By.xpath("//form//div//div//select[@id='selectProductSort']");
    private By dressesNamesList = By.xpath("//div//div[@class = 'right-block']//h5//a[@class='product-name']");

    public DressesPage(WebDriver driver){
        this.driver = driver;
    }
    public void chooseTheDress(String dressNumber){
        String dresses = "//*[@id=\"center_column\"]/ul/li[%s]/div/div[2]/h5/a";
        dresses = String.format(dresses, dressNumber);
        waitPresent(By.xpath(dresses), driver);
        driver.findElement(By.xpath(dresses)).click();
    }
    public void sortBy(String text){
        waitPresent(selectSortBy, driver);
        driver.findElement(selectSortBy).click();
        WebElement element = driver.findElement(sortByOptions);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public void enterWantedQuantity(String quantity) {
        waitPresent(quantityField, driver);
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(quantity);
    }
    public void setTheSize(String size){
        waitPresent(selectDropDown, driver);
        driver.findElement(selectDropDown).click();
        Select select = new Select(driver.findElement(chooseTheSize));
        select.selectByVisibleText(size);
    }
    public void clickAddToCart(){
        waitPresent(addToCartButton, driver);
        addToCart(addToCartButton, driver);
    }
    public void clickContinueShopping(){
        waitPresent(continueShoppingButton, driver);
        driver.findElement(continueShoppingButton).click();
    }
    public List<String> checkDressesOrder(){
        waitPresent(dressesNamesList, driver);
        List<WebElement> myList=driver.findElements(dressesNamesList);
        List<String> all_elements_text=new ArrayList<>();
        for(int i=0; i<myList.size(); i++) {
            all_elements_text.add(myList.get(i).getText());
        }
        return all_elements_text;
    }
    public String checkTheColourAndSize(){
        waitPresent(checkTheColourAndSize,driver);
        return driver.findElement(checkTheColourAndSize).getText();
    }
    public String checkIfDressIsSelected(){
        waitPresent(checkDressName, driver);
        return driver.findElement(checkDressName).getText();
    }
    public String checkTheNumberOfDresses(){
        waitPresent(numberOfDresses, driver);
        return driver.findElement(numberOfDresses).getText();
    }
    public String checkCategoryName(){
        waitPresent(categoryName, driver);
        return driver.findElement(categoryName).getText();
    }
    public void chooseTheDressColour(String wantedColour){
        String colour = "//div//ul//li//a[@title='%s']";
        colour = String.format(colour, wantedColour);
        waitPresent(By.xpath(colour), driver);
        driver.findElement(By.xpath(colour)).click();
    }
    public CartPage proceedToCheckOut(){
        waitPresent(proceedToCheckOutButton, driver);
        driver.findElement(proceedToCheckOutButton).click();
        return new CartPage(driver);
    }
}
