/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * @Jesús Alberto Sanchez Mendieta
 * @Isaac Misael Vazquez Albor
 * @Francisco Gamaliel Alvaro Portillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.derby;

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugador;

import java.util.List;
public interface JugadorDaoDerby {

    Jugador create(Jugador j);
    List<Jugador> get(Jugador j);
    List<Jugador> getAll();
    void delete(Jugador j);
    Jugador update(Jugador j);

}
