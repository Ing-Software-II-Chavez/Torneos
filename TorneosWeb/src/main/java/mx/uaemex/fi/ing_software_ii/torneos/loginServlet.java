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

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerby;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugador;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
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

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        Connection con;
	        Jugador jugador = new Jugador();

	        String usuario = request.getParameter("usuario");
	        String contrasena = request.getParameter("contrasena");
	        

	        try {
	            con = this.ds.getConnection();

	            JugadorDaoDerbyImp jgdrget = new JugadorDaoDerbyImp();
	            jgdrget.setCon(con);
	            
	            Jugador jgdr = new Jugador();
	            jgdr.setUsuario(usuario);
	            
	             jgdr = jgdrget.getCredentials(jgdr);
	                
	             
	             if(jgdr.getUsuario() == null){
	                  
	            	 request.getRequestDispatcher("NoFoundUser.jsp").forward(request, response);
	            	 
	                }else if(jgdr.getUsuario().equals(usuario) && jgdr.getContrasena().equals(contrasena) ){
	                   
	                	HttpSession session = request.getSession(true);

		                session.setAttribute("jugador", jugador);

		                request.getRequestDispatcher("respuesta.jsp").forward(request, response);
	                }else{
	                    
	                	 request.getRequestDispatcher("crdclsError.jsp").forward(request, response);
	                }

	        } catch (Exception e) {
	            throw new ServletException(e.getMessage());
	        }
	    }

}
