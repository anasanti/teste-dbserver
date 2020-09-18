Prova de QA da DBServer
==================
Como executar
------------------
Os testes podem ser executados atraves de uma IDE ou pelo terminal com o comando na raiz do projeto.

    mvn test

Tecnicas de testes escolhidas
------------------
Foi realizado testes funcionais e de estrutura na UI e de API.

Teste
------------------
Realizado por BDD em RealizarCompra.feature.
Teste de API em APITest.
Evidências dos testes automatizados se encomtra em screenshots.

Para execucao dos testes e necessario ter os browser nas versoes abaixo
------------------
Chrome - Version 85.0.4183.87
<br />Firefox - Version 80.0.1

O brownser de execucao:
 ------------------
O navegador está definido para rodar nas duas versões, para alterar para a versão desejada acessar:

    src/test/java/resources/RealizarCompra.feature

Na opção de Exemplos de Navegadores.

Desenvolvido com
------------------
Selenium - 3.141.59 
<br />Cucumber - 1.2.5 
<br />Maven - 3.6.3 
<br />Java - 8 
<br />JUnit - 4.11 
<br />Windows - 10 

Autora
------------------
Ana Carolina Prates Santi.

Oportunidade de melhoria
------------------
Criação do relatório de caso de testes, com:
<br />Definição do projeto;
<br />Objetivo do teste;
<br />Resumo do teste;
<br />Defeito.

Criação de casos positivos e negativos do site.
Validação do sistema.

Observações
------------------
Na classe RealizarComprasSteps foi criado um gerador de email, para não ocorrer duplicidade ao realizar o teste.
