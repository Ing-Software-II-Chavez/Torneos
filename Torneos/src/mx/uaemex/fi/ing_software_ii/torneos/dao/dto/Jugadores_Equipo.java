/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * @Jesús Alberto Sanchez Mendieta
 * @Isaac Misael Vazquez Albor
 * @Francisco Gamaliel Alvaro Portillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.dto;

public class Jugadores_Equipo {
    
    private int jugador;
    private int equipo;
    private String nombre_equipo;
    
    public int getJugador() {
        return jugador;
    }
    public void setJugador(int jugador) {
        this.jugador = jugador;
    }
    public int getEquipo() {
        return equipo;
    }
    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }
    
    public String getNombre_equipo() {
        return nombre_equipo;
    }
    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }
    
    @Override
    public String toString() {
        return "Jugadores_Equipo [jugador=" + jugador + ", equipo=" + equipo + ", nombre_equipo=" + nombre_equipo + "]";
    }

    
}
