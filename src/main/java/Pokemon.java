public class Pokemon {
    String nombre;
    String imagen;
    String tipo1;
    String tipo2;
    String habilidad1;
    String habilidad2;
    String descripcion;

    /**
     * Contructor vacío de pokemon
     */
    public Pokemon() {}

    /**
     * Constuctor del pokemon con datos
     * @param nombre el nombre del pokemon
     * @param imagen la imagen del pokemon
     * @param tipo1 el primer tipo del pokemon
     * @param tipo2 el segundo tipo del pokemon (puede no tener)
     * @param habilidad1 la primera habilidad del pokemon
     * @param habilidad2 la segunda habilidad del pokemon (puede no tener)
     * @param descripcion la descripcion del pokemon
     */
    public Pokemon(String nombre, String imagen, String tipo1, String tipo2, String habilidad1, String habilidad2, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.habilidad1 = habilidad1;
        this.habilidad2 = habilidad2;
        this.descripcion = descripcion;
    }

    //Getters y setters de los distintos campos del pokemon

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
