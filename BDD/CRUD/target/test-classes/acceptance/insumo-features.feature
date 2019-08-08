Feature: Insumo

##----------------------------CREATE----------------------------------------------
##--------------------------------------------------------------------------------

 Scenario Outline: add um novo insumo
	
    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Insumo"
    And Clique no botao "ADICIONAR INSUMO"
    When Preencher os campos do insumo "<nome>", "<categoria>", "<descricao>", "<estoque>" e "<capacidade>"
    And Clique no botao "<botao>"
    Then Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"
    And Eu devo ser capaz de visualizar os campos do insumo "<nome>", "<categoria>", "<descricao>", "<estoque>" e "<capacidade>"

    Examples:
| nome		| categoria	| descricao	   | estoque | capacidade| mensagem		| botao_alerta | botao |
| Uai Minas	| garrafas	| Uai Minas 800ml  | 500     | 800	 | Insumo adicionado com sucesso! | OK | CONFIRMAR |
| Amburaninha	| garrafas	| Amburaninha 300ml| 340     | 300	 | Insumo adicionado com sucesso! | OK | CONFIRMAR |


##----------------------------READ------------------------------------------------
##--------------------------------------------------------------------------------

 Scenario Outline: ler um insumo

    Given Eu esteja na tela Inicial
    When Clique no botao lateral "Insumo"
    And Preencher o campo de pesquisa por "<campo>"
    Then Eu devo ser capaz de visualizar o campo "<campo>"

    Examples:
| campo |
| Uai Minas |
| Amburaninha |

##----------------------------UPDATE------------------------------------------------
##----------------------------------------------------------------------------------

 Scenario Outline: atualizar um insumo

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Insumo"
    When Clicar no botao "Editar" do insumo com os campos "<nome>", "<categoria>", "<descricao>", "<estoque>" e "<capacidade>"
    And Alterar os campos do insumo com "<newnome>", "<newestoque>" e "<newcapacidade>"
    And Clique no botao "<botao>"
    Then Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"
    And Eu devo ser capaz de visualizar os campos do insumo "<newnome>", "<categoria>", "<descricao>", "<newestoque>" e "<newcapacidade>"

    Examples:
| nome		| categoria	| descricao	   | estoque | capacidade| newnome    | newestoque | newcapacidade | botao | mensagem | botao_alerta |
| Uai Minas	| garrafas	| Uai Minas 800ml  | 500     | 800	 | Trem Minas | 300	   | 700 | CONFIRMAR | Insumo adicionado com sucesso! | OK |
| Amburaninha	| garrafas	| Amburaninha 300ml| 340     | 300	 | Ambura     | 400	   | 500 | CONFIRMAR | Insumo adicionado com sucesso! | OK |



##----------------------------DELETE------------------------------------------------
##----------------------------------------------------------------------------------

 Scenario Outline: excluir um insumo

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Insumo"
    When Clicar no botao "Excluir" do insumo com os campos "<nome>", "<categoria>", "<descricao>", "<estoque>" e "<capacidade>"
    And Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"
    Then Devo receber a mensagem "<mensagem2>"
    And Clicar no botao de alerta "<botao_alerta2>"
    And Preencher o campo de pesquisa por "<nome>"
    And A saida deve ser "<output>"

    Examples:
| nome		| categoria	| descricao	   | estoque | capacidade| botao_alerta2     | output | mensagem | botao_alerta | mensagem2 |
| Trem Minas	| garrafas	| Uai Minas 800ml  | 300     | 700| OK || Você tem certeza que deseja remover esses dados ? | SIM | Objeto deletado com sucesso! |
| Ambura                    | garrafas	| Amburaninha 300ml| 400    | 500| OK || Você tem certeza que deseja remover esses dados ? | SIM | Objeto deletado com sucesso! |
