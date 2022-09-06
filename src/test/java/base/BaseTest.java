package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import static utils.Utils.takeScreenshot;

public class BaseTest{
    WebDriver driver;
   public HomePage homePage;
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }
    @AfterMethod
    public void screenshot(ITestResult testResult){
        takeScreenshot(testResult, driver);
    }
    @AfterClass
    public void driverQuit(){
        driver.quit();
    }
}






