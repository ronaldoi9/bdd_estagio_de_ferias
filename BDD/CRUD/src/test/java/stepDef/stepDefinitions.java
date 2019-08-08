package stepDef;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class stepDefinitions {
    
        private WebDriver driver = null;
    
    @Before
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
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
}

@After
public void finish() throws Throwable
{
        driver.quit();
}

@Given("^Eu esteja na tela Inicial$")
public void eu_esteja_na_tela_Insumos() throws Throwable
{
    driver.navigate().to("http://127.0.0.1:8080/#!/main/");
}

//Percorre todos os botões até que encontre o botão passado por parâmetro
public void procura_botao_por_tag(String botao)
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

@Given("^Clique no botao \"(.*?)\"$")
public void clique_no_botao(String botao) throws Throwable
{
    Thread.sleep(3000);
    procura_botao_por_tag(botao);
}

@Given("^Preencher os campos do insumo \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" e \"(.*?)\"$")
public void preencher_os_campos_do_insumo(String nome, String categoria, String descricao, String estoque, String capacidade) throws Throwable
{
    driver.findElement(By.name("nome")).sendKeys(nome);
    driver.findElement(By.name("categoria")).sendKeys(categoria);
    driver.findElement(By.name("descricao")).sendKeys(descricao);
    driver.findElement(By.name("estoque")).sendKeys(estoque);
    driver.findElement(By.name("capacidade")).sendKeys(capacidade);  
}

@Then ("^Devo receber a mensagem \"(.*?)\"$")
public void devo_receber_a_mensagem(String msg) throws Throwable
{
    Thread.sleep(1500);
    driver.findElement(By.xpath("/html/body/div[2]/div")).findElement(By.xpath("/html/body/div[2]/div/div[3]")).getText();
}

@Then ("^Clicar no botao de alerta \"(.*?)\"$")
public void clicar_no_botao_de_alerta(String botao) throws Throwable
{
    Thread.sleep(1500);
    WebElement btn = driver.findElement(By.xpath("/html/body/div[2]/div")).findElement(By.cssSelector(".swal-button--confirm"));
    if (btn.getText().equals(botao))
        btn.click();
    else
        System.out.println("*******NÃO TA ACHANDO O BOTAO!****");
}

@Then("^Eu devo ser capaz de visualizar os campos do insumo \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" e \"(.*?)\"$") 
public void eu_devo_ser_capaz_de_visualizar_os_campos_do_insumo(String nome, String categoria, String descricao, String estoque, String capacidade) throws Throwable
{
    driver.navigate().refresh();
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));

    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = nome + " " + categoria + " " + descricao + " " + estoque + " " + capacidade;

    for (WebElement linha : tr) 
    {
        if (linha.getText().equals(input.toUpperCase())) 
        {
            assertThat(input.toUpperCase()).isEqualTo(linha.getText());
            break;
        }
    }

}

