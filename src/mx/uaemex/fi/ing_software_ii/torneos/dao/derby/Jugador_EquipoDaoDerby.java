/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.derby;

import java.util.List;

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugadores_Equipo;

public interface Jugador_EquipoDaoDerby {
    
    Jugadores_Equipo create(Jugadores_Equipo jug_eq);
    List<Jugadores_Equipo> get(Jugadores_Equipo jug_eq);
    List<Jugadores_Equipo> getAll();
    void delete(Jugadores_Equipo jug_eq);
    Jugadores_Equipo update(Jugadores_Equipo jug_eq);

}
