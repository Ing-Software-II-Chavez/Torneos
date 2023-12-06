package mx.uaemex.fi.ing_software_ii.torneos.dao.derby;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Torneo;
import mx.uaemex.fi.ing_software_ii.torneos.dao.error.PersistenciaException;

public class TorneoDaoDerbyImp implements TorneoDaoDerby {

    private Connection con;
    
    public TorneoDaoDerbyImp(){ }

    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * Crea un registro de un torneo usando las caracter&iacute;sticas del par&aacute;metro.
     * @param t torneo con los datos con los que se crear&aacute; el registro.
     * @return torneo registrado en la Base de Datos, incluyendo el ID.
     */
    @Override
    public Torneo create(Torneo t) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Torneo torneoRegistrado;

        try {
            atributos = this.getAtributos(t);
            if ( atributos.isEmpty() )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 1);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            torneoRegistrado = get(t).get(0);
            return torneoRegistrado;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }

    /**
     * Borra el registro del torneo que tenga un id igual al id del torneo dado como par&aacute;metro
     * @param t torneo con los datos de consulta.
     */
    @Override
    public void delete(Torneo t) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;

        try{
            atributos = this.getAtributos(t);
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
     * Consulta de una lista de torneos siguiendo la filosof&iacute;a de query-by-example, de forma que
     * la consulta se realizar&aacute; usando las caracter&iacute;sticas del par&aacute;metro.
     * @param t torneo con los datos de consulta.
     * @return Lista de torneos (que coinciden con los datos de consulta) le&iacute;dos de la base.
     */
    @Override
    public List<Torneo> get(Torneo t) {
        Map<String, Object> atributos;
        ResultSet rs;
        Statement stmt;
        String sql;
        Torneo tAux;
        List<Torneo> resultado = new ArrayList<>();

        try {
            atributos = this.getAtributos(t);
            if( atributos.isEmpty() ) return resultado;

            sql = construirConsultaSQL(atributos, 2);
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                tAux = new Torneo();
                tAux.setId( rs.getInt("ID") );
                tAux.setNombre( rs.getString("NOMBRE") );
                resultado.add( tAux );
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
     * Consulta la lista completa de los torneos.
     * @return Lista que contiene a todos los torneos registrados en la base de datos.
     */
    @Override
    public List<Torneo> getAll() {
        String sql;
        ResultSet rs;
        Statement stmt;
        Torneo t;
        List<Torneo> resultado = new ArrayList<>();

        try {
            stmt = this.con.createStatement();
            sql = "SELECT * FROM TORNEOS";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                t = new Torneo();
                t.setId(rs.getInt("ID"));
                t.setNombre(rs.getString("NOMBRE"));
                resultado.add(t);
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
     * Modifica el registro de un torneo usando las caracter&iacute;sticas del par&aacute;metro.
     * @param t torneo con el ID del registro a modifcar y los nuevos valores de los atributos.
     * @return torneo con los nuevos valores.
     */
    @Override
    public Torneo update(Torneo t) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Torneo tModificado;

        try{
            atributos = this.getAtributos(t);
            if ( atributos.size() < 2 )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'
            if ( !atributos.containsKey("ID") )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 3);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            tModificado = get(t).get(0);
            return tModificado;
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
                sql.append("INSERT INTO TORNEOS(");
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
                sql.append("SELECT * FROM TORNEOS WHERE ");
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
                sql.append("UPDATE TORNEOS SET ");
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
                sql.append("DELETE FROM TORNEOS WHERE ID=").append(id);
            }
        }
        return sql.toString();
    }

    /**
     * Obtiene los atributos de un objeto Torneo y los almacena en un mapa clave-valor
     * @param t Objeto torneo del cual se obtendr&aacute;n los atributos.
     * @return Mapa que contiene los atributos del Torneo, donde las claves son los nombres
     *         de los atributos y los valores son los valores de los atributos correspondientes.
     *         Los atributos nulos o los iguales o menores a cero no se incluir&aacute;n en el mapa.
     */
    private Map<String, Object> getAtributos(Torneo t){
        Map<String, Object> atributos = new HashMap<>();

        if (t.getId() > 0) {
            atributos.put("ID", t.getId());
        }

        if (t.getNombre() != null ) {
            atributos.put("NOMBRE", t.getNombre());
        }
        
        return atributos;
    }

    
}
