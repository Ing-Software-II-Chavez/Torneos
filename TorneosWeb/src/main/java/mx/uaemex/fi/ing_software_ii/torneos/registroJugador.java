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
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerby;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugador;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet("/RegistroJugador")
public class registroJugador extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataSource ds;
    
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaNacimiento;
    private String numCuenta;
    private String correo;
    private String usuario;
    private String contrasena;
    

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
    	request.getRequestDispatcher("registro.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con;
        JugadorDaoDerby dao;
        JugadorDaoDerbyImp realDao;
        Jugador jugador = new Jugador();
        
        String[]cookies_names = {"nombre", "primerApellido", "segundoApellido", "fechaNacimiento", "numCuenta", "correo", "usuario", "contrasena"};
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
        
        if(CookiesExist(request, cookies_names)) {
	        nombre = obtenerValorCookie(request, cookies_names[0]);
	        primerApellido = obtenerValorCookie(request, cookies_names[1]);
	        segundoApellido = obtenerValorCookie(request, cookies_names[2]);
	        fechaNacimiento = obtenerValorCookie(request, cookies_names[3]);
	        numCuenta = obtenerValorCookie(request, cookies_names[4]);
	        correo = obtenerValorCookie(request, cookies_names[5]);
	        usuario = obtenerValorCookie(request, cookies_names[6]);
	        contrasena = obtenerValorCookie(request, cookies_names[7]);
	        
        }else {
        	
        	nombre = request.getParameter("nombre");
	        primerApellido = request.getParameter("apellido");
	        segundoApellido = request.getParameter("segundoapellido");
	        fechaNacimiento = request.getParameter("fechanacimiento");
//	        fechaNacimiento = formatter.format(fechaNacimiento);
	        numCuenta = request.getParameter("numerocuenta");
	        correo = request.getParameter("correo");
	        usuario = request.getParameter("login");
	        contrasena = request.getParameter("password");
        }
        
        if(esNumero(numCuenta) && numCuenta.length() == 7 && validarPassword(contrasena)) {
        	try {
        		con = this.ds.getConnection();
        		realDao = new JugadorDaoDerbyImp();
        		realDao.setCon(con);
        		dao = realDao;
        		
        		
        		
//        		if(nCuenta == 0) {
	        		jugador.setNombre(nombre);
	        		jugador.setPrimerApellido(primerApellido);
	        		jugador.setSegundoApellido(segundoApellido);
	        		jugador.setFechaNacimiento(fechaNacimiento);
	        		jugador.setNumCuenta(Integer.parseInt(numCuenta));
	        		jugador.setCorreo(correo);
	        		jugador.setUsuario(usuario);
	        		jugador.setContrasena(contrasena);
        		
        			dao.create(jugador);
        			eliminarCookiesExistentes(request,response);
        			
        			HttpSession session = request.getSession(true);
        			
            		session.setAttribute("jugador", jugador);
            		request.getRequestDispatcher("respuesta.jsp").forward(request, response);
//        		}else {
//        			request.setAttribute("nombreGC", nombre);
//        	        request.setAttribute("primerApellidoGC", primerApellido);
//        	        request.setAttribute("segundoApellidoGC", segundoApellido);
//        	        request.setAttribute("fechaNacimientoGC", fechaNacimiento);
//        	        request.setAttribute("numeroCuentaGC", numCuenta);
//        	        request.setAttribute("correoGC", correo);
//        	        request.setAttribute("usuarioGC", usuario);
//        	        request.setAttribute("passwordGC", contrasena);
//        			request.setAttribute("error", "El usuario con número de cuenta: " + numCuenta + " ya existe.");
//            	    request.getRequestDispatcher("registro.jsp").forward(request, response);
//        		}
        		
        	} catch (Exception e) {
        		request.setAttribute("nombreGC", nombre);
    	        request.setAttribute("primerApellidoGC", primerApellido);
    	        request.setAttribute("segundoApellidoGC", segundoApellido);
    	        request.setAttribute("fechaNacimientoGC", fechaNacimiento);
    	        request.setAttribute("numeroCuentaGC", numCuenta);
    	        request.setAttribute("correoGC", correo);
    	        request.setAttribute("usuarioGC", usuario);
    	        request.setAttribute("passwordGC", contrasena);
            	request.setAttribute("error", "Algo ha salido mal, rectifique que los datos sean correctos!");
        	    request.getRequestDispatcher("registro.jsp").forward(request, response);
        	}
        }else if(!validarPassword(contrasena)){
        	request.setAttribute("nombreGC", nombre);
	        request.setAttribute("primerApellidoGC", primerApellido);
	        request.setAttribute("segundoApellidoGC", segundoApellido);
	        request.setAttribute("fechaNacimientoGC", fechaNacimiento);
	        request.setAttribute("numeroCuentaGC", numCuenta);
	        request.setAttribute("correoGC", correo);
	        request.setAttribute("usuarioGC", usuario);
	        request.setAttribute("passwordGC", contrasena);
        	request.setAttribute("error", "La contraseña debe contar con al menos 6 caracteres, una mayuscula, un número y un caracter especial...");
    	    request.getRequestDispatcher("registro.jsp").forward(request, response);
        }else {
        	request.setAttribute("nombreGC", nombre);
	        request.setAttribute("primerApellidoGC", primerApellido);
	        request.setAttribute("segundoApellidoGC", segundoApellido);
	        request.setAttribute("fechaNacimientoGC", fechaNacimiento);
	        request.setAttribute("numeroCuentaGC", numCuenta);
	        request.setAttribute("correoGC", correo);
	        request.setAttribute("usuarioGC", usuario);
	        request.setAttribute("passwordGC", contrasena);
        	request.setAttribute("error", "Ingrese un número de cuenta válido de 7 dígitos!");
    	    request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
        guardarDatosEnCookies(request, response);
    }
    
    private boolean validarPassword(String contrasena) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$";
        return contrasena != null && contrasena.matches(regex);
    }
    
   private boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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

	private void guardarDatosEnCookies(HttpServletRequest request, HttpServletResponse response) {
	    Cookie nombreCookie = new Cookie("nombre", nombre);
	    Cookie apellido1_Cookie = new Cookie("primerApellido", primerApellido);
	    Cookie apellido2_Cookie = new Cookie("segundoApellido", segundoApellido);
	    Cookie fechaNac_Cookie = new Cookie("fechaNacimiento", fechaNacimiento);
	    Cookie numCuenta_Cookie = new Cookie("numCuenta", numCuenta);
	    Cookie correo_Cookie = new Cookie("correo", correo);
	    Cookie usuario_Cookie = new Cookie("usuario", usuario);
	    Cookie contrasena_Cookie = new Cookie("contrasena", contrasena);

	    response.addCookie(nombreCookie);
	    response.addCookie(apellido1_Cookie);
	    response.addCookie(apellido2_Cookie);
	    response.addCookie(fechaNac_Cookie);
	    response.addCookie(numCuenta_Cookie);
	    response.addCookie(correo_Cookie);
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
}