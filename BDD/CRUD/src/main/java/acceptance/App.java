package acceptance;

// Classe responsável pelos testes das funções implementadas em stepDefinitions

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class App 
{
        private WebDriver driver = null;
    
    public void setUp() throws Throwable
{
        //Informa o caminho do firefox
        File path = new File("/opt/firefox/firefox");
       
        //Informa a opção de arquivo binário
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary (path));
        
        //Instancia o Firefox com a opção configurada
        driver = new FirefoxDriver(options);
        
        //Espera até 20 segundos para carregar os elementos na página
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
}

public void finish() throws Throwable
{
        driver.quit();
}

public void eu_esteja_logado_no_sistema_com() throws Throwable
{
    driver.navigate().to("http://127.0.0.1:8080/");
    driver.findElement(By.name("usuario")).sendKeys("devteste");
    driver.findElement(By.name("senha")).sendKeys("devteste");
    driver.findElement(By.tagName("button")).click();
}

//Percorre todos os botões até que encontre o botão passado por parâmetro
public void procura_botao(String botao)
{
    int i = 0;
    while(i >= 0)
    {
        if (driver.findElements(By.tagName("button")).get(i).getText().equals(botao))
        {
            driver.findElements(By.tagName("button")).get(i).click();
            i = -1;
        }
        else
        {
            i++;
        }
    }
}

public void clique_no_botao_lateral(String botao)
{
    //Procura 
    int i = 1;
    while (i >= 1)
    {
        if (driver.findElement(By.xpath("/html/body/div[1]/div[1]/nav/div/ul/li[2]/a["+i+"]")).getText().equals(botao))
        {
            driver.findElement(By.xpath("/html/body/div/div[1]/nav/div/ul/li[2]/a["+i+"]")).click();
            i = 0;
        }
        else
            i++;
    }
    
}

public void eu_clicar_no_botao(String botao) throws Throwable
{
    Thread.sleep(3000);
    procura_botao(botao);
}
 
public void eu_devo_ser_capaz_de_visualizar_os_campos(String nome, String categoria, String descricao, String estoque, String capacidade) throws Throwable
{
     Thread.sleep(3000);
     driver.navigate().refresh();
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));

    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = nome + " " + categoria + " " + descricao + " " + estoque+" "+capacidade;

    for (WebElement linha : tr) {
        System.out.println("LINHA: "+ linha.getText());
        System.out.println("INPUT: "+ input.toUpperCase());
        if (linha.getText().equals(input.toUpperCase())) {
             System.out.println("*****É IGUAL, FIM DE PAPO***");
            break;
        }
    }
    
}

public void clicar_no_botao_do_insumo_com_os_campos(String botao, String nome, String categoria, String descricao, String estoque, String capacidade) throws Throwable
{
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = nome+" " +categoria+ " " +descricao+" "+estoque+" "+capacidade;
    
    //Verifica se a opção do botão é editar ou excluir i = 2 e j = 1 editar / i = 2 e j = 2 excluir
    int i, j;
    if (botao.equals("Editar"))
    {
         i = 1;
         j = 1;
    }
    else
    {
        i = 1;
        j = 2;
    }
    //Pesquisa aonde esta o botão desejado e depois clica nele
    for (WebElement linha : tr)
    {           
        if (linha.getText().equals(input.toUpperCase()))
        {
            driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table/tbody/tr["+Integer.toString(i)+"]/td[6]/button["+Integer.toString(j)+"]")).click();
            break;
        }
        else
        {
            i++;
        }
    }
}

public void alterar_os_campos_com(String newnome, String newestoque, String newcapacidade) throws Throwable
{
    driver.findElement(By.name("nome")).clear();
    driver.findElement(By.name("nome")).sendKeys(newnome);
    int i = 0;
    while (i >=0)
    {
        if (driver.findElement(By.cssSelector("select.form-control")).findElements(By.tagName("option")).get(i).getText().equals("GARRAFAS"))
        {
            driver.findElement(By.cssSelector("select.form-control")).findElements(By.tagName("option")).get(i).click();
            i = -1;
        }
        else
            i++;    
    }
    driver.findElement(By.name("estoque")).clear();
    driver.findElement(By.name("estoque")).sendKeys(newestoque);
    driver.findElement(By.name("capacidade")).clear();
    driver.findElement(By.name("capacidade")).sendKeys(newcapacidade);
}

