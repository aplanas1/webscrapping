import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase App; Esta clase es donde se ejecuta la mayoria del codigo.
 */

public class App implements Runnable {

    // Declaracion de clases
    SO so = new SO();
    Pokemon pokemon = new Pokemon();
    PokemonCollection pokemonCollection = new PokemonCollection();
    File csvFile;
    File xmlFile;
    CSV csv;
    JAXB jaxb;

    /**
     * El constructo de la clase App
     * @param csvFile el archivo csv declarado en la clase main
     * @param xmlFile el archivo xml declarado en la clase main
     */

    public App(File csvFile, File xmlFile){
        this.csvFile = csvFile;
        this.xmlFile = xmlFile;
    }

    /**
     * Run de la clase App, es donde se encuentra el codigo
     */

    @Override
    public void run(){

        // Permite elegir el sistema operativo, por lo que proporciona compatibilidad tanto con Windows como con Ubuntu
        System.out.println("¿Cual es tu Sistema Operativo(SO)?");
        WebDriver driver = so.elegirSO();

        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv("HOME"));

        //Delay para aceptar las cookies
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(new By.ByClassName("NN0_TB_DIsNmMHgJWgT7U")));
        WebElement aceptar = driver.findElement(new By.ByClassName("NN0_TB_DIsNmMHgJWgT7U"));
        aceptar.click();

        //Declaración de elementos que seran usados como base del websrapping
        List<WebElement> pokemons = new ArrayList<>();
        List<String> links = new ArrayList();
        char letra = 'A';

        //Busca los links de todos los pokemons de la wiki
        try {
            for (int i = 0; i < 26; i++) {
                driver.get("https://pokemon.fandom.com/es/wiki/Categor%C3%ADa:Lista_de_Pok%C3%A9mon_por_nombre?from=" + letra);
                pokemons = driver.findElements(new By.ByClassName("category-page__member-link"));
                for (WebElement poke: pokemons) {
                    String control = poke.getAttribute("title");
                    System.out.println(control);
                    //Descarta el listado de los pokemons de Pokémon GO
                    if (!control.equals("Lista de Pokémon de Pokémon GO")) {
                        if (!control.equals("Lista de Pokémon en Pokémon GO de la primera generación")){
                        links.add(poke.getAttribute("href"));
                        }
                    }
                }
                letra++;
            }
            //Bucle para procesar los pokemons
            for (String link : links) {
                procesarPokemon(driver, link);
            }
            //Crea los documentos csv y xml
            csv = new CSV(pokemonCollection, csvFile);
            jaxb = new JAXB(pokemonCollection, xmlFile);
            driver.quit();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Metodo procesarPokemon, en este metodo se procesan los datos de los pokemons
     * @param driver el driver del webscrapping
     * @param link el link de la web
     * @throws IOException
     */

    public void procesarPokemon(WebDriver driver, String link) throws IOException {
        WebElement nombre;
        WebElement imagen;
        WebElement tipo1 = null;
        String tipo1n = "";
        WebElement tipo2 = null;
        String tipo2n = "";
        WebElement habilidad1 = null;
        String habilidad1n = "";
        WebElement habilidad2 = null;
        String habilidad2n = "";
        WebElement descripcion;

        driver.navigate().to(link);

        //Busca los atributos requeridos
        nombre = driver.findElement(new By.ByClassName("page-header__title"));
        imagen = driver.findElement(new By.ByClassName("pi-image-thumbnail"));
        //Los pokemons pueden tener 1 o 2 tipos y habilidades, lo que hace que la estructura de la pagina cambie, esto previene que el webscrapping se detenga
        try {
            tipo1 = driver.findElement(new By.ByXPath("//div[@data-source='tipo1']//div[1]//a[1]"));
            tipo1n = tipo1.getAttribute("title");
        } catch(Exception err){}
        try {
            tipo2 = driver.findElement(new By.ByXPath("//div[@data-source='tipo1']//div[1]//a[2]"));
            tipo2n = tipo2.getAttribute("title");
        } catch(Exception err){}
        try {
            habilidad1 = driver.findElement(new By.ByXPath("//div[@data-source='habilidad1']//div[1]//a[1]"));
            habilidad1n = habilidad1.getText();
        } catch(Exception err){}
        try {
            habilidad2 = driver.findElement(new By.ByXPath("//div[@data-source='habilidad1']//div[1]//a[2]"));
            habilidad2n = habilidad2.getText();
        } catch(Exception err){}
        descripcion = driver.findElement(new By.ByXPath("//div[@class='mw-parser-output']//p[3]"));

        System.out.println("Nombre: " + nombre.getText());
        System.out.println("Imagen: " + imagen.getAttribute("src"));
        System.out.println("Tipo 1: " + tipo1n);
        System.out.println("Tipo 2: " + tipo2n);
        System.out.println("Habilidad 1: " + habilidad1n);
        System.out.println("Habilidad 2: " + habilidad2n);
        System.out.println("Descripcion: " + descripcion.getText());

        //Añade el pokemon en cuestion a la lista de pokemons
        pokemonCollection.addPokemon(
                nombre.getText(),
                imagen.getAttribute("src"),
                tipo1n,
                tipo2n,
                habilidad1n,
                habilidad2n,
                descripcion.getText());
    }
}
