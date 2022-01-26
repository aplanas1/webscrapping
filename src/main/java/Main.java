import java.io.*;

public class Main {

  public static void main(String[] args) {

    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));

    File csvFile = new File("src/pokemons.csv");
    File xmlFile = new File("src/pokemons.xml");
    App app = new App(csvFile, xmlFile);
  }
}
