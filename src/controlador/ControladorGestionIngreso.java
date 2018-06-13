package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Gestion;
import modelo.Usuario;
import modelo.UsuarioDAO;
import modelo.gestionDAO;

/**
 * Servlet implementation class ControladorGestion
 */
@WebServlet("/ControladorGestionIngreso")
public class ControladorGestionIngreso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorGestionIngreso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession(false)!= null){
		
			String id_gestion_borrar = request.getParameter("borrar");
			if(id_gestion_borrar== "0"){
				
				List<Gestion> misGestiones = new gestionDAO().getAllGestiones();
				request.setAttribute("gestiones", misGestiones);
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/gestion.jsp").forward(request, response);
				return;
				
			}
			int id_borrar = Integer.parseInt(id_gestion_borrar);
			gestionDAO miGestionDAO = new gestionDAO();
			miGestionDAO.borrarGestion(id_borrar);
			List<Gestion> misGestiones = new gestionDAO().getAllGestiones();
			request.setAttribute("gestiones", misGestiones);
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/gestion.jsp").forward(request, response);
		
		
		}else{
			
			response.sendRedirect("./index.html");
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			UsuarioDAO miUsuarioDAO = new UsuarioDAO();
			String pass_a_MD5 = miUsuarioDAO.stringAMD5(password);
			Usuario miUser = miUsuarioDAO.validaAdmin(user, pass_a_MD5);

			if(miUser != null){
				
				HttpSession session = request.getSession();
				   session.setMaxInactiveInterval(120*60);
				
				List<Gestion> misGestiones = new gestionDAO().getAllGestiones();
				request.setAttribute("gestiones", misGestiones);
				
				getServletContext().getRequestDispatcher("/WEB-INF/jsp/gestion.jsp").forward(request, response);
			}else{
				
				response.sendRedirect("./LogInGestion.html");
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			response.sendRedirect("./LogInGestion.html");
		}

		
	}

}
