package mx.uaemex.fi.ing_software_ii.torneos;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerby;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.JugadorDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Jugador;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet("/RegistroJugador")
public class registroJugador extends HttpServlet {
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
        JugadorDaoDerby dao;
        JugadorDaoDerbyImp realDao;
        Jugador jugador = new Jugador();

        String nombre = request.getParameter("nombre");
        String primerApellido = request.getParameter("apellido");
        String segundoApellido = request.getParameter("segundoapellido");
        String fechaNacimiento = request.getParameter("fechanacimiento");
        int numCuenta = Integer.parseInt(request.getParameter("numerocuenta"));
        String correo = request.getParameter("correo");
        String usuario = request.getParameter("login");
        String contrasena = request.getParameter("password");

        try {
            con = this.ds.getConnection();

            realDao = new JugadorDaoDerbyImp();
            realDao.setCon(con);
            dao = realDao;

                jugador.setNombre(nombre);
                jugador.setPrimerApellido(primerApellido);
                jugador.setSegundoApellido(segundoApellido);
                jugador.setFechaNacimiento(fechaNacimiento);
                jugador.setNumCuenta(numCuenta);
                jugador.setCorreo(correo);
                jugador.setUsuario(usuario);
                jugador.setContrasena(contrasena);

                dao.create(jugador);
                
                HttpSession session = request.getSession(true);

                session.setAttribute("jugador", jugador);

                request.getRequestDispatcher("respuesta.jsp").forward(request, response);
            
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

}