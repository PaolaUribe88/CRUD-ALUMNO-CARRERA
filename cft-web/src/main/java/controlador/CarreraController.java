package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Carrera;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CarreraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
				eliminarCarrera(carreraId);
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
			Carrera carrera = getCarreraById(carreraId);
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
					List<Carrera> carreras = getCarrera();
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
				crearCarrera(carreraNueva);
				response.sendRedirect("/cft-web/CarreraController?accion=listar");
			} catch (SQLException | NamingException e) {				
				e.printStackTrace();
				response.sendError(700);
		   }
		}else {
			Carrera carreraEditar = new Carrera(id, nombre, codigo);
			try {
				editarCarrera(carreraEditar);
				response.sendRedirect("/cft-web/CarreraController?accion=listar");
			}catch(SQLException | NamingException sql) {
					sql.getStackTrace();
					response.sendError(500);
			}
		}		
	}
	
	private Connection getConexion() throws NamingException, SQLException {
		InitialContext contextoInicial = new InitialContext();
		DataSource dataSource = (DataSource) contextoInicial.lookup("java:comp/env/jdbc/postgres");
		return dataSource.getConnection();
	}
	private Carrera getCarreraById(int carreraId) throws SQLException, NamingException {
		try (
				Connection conexion = getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM carreras1 WHERE id = ?");
			) {
				declaracion.setInt(1, carreraId);
				ResultSet rs = declaracion.executeQuery();
				if(rs.next()) {
					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String codigo = rs.getString("codigo");
					return new Carrera(id, nombre, codigo);
				} else {
					return null;
				}
		}
	}
	public List<Carrera> getCarrera()throws SQLException, NamingException{//TRAE TODOS LOS ALUMNOS DE LA BD
		try (
				Connection conexion = getConexion();
				Statement declaracion = conexion.createStatement();
			){
				ResultSet rs = declaracion.executeQuery("SELECT * FROM carreras1");
				List<Carrera> carreras = new ArrayList<Carrera>();
				while (rs.next()) {
					
					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String codigo = rs.getString("codigo");
					Carrera carrera = new Carrera(id, nombre, codigo);
					// agregar a la lista
					carreras.add(carrera);
					
				}
				return carreras;
			}
	}
	private void crearCarrera(Carrera carrera) throws SQLException, NamingException {
		try (
				Connection conexion = getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO carreras1(nombre, codigo) VALUES(?,?)");	
			){
				declaracion.setString(1,carrera.getNombre());
				declaracion.setString(2,carrera.getCodigo());
				int filasInsertadas = declaracion.executeUpdate();
			
			}
		}
	private void eliminarCarrera(int carreraId) throws SQLException, NamingException {
		try (
				Connection conexion = getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("DELETE FROM Carreras1 WHERE id = ?");	
			){
				declaracion.setInt(1,carreraId);
				int filasEliminadas = declaracion.executeUpdate();
			} 
	}
	private void editarCarrera(Carrera carrera) throws SQLException, NamingException {
		try (
				Connection conexion = getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("UPDATE carreras1 SET nombre =?, codigo=? WHERE id = ?");	
			){
				declaracion.setString(1,carrera.getNombre());
				declaracion.setString(2,carrera.getCodigo());
				declaracion.setInt(3,carrera.getId());
				declaracion.executeUpdate();
			} 
		
	}
	
	}
