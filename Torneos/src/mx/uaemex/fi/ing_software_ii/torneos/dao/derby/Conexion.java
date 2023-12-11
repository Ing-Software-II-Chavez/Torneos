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
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexion {
	
	protected Connection conexion;
	
	public Conexion() {
		String url = ConexionEstatica.CONEXION_CREDENTIALS;
		try {
			this.conexion = DriverManager.getConnection(url);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public Conexion(Connection con) {
		this.conexion = con;
	}
	
	public void setConexion(Connection con) {
		this.conexion = con;
	}	
}
