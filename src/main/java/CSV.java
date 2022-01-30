import com.opencsv.CSVWriter;
import java.io.*;
import java.util.List;

/**
 * Esta clase sirve para crear el csv.
 */
public class CSV {
    /**
     * Este metodo constructor sirve para crear el csv
     * @param pokemones Recibe una lista con la cual va a trabajar para escribir en el csv
     * @param file Recibe un file el cual  eliminara y despues trabajara con el.
     */
    CSV(PokemonCollection pokemones, File file){
        CSVWriter csvWriter;
        String[] infoPokemon =  new String[7];
        List<Pokemon> collection = pokemones.getPokemons();
        file.delete();
        for (Pokemon pok: collection) {
            infoPokemon[0] = pok.nombre;
            infoPokemon[1] = pok.imagen;
            infoPokemon[2] = pok.tipo1;
            infoPokemon[3] = pok.tipo2;
            infoPokemon[4] = pok.habilidad1;
            infoPokemon[5] = pok.habilidad2;
            infoPokemon[6] = pok.descripcion;

            try {
                csvWriter = new CSVWriter(new FileWriter(file, true));
                csvWriter.writeNext(infoPokemon);
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ruta del archivo " + file.getPath());
    }
}
