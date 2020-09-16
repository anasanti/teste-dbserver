package desafio;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimuladorUI {
    private WebDriver driver;
    private WebDriverWait wait;

    private by produto1 = by.data-id-product(1);
    private by produto2 = by.data-id-product(2);
    private by produto3 = by.data-id-product(3);
    private by produto4 = by.data-id-product(4);
    private by produto5 = by.data-id-product(5);
    private by produto6 = by.data-id-product(6);
    private by produto7 = by.data-id-product(7);
    private by produtoaadicionarnocarrinho - by.class="clearfix";

    public SimuladorUI(WebDriver drive, WebDriverWait wait) {
        this.driver = drive;
        this.wait = wait;
    }

    public SelecionarProduto1(){
        wait.until(ExpectedConditions.elementToBeClickable(produto1));
        driver.findElements(produto1).get(0).click();
    }

    public SelecionarProduto2(){
        wait.until(ExpectedConditions.elementToBeClickable(produto2));
        driver.findElements(produto2).get(0).click();
    }

    public SelecionarProduto3(){
        wait.until(ExpectedConditions.elementToBeClickable(produto3));
        driver.findElements(produto3).get(0).click();
    }

    public SelecionarProduto4(){
        wait.until(ExpectedConditions.elementToBeClickable(produto4));
        driver.findElements(produto4).get(0).click();
    }

    public SelecionarProduto5(){
        wait.until(ExpectedConditions.elementToBeClickable(produto5));
        driver.findElements(produto5).get(0).click();
    }

    public SelecionarProduto6(){
        wait.until(ExpectedConditions.elementToBeClickable(produto6));
        driver.findElements(produto6).get(0).click();
    }

    public SelecionarProduto7(){
        wait.until(ExpectedConditions.elementToBeClickable(produto7));
        driver.findElements(produto7).get(0).click();
    }



}