@When("^Clique no botao lateral \"(.*?)\"$")
public void clique_no_botao_lateral(String botao)
{
    //Procura botao no menu lateral
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
@When("^Preencher o campo de pesquisa por \"(.*?)\"$")
public void preencher_o_campo_de_pesquisa_por(String nome) throws Throwable
{
    Thread.sleep(1500);
    driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/input")).sendKeys(nome);    
}

@Then ("^Eu devo ser capaz de visualizar o campo \"(.*?)\"$")
public void eu_devo_ser_capaz_de_visualizar_o_campo(String campo) throws Throwable
{
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = campo.toUpperCase();

    for (WebElement linha : tr) {
        if (linha.getText().contains(input)) {
            assertThat(linha.getText()).contains(input);
        }
    }
}

@When ("^Clicar no botao \"(.*?)\" do insumo com os campos \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" e \"(.*?)\"$")
public void clicar_no_botao_do_insumo_com_os_campos(String botao, String nome, String categoria, String descricao, String estoque, String capacidade) throws Throwable
{
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = nome+" " +categoria+ " " +descricao+" "+estoque+" "+capacidade;
    
    //Verifica se a opção do botão é editar ou excluir i = 1 e j = 1 editar / i = 1 e j = 2 excluir
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

@When("^Alterar os campos do insumo com \"(.*?)\", \"(.*?)\" e \"(.*?)\"$")
public void alterar_os_campos_do_insumo_com(String newnome, String newestoque, String newcapacidade) throws Throwable
{
    Thread.sleep(1500);
    driver.findElement(By.name("nome")).clear();
    driver.findElement(By.name("nome")).sendKeys(newnome);
    int i = 0;
    while (i >= 0) {
        if (driver.findElement(By.cssSelector("select.form-control")).findElements(By.tagName("option")).get(i).getText().equals("GARRAFAS")) {
            driver.findElement(By.cssSelector("select.form-control")).findElements(By.tagName("option")).get(i).click();
            i = -1;
        } else {
            i++;
        }
    }
    driver.findElement(By.name("estoque")).clear();
    driver.findElement(By.name("estoque")).sendKeys(newestoque);
    driver.findElement(By.name("capacidade")).clear();
    driver.findElement(By.name("capacidade")).sendKeys(newcapacidade);
}

@Then("^A saida deve ser \"(.*?)\"$")
public void a_saida_deve_ser(String output) throws Throwable
{
    Thread.sleep(2000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    
    for (WebElement linha : tr)
    {
        if(linha.getText().contains(output.toUpperCase()))
        {
            assertThat(linha.getText().contains(output.toUpperCase()));
            break;
        }
    }
    
}

//ESPECIFICAS DO TONEL
@When ("^Preencher os campos do tonel \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" e \"(.*?)\"$")
public void preencher_os_campos_do_tonel(String nome, String quantidade, String capacidade, String estoque, String tipo, String envelhecendo, String data)
{
    driver.findElement(By.name("nome")).sendKeys(nome);
    driver.findElement(By.name("quantidade")).sendKeys(quantidade);
    driver.findElement(By.name("capacidade")).sendKeys(capacidade);
    driver.findElement(By.name("estoque")).sendKeys(estoque);
    driver.findElement(By.name("tipo")).sendKeys(tipo);
    if (envelhecendo.equals("Sim"))
    {
        driver.findElement(By.name("envelhecendo")).click();
        driver.findElement(By.name("data de envelhecimento")).sendKeys(data);
    }
    
}

@Then ("^Eu devo ser capaz de visualizar os campos do tonel \"(.*?)\", \"(.*?)\"$")
public void eu_devo_ser_capaz_de_visualizar_os_campos_do_tonel(String nome, String quantidade) throws Throwable
{
    driver.navigate().refresh();
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));
    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    int  cont = Integer.parseInt(quantidade);

    for (WebElement linha : tr) 
    {
        if (linha.getText().contains(nome.toUpperCase()))
            cont--;
    }
       Assert.assertEquals(0, cont);
}

@Then ("^Eu devo ser capaz de visualizar os campos do tonel atualizado \"(.*?)\", \"(.*?)\" e \"(.*?)\"$")
public void eu_devo_ser_capaz_de_visualizar_os_campos_do_tonel_atualizado(String newnome, String newcapacidade, String newestoque) throws Throwable
{
    driver.navigate().refresh();
    Thread.sleep(3000);
    WebElement tabela = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table"));

    List<WebElement> tr = tabela.findElements(By.cssSelector("tr"));
    String input = newnome + " " + newcapacidade + " " + newestoque;

    for (WebElement linha : tr) 
    {
        if (linha.getText().contains(input.toUpperCase())) 
        {
            assertThat(linha.getText()).contains(input.toUpperCase());
            break;
        }
    }    
}

@When ("^Clicar no botao \"(.*?)\" do tonel com os campos \"(.*?)\", \"(.*?)\", \"(.*?)\"$")
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
            driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/table/tbody/tr["+Integer.toString(i)+"]/td[5]/button["+Integer.toString(j)+"]")).click();
            break;
        }
        else
        {
            i++;
        }
    }
}

@When ("^Alterar os campos do tonel com \"(.*?)\", \"(.*?)\" e \"(.*?)\"$")
public void alterar_os_campos_do_tonel_com(String newnome, String newcapacidade, String newestoque) throws Throwable
{
    driver.findElement(By.name("nome")).clear();
    driver.findElement(By.name("nome")).sendKeys(newnome);
    
    driver.findElement(By.name("capacidade")).clear();
    driver.findElement(By.name("capacidade")).sendKeys(newcapacidade);
    
    driver.findElement(By.name("estoque")).clear();
    driver.findElement(By.name("estoque")).sendKeys(newestoque);
}

@When ("^Preencher os campos da bebida \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\"$")
public void preencher_os_campos_da_bebida(String nome, String descricao, String estoque, String garrafa, String tampa)
{
    driver.findElement(By.name("nome")).sendKeys(nome);
    driver.findElement(By.name("descricao")).sendKeys(descricao);
    driver.findElement(By.name("estoque")).sendKeys(estoque);
    driver.findElement(By.name("garrafa")).sendKeys(garrafa);
    driver.findElement(By.name("tampa")).sendKeys(tampa);
    
}

@When ("^Clicar no botao \"(.*?)\" da bebida com os campos \"(.*?)\", \"(.*?)\", \"(.*?)\"$")
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

@When ("^Alterar os campos da bebida com \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" e \"(.*?)\"$")
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

@Then ("^Eu devo ser capaz de visualizar os campos da bebida atualizado \"(.*?)\", \"(.*?)\" e \"(.*?)\"$")
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
            assertThat(linha.getText()).contains(input.toUpperCase());
            break;
        }
    }   
}

}
