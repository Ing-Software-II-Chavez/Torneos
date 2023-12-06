/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.dto;

public class Torneo {

    private int id;
    private String nombre;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Torneo [id=" + id + ", nombre=" + nombre + "]";
    }

}
