package support;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static runner.RunBase.getDriver;

public class Commands {

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
            waitElementBeClickable(element, 10000);
            getDriver().findElement(element).click();
            System.out.println("Clicou no elemento: " + element);
        } catch (Exception error) {
            System.out.println("*******Aconteceu um erro ao tentar clicar no elemento: " + element);
            new Exception(error);
        }
        System.out.println("###########################");
    }

    public static void fillField(By element, String value) {
        System.out.println("###################");
        try {
            System.out.println("Vai preencher o campo: " + element);
            waitElementBeClickable(element, 10);
            getDriver().findElement(element).sendKeys(value);
            System.out.println("Clicou no elemento: " + element);
        } catch (Exception error) {
            System.out.println("*******Aconteceu um erro ao tentar preencher o campo: " + element);
            new Exception(error);
        }
        System.out.println("###########################");
    }

    public static void checkMessage(By element, String expectedMessage) {
        String actualMessage = "";
        System.out.println("###########################");

            System.out.println("Vai validar mensagem:" + expectedMessage);
            waitElementBeVisible(element, 10);
            actualMessage = getDriver().findElement(element).getText();
            // quanto não der certo , vai quebrar o teste!!!
            Assert.assertEquals("Error ao validar mensagens!", expectedMessage, actualMessage);

            System.out.println("Validou mensagem" + expectedMessage);
            System.out.println("###########################");

        }



    }



