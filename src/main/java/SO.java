import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.awt.*;

public class SO {
    /**
     * Este metodo permite elegir el sistema operativo desde el que trabajas.
     * @return Este devuelve el Webdriver con la info del geckodriver segun el sistema operativo.
     */
    public WebDriver elegirSO(){
        Menu menu = new Menu();
        WebDriver driver = null;
        String[] opcionesSO = {"Windows", "Linux"};
        String opcionSO = menu.elegirOpcion(opcionesSO);

        if ("1".equals(opcionSO)){
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
            driver.get("https://pokemon.fandom.com/es/wiki/Categor%C3%ADa:Lista_de_Pok%C3%A9mon_por_nombre");

        } else if ("2".equals(opcionSO)){
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
            driver.get("https://pokemon.fandom.com/es/wiki/Categor%C3%ADa:Lista_de_Pok%C3%A9mon_por_nombre");
        }
        return driver;
    }
}
