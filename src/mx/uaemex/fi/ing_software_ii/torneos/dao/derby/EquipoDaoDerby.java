/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.derby;

import java.util.List;

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Equipo;

public interface EquipoDaoDerby {
    
    Equipo create(Equipo e);
    List<Equipo> get(Equipo e);
    List<Equipo> getAll();
    void delete(Equipo e);
    Equipo update(Equipo e);
}
