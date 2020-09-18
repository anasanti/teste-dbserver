package stepDefinitions.realizarCompra;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.es.Dado;
import cucumber.api.java.it.Quando;
import cucumber.api.java.pt.Entao;
import enums.NavegadorEnum;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.DriveManager;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

public class RealizarCompraSteps {

    private DriveManager driveManager;
    private WebDriver webDriver;
    private WebDriverWait wait;

    private String nomeDoProdutoEscolhido;

    @Dado("^que estou acessando a url \"([^\"]*)\" pelo navegador \"([^\"]*)\"$")
    public void queEstouAcessandoAUrlPeloNavegador(String url, NavegadorEnum navegador) {
        this.driveManager = new DriveManager(navegador, url);
        this.wait = new WebDriverWait(driveManager.getDriver(), 15);
        this.webDriver = driveManager.getDriver();
    }

    @Quando("^adiciono um produto escolhido no carrinho$")
    public void adicionoUmProdutoEscolhidoNoCarrinho() {
        Actions acao = new Actions(webDriver);
        WebElement primeiroProduto = webDriver.findElements(By.className("ajax_block_product")).get(0);

        wait.until(ExpectedConditions.visibilityOf(primeiroProduto));

        this.nomeDoProdutoEscolhido = primeiroProduto.findElement(By.className("product-name")).getText();

        acao.moveToElement(primeiroProduto)
                .moveToElement(primeiroProduto.findElement(By.className("ajax_add_to_cart_button")))
                .click().build().perform();
    }


    @Quando("^prossigo para finalizar a compra$")
    public void prossigoParaFinalizarACompra() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
        webDriver.findElements(By.className("button-medium")).get(0).click();
    }

    @Quando("^valido se o produto foi corretamente adicionado ao carrinho de compra$")
    public void validoSeOProdutoFoiCorretamenteAdicionadoAoCarrinhoDeCompra() throws Exception {
        String textoProdutoSelecionadoParaCompra = webDriver
                .findElement(By.id("cart_summary"))
                .findElement(By.className("product-name")).getText();

        Assert.assertEquals(nomeDoProdutoEscolhido, textoProdutoSelecionadoParaCompra);
    }

    @Quando("^sigo para a etapa de cadastro$")
    public void sigoParaAEtapaDeCadastro() {
        webDriver.findElement(By.className("standard-checkout")).click();
    }

    @Quando("^solicito a criação de conta com um email$")
    public void solicitoACriaçãoDeContaComUmEmail() {
        By byEmailInput = By.id("email_create");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byEmailInput));
        webDriver.findElement(byEmailInput).sendKeys(geradorEmail());
        webDriver.findElement(By.id("SubmitCreate")).click();
    }

    @Quando("^informe os seguintes dados de cadastro$")
    public void informeOsSeguintesDadosDeCadastro(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account_creation")));
        webDriver.findElement(By.id("customer_firstname")).sendKeys(dataMap.get("nome"));
        webDriver.findElement(By.id("customer_lastname")).sendKeys(dataMap.get("sobrenome"));
        webDriver.findElement(By.id("passwd")).sendKeys(dataMap.get("senha"));
        webDriver.findElement(By.id("address1")).sendKeys(dataMap.get("endereco"));
        webDriver.findElement(By.id("city")).sendKeys(dataMap.get("cidade"));
        webDriver.findElement(By.id("id_state")).sendKeys(dataMap.get("estado"));
        webDriver.findElement(By.id("postcode")).sendKeys(dataMap.get("cep"));
        webDriver.findElement(By.id("id_country")).sendKeys(dataMap.get("pais"));
        webDriver.findElement(By.id("phone_mobile")).sendKeys(dataMap.get("celular"));
    }

    @Quando("^confirmo o cadastro$")
    public void confirmoOCadastro() {
        webDriver.findElement(By.id("submitAccount")).click();
    }


    @Quando("^valido se as seguintes informações estão corretas$")
    public void validoSeAsSeguintesInformacoesEstaoCorretas(DataTable dataTable) throws Exception {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

        String nomeCadastrado = webDriver.findElement(By.className("address_firstname")).getText();
        String enderecoCadastrado =  webDriver.findElement(By.className("address_address1")).getText();
        String cidadeCadastrado = webDriver.findElement(By.className("address_city")).getText();
        String paisCadastrado = webDriver.findElement(By.className("address_country_name")).getText();
        String celularCadastrado = webDriver.findElement(By.className("address_phone_mobile")).getText();

        Assert.assertEquals(dataMap.get("nomeEsperado"), nomeCadastrado);
        Assert.assertEquals(dataMap.get("enderecoEsperado"), enderecoCadastrado);
        Assert.assertEquals(dataMap.get("cidadeEsperado"), cidadeCadastrado);
        Assert.assertEquals(dataMap.get("paisEsperado"), paisCadastrado);
        Assert.assertEquals(dataMap.get("celularEsperado"), celularCadastrado);
    }

    @Quando("^sigo para escolher o modo de envio$")
    public void sigoParaEscolherOModoDeEnvio() {
        webDriver.findElement(By.name("processAddress")).click();
    }

    @Quando("^aceito os termos de serviço$")
    public void aceitoOsTermosDeServiço() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv")));
        webDriver.findElement(By.id("uniform-cgv")).click();
    }

    @Quando("^sigo para a forma de pagamento$")
    public void sigoParaAFormaDePagamento() {
        webDriver.findElement(By.name("processCarrier")).click();
    }

    @Quando("^valido o valor total da compra$")
    public void validoOValorTotalDaCompra() throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("total_product")));
        String stringTotalProduto = webDriver.findElement(By.id("total_product")).getText();
        String stringTotalFrete = webDriver.findElement(By.id("total_shipping")).getText();
        String stringValorTotal = webDriver.findElement(By.id("total_price")).getText();

        BigDecimal totalProduto = new BigDecimal(stringTotalProduto.replace("$", ""));
        BigDecimal totalFrete = new BigDecimal(stringTotalFrete.replace("$", ""));
        BigDecimal valorTotal = new BigDecimal(stringValorTotal.replace("$", ""));

        Assert.assertEquals((totalFrete.add(totalProduto)), valorTotal);
    }

    @Quando("^seleciono o metodo de pagamento$")
    public void selecionoOMetodoDePagamento() {
        webDriver.findElement(By.className("bankwire")).click();
    }

    @Entao("^confirmo a compra$")
    public void confirmoACompra() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cart_navigation\"]/button")));
        webDriver.findElement(By.id("cart_navigation"))
                .findElement(By.className("button")).click();
    }

    @Entao("^valido se foi finalizada com sucesso$")
    public void validoSeFoiFinalizadaComSucesso() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("box")));
        String confirmacaoDeCompra = webDriver.findElement(By.className("cheque-indent")).getText();

        Assert.assertEquals("Your order on My Store is complete.", confirmacaoDeCompra);
    }

    @After
    public void encerrarSessao() {
        driveManager.finalizarSessao();
    }

    private String geradorEmail(){
        return "teste"+new Random().nextInt(1000000)+"@teste.com";
    }
}
