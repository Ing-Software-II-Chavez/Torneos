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

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jornada;
import mx.uaemex.fi.ing_software_ii.torneos.dao.error.PersistenciaException;

public class JornadaDaoDerbyImp implements JornadaDaoDerby {

    private Connection con;
    
    public JornadaDaoDerbyImp(){ }

    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * Crea un registro de una Jornada usando las caracter&iacute;sticas del par&aacute;metro.
     * @param jor Jornada con los datos con los que se crear&aacute; el registro.
     * @return Jornada registrado en la Base de Datos, incluyendo el ID.
     */
    @Override
    public Jornada create(Jornada jor) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Jornada jornadaRegistrado;

        try {
            atributos = this.getAtributos(jor);
            if ( atributos.isEmpty() )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 1);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            jornadaRegistrado = get(jor).get(0);
            return jornadaRegistrado;
        } catch (SQLException ex) {
            throw new PersistenciaException(ex);
        }
    }

    /**
     * Borra el registro de la Jornada que tenga un id igual al id de la Jornada dado como par&aacute;metro
     * @param jor Jornada con los datos de consulta.
     */
    @Override
    public void delete(Jornada jor) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;

        try{
            atributos = this.getAtributos(jor);
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
     * Consulta de una lista de Jornadas siguiendo la filosof&iacute;a de query-by-example, de forma que
     * la consulta se realizar&aacute; usando las caracter&iacute;sticas del par&aacute;metro.
     * @param jor Jornada con los datos de consulta.
     * @return Lista de Jornadas (que coinciden con los datos de consulta) le&iacute;dos de la base.
     */
    @Override
    public List<Jornada> get(Jornada jor) {
        Map<String, Object> atributos;
        ResultSet rs;
        Statement stmt;
        String sql;
        Jornada jAux;
        List<Jornada> resultado = new ArrayList<>();

        try {
            atributos = this.getAtributos(jor);
            if( atributos.isEmpty() ) return resultado;

            sql = construirConsultaSQL(atributos, 2);
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                jAux = new Jornada();
                jAux.setId( rs.getInt("ID") );
                jAux.setLocal( rs.getInt("LOCAL") );
                jAux.setVisitante( rs.getInt("VISITANTE") );
                jAux.setFecha( rs.getString("FECHA") );
                jAux.setHora( rs.getString("HORA") );
                resultado.add( jAux );
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
     * Consulta la lista completa de las Jornadas.
     * @return Lista que contiene a todos las Jornadas registrados en la base de datos.
     */
    @Override
    public List<Jornada> getAll() {
        String sql;
        ResultSet rs;
        Statement stmt;
        Jornada jor;
        List<Jornada> resultado = new ArrayList<>();

        try {
            stmt = this.con.createStatement();
            sql = "SELECT * FROM JORNADAS";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                jor = new Jornada();
                jor.setId( rs.getInt("ID") );
                jor.setLocal( rs.getInt("LOCAL") );
                jor.setVisitante( rs.getInt("VISITANTE") );
                jor.setFecha( rs.getString("FECHA") );
                jor.setHora( rs.getString("HORA") );
                resultado.add( jor );
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
     * Modifica el registro de una jornada usando las caracter&iacute;sticas del par&aacute;metro.
     * @param jor jornada con el ID del registro a modifcar y los nuevos valores de los atributos.
     * @return jornada con los nuevos valores.
     */
    @Override
    public Jornada update(Jornada jor) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Jornada jModificado;

        try{
            atributos = this.getAtributos(jor);
            if ( atributos.size() < 2 )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'
            if ( !atributos.containsKey("ID") )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 3);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            jModificado = get(jor).get(0);
            return jModificado;
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
                sql.append("INSERT INTO JORNADAS(");
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
                sql.append("SELECT * FROM JORNADAS WHERE ");
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
                sql.append("UPDATE JORNADAS SET ");
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
                sql.append("DELETE FROM JORNADAS WHERE ID=").append(id);
            }
        }
        return sql.toString();
    }

    /**
     * Obtiene los atributos de un objeto Jornada y los almacena en un mapa clave-valor
     * @param jor Objeto Jornada del cual se obtendr&aacute;n los atributos.
     * @return Mapa que contiene los atributos de la Jornada, donde las claves son los nombres
     *         de los atributos y los valores son los valores de los atributos correspondientes.
     *         Los atributos nulos o los iguales o menores a cero no se incluir&aacute;n en el mapa.
     */
    private Map<String, Object> getAtributos(Jornada jor){
        Map<String, Object> atributos = new HashMap<>();

        if (jor.getId() > 0) {
            atributos.put("ID", jor.getId());
        }

        if (jor.getLocal() > 0) {
            atributos.put("LOCAL", jor.getLocal());
        }

        if (jor.getVisitante() > 0) {
            atributos.put("VISITANTE", jor.getVisitante());
        }

        if (jor.getFecha() != null ) {
            atributos.put("FECHA", jor.getFecha());
        }

        if (jor.getHora() != null ) {
            atributos.put("HORA", jor.getHora());
        }
        
        return atributos;
    }

    
}
