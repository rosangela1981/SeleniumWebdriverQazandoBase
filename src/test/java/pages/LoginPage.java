package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.RunCucumber;

import static support.Commands.*;

public class LoginPage extends RunCucumber {

    // elementos
    private String URL = "http://automationpratice.com.br/";
    private By botaoAcessarLogin = By.cssSelector(".right_list_fix > li > a > .fa-user");
    private By campoEmail = By.id("user");
    private By campoSenha = By.id("password");
    private By botaoFazerLogin = By.id("btnLogin");
    private By botaoAcessarCadastro = By.cssSelector(".right_list_fix > li > a > .fa-lock");

    // ações / funções / métodos
    public void acessarAplicao() {
        getDriver().get(URL);
    }

    public void acessarTelaLogin() {
        clickElement(botaoAcessarLogin);

    }

    public void preencheEmail(String email){
         getDriver().findElement(campoEmail).sendKeys(email);
    }

    public void preencherSenha(String senha){
        fillField(campoSenha, senha);

    }

    public void clicarLogin(){
        clickElement(By.id("btnLogin"));
    }

    public void verificaLoginSucesso(){
        checkMessage (By.id("swal2-title"), "Login realizado");

    }


    public void verificaCampoVazio(String message){

        // Espera até 10 segundos pelo elemento aparecer antes de tentar ler o texto
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("invalid_input")));
        String textError = getDriver().findElement(By.className("invalid_input")).getText();
        Assert.assertEquals(message, textError);
    }

    public void acessarTelaCadastro(){
        clickElement(botaoAcessarCadastro);

    }

}
