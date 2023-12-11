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
    
    @Override
    public String toString() {
        return "Jugadores_Equipo [jugador=" + jugador + ", equipo=" + equipo + "]";
    }

    
}
