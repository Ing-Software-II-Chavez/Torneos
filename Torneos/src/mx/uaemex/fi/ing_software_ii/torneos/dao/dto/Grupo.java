/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * @Jesús Alberto Sanchez Mendieta
 * @Isaac Misael Vazquez Albor
 * @Francisco Gamaliel Alvaro Portillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.dto;

public class Grupo {

    private int id;
    private String nombre;
    private int torneo;

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
    public int getTorneo() {
        return torneo;
    }
    public void setTorneo(int torneo) {
        this.torneo = torneo;
    }
    
    @Override
    public String toString() {
        return "Grupo [id=" + id + ", nombre=" + nombre + ", torneo=" + torneo + "]";
    }

    

    
}
