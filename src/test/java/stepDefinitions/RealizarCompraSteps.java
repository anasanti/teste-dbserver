package stepDefinitions;

import cucumber.api.java.es.Dado;
import cucumber.api.java.it.Quando;
import cucumber.api.java.pt.Entao;
import desafio.SimuladorUI;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.DriveManager;

public class RealizarCompraSteps {
    private DriveManager driver;
    private SimuladorUI simulador;
    private WebDriverWait wait;

    @Dado("^que estou no site$")
    public void setUp() {

        driver = new DriveManager("Chrome");
        wait = new WebDriverWait(driver.getDriver(), 15);
        simulador = new SimuladorUI(driver.getDriver(), wait);
    }

    @Quando("^escolho um produto$")
    public void escolhoUmProduto() throws Exception {
        simulador.addoProdutoNoCarrinho();
    }

    @Quando("^adiciono o produto no carrinho de compras$")
    public void adicionoOProdutoNoCarrinhoDeCompras() throws Exception {
        simulador.seguirParaOCarrinho();
    }

    @Quando("^prossigo para finalizar a compra$")
    public void prossigoParaFinalizarACompra() throws Exception {
        simulador.seguirParaOSignIn();
    }

    @Quando("^valido se o produto foi corretamente adicionado ao carrinho de compra$")
    public void validoSeOProdutoFoiCorretamenteAdicionadaAoCarrinhoDeCompras() throws Exception {
        simulador.validarItemAdicionadoEIgualAoEscolhido();
    }

    @Quando("^realizo o cadastro do cliente$")
    public void realizoOCadastroDoCliente() throws Exception {
        simulador.criarUsuario("new@dbserver.com.br");
        simulador.cadastroDeInformacaoPessoal("Ana Carolina", "Santi", "debeaver");
        simulador.cadastroDeEndereco("650 S R L Thornton Fwy", "Dallas", "75203", "14695547500");
    }

    @Quando("^valido se o endereço esta correto$")
    public void validoSeOEnderecoEstaCorreto() throws Exception {
        simulador.confirmacaoDeEndereco("Ana Carolina Santi", "650 S R L Thornton Fwy", "Dallas, Texas 75203", "United States", "14695547500");
    }

    @Quando("^aceito os termos de serviço$")
    public void aceitoOsTermosDeServico() throws Exception {
        simulador.aceiteTermosDeServico();
    }

    @Quando("^valido o valor total da compra$")
    public void validoOValorTotalDaCompra() throws Exception {
        simulador.valorTotaldaCompra();
    }

    @Quando("^seleciono o metodo de pagamento$")
    public void SelecionoOmetodoDePagamento() throws Exception {
        simulador.selecionarMetodoDePagamento();
    }

    @Entao("^confirmo a compra$")
    public void confirmoACompra() throws Exception {
        simulador.confirmoCompra();
    }

    @Entao("^valido se foi finalizada com sucesso$")
    public void validoSeFoiFinalizadaComSuccesso() throws Exception {
        simulador.confirmacaoDeCompra();
        driver.endSession();
    }


}
