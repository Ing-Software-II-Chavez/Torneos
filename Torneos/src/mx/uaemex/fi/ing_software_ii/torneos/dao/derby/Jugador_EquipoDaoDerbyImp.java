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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugadores_Equipo;
import mx.uaemex.fi.ing_software_ii.torneos.dao.error.PersistenciaException;

public class Jugador_EquipoDaoDerbyImp implements Jugador_EquipoDaoDerby  {

private Connection con;
    
    public Jugador_EquipoDaoDerbyImp(){ }

    public void setCon(Connection con) {
        this.con = con;
    }
	
	@Override
	public Jugadores_Equipo create(Jugadores_Equipo jug_eq) {
		PreparedStatement stmt;
		int ins;
		String sql;
		Jugadores_Equipo insertado;
		
		try{
		
			sql = "INSERT INTO JUGADORES_EQUIPO(jugador, equipo) VALUES(?,?)";
			stmt = this.con.prepareStatement(sql); 
			
			stmt.setInt(1, jug_eq.getJugador());
			stmt.setInt(2, jug_eq.getEquipo());

            ins = stmt.executeUpdate();

            if(ins == 1) {
            	insertado = new Jugadores_Equipo();
            } else {
            	
            	return null;
            }
            
            stmt.close();
            con.close();
			
            return insertado;
			
		}catch (SQLException ex) {
			 ex.printStackTrace();
			throw new PersistenciaException(ex);
		}
	}

	@Override
	public List<Jugadores_Equipo> get(Jugadores_Equipo jug_eq) {
		Statement stmt;
	    String sql;
	    ResultSet rs;
	    Map<String, Object> atributos;
	    Jugadores_Equipo consultado;
	    List<Jugadores_Equipo> jugadores = new ArrayList<>();

	    try {
	        stmt = this.con.createStatement();
	        sql = "SELECT * FROM JUGADORES_EQUIPO";
	        atributos = this.getAtributos(jug_eq);

	        if (!atributos.isEmpty()) {
	            sql += " WHERE ";
	            for (Map.Entry<String, Object> coso : atributos.entrySet()) {
	                String key = coso.getKey();
	                Object value = coso.getValue();
	                String cad = key + "=";

	                if (value instanceof String) {
	                    cad += "'" + value.toString() + "' and ";
	                } else {
	                    cad += value.toString() + " and ";
	                }
	                sql += cad;
	            }
	            sql = sql.substring(0, sql.length() - 5);
	        }

	        System.out.println(sql);
	        rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	            consultado = new Jugadores_Equipo();
	            consultado.setJugador(rs.getInt("jugador"));
	            consultado.setEquipo(rs.getInt("equipo"));
	            jugadores.add(consultado);
	        }

	        stmt.close();
	        con.close();
	        return jugadores;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new PersistenciaException(e);
	    }
	}
	
	public List<Jugadores_Equipo> getTeams(Jugadores_Equipo jug_eq) {
	    PreparedStatement pstmt;
	    String sql;
	    ResultSet rs;
	    Map<String, Object> atributos;
	    Jugadores_Equipo consultado;
	    List<Jugadores_Equipo> jugadores = new ArrayList<>();

	    try {
	        sql = "SELECT e.nombre AS nombre_equipo " +
	              "FROM jugadores_equipo je " +
	              "JOIN equipos e ON je.equipo = e.id " +
	              "WHERE je.jugador = ?";

	        pstmt = this.con.prepareStatement(sql);
	        pstmt.setInt(1, jug_eq.getJugador()); // Asigna el valor del jugador al parámetro en la consulta SQL

	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            consultado = new Jugadores_Equipo();
	            consultado.setNombre_equipo(rs.getString("NOMBRE_EQUIPO"));
	            jugadores.add(consultado);
	        }

	        pstmt.close();
	        con.close();
	        return jugadores;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new PersistenciaException(e);
	    }
	}
	

	@Override
	public List<Jugadores_Equipo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Jugadores_Equipo jug_eq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Jugadores_Equipo update(Jugadores_Equipo jug_eq) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Map<String, Object> getAtributos(Jugadores_Equipo jug_eq){
		Map<String, Object> resul = new HashMap<String, Object>();
		int var;
		
		var = jug_eq.getJugador();
		if(var > 0) {
			resul.put("jugador", var);
		}
		
		var = jug_eq.getEquipo();
		if(var > 0) {
			resul.put("equipo", var);
		}
		
		return resul;
	}

	
}
