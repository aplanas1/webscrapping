import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class App {

    Pokemon pokemon = new Pokemon();
    File csvFile;
    File xmlFile;

    public App(File csvFile, File xmlFile) {
        this.csvFile = csvFile;
        this.xmlFile = xmlFile;
    }

    public void run(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://pokemon.fandom.com/es/wiki/Categor%C3%ADa:Lista_de_Pok%C3%A9mon_por_nombre");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(new By.ByClassName("NN0_TB_DIsNmMHgJWgT7U")));

        WebElement aceptar = driver.findElement(new By.ByClassName("NN0_TB_DIsNmMHgJWgT7U"));
        aceptar.click();

        List<WebElement> pokemons = new ArrayList<>();
        List<String> links = new ArrayList();
        char letra = 'A';

        try {
            BufferedWriter outputStream = new BufferedWriter(new FileWriter(csvFile));
            for (int i = 0; /*i < 26*/ i < 1; i++) {
                driver.get("https://pokemon.fandom.com/es/wiki/Categor%C3%ADa:Lista_de_Pok%C3%A9mon_por_nombre?from=" + letra);
                pokemons = driver.findElements(new By.ByClassName("category-page__member-link"));
                for (WebElement poke: pokemons) {
                    links.add(poke.getAttribute("href"));
                }
                for (String link : links) {
                    procesarPokemon(driver, outputStream, link);
                }
                letra++;
            }
            outputStream.close();
            driver.quit();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void procesarPokemon(WebDriver driver, BufferedWriter outputStream, String link) throws IOException {
        driver.navigate().to(link);

        procesarNombre(driver, outputStream);
        procesarTipo(driver, outputStream);
        procesarHabilidad(driver, outputStream);
        procesarDescripcion(driver, outputStream);

        driver.navigate().back();
    }

    public void procesarNombre(WebDriver driver, BufferedWriter outputStream) throws IOException {
        WebElement nombre;

        nombre = driver.findElement(new By.ByClassName("page-header__title"));
        pokemon.setNombre(nombre.getText());
        System.out.println("Nombre: " + pokemon.getNombre());
    }

    public void procesarTipo(WebDriver driver, BufferedWriter outputStream) throws IOException {
        WebElement tipo1 = null;
        WebElement tipo2 = null;
        int tipos = 0;

        try {
            tipo1 = driver.findElement(new By.ByXPath("//div[@data-source='tipo1']//div[1]//a[1]"));
            tipos = 0;
        } catch(Exception err){}
        try {
            tipo2 = driver.findElement(new By.ByXPath("//div[@data-source='tipo1']//div[1]//a[2]"));
            tipos = 1;
        } catch(Exception err){}
        if (tipos == 0) {
            pokemon.setTipo1(tipo1.getAttribute("title"));
            System.out.println("Tipo: " + pokemon.getTipo1());
        } else if (tipos == 1) {
            pokemon.setTipo1(tipo1.getAttribute("title"));
            pokemon.setTipo2(tipo2.getAttribute("title"));
            System.out.println("Tipo 1: " + pokemon.getTipo1());
            System.out.println("Tipo 2: " + pokemon.getTipo2());
        }
    }

    public void procesarHabilidad(WebDriver driver, BufferedWriter outputStream) throws IOException {
        WebElement habilidad1 = null;
        WebElement habilidad2 = null;
        int habilidades = 0;

        try {
            habilidad1 = driver.findElement(new By.ByXPath("//div[@data-source='habilidad1']//div[1]//a[1]"));
            habilidades = 0;
        } catch(Exception err){}
        try {
            habilidad2 = driver.findElement(new By.ByXPath("//div[@data-source='habilidad1']//div[1]//a[2]"));
            habilidades = 1;
        } catch(Exception err){}
        if (habilidades == 0) {
            pokemon.setHabilidad1(habilidad1.getAttribute("title"));
            System.out.println("Habilidad: " + pokemon.getHabilidad1());
        } else if (habilidades == 1) {
            pokemon.setHabilidad1(habilidad1.getAttribute("title"));
            pokemon.setHabilidad2(habilidad2.getAttribute("title"));
            System.out.println("Habilidad 1: " + pokemon.getHabilidad1());
            System.out.println("Habilidad 2: " + pokemon.getHabilidad2());
        }
    }

    public void procesarDescripcion(WebDriver driver, BufferedWriter outputStream) throws IOException {
        WebElement descripcion;

        descripcion = driver.findElement(new By.ByXPath("//div[@class='mw-parser-output']//p[3]"));
        pokemon.setDescripcion(descripcion.getText());
        System.out.println("Descripcion: " + pokemon.getDescripcion());
    }
}
