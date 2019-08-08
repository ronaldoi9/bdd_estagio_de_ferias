Feature: Tonel

##----------------------------CREATE----------------------------------------------
##--------------------------------------------------------------------------------

Scenario Outline: add tonel

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Tonel"
    And Clique no botao "ADICIONAR TONEL"
    When Preencher os campos do tonel "<nome>", "<quantidade>", "<capacidade>", "<estoque>", "<tipo>", "<envelhecendo>" e "<Data de Envelhecimento>"
    And Clique no botao "<botao>"
    Then Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"
    And Eu devo ser capaz de visualizar os campos do tonel "<nome>", "<quantidade>"

    Examples:
| nome		     | quantidade | capacidade | estoque | tipo | envelhecendo | Data de Envelhecimento | mensagem | botao_alerta | botao |
| Carvalho Americano | 5	  | 10	       | 10	 | Carvalho Americano | Sim | 02082019 | Tonel foi adicionado com sucesso! | OK | CONFIRMAR |


##----------------------------READ----------------------------------------------
##--------------------------------------------------------------------------------

Scenario Outline: ler tonel

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Tonel"
    When Preencher o campo de pesquisa por "<campo>"
    Then Eu devo ser capaz de visualizar o campo "<campo>"

    Examples:
| campo 	|
| Carvalho Americano |

##----------------------------UPDATE----------------------------------------------
##--------------------------------------------------------------------------------

Scenario Outline: atualiza tonel

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Tonel"
    When Clicar no botao "Editar" do tonel com os campos "<nome>", "<capacidade>", "<estoque>"
    And Alterar os campos do tonel com "<newnome>", "<newcapacidade>" e "<newestoque>"
    And Clique no botao "<botao>"
    Then Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"
    And Eu devo ser capaz de visualizar os campos do tonel atualizado "<newnome>", "<newcapacidade>" e "<newestoque>"

    Examples:
| nome		     | capacidade | estoque      | newnome | newcapacidade | newestoque | botao | mensagem | botao_alerta |
| Carvalho Americano 01| 10	       | 10	 | Carvalho Brasileiro| 20 | 30 | CONFIRMAR | Tonel foi adicionado com sucesso! | OK |

##----------------------------DELETE----------------------------------------------
##--------------------------------------------------------------------------------

Scenario Outline: deleta tonel

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Tonel"
    When Clicar no botao "Excluir" do tonel com os campos "<nome>", "<capacidade>", "<estoque>"
    And Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"
    Then Devo receber a mensagem "<mensagem2>"
    And Clicar no botao de alerta "<botao_alerta2>"
    And Preencher o campo de pesquisa por "<campo>"
    And A saida deve ser "<output>"

    Examples:
| nome		  | capacidade | estoque  | campo | output | mensagem | botao_alerta | mensagem2 | botao_alerta2 |
| Carvalho Brasileiro 	  | 20	       | 30	| Carvalho Brasileiro || Você tem certeza que deseja remover esse Tonel ? | Sim | Tonel deletado com sucesso! | OK |

