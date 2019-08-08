Feature: Insumo

##----------------------------CREATE----------------------------------------------
##--------------------------------------------------------------------------------

 Scenario Outline: add um novo insumo
	
    Given Eu esteja na tela Insumos
    And Clique no botão "ADICIONAR"
    And Preencher os campos "<nome>", "<categoria>", "<descricao>", "<estoque>" e "<capacidade>"
    When Eu clicar no botao "ADICIONAR INSUMO"
    #Then Eu devo receber uma mensagem "<mensagem>"
    #And Clicar no botao "btn_ok"
    Then Eu devo ser capaz de visualizar os campos "<nome>", "<categoria>", "<descricao>", "<estoque>" e "<capacidade>"

    Examples:
| nome		| categoria	| descricao	   | estoque | capacidade| mensagem		|
| Uai Minas	| garrafas	| Uai Minas 800ml  | 500     | 800	 | Adicionado com sucesso |
| Amburaninha	| garrafas	| Amburaninha 300ml| 340     | 300	 | Adicionado com sucesso |
| Caixa		| caixas		| Teste caixa	   | 20      | 60	 | Adicionado com sucesso |


##----------------------------READ------------------------------------------------
##--------------------------------------------------------------------------------

 Scenario Outline: ler um insumo

    Given Eu esteja na tela Insumos
    #And Clique no botao "LIST"
    When Clique no botão lateral "Insumo"
    And Preencher o campo de pesquisa por "<nome>"
    Then Devo ser capaz de visualizar o insumo com seus atributos "<nome>", "<categoria>" e "<descricao>"

    Examples:
| nome		| categoria	| descricao	   |
| Uai Minas	| garrafas	| Uai Minas 800ml  |
| Amburaninha	| garrafas	| Amburaninha 300ml|
| Caixa		| caixas		| Teste caixa	   |

##----------------------------UPDATE------------------------------------------------
##----------------------------------------------------------------------------------

 Scenario Outline: atualizar um insumo

    Given Eu esteja na tela Insumos
    And Clique no botão lateral "Insumo"
    When Clicar no botao "Editar" do insumo com os campos "<nome>", "<categoria>", "<descricao>", "<estoque>" e "<capacidade>"
    And Alterar os campos com "<newnome>", "<newestoque>" e "<newcapacidade>"
    And Clique no botão "<botao>"
    And Atualizar a pagina
    Then Eu devo ser capaz de visualizar os campos "<newnome>", "<categoria>", "<descricao>", "<newestoque>" e "<newcapacidade>"

    Examples:
| nome		| categoria	| descricao	   | estoque | capacidade| newnome    | newestoque | newcapacidade | botao |
| Uai Minas	| garrafas	| Uai Minas 800ml  | 500     | 800	 | Trem Minas | 300	   | 700 | ADICIONAR INSUMO |
| Amburaninha	| garrafas	| Amburaninha 300ml| 340     | 300	 | Ambura     | 400	   | 500 | ADICIONAR INSUMO |



##----------------------------DELETE------------------------------------------------
##----------------------------------------------------------------------------------

 Scenario Outline: excluir um insumo

    Given Eu esteja na tela Insumos
    And Clique no botão lateral "Insumo"
    When Clicar no botao "Excluir" do insumo com os campos "<nome>", "<categoria>", "<descricao>", "<estoque>" e "<capacidade>"
    #And Clicar no "<botao>"
    And Preencher o campo de pesquisa por "<nome>"
    Then A saida deve ser "<output>"

    Examples:
| nome		| categoria	| descricao	   | estoque | capacidade| botao     | output 	|
| Trem Minas	| garrafas	| Uai Minas 800ml  | 300     | 700	 | Confirmar ||
| Ambura	| garrafas	| Amburaninha 300ml| 400    | 500	 | Confirmar ||
| Caixa		| caixas		| Teste caixa	   | 20      | 60	 | | Confirmar | |
