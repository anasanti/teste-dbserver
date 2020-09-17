package desafio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.DriveManager;

public class UITest {
    private DriveManager driver;
    private SimuladorUI simulador;
    private WebDriverWait wait;

    @Before
    public void setUp() {

        driver = new DriveManager("Chrome");
        wait = new WebDriverWait(driver.getDriver(), 15);
        simulador = new SimuladorUI(driver.getDriver(), wait);
    }

    @Test
    public void fazerCompraDeUmProduto() throws Exception {
        simulador.addoProdutoNoCarrinho();
        simulador.seguirParaOCarrinho();
        simulador.seguirParaOSignIn();
        simulador.validarItemAdicionadoEIgualAoEscolhido();
        simulador.criarUsuario("testeemailaaa@dbserver.com.br");
        simulador.cadastroDeInformacaoPessoal("Ana Carolina","Santi","debeaver");
        simulador.cadastroDeEndereco("650 S R L Thornton Fwy", "Dallas","75203", "14695547500");
        simulador.confirmacaoDeEndereco("Ana Carolina Santi","650 S R L Thornton Fwy","Dallas, Texas 75203","United States","14695547500" );
        simulador.aceiteTermosDeServico();
        simulador.valorTotaldaCompra();
        simulador.selecionarMetodoDePagamento();
        simulador.confirmacaoDeCompra();
    }

    @After
    public void closePage() {

        driver.endSession();
    }


}
