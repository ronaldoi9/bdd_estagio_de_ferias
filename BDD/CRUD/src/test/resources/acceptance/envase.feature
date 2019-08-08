Feature: Envase

##----------------------------CREATE----------------------------------------------
##-------------------------------------------------------------------------------------

Scenario Outline: add envase

    Given Eu esteja na tela Inicial
    And Clique no botao lateral "Envase"
    And Clique no botao "ADICIONAR ENVASE"
    When Preencher os campos do envase "<tonel>", "<produto>", "<quantidade>", "<lote>", "<data>"
    And Clique no botao "<botao>"
    Then Devo receber a mensagem "<mensagem>"
    And Clicar no botao de alerta "<botao_alerta>"

#Terminar a implementação
   
