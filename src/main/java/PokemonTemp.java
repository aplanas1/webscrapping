public class PokemonTemp {
    String imagen;
    String nombre;
    String tipo1;
    String tipo2;
    String habilidad1;
    String habilidad2;
    String descripcion;

    public PokemonTemp() {}

    public PokemonTemp(String imagen, String nombre, String tipo1, String tipo2, String habilidad1, String habilidad2, String descripcion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.habilidad1 = habilidad1;
        this.habilidad2 = habilidad2;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public String getHabilidad1() {
        return habilidad1;
    }

    public void setHabilidad1(String habilidad1) {
        this.habilidad1 = habilidad1;
    }

    public String getHabilidad2() {
        return habilidad2;
    }

    public void setHabilidad2(String habilidad2) {
        this.habilidad2 = habilidad2;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
