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

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Torneo;

public interface TorneoDaoDerby {

    Torneo create(Torneo t);
    List<Torneo> get(Torneo t);
    List<Torneo> getAll();
    void delete(Torneo t);
    Torneo update(Torneo t);

}
