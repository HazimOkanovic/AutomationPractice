package utils;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Utils {
    private WebDriver driver;
    private static By addToCartButton = By.xpath("//div//p//button[@name='Submit']");
    private static By plusButton = By.xpath("//a//span//i[@class='icon-plus']");
    private static By minusButton = By.xpath("//a//span//i[@class='icon-minus']");
    private static By selectDropDown = By.xpath("//div//div[@id='uniform-group_1']");
    private static By chooseTheSize = By.xpath("//div//div/select[@name='group_1']");

    public Utils(WebDriver driver){
        this.driver = driver;
    }
    public static void waitPresent(By element, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public static void takeScreenshot(ITestResult result, WebDriver driver) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("c://Users/Startklar/Documents/AutomationPractice/Resources/Screenshots/" + result.getName() + result.getEndMillis() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
    public static void hoverOverElements( By elementToHover, WebDriver driver){
        Actions actions = new Actions(driver);
        WebElement elementToHoverOver = driver.findElement(elementToHover);
        actions.moveToElement(elementToHoverOver).build().perform();
    }

    public static void decreaseProductNumberBy(By element, int number, WebDriver driver){
        waitPresent(element, driver);
        for (int i = 0; i<number; i++) {
            driver.findElement(element).click();
        }
    }
    public static void addToCart(By element, WebDriver driver){
        waitPresent(element, driver);
        driver.findElement(element).click();
    }
}
