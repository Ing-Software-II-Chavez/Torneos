/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.derby;

import java.util.List;

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jornada;

public interface JornadaDaoDerby {
    
    Jornada create(Jornada jor);
    List<Jornada> get(Jornada jor);
    List<Jornada> getAll();
    void delete(Jornada jor);
    Jornada update(Jornada jor);

}
