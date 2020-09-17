package desafio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimuladorUI {
    private WebDriver driver;
    private WebDriverWait wait;


    private By cardDoProduto1 = By.className("product-container");
    private By botaoAddToCart = By.className("ajax_add_to_cart_button");
    private By elementoNomeDoProdutoEscolhido = By.linkText("Faded Short Sleeve T-shirts");
    private By modalProdutoAddAoCarrinho = By.id("layer_cart");
    private By botaoProcedToCheckoutLayerCart = By.className("button-medium");
    private By shoppingCartSummary = By.id("cart_title");
    private By produtoNoCarrinhoDeCompra = By.linkText("Faded Short Sleeve T-shirts");
    private By botaoProcedToCheckoutSummary = By.className("standard-checkout");
    private By authenticationUser = By.className("page-heading");
    private By criacaoDeEmail = By.id("email_create");
    private By botaoCreateAnAccount = By.id("SubmitCreate");
    private By createAnAccount = By.className("account_creation");
    private By firstName = By.id("customer_firstname");
    private By lastName = By.id("customer_lastname");
    private By password = By.id("passwd");
    private By adress = By.id("address1");
    private By city = By.id("city");
    private By state = By.id("id_state");
    private By postalCode = By.id("postcode");
    private By country = By.id("id_country");
    private By phoneMobile = By.id("phone_mobile");
    private By buttonSubimitAccount = By.id("submitAccount");
    private By confirmAdress = By.className("page-heading");
    private By adressName = By.className("address_firstname");
    private By adressAdress = By.className("address_address1");
    private By adressCity = By.className("address_city");
    private By adressCountry = By.className("address_country_name");
    private By adressPhone = By.className("address_phone_mobile");
    private By buttonProceedToCheckOut = By.name("processAddress");
    private By pageShipping = By.id("carrier_area");
    private By selectTermsOfService = By.id("cgv");
    private By buttonProceedToCheckOutShipping = By.name("processCarrier");
    private By totalDosProdutos = By.id("total_product");
    private By totalDoFrete = By.id("total_shipping");
    private By total = By.id("total_price");
    private By pleaseChooseYourPaymentMethod = By.className("page-heading");
    private By pagamentoBanco = By.className("bankwire");
    private By orderSumary = By.className("page-heading");
    private By iConfirmMyOrder = By.xpath("//*[@id=\"cart_navigation\"]/button");
    private By orderConfirmation = By.className("page-heading");



    private String nomeDoProdutoEscolhido;
    private String produtoSelecionadoParaCompra;


    public SimuladorUI(WebDriver drive, WebDriverWait wait) {
        this.driver = drive;
        this.wait = wait;
    }

    public void addoProdutoNoCarrinho() {
        Actions acao = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardDoProduto1));
        nomeDoProdutoEscolhido = driver.findElement(elementoNomeDoProdutoEscolhido).getText();
        acao.moveToElement(driver.findElement(cardDoProduto1));
        acao.moveToElement(driver.findElements(botaoAddToCart).get(0));
        acao.click().build().perform();
    }

    public void seguirParaOCarrinho() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalProdutoAddAoCarrinho));
        driver.findElements(botaoProcedToCheckoutLayerCart).get(0).click();
    }

    public void seguirParaOSignIn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartSummary));
        produtoSelecionadoParaCompra = driver.findElement(produtoNoCarrinhoDeCompra).getText();
        driver.findElements(botaoProcedToCheckoutSummary).get(0).click();
    }

    public void validarItemAdicionadoEIgualAoEscolhido() throws Exception {
        if (!produtoSelecionadoParaCompra.equals(nomeDoProdutoEscolhido)) {
            throw new Exception("Produto escolhido diferente do produto adicionado ao carrinho.");
        }
    }

    public void criarUsuario(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(authenticationUser));
        driver.findElement(criacaoDeEmail).sendKeys(email);
        driver.findElement(botaoCreateAnAccount).click();
    }

    public void cadastroDeInformacaoPessoal(String nome, String sobrenome, String senha) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createAnAccount));
        driver.findElement(firstName).sendKeys(nome);
        driver.findElement(lastName).sendKeys(sobrenome);
        driver.findElement(password).sendKeys(senha);

    }

    public void cadastroDeEndereco(String endereco, String cidade, String cep, String celular) {
        Select pais = new Select(driver.findElement(country));
        Select estado = new Select(driver.findElement(state));
        driver.findElement(adress).sendKeys(endereco);
        driver.findElement(city).sendKeys( cidade);
        estado.selectByVisibleText("Texas");
        driver.findElement(postalCode).sendKeys(cep);
        pais.selectByVisibleText("United States");
        driver.findElement(phoneMobile).sendKeys( celular);
        driver.findElement(buttonSubimitAccount).click();
    }

    public void confirmacaoDeEndereco(String nomeEsperado, String enderecoEsperado, String cidadeEsperada, String paisEspeado, String celularEsperado) throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmAdress));

        if (!nomeEsperado.equals(driver.findElement(adressName).getText()))
            throw new Exception("Nomes divergetes");

        if (!enderecoEsperado.equals(driver.findElement(adressAdress).getText()))
            throw new Exception("Endereços divergentes");

        if (!cidadeEsperada.equals(driver.findElement(adressCity).getText()))
            throw new Exception("Cidade divergentes");

        if (!paisEspeado.equals(driver.findElement(adressCountry).getText()))
            throw new Exception("País divergentes");

        if (!celularEsperado.equals(driver.findElement(adressPhone).getText()))
            throw new Exception("Celular divergentes");

        driver.findElement(buttonProceedToCheckOut).click();
    }

    public void aceiteTermosDeServico(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageShipping));
        driver.findElement(selectTermsOfService).click();
        driver.findElement(buttonProceedToCheckOutShipping).click();
    }

    public void valorTotaldaCompra() throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pleaseChooseYourPaymentMethod));
        String stringTotalProduto = driver.findElement(totalDosProdutos).getText();
        String stringTotalFrete = driver.findElement(totalDoFrete).getText();
        String stringValorTotal = driver.findElement(total).getText();

        Double totalProduto = Double.parseDouble(stringTotalProduto.replace("$", ""));
        Double totalFrete = Double.parseDouble(stringTotalFrete.replace("$", ""));
        Double valorTotal = Double.parseDouble(stringValorTotal.replace("$", ""));

        if (!(totalProduto+totalFrete == valorTotal))
            throw new Exception("Valor do produto e frete divergente ao valor total da compra");

        driver.findElement(pagamentoBanco).click();

    }

    public void selecionarMetodoDePagamento(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderSumary));
        driver.findElement(iConfirmMyOrder).click();
    }

    public void confirmacaoDeCompra(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation));
    }

}
