# language: pt

@tag
  Funcionalidade: Realizar uma compra

    Contexto:
      Dado que estou no site

      @sucesso @compra @andamento
      Cenario: Realizar uma compra com sucesso
        Quando escolho um produto
        E adiciono o produto no carrinho de compras
        E prossigo para finalizar a compra
        E valido se o produto foi corretamente adicionado ao carrinho de compra
        E realizo o cadastro do cliente
        E valido se o endereço esta correto
        E aceito os termos de serviço
        E valido o valor total da compra
        E seleciono o metodo de pagamento
        Entao confirmo a compra
        E valido se foi finalizada com sucesso



