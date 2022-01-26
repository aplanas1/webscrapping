import java.io.*;

public class CSV {

    private File file;
    private File newFile;
    Pokemon pokemon;

    public CSV(File file, Pokemon pokemon) {
        this.file = file;
        this.pokemon = pokemon;
        String header = "nombre,tipo1,tipo2,habilidad1,habilidad2,descripcion";
        if (!file.exists()) write(header);

    }

    public void pokemonToCSV() {
        String str = pokemon.getNombre()+","+pokemon.getTipo1()+","+pokemon.getTipo2()+","+pokemon.getHabilidad1()+","+pokemon.getHabilidad2()+","+pokemon.getDescripcion();
        write(str);
    }
    public void write(String str) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(str);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
