package selenium;

import enums.NavegadorEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriveManager {

    private WebDriver driver;

    public DriveManager(NavegadorEnum navegador, String url){
        selecionarNavegador(navegador);
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void selecionarNavegador(NavegadorEnum navegador) {
        if(navegador == NavegadorEnum.CHROME)
        {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (navegador == NavegadorEnum.FIREFOX) {
            System.setProperty("webdriver.gecko.driver","src\\main\\resources\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else{
            System.setProperty("webdriver.edge.driver","src\\main\\resources\\driver\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
    }

    public void finalizarSessao() {
        driver.quit();
    }

}