public void preencher_o_campo_de_pesquisa_por(String nome) throws Throwable
{
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/input")).sendKeys(nome);    
}

public void a_saida_deve_ser(String output) throws Throwable
{
{
    Thread.sleep(2000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    
    for (WebElement linha : tr)
    {
        if(linha.getText().contains(output))
        {
            System.out.println("DEU BOM CONSAGRADO");
            break;
        }
    }
    
}    

}

public void preencher_os_campos_do_insumo(String nome, String categoria, String descricao, String estoque, String capacidade) throws Throwable
{
    driver.findElement(By.name("nome")).sendKeys(nome);
    driver.findElement(By.name("categoria")).sendKeys(categoria);
    driver.findElement(By.name("descricao")).sendKeys(descricao);
    driver.findElement(By.name("estoque")).sendKeys(estoque);
    driver.findElement(By.name("capacidade")).sendKeys(capacidade);
}

public void devo_receber_uma_mensagem(String msg) throws Throwable
{
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/div[2]/div")).findElement(By.xpath("/html/body/div[2]/div/div[3]")).getText();
}

public void clicar_no_botao_de_alerta(String botao) throws Throwable
{
    Thread.sleep(2000);
    WebElement btn = driver.findElement(By.xpath("/html/body/div[2]/div")).findElement(By.xpath("/html/body/div[2]/div/div[4]/div/button"));
    if (btn.getText().equals(botao))
        btn.click();
}

public void preencher_os_campos_do_tonel(String nome, String quantidade, String capacidade, String estoque, String tipo, String envelhecendo, String data) throws Throwable
{
    driver.findElement(By.name("nome")).sendKeys(nome);
    driver.findElement(By.name("quantidade")).sendKeys(quantidade);
    driver.findElement(By.name("capacidade")).sendKeys(capacidade);
    driver.findElement(By.name("estoque")).sendKeys(estoque); 
     
    if (envelhecendo.equals("Sim"))
    {
        driver.findElement(By.name("envelhecendo")).click();
        driver.findElement(By.name("dataEnvelhecimento")).click();
        driver.findElement(By.name("dataEnvelhecimento")).clear();
    }
    
}

public void eu_devo_ser_capaz_de_visualizar_os_campos_do_tonel(String nome, String quantidade) throws Throwable
{
    driver.navigate().refresh();
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    int  cont = Integer.parseInt(quantidade);

    for (WebElement linha : tr) 
    {
        String [] aux = linha.getText().split(" ");
        //Verifica se é menor do que 10 para fazer a comparação com adição do 0 à esquerda
        if (cont <= 9)
        {
            if (aux[0].equals(nome.toUpperCase()) && aux[1].equals("0"+Integer.toString(cont)))
                cont--;
        }
        else if (aux[0].equals(nome.toUpperCase()) && aux[1].equals(Integer.toString(cont)))
                cont--;
    }
    if (cont == 0)
        System.out.println("***DEU BOM MEU CHAPA****");
    else
        System.out.println("AZEDOOO!!");
    
}
public void atualiza_pagina() throws Throwable
{
    driver.navigate().refresh();
}

public void eu_devo_ser_capaz_de_visualizar_o_campo(String campo) throws Throwable
{
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = campo.toUpperCase();

    for (WebElement linha : tr) {
        if (linha.getText().contains(input)) {
            System.out.println("DEU BOM!!");
        }
    }
}

public void clicar_no_botao_do_tonel_com_os_campos(String botao, String nome, String capacidade, String estoque) throws Throwable
{
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = nome+" " +capacidade+ " " +estoque;
    
    //Verifica se a opção do botão é editar ou excluir i = 1 e j = 1 editar / i = 1 e j = 2 excluir / i = 1 e j = 3 detalhes
    int i, j;
    if (botao.equals("Editar"))
    {
         i = 1;
         j = 1;
    }
    else if (botao.equals("Excluir"))
    {
        i = 1;
        j = 2;
    }
    else
    {
        i = 1;
        j = 3;
    }
    //Pesquisa aonde esta o botão desejado e depois clica nele
    for (WebElement linha : tr)
    {
        if (linha.getText().contains(input.toUpperCase()))
        {
            driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table/tbody/tr["+Integer.toString(i)+"]/td[7]/button["+Integer.toString(j)+"]")).click();
            break;
        }
        else
        {
            i++;
        }
    }
}
public void alterar_os_campos_do_tonel_com(String newnome, String newcapacidade, String newestoque) throws Throwable
{
    driver.findElement(By.name("nome")).clear();
    driver.findElement(By.name("nome")).sendKeys(newnome);
    
    driver.findElement(By.name("capacidade")).clear();
    driver.findElement(By.name("capacidade")).sendKeys(newcapacidade);
    
    driver.findElement(By.name("estoque")).clear();
    driver.findElement(By.name("estoque")).sendKeys(newestoque);
}

public void preencher_os_campos_da_bebida(String nome, String descricao, String estoque, String garrafa, String tampa)
{
    driver.findElement(By.name("nome")).sendKeys(nome);
    driver.findElement(By.name("descricao")).sendKeys(descricao);
    driver.findElement(By.name("estoque")).sendKeys(estoque);
    driver.findElement(By.name("garrafa")).sendKeys(garrafa);
    driver.findElement(By.name("tampa")).sendKeys(tampa);
    
}

public void clicar_no_botao_da_bebida_com_os_campos(String botao, String nome, String descricao, String estoque) throws Throwable
{
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = nome+" " +descricao+ " " +estoque;
    
    //Verifica se a opção do botão é editar ou excluir i = 1 e j = 1 editar / i = 1 e j = 2 excluir / i = 1 e j = 3 detalhes
    int i, j;
    if (botao.equals("Editar"))
    {
         i = 1;
         j = 1;
    }
    else if (botao.equals("Excluir"))
    {
        i = 1;
        j = 2;
    }
    else
    {
        i = 1;
        j = 3;
    }
    
    //Pesquisa aonde esta o botão desejado e depois clica nele
    for (WebElement linha : tr)
    {
        if (linha.getText().contains(input.toUpperCase()))
        {
            driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table/tbody/tr["+Integer.toString(i)+"]/td[4]/button["+Integer.toString(j)+"]")).click();
            break;
        }
        else
        {
            i++;
        }
    }
}

public void alterar_os_campos_da_bebida_com(String newnome, String newdescricao, String newestoque, String garrafa, String tampa) throws Throwable
{
    driver.findElement(By.name("nome")).clear();
    driver.findElement(By.name("nome")).sendKeys(newnome);
    
    driver.findElement(By.name("descricao")).clear();
    driver.findElement(By.name("descricao")).sendKeys(newdescricao);
    
    driver.findElement(By.name("estoque")).clear();
    driver.findElement(By.name("estoque")).sendKeys(newestoque);
    
    driver.findElement(By.name("garrafa")).sendKeys(garrafa);
    driver.findElement(By.name("tampa")).sendKeys(tampa);
}

public void eu_devo_ser_capaz_de_visualizar_os_campos_da_bebida_atualizado(String newnome, String newdescricao, String newestoque) throws Throwable
{
    driver.navigate().refresh();
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = newnome + " " + newdescricao + " " + newestoque;

    for (WebElement linha : tr) 
    {
        if (linha.getText().contains(input.toUpperCase())) 
        {
            System.out.println("****SUCESSO***");
            break;
        }
    }   
}
    public static void main( String[] args ) throws Throwable
    {
        App driver = new App();
        driver.setUp();
        driver.eu_esteja_logado_no_sistema_com();
        driver.clique_no_botao_lateral("Insumo");
        driver.eu_clicar_no_botao("ADICIONAR INSUMO");
        driver.preencher_os_campos_do_insumo("UAI MINAS", "GARRAFAS", "UAI MINAS 800ML", "500", "800");
        driver.eu_clicar_no_botao("CONFIRMAR");
//       driver.clicar_no_botao_do_insumo_com_os_campos("Editar", "AMBURANINHA", "GARRAFA", "AMBURANINHA 300ML", "340", "300");
//       driver.alterar_os_campos_com("Ambura", "0", "10");
        
        
    }

}
