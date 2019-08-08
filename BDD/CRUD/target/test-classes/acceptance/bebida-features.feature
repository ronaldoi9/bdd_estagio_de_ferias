Feature: Bebida

##----------------------------CREATE----------------------------------------------
##-----------------------------------------------------------------------------------

Scenario Outline: add bebida

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Bebida"
    And Clique no botao "ADICIONAR BEBIDA"
    When Preencher os campos da bebida "<nome>", "<descricao>", "<estoque>", "<garrafa>", "<tampa>"
    And Clique no botao "<botao>"
    Then Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"

Examples:
| nome | descricao | estoque | garrafa | tampa | botao | mensagem | botao_alerta |
| BEBIDA | BEBIDA DA BOA | 10 | TREM MINAS | TAMPA | CONFIRMAR | Bebida foi adicionado com sucesso! | OK |

##----------------------------READ----------------------------------------------
##--------------------------------------------------------------------------------

Scenario Outline: ler bebida

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Bebida"
    When Preencher o campo de pesquisa por "<campo>"
    Then Eu devo ser capaz de visualizar o campo "<campo>"

Examples:
| campo |
| BEBIDA |

##----------------------------UPDATE----------------------------------------------
##-----------------------------------------------------------------------------------

Scenario Outline: atualiza bebida

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Bebida"
    When Clicar no botao "Editar" da bebida com os campos "<nome>", "<descricao>", "<estoque>"
    And Alterar os campos da bebida com "<newnome>", "<newdescricao>", "<newestoque>", "<garrafa>" e "<tampa>"
    And Clique no botao "<botao>"
    Then Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"
    And Eu devo ser capaz de visualizar os campos da bebida atualizado "<newnome>", "<newdescricao>" e "<newestoque>"

Examples:
| nome | descricao | estoque | newnome | newdescricao |newestoque | garrafa | tampa | botao | mensagem | botao_alerta |
| BEBIDA | BEBIDA DA BOA | 10 | NOVA BEBIDA | BEBIDA NORMAL | 40 | TREM MINAS | TAMPA | CONFIRMAR | Bebida foi adicionado com sucesso! | OK |

##----------------------------DELETE----------------------------------------------
##-----------------------------------------------------------------------------------

Scenario Outline: deleta bebida

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Bebida"
    When Clicar no botao "Excluir" da bebida com os campos "<nome>", "<descricao>", "<estoque>"
    And Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"
    Then Devo receber a mensagem "<mensagem2>"
    And Clicar no botao de alerta "<botao_alerta2>"
    And Preencher o campo de pesquisa por "<campo>"
    And A saida deve ser "<output>"

Examples:
| nome | descricao | estoque | mensagem | botao_alerta | mensagem2 | botao_alerta2 | campo | output |
| NOVA BEBIDA | BEBIDA NORMAL | 40 | Você tem certeza que deseja remover esses dados ? | SIM | Objeto deletado com sucesso! | OK | NOVA BEBIDA | |