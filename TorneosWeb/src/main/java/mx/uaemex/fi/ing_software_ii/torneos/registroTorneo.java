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

import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.TorneoDaoDerby;
import mx.uaemex.fi.ing_software_ii.torneos.dao.derby.TorneoDaoDerbyImp;
import mx.uaemex.fi.ing_software_ii.torneos.dao.dto.Torneo;

@WebServlet("/RegistroTorneo")
public class registroTorneo extends HttpServlet {
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
	        TorneoDaoDerby dao;
	        TorneoDaoDerbyImp realDao;
	        Torneo torneo = new Torneo();
	        
	        int idtorneo = Integer.parseInt(request.getParameter("idtorneo"));
	        String nombre = request.getParameter("nombre");
	        String disciplina = request.getParameter("disciplina");
	        int numEquipos = Integer.parseInt(request.getParameter("numEquipos"));
	        String fechaInicio = request.getParameter("fechaInicio");
	        String fechaFin = request.getParameter("fechaFin");
	        int numGrupos = Integer.parseInt(request.getParameter("numGrupos"));
	       
	        
	        try {
	            con = this.ds.getConnection();

	            realDao = new TorneoDaoDerbyImp();
	            realDao.setCon(con);
	            dao = realDao;

	                torneo.setId(idtorneo);
	                torneo.setNombre(nombre);
	                torneo.setFecha_inicio(fechaInicio);
	                torneo.setFecha_fin(fechaFin);
	                torneo.setDisciplina(disciplina);
	                torneo.setNumero_equipos(numEquipos);
	                torneo.setNumero_grupos(numGrupos);

	                dao.create(torneo);
	                
	                request.setAttribute("idtorneo", idtorneo);
	                request.setAttribute("nombre", nombre);
	                request.setAttribute("fechaInicio", fechaInicio);
	                request.setAttribute("fechaFin", fechaFin);
	                request.setAttribute("disciplina", disciplina);
	                request.setAttribute("numEquipos", numEquipos);
	                request.setAttribute("numGrupos", numGrupos);
	                
	                request.getRequestDispatcher("grupos.jsp").forward(request, response);
	            
	        } catch (Exception e) {
	        	throw new ServletException(e.getMessage());
	        }
	        
	    }

}
