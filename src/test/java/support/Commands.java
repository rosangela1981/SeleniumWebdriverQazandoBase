package support;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static runner.RunBase.getDriver;

public class Commands {

    // Tempo de 20s para garantir estabilidade em ambiente de CI/CD
    private static final int DEFAULT_WAIT_TIME = 20;

    public static void waitElementBeClickable(By element, Integer tempo) {
        WebDriverWait wait = new WebDriverWait(getDriver(), tempo);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitElementBeVisible(By element, Integer tempo) {
        WebDriverWait wait = new WebDriverWait(getDriver(), tempo);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void clickElement(By element) {
        System.out.println("###################");
        try {
            System.out.println("*******Vai clicar no elemento: " + element);
            waitElementBeClickable(element, DEFAULT_WAIT_TIME);
            getDriver().findElement(element).click();
            System.out.println("Clicou no elemento: " + element);
        } catch (Exception error) {
            System.out.println("*******Aconteceu um erro ao tentar clicar no elemento: " + element);
            // Lança a exceção para o JUnit marcar o teste como falha
            throw new RuntimeException("Erro ao clicar no elemento: " + element, error);
        }
        System.out.println("###########################");
    }

    public static void fillField(By element, String value) {
        System.out.println("###################");
        try {
            System.out.println("Vai preencher o campo: " + element + " com o valor: " + value);
            waitElementBeClickable(element, DEFAULT_WAIT_TIME);
            getDriver().findElement(element).sendKeys(value);
            System.out.println("Preencheu o campo: " + element);
        } catch (Exception error) {
            System.out.println("*******Aconteceu um erro ao tentar preencher o campo: " + element);
            throw new RuntimeException("Erro ao preencher o campo: " + element, error);
        }
        System.out.println("###########################");
    }

    public static void checkMessage(By element, String expectedMessage) {
        System.out.println("###########################");
        try {
            System.out.println("Vai validar mensagem: " + expectedMessage);
            waitElementBeVisible(element, DEFAULT_WAIT_TIME);
            String actualMessage = getDriver().findElement(element).getText();
            Assert.assertEquals("Erro ao validar mensagens!", expectedMessage, actualMessage);
            System.out.println("Validou mensagem: " + expectedMessage);
        } catch (Exception error) {
            System.out.println("*******Aconteceu um erro ao validar mensagem: " + error.getMessage());
            throw error; // Repassa o erro do Assert para o JUnit
        }
        System.out.println("###########################");
    }
}


