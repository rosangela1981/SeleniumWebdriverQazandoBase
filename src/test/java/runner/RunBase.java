package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class RunBase {

    static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome");
            return getDriver(browser);
        }
        return driver;
    }

    public static WebDriver getDriver(String browser) {
        if (driver != null) {
            return driver;
        }

        switch (browser) {
            case "chrome":
                // Para você rodar no seu PC (com janela aberta)
                driver = new ChromeDriver();
                break;
            case "chrome-ci":
                // Para o GitHub Actions (invisível e estável)
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Navegador não suportado: " + browser);
        }

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        return driver;
    }

    public static void finish() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}