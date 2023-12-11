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
import jakarta.servlet.http.Cookie;
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

import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerby;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.Jugador_EquipoDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugador;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugadores_Equipo;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String contrasena;
	
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
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	request.getRequestDispatcher("login.jsp").forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        Connection con;
	        
	        String[]cookies_names = {"usuarioLogin", "contrasenaLogin"};
	        
	        if(CookiesExist(request, cookies_names)) {
	        	usuario = obtenerValorCookie(request, cookies_names[0]);
		        contrasena = obtenerValorCookie(request, cookies_names[1]);
	        }else {	        	
	        	usuario = request.getParameter("usuario");
	        	contrasena = request.getParameter("contrasena");
	        }
	        

	        try {
	            con = this.ds.getConnection();

	            JugadorDaoDerbyImp jgdrget = new JugadorDaoDerbyImp();
	            Jugador_EquipoDaoDerbyImp jgeqget = new Jugador_EquipoDaoDerbyImp();
	            
	            jgdrget.setCon(con);
	            
	            Jugador jugador = new Jugador();
	            Jugadores_Equipo jueq = new Jugadores_Equipo();
	            List<Jugadores_Equipo> jueq2 = new ArrayList();
	            
	            jugador.setUsuario(usuario);
	            
	            jugador = jgdrget.getCredentials(jugador);
	            
	            jgeqget.setCon(con);
	            
	            jueq.setJugador(jugador.getNumCuenta());
	            
	            jueq2 =  jgeqget.getTeams(jueq);
	               
	             
	             if(jugador.getUsuario() == null){
	                  
	            	    request.setAttribute("usuarioGC", usuario);
	        	        request.setAttribute("passwordGC", contrasena);
	                	request.setAttribute("error", "Usuario no encontrado");
	            	    request.getRequestDispatcher("login.jsp").forward(request, response);
	            	 
	              }else if(jugador.getUsuario().equals(usuario) && jugador.getContrasena().equals(contrasena) ){  	
	                	HttpSession session = request.getSession(true);
	                	
		                session.setAttribute("jugador", jugador);
    
		                session.setAttribute("jueq2", jueq2);
		                request.getRequestDispatcher("respuesta.jsp").forward(request, response);
		                eliminarCookiesExistentes(request,response);
	              }else{
	                    
	                	request.setAttribute("usuarioGC", usuario);
	        	        request.setAttribute("passwordGC", contrasena);
	                	request.setAttribute("error", "Tu usuario o contraseña con incorrectos!");
	            	    request.getRequestDispatcher("login.jsp").forward(request, response);
	              }
	              guardarDatosEnCookies(request, response);

	        } catch (Exception e) {
    	        request.setAttribute("usuarioGC", usuario);
    	        request.setAttribute("passwordGC", contrasena);
            	request.setAttribute("error", "Algo ha salido mal, rectifique que los datos sean correctos!");
        	    request.getRequestDispatcher("login.jsp").forward(request, response);
//	        	throw new ServletException(e.getMessage());
	        }
	    }
	    
	    private void guardarDatosEnCookies(HttpServletRequest request, HttpServletResponse response) {
		    Cookie usuario_Cookie = new Cookie("usuarioLogin", usuario);
		    Cookie contrasena_Cookie = new Cookie("contrasenaLogin", contrasena);

		    response.addCookie(usuario_Cookie);
		    response.addCookie(contrasena_Cookie);
		}
		
		private String obtenerValorCookie(HttpServletRequest request, String nombreCookie) {
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if (nombreCookie.equals(cookie.getName())) {
		                return cookie.getValue();
		            }
		        }
		    }
		    return null;
		}
		
		private boolean CookiesExist(HttpServletRequest request, String[] nombresCookies) {
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            for (String nombre : nombresCookies) {
		                if (nombre.equals(cookie.getName())) {
		                    return true; // La cookie fue encontrada
		                }
		            }
		        }
		    }
		    return false; // Ninguna de las cookies fue encontrada
		}
		
		private void eliminarCookiesExistentes(HttpServletRequest request, HttpServletResponse response) {
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            cookie.setMaxAge(0); // Eliminar la cookie
		            response.addCookie(cookie); // Agregar la cookie al response para eliminarla
		        }
		    }
		}
}
