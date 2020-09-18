# language: pt

@tag
Funcionalidade: Realizar uma compra

  @sucesso @compra @andamento
  Esquema do Cenario: Realizar uma compra com sucesso
    Dado que estou acessando a url "http://automationpractice.com/index.php?" pelo navegador "<navegador>"
    Quando adiciono um produto escolhido no carrinho
    E prossigo para finalizar a compra
    E valido se o produto foi corretamente adicionado ao carrinho de compra
    E sigo para a etapa de cadastro
    E solicito a criação de conta com um email
    E informe os seguintes dados de cadastro
      | key       | value                  |
      | nome      | Ana                    |
      | sobrenome | Santi                  |
      | senha     | 12345                  |
      | endereco  | 650 S R L Thornton Fwy |
      | cidade    | Dallas                 |
      | estado    | Texas                  |
      | pais      | United States          |
      | cep       | 75203                  |
      | celular   | 14695547500            |
    E confirmo o cadastro
    E valido se as seguintes informações estão corretas
      | key              | value                  |
      | nomeEsperado     | Ana Santi              |
      | enderecoEsperado | 650 S R L Thornton Fwy |
      | cidadeEsperado   | Dallas, Texas 75203    |
      | paisEsperado     | United States          |
      | celularEsperado  | 14695547500            |
    E sigo para escolher o modo de envio
    E aceito os termos de serviço
    E sigo para a forma de pagamento
    E valido o valor total da compra
    E seleciono o metodo de pagamento
    Entao confirmo a compra
    E valido se foi finalizada com sucesso

    Exemplos:
      | navegador |
      | CHROME    |
      | FIREFOX   |