package mx.uaemex.fi.ing_software_ii.torneos.dao.derby;

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugador;
import mx.uaemex.fi.ing_software_ii.torneos.dao.error.PersistenciaException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JugadorDaoDerbyImp implements  JugadorDaoDerby{

    private Connection con;

    public JugadorDaoDerbyImp() { }

    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * Crea un registro de un jugador usando las caracter&iacute;sticas del par&aacute;metro.
     * @param j Jugador con los datos con los que se crear&aacute; el registro.
     * @return Jugador registrado en la Base de Datos, incluyendo el ID.
     */
    @Override
    public Jugador create(Jugador j) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Jugador jugadorRegistrado;

        try {
            atributos = this.getAtributos(j);
            if ( atributos.isEmpty() )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 1);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            jugadorRegistrado = get(j).get(0);
            return jugadorRegistrado;
        } catch (SQLException e) {
            throw new PersistenciaException(e);
        }
    }

    public Jugador getCredentials(Jugador j) {
    	Statement stmt;
		String sql, cad;
		ResultSet rs;
		Map<String, Object> atributos;
		Jugador consultado;
		
		try {
<<<<<<< HEAD
			stmt = this.conexion.createStatement();
=======
			stmt = this.con.createStatement();
>>>>>>> f3264da (loginServlet)
			sql = "SELECT * FROM JUGADORES WHERE ";
			atributos = this.getAtributos(j);
			
			if(atributos.size()>0) {
				for (Map.Entry<String, Object> coso : atributos.entrySet()) {
				    String key = coso.getKey();
				    Object value = coso.getValue();
				    cad = key+"=";
				    if(value instanceof String) {
				    	cad +="'"+value.toString()+"' and ";
				    } else {
				    	cad += value.toString()+ " and ";
				    }
				    sql+=cad;
				}
				sql = sql.substring(0,sql.length()-5);
				rs = stmt.executeQuery(sql);
				consultado = new Jugador();
				while(rs.next()) {
					
					consultado.setUsuario(rs.getString("usuario"));
					consultado.setContrasena(rs.getString("contrasena"));
				
				}
				return consultado;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}             
    }

    /**
     * Consulta de una lista de Jugadores siguiendo la filosof&iacute;a de query-by-example, de forma que
     * la consulta se realizar&aacute; usando las caracter&iacute;sticas del par&aacute;metro.
     * @param j Jugador con los datos de consulta.
     * @return Lista de jugadores (que coinciden con los datos de consulta) le&iacute;dos de la base.
     */
    @Override
    public List<Jugador> get(Jugador j) {
        Map<String, Object> atributos;
        ResultSet rs;
        Statement stmt;
        String sql;
        Jugador jAux;
        List<Jugador> resultado = new ArrayList<>();

        try {
            atributos = this.getAtributos(j);
            if( atributos.isEmpty() ) return resultado;

            sql = construirConsultaSQL(atributos, 2);
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                jAux = new Jugador();
//                jAux.setId( rs.getInt("ID") );
                jAux.setNumCuenta( rs.getInt("NUMERO_CUENTA") );
                jAux.setNombre( rs.getString("NOMBRE") );
                jAux.setPrimerApellido( rs.getString("PRIMER_APELLIDO") );
                jAux.setSegundoApellido( rs.getString("SEGUNDO_APELLIDO") );
                jAux.setFechaNacimiento( rs.getString("FECHA_NACIMIENTO") );
                jAux.setCorreo( rs.getString("CORREO") );
                jAux.setUsuario( rs.getString("USUARIO") );
                jAux.setContrasena( rs.getString("CONTRASENA") );
                resultado.add( jAux );
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
     * Consulta la lista completa de los Jugadores.
     * @return Lista que contiene a todos los Jugadores registrados en la base de datos.
     */
    @Override
    public List<Jugador> getAll() {
        String sql;
        ResultSet rs;
        Statement stmt;
        Jugador j;
        List<Jugador> resultado = new ArrayList<>();

        try {
            stmt = this.con.createStatement();
            sql = "SELECT * FROM JUGADORES";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                j = new Jugador();
//                j.setId(rs.getInt("ID"));
                j.setNumCuenta(rs.getInt("NUMERO_CUENTA"));
                j.setNombre(rs.getString("NOMBRE"));
                j.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
                j.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
                j.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
                j.setCorreo(rs.getString("CORREO"));
                j.setUsuario(rs.getString("USUARIO"));
                j.setContrasena(rs.getString("CONTRASENA"));
                resultado.add(j);
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
     * Borra el registro del Jugador que tenga un id igual al id del Jugador dado como par&aacute;metro
     * @param j Jugador con los datos de consulta.
     */
    @Override
    public void delete(Jugador j) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;

        try{
            atributos = this.getAtributos(j);
            if ( atributos.isEmpty() )
                return; // Se puede cambiar por 'throw new PersistenciaException()'
            if ( !atributos.containsKey("NUMERO_CUENTA") )
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
     * Modifica el registro de un Jugador usando las caracter&iacute;sticas del par&aacute;metro.
     * @param j Jugador con el ID del registro a modifcar y los nuevos valores de los atributos.
     * @return Jugador con los nuevos valores.
     */
    @Override
    public Jugador update(Jugador j) {
        Map<String, Object> atributos;
        Statement stmt;
        String sql;
        Jugador jModificado;

        try{
            atributos = this.getAtributos(j);
            if ( atributos.size() < 2 )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'
            if ( !atributos.containsKey("NUMERO_CUENTA") )
                return null; // Se puede cambiar por 'throw new PersistenciaException()'

            sql = construirConsultaSQL(atributos, 3);
            stmt = this.con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            jModificado = get(j).get(0);
            return jModificado;
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
                sql.append("INSERT INTO JUGADORES(");
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
                sql.append("SELECT * FROM JUGADORES WHERE ");
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
                id  = String.valueOf( atributos.remove("NUMERO_CUENTA") );
                sql.append("UPDATE JUGADORES SET ");
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
                sql.append(" WHERE NUMERO_CUENTA=").append(id);
            }
            case 4 -> {
                id  = String.valueOf( atributos.remove("NUMERO_CUENTA") );
                sql.append("DELETE FROM JUGADORES WHERE NUMERO_CUENTA=").append(id);
            }
        }
        return sql.toString();
    }

    /**
     * Obtiene los atributos de un objeto Jugador y los almacena en un mapa clave-valor
     * @param j Objeto jugador del cual se obtendr&aacute;n los atributos.
     * @return Mapa que contiene los atributos del Jugador, donde las claves son los nombres
     *         de los atributos y los valores son los valores de los atributos correspondientes.
     *         Los atributos nulos o los iguales o menores a cero no se incluir&aacute;n en el mapa.
     */
    private Map<String, Object> getAtributos(Jugador j){
        Map<String, Object> atributos = new HashMap<>();

        if (j.getContrasena() != null ) {
            atributos.put("CONTRASENA", j.getContrasena());
        }

        if (j.getUsuario() != null) {
            atributos.put("USUARIO", j.getUsuario());
        }

        if (j.getCorreo() != null) {
            atributos.put("CORREO", j.getCorreo());
        }

        if (j.getFechaNacimiento() != null) {
            atributos.put("FECHA_NACIMIENTO", j.getFechaNacimiento());
        }
        if(j.getNombre() != null) {
            atributos.put("NOMBRE", j.getNombre());
        }
        if (j.getSegundoApellido() != null) {
            atributos.put("SEGUNDO_APELLIDO", j.getSegundoApellido());
        }
        if (j.getPrimerApellido() != null) {
            atributos.put("PRIMER_APELLIDO", j.getPrimerApellido());
        }
        if (j.getNumCuenta() > 0) {
            atributos.put("NUMERO_CUENTA", j.getNumCuenta());
        }
//        if (j.getId() > 0) {
//            atributos.put("ID", j.getId());
//        }
        return atributos;
    }

}