/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * @Jesús Alberto Sanchez Mendieta
 * @Isaac Misael Vazquez Albor
 * @Francisco Gamaliel Alvaro Portillo
 * 
 * */

package mx.uaemex.fi.ing_software_ii.torneos.dao.derby;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Equipo;
import mx.uaemex.fi.ing_software_ii.torneos.dao.error.PersistenciaException;

public class EquipoDaoDerbyImp implements EquipoDaoDerby {

    private Connection con;
    
    public EquipoDaoDerbyImp(){ }

    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * Crea un registro de un Equipo usando las caracter&iacute;sticas del par&aacute;metro.
     * @param e Equipo con los datos con los que se crear&aacute; el registro.
     * @return Equipo registrado en la Base de Datos, incluyendo el ID.
     */
    @Override
    public Equipo create(Equipo e) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Equipo equipoRegistrado;

        try {
            atributos = this.getAtributos(e);
            if ( atributos.isEmpty() )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 1);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            equipoRegistrado = get(e).get(0);
            return equipoRegistrado;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    /**
     * Borra el registro del equipo que tenga un id igual al id del grupo dado como par&aacute;metro
     * @param e equipo con los datos de consulta.
     */
    @Override
    public void delete(Equipo e) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;

        try{
            atributos = this.getAtributos(e);
            if ( atributos.isEmpty() )
                return; // Se puede cambiar por 'throw new PersistenciaException()'
            if ( !atributos.containsKey("ID") )
                return; // Se puede cambiar por 'throw new PersistenciaException()'

            sql  = construirConsultaSQL(atributos, 4);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            this.con.close();
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        }
        
    }

    /**
     * Consulta de una lista de equipos siguiendo la filosof&iacute;a de query-by-example, de forma que
     * la consulta se realizar&aacute; usando las caracter&iacute;sticas del par&aacute;metro.
     * @param e equipo con los datos de consulta.
     * @return Lista de equipos (que coinciden con los datos de consulta) le&iacute;dos de la base.
     */
    @Override
    public List<Equipo> get(Equipo e) {
        Map<String, Object> atributos;
        ResultSet rs;
        Statement stmt;
        String sql;
        Equipo eAux;
        List<Equipo> resultado = new ArrayList<>();

        try {
            atributos = this.getAtributos(e);
            if( atributos.isEmpty() ) return resultado;

            sql = construirConsultaSQL(atributos, 2);
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                eAux = new Equipo();
                eAux.setId( rs.getInt("ID") );
                eAux.setNombre( rs.getString("NOMBRE") );
                eAux.setId( rs.getInt("GRUPO") );
                resultado.add( eAux );
            }
            rs.close();
            stmt.close();
            this.con.close();
            return resultado;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    /**
     * Consulta la lista completa de los equipos.
     * @return Lista que contiene a todos los equipos registrados en la base de datos.
     */
    @Override
    public List<Equipo> getAll() {
        String sql;
        ResultSet rs;
        Statement stmt;
        Equipo e;
        List<Equipo> resultado = new ArrayList<>();

        try {
            stmt = this.con.createStatement();
            sql = "SELECT * FROM EQUIPOS";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                e = new Equipo();
                e.setId(rs.getInt("ID"));
                e.setNombre(rs.getString("NOMBRE"));
                e.setId(rs.getInt("GRUPO"));
                resultado.add(e);
            }
            rs.close();
            stmt.close();
            this.con.close();
            return resultado;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    /**
     * Modifica el registro de un grupo usando las caracter&iacute;sticas del par&aacute;metro.
     * @param e equipo con el ID del registro a modifcar y los nuevos valores de los atributos.
     * @return equipo con los nuevos valores.
     */
    @Override
    public Equipo update(Equipo e) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Equipo eModificado;

        try{
            atributos = this.getAtributos(e);
            if ( atributos.size() < 2 )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'
            if ( !atributos.containsKey("ID") )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 3);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            eModificado = get(e).get(0);
            return eModificado;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    /**
     * Construye una consulta SQL basada en los atributos proporcionados en el mapa dado como
     * par&aacute;metro y el tipo de consulta especificado.
     * @param atributos Un mapa que contiene los atributos a utilizar en la consulta.
     * @param tipoConsulta El tipo de consulta SQL (1 para INSERT, 2 para SELECT, 3 para UPDATE,
     *                     4 para DELETE).
     * @return Una cadena que representa la consulta SQL generada.
     */
    private String construirConsultaSQL(Map<String, Object> atributos, int tipoConsulta){
        StringBuilder sql = new StringBuilder();
        String id;

        switch (tipoConsulta) {
            case 1 -> {
                sql.append("INSERT INTO EQUIPOS(");
                for (Map.Entry<String, Object> a : atributos.entrySet()) {
                    String key = a.getKey();
                    sql.append(key).append(", ");
                }
                sql.delete(sql.length() - 2, sql.length());
                sql.append(") VALUES (");
                for (Map.Entry<String, Object> a : atributos.entrySet()) {
                    Object value = a.getValue();
                    if (value instanceof String)
                        sql.append("'").append(value).append("', ");
                    else
                        sql.append(value).append(", ");
                }
                sql.delete(sql.length() - 2, sql.length());
                sql.append(")");
            }
            case 2 -> {
                sql.append("SELECT * FROM EQUIPOS WHERE ");
                for (Map.Entry<String, Object> a : atributos.entrySet()) {
                    String key = a.getKey();
                    Object value = a.getValue();
                    sql.append(key).append("=");
                    if (value instanceof String)
                        sql.append("'").append(value).append("' AND ");
                    else
                        sql.append(value).append(" AND ");
                }
                sql.delete(sql.length() - 5, sql.length());
            }
            case 3 -> {
                id  = String.valueOf( atributos.remove("ID") );
                sql.append("UPDATE EQUIPOS SET ");
                for (Map.Entry<String, Object> a : atributos.entrySet()) {
                    String key   = a.getKey();
                    Object value = a.getValue();
                    sql.append(key).append("=");
                    if (value instanceof String)
                        sql.append("'").append(value).append("', ");
                    else
                        sql.append(value).append(", ");
                }
                sql.delete(sql.length() - 2, sql.length());
                sql.append(" WHERE ID=").append(id);
            }
            case 4 -> {
                id  = String.valueOf( atributos.remove("ID") );
                sql.append("DELETE FROM EQUIPOS WHERE ID=").append(id);
            }
        }
        return sql.toString();
    }

    /**
     * Obtiene los atributos de un objeto Equipo y los almacena en un mapa clave-valor
     * @param e Objeto equipo del cual se obtendr&aacute;n los atributos.
     * @return Mapa que contiene los atributos del equipo, donde las claves son los nombres
     *         de los atributos y los valores son los valores de los atributos correspondientes.
     *         Los atributos nulos o los iguales o menores a cero no se incluir&aacute;n en el mapa.
     */
    private Map<String, Object> getAtributos(Equipo e){
        Map<String, Object> atributos = new HashMap<>();

        if (e.getId() > 0) {
            atributos.put("ID", e.getId());
        }

        if (e.getNombre() != null ) {
            atributos.put("NOMBRE", e.getNombre());
        }

        if (e.getGrupo() > 0) {
            atributos.put("GRUPO", e.getGrupo());
        }
        
        return atributos;
    }

    
}
