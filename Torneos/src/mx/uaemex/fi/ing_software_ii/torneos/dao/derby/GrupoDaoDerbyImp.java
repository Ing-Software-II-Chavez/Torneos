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

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Grupo;
import mx.uaemex.fi.ing_software_ii.torneos.dao.error.PersistenciaException;

public class GrupoDaoDerbyImp implements GrupoDaoDerby {

    private Connection con;
    
    public GrupoDaoDerbyImp(){ }

    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * Crea un registro de un grupo usando las caracter&iacute;sticas del par&aacute;metro.
     * @param g grupo con los datos con los que se crear&aacute; el registro.
     * @return grupo registrado en la Base de Datos, incluyendo el ID.
     */
    @Override
    public Grupo create(Grupo g) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Grupo grupoRegistrado;

        try {
            atributos = this.getAtributos(g);
            if ( atributos.isEmpty() )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 1);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            grupoRegistrado = get(g).get(0);
            return grupoRegistrado;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }

    /**
     * Borra el registro del grupo que tenga un id igual al id del grupo dado como par&aacute;metro
     * @param g grupo con los datos de consulta.
     */
    @Override
    public void delete(Grupo g) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;

        try{
            atributos = this.getAtributos(g);
            if ( atributos.isEmpty() )
                return; // Se puede cambiar por 'throw new PersistenciaException()'
            if ( !atributos.containsKey("ID") )
                return; // Se puede cambiar por 'throw new PersistenciaException()'

            sql  = construirConsultaSQL(atributos, 4);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            this.con.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
        
    }

    /**
     * Consulta de una lista de grupos siguiendo la filosof&iacute;a de query-by-example, de forma que
     * la consulta se realizar&aacute; usando las caracter&iacute;sticas del par&aacute;metro.
     * @param g grupo con los datos de consulta.
     * @return Lista de grupos (que coinciden con los datos de consulta) le&iacute;dos de la base.
     */
    @Override
    public List<Grupo> get(Grupo g) {
        Map<String, Object> atributos;
        ResultSet rs;
        Statement stmt;
        String sql;
        Grupo gAux;
        List<Grupo> resultado = new ArrayList<>();

        try {
            atributos = this.getAtributos(g);
            if( atributos.isEmpty() ) return resultado;

            sql = construirConsultaSQL(atributos, 2);
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                gAux = new Grupo();
                gAux.setId( rs.getInt("ID") );
                gAux.setNombre( rs.getString("NOMBRE") );
                gAux.setId( rs.getInt("TORNEO") );
                resultado.add( gAux );
            }
            rs.close();
            stmt.close();
            this.con.close();
            return resultado;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }

    /**
     * Consulta la lista completa de los grupos.
     * @return Lista que contiene a todos los grupos registrados en la base de datos.
     */
    @Override
    public List<Grupo> getAll() {
        String sql;
        ResultSet rs;
        Statement stmt;
        Grupo g;
        List<Grupo> resultado = new ArrayList<>();

        try {
            stmt = this.con.createStatement();
            sql = "SELECT * FROM GRUPOS";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                g = new Grupo();
                g.setId(rs.getInt("ID"));
                g.setNombre(rs.getString("NOMBRE"));
                g.setId(rs.getInt("TORNEO"));
                resultado.add(g);
            }
            rs.close();
            stmt.close();
            this.con.close();
            return resultado;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }

    /**
     * Modifica el registro de un grupo usando las caracter&iacute;sticas del par&aacute;metro.
     * @param g grupo con el ID del registro a modifcar y los nuevos valores de los atributos.
     * @return grupo con los nuevos valores.
     */
    @Override
    public Grupo update(Grupo g) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Grupo gModificado;

        try{
            atributos = this.getAtributos(g);
            if ( atributos.size() < 2 )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'
            if ( !atributos.containsKey("ID") )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 3);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            gModificado = get(g).get(0);
            return gModificado;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
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
                sql.append("INSERT INTO GRUPOS(");
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
                sql.append("SELECT * FROM GRUPOS WHERE ");
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
                sql.append("UPDATE GRUPOS SET ");
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
                sql.append("DELETE FROM GRUPOS WHERE ID=").append(id);
            }
        }
        return sql.toString();
    }

    /**
     * Obtiene los atributos de un objeto Grupo y los almacena en un mapa clave-valor
     * @param g Objeto grupo del cual se obtendr&aacute;n los atributos.
     * @return Mapa que contiene los atributos del grupo, donde las claves son los nombres
     *         de los atributos y los valores son los valores de los atributos correspondientes.
     *         Los atributos nulos o los iguales o menores a cero no se incluir&aacute;n en el mapa.
     */
    private Map<String, Object> getAtributos(Grupo g){
        Map<String, Object> atributos = new HashMap<>();

        if (g.getId() > 0) {
            atributos.put("ID", g.getId());
        }

        if (g.getNombre() != null ) {
            atributos.put("NOMBRE", g.getNombre());
        }

        if (g.getTorneo() > 0) {
            atributos.put("TORNEO", g.getTorneo());
        }
        
        return atributos;
    }

    
}
