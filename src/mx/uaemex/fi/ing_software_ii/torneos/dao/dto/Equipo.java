/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.dto;

public class Equipo {

    private int id;
    private String nombre;
    private int grupo;

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
    public int getGrupo() {
        return grupo;
    }
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
    
    @Override
    public String toString() {
        return "Equipo [id=" + id + ", nombre=" + nombre + ", grupo=" + grupo + "]";
    }

    
    
}
