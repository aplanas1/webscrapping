import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="PokemonCollection")
public class PokemonCollection {
    List<Pokemon> pokemons;
    /**
     * Este metodo constructor crea la lista.
     */
    public PokemonCollection(){
        pokemons = new ArrayList<>();
    }

    /**
     * Este es el metodo getter, permite obetner la lista de pokemons.
     * @return Retorna una lista de pokemons.
     */
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * Este es el metodo setter, permite setear los pokemons.
     * @param pk Recibe e iguala unas listas de pokemons.
     */
    @XmlElement(name = "Pokemons")
    public void setPokemons(List<Pokemon> pk) {
        pokemons = pk;
    }

    /**
     * El metodo addPokemon permite añadir nuevos pokemons a la lista
     * @param nombre el nombre del pokemon
     * @param imagen la imagen del pokemon
     * @param tipo1 el primer tipo del pokemon
     * @param tipo2 el segundo tipo del pokemon (puede no tener)
     * @param habilidad1 la primera habilidad del pokemon
     * @param habilidad2 la segunda habilidad del pokemon (puede no tener)
     * @param descripcion la descripcion del pokemon
     */

    public void addPokemon(String nombre, String imagen, String tipo1, String tipo2, String habilidad1, String habilidad2, String descripcion){
        pokemons.add(new Pokemon(
                nombre,
                imagen,
                tipo1,
                tipo2,
                habilidad1,
                habilidad2,
                descripcion));
    }
}
