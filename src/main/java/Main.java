import java.io.*;
import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);

  /**
   * Clase principal del webscrapping
   * @param args recoge los argumentos intoducidos, no se usa en esta aplicación
   */

  public static void main(String[] args) {
    File csvFile = new File("src/documents/pokemons.csv");
    File xmlFile = new File("src/documents/pokemons.xml");

    App app = new App(csvFile, xmlFile);
    app.run();
  }
}
