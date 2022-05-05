package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Carrera;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.naming.NamingException;

import daoService.AlumnoDAO;
import daoService.AlumnoDAOImp;
import daoService.CarreraDAO;
import daoService.CarreraDAOImp;

public class AlumnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CarreraDAO carreraDAO;
	private AlumnoDAO alumnoDAO;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	
		this.carreraDAO = new CarreraDAOImp();
		this.alumnoDAO = new AlumnoDAOImp( this.carreraDAO );
	}
	public AlumnoController() {
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion") == null ? "": request.getParameter("accion") ;
		String vistaJSP ="";
		switch (accion) {
			case "eliminar":
				try {		
					int alumnoId = Integer.parseInt( request.getParameter("id"));
					alumnoDAO.eliminarAlumno(alumnoId);
					response.sendRedirect("/cft-web/AlumnoController?accion=listar");
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
					int alumnoId = Integer.parseInt( request.getParameter("id") );
					List<Carrera> carreras = carreraDAO.findAllCarrera();					
					request.setAttribute("carreras", carreras);
					Alumno alumno = alumnoDAO.findAlumnoById(alumnoId);
					request.setAttribute("alumno", alumno);
					vistaJSP = "/WEB-INF/jsp/view/alumno/alumno-form.jsp";
					request
						.getRequestDispatcher(vistaJSP)
						.forward(request, response)
					;
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					response.sendError(500);
				} catch (NamingException ne) {
					ne.printStackTrace();
					response.sendError(500);
				}
				break;
			case "form":
					vistaJSP= "/WEB-INF/jsp/view/alumno/alumno-form.jsp";
					List<Carrera> carreras = null;
					
					try {
						carreras = carreraDAO.findAllCarrera();
						request.setAttribute("carreras", carreras);
					} catch(SQLException | NamingException e) {
						e.printStackTrace();
						response.sendError(300);
						return;
					}
					
					request
					.getRequestDispatcher(vistaJSP)
					.forward(request, response);	
					break;
			case "listar":
				try {
						List<Alumno> alumnos = alumnoDAO.findAllAlumnos();
						request.setAttribute("alumnos", alumnos);
						vistaJSP="/WEB-INF/jsp/view/alumno/alumno-listado.jsp";
						request
								.getRequestDispatcher(vistaJSP)
								.forward(request, response);
					} catch ( SQLException| NamingException e) {
							e.printStackTrace();
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
		String nombre 	 = request.getParameter("nombre");
		Carrera carrera = null;
			
		try {
			int carreraId = Integer.parseInt(request.getParameter("carrera_id"));
			carrera       = carreraDAO.findCarreraById(carreraId);
		} catch (SQLException | NamingException e1 ) {
				e1.printStackTrace();
				response.sendError(500);
				return;
		} 
		
	
			//AL SERVLET LE LLEGA EL PARAMETRO COMO ESTRING, INPUT DATE DE HTML5 
			//DEBEMOS PARSEAR PARA CONVERTIR EN FECHA JAVA get parameter utilizamos la etiqueta de nombre que se le da en el form
			LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("nacimiento"));
		
		if(id == 0) {
			//CREAR ALUMNO
			
			Alumno alumnoNuevo = new Alumno(nombre, carrera, fechaNacimiento);
			try {
				alumnoDAO.crearAlumno(alumnoNuevo);
				response.sendRedirect("/cft-web/AlumnoController?accion=listar");
			} catch (SQLException | NamingException e) {				
				e.printStackTrace();
				response.sendError(700);
			}
		}else {
			// editar
			Alumno alumnoAEditar = new Alumno(id, nombre, carrera, fechaNacimiento);
			try {
				alumnoDAO.editarAlumno(alumnoAEditar);
				response.sendRedirect("/cft-web/AlumnoController?accion=listar");
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
		}
	}

	}
}
