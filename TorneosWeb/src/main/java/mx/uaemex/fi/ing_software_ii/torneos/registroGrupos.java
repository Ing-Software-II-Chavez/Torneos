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
import java.io.IOException;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.GrupoDaoDerby;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.GrupoDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Grupo;


@WebServlet("/RegistroGrupos")
public class registroGrupos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;
	private int idgrupo;
	private String nombre;
	private int idTrneo;

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

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        Connection con = null;
	        GrupoDaoDerby dao = null;
	        GrupoDaoDerbyImp realDao = null;

	        try {
	            
	            int numGrps = Integer.parseInt(request.getParameter("numGruposTotal"));

	            for (int i = 1; i <= numGrps; i++) {
	            	
	            	con = this.ds.getConnection();
	            	realDao = new GrupoDaoDerbyImp();
		            realDao.setCon(con);
		            dao = realDao;

	                int idgrupo = Integer.parseInt(request.getParameter("idGrupo" + i));
	                String nombre = request.getParameter("nombreGrupo" + i);
	                int idTrneo = Integer.parseInt(request.getParameter("idtorneo"));

	                Grupo grupo = new Grupo();
	                grupo.setId(idgrupo);
	                grupo.setNombre(nombre);
	                grupo.setTorneo(idTrneo);

	                dao.create(grupo);
	            }
	            
	            request.getRequestDispatcher("grupos.jsp").forward(request, response);
	        } catch (Exception e) {
	            throw new ServletException(e.getMessage());
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (Exception e) {
	                // Manejar la excepción de cierre de conexión si es necesario
	            }
	        }

	      
	    }
}