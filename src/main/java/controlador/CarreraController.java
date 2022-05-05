package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import daoService.CarreraDAO;
import daoService.CarreraDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Carrera;


public class CarreraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CarreraDAO carreraDAO;
       
   	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		this.carreraDAO= new CarreraDAOImp();
	}
	public CarreraController() {
    	
        super();
     }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion") == null ? "": request.getParameter("accion");
		String vistaJSP ="";
		switch (accion) {
		case "eliminar":
			try {		
				int carreraId = Integer.parseInt( request.getParameter("id"));
				carreraDAO.eliminarCarrera(carreraId);
				response.sendRedirect("/cft-web/CarreraController?accion=listar");
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					response.sendError(100);
				} catch (NamingException e1) {
					e1.printStackTrace();
					response.sendError(200);
				}				
				break;
		case "editar":
		try {
			int carreraId = Integer.parseInt( request.getParameter("id"));
			Carrera carrera = carreraDAO.findCarreraById(carreraId);
			request.setAttribute("carrera",carrera);
			vistaJSP= "/WEB-INF/jsp/view/carrera/carrera-form.jsp";
			request
					.getRequestDispatcher(vistaJSP)
					.forward(request, response);	
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			response.sendError(300);
		} catch (NamingException ne) {
			ne.printStackTrace();
			response.sendError(400);
		}
		break;		
		
		case "form":
				vistaJSP= "/WEB-INF/jsp/view/carrera/carrera-form.jsp";
			request
				.getRequestDispatcher(vistaJSP)
				.forward(request, response);	
				break;
		case "listar":
			try {
					List<Carrera> carreras = carreraDAO.findAllCarrera();
					request.setAttribute("carreras", carreras);
					vistaJSP="/WEB-INF/jsp/view/carrera/carrera-listado.jsp";
					request
							.getRequestDispatcher(vistaJSP)
							.forward(request, response);
				} catch ( SQLException| NamingException e) {
						response.sendError(500);
				}
				break;
			default:
					response.sendError(600);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
				id= Integer.parseInt(request.getParameter("id"));
		
		}  catch (NumberFormatException e) {
			System.err.println("id se setea a 0 de manera automática.");
		}
				String nombre = request.getParameter("nombre");
				String codigo = request.getParameter("codigo");
		if(id == 0) {
			Carrera carreraNueva = new Carrera(nombre,codigo);
			try {
				carreraDAO.crearCarrera(carreraNueva);
				response.sendRedirect("/cft-web/CarreraController?accion=listar");
			} catch (SQLException | NamingException e) {				
				e.printStackTrace();
				response.sendError(700);
		   }
		}else {
			Carrera carreraEditar = new Carrera(id, nombre, codigo);
			try {
				carreraDAO.editarCarrera(carreraEditar);
				response.sendRedirect("/cft-web/CarreraController?accion=listar");
			}catch(SQLException | NamingException sql) {
					sql.getStackTrace();
					response.sendError(500);
			}
		}		
	}
	
}