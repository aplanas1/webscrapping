import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvBadConverterException;
import org.apache.commons.collections.ArrayStack;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
  // Libreria necesaria a implementar: https://www.baeldung.com/opencsv

  public static void main(String[] args) {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));
  //  System.out.println(System.getenv(""));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    //File pathBinary = new File("src/main/resources/firefox");
    //FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
    //DesiredCapabilities desired = new DesiredCapabilities();
    FirefoxOptions options = new FirefoxOptions();
    //desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
    WebDriver driver = new FirefoxDriver(options);
    driver.get("https://pokemon.fandom.com/es/wiki/Categor%C3%ADa:Lista_de_Pok%C3%A9mon_por_nombre");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.elementToBeClickable(new By.ByClassName("NN0_TB_DIsNmMHgJWgT7U")));

    WebElement aceptar = driver.findElement(new By.ByClassName("NN0_TB_DIsNmMHgJWgT7U"));
    aceptar.click();

    List<WebElement> pokemons = new ArrayList<>();
    List<String> links = new ArrayList();
    WebElement nombre;
    WebElement tipo1 = null;
    WebElement tipo2 = null;
    WebElement habilidad1 = null;
    WebElement habilidad2 = null;
    WebElement descripcion;
    int tipos = 0;
    int habilidades = 0;

    char letra = 'A';
    int f = 0;
    try {
      BufferedWriter outputStream = new BufferedWriter(new FileWriter(new File("src/pokemons.csv")));
      for (int i = 0; /*i < 26*/ i < 1; i++) {
        driver.get("https://pokemon.fandom.com/es/wiki/Categor%C3%ADa:Lista_de_Pok%C3%A9mon_por_nombre?from=" + letra);
        pokemons = driver.findElements(new By.ByClassName("category-page__member-link"));
        for (WebElement pokemon: pokemons) {
          links.add(pokemon.getAttribute("href"));
        }
        for (String link : links) {
          //wait.until(ExpectedConditions.elementToBeClickable(link));
          //System.out.println(pokemon.getAttribute("title"));
          driver.navigate().to(link);
          nombre = driver.findElement(new By.ByClassName("page-header__title"));
          outputStream.write(nombre.getText() + ", ");
          System.out.println("Nombre: " + nombre.getText());
          try {
            tipo1 = driver.findElement(new By.ByXPath("//div[@data-source='tipo1']//div[1]//a[1]"));
            tipos = 0;
          } catch(Exception err){}
          try {
            tipo2 = driver.findElement(new By.ByXPath("//div[@data-source='tipo1']//div[1]//a[2]"));
            tipos = 1;
          } catch(Exception err){}
          if (tipos == 0) {
            System.out.println("Tipo: " + tipo1.getAttribute("title"));
            outputStream.write(tipo1.getAttribute("title") + ", ");
            outputStream.write("- , ");
          } else if (tipos == 1) {
            System.out.println("Tipo 1: " + tipo1.getAttribute("title"));
            System.out.println("Tipo 2: " + tipo2.getAttribute("title"));
            outputStream.write(tipo1.getAttribute("title") + ", ");
            outputStream.write(tipo2.getAttribute("title") + ", ");
          }

          try {
            habilidad1 = driver.findElement(new By.ByXPath("//div[@data-source='habilidad1']//div[1]//a[1]"));
            habilidades = 0;
          } catch(Exception err){}
          try {
            habilidad2 = driver.findElement(new By.ByXPath("//div[@data-source='habilidad1']//div[1]//a[2]"));
            habilidades = 1;
          } catch(Exception err){}
          if (habilidades == 0) {
            System.out.println("Habilidad: " + habilidad1.getAttribute("title"));
            outputStream.write(habilidad1.getAttribute("title") + ", ");
            outputStream.write("- , ");
          } else if (habilidades == 1) {
            System.out.println("Habilidad 1: " + habilidad1.getAttribute("title"));
            System.out.println("Habilidad 2: " + habilidad2.getAttribute("title"));
            outputStream.write(habilidad1.getAttribute("title") + ", ");
            outputStream.write(habilidad2.getAttribute("title") + ", ");
          }
          descripcion = driver.findElement(new By.ByXPath("//div[@class='mw-parser-output']//p[3]"));
          outputStream.write(descripcion.getText() + "\n");
          System.out.println("Descripcion: " + descripcion.getText());

          driver.navigate().back();
        }
        letra++;
      }
      outputStream.close();
      driver.quit();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }
}
