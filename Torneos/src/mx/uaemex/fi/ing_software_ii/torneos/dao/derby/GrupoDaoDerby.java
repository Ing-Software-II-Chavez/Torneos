/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * @Jesús Alberto Sanchez Mendieta
 * @Isaac Misael Vazquez Albor
 * @Francisco Gamaliel Alvaro Portillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.derby;

import java.util.List;

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Grupo;

public interface GrupoDaoDerby {
    
    Grupo create(Grupo g);
    List<Grupo> get(Grupo g);
    List<Grupo> getAll();
    void delete(Grupo g);
    Grupo update(Grupo g);
}
