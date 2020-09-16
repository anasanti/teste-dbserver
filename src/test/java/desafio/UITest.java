package desafio;

import org.junit.Before;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.DriveManager;

public class UITest {
    private DriveManager driver;
    private SimuladorUI simulador;
    private WebDriverWait wait;

    @Before
    public void setUp() {

        driver = new DriveManager("Chrome");
        wait = new WebDriverWait(driver.getDriver(), 5);
        simulador = new SimuladorUI(driver.getDriver(), wait);
    }

    @test



}
