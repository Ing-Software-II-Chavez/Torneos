/*
 * @José Juan García Romero
 * @Luis Angel Rocha Ronquillo
 * @Jesús Alberto Sanchez Mendieta
 * @Isaac Misael Vazquez Albor
 * @Francisco Gamaliel Alvaro Portillo
 * 
 * */
package mx.uaemex.fi.ing_software_ii.torneos;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.EquipoDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.Jugador_EquipoDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Equipo;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugador;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugadores_Equipo;

@WebServlet("/Registrar")
public class Registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        InitialContext cxt;

        try {
            cxt = new InitialContext();
            if (cxt != null) {
                this.ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/ds");
                if (this.ds == null) {
                    throw new ServletException("Data source not found!");
                }
            }
        } catch (NamingException e) {
            throw new ServletException("No hay contexto incial :(");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con;

        String usuario = request.getParameter("usuario");
        String nombre = request.getParameter("nombre");
        int id = Integer.parseInt(request.getParameter("id"));
        int numCuenta = 0;
        
        try {
            con = this.ds.getConnection();

            JugadorDaoDerbyImp jugget = new JugadorDaoDerbyImp();
            jugget.setCon(con);
            
            Jugador jugador = new Jugador();
            jugador.setUsuario(usuario);
            jugador.setNombre(nombre);
            
            jugador = jugget.getCredentials(jugador);
               
             numCuenta = jugador.getNumCuenta();
             
             Jugador_EquipoDaoDerbyImp jugequ = new Jugador_EquipoDaoDerbyImp();
             jugequ.setCon(con);
             
             Jugadores_Equipo jugadorEquipo = new Jugadores_Equipo();
             
             jugadorEquipo.setJugador(numCuenta);
             jugadorEquipo.setEquipo(id);
             
             jugequ.create(jugadorEquipo);
             
	        request.getRequestDispatcher("msjRegistro.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

}
