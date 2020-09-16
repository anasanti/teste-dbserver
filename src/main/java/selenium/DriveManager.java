package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriveManager {
    private WebDriver driver;

    public DriveManager(String browserName){
        chooseBrowser(browserName);
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php?");
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void chooseBrowser(String browserName) {
        if(browserName.equalsIgnoreCase("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver","src\\main\\resources\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else{
            System.setProperty("webdriver.edge.driver","src\\main\\resources\\driver\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
    }

    public void endSession() {
        driver.quit();
    }

}
