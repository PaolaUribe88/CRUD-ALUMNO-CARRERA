package daoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Alumno;
import modelo.Carrera;

public class AlumnoDAOImp implements AlumnoDAO {
	//IMPLEMENTACION DE INTERFAZ ALUMNODAO.JAVA, INTERFAZ QUE SE GENERA DE LA CLASE POJO ALUMNO.JAVA
	private CarreraDAO carreraDAO;
	
	public AlumnoDAOImp(CarreraDAO carreraDAO) {
		this.carreraDAO = carreraDAO;
		}
	@Override
	public List<Alumno> findAllAlumnos() throws SQLException, NamingException  {
		try (
				Connection conexion = DbUtils.getConexion();
				Statement declaracion = conexion.createStatement();
			){
				ResultSet rs = declaracion.executeQuery("SELECT * FROM alumnos1");
				List<Alumno> alumnos = new ArrayList<>();
				while (rs.next()) {
					
					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					int carreraId = rs.getInt("carreras1_id");
					Carrera carrera = carreraDAO.findCarreraById(carreraId);
					LocalDate fechaNacimiento = rs.getObject("fecha_nacimiento", LocalDate.class);
					// instanciar objeto alumno
					Alumno alumno = new Alumno(id, nombre, carrera, fechaNacimiento);
					// agregar a la lista
					alumnos.add(alumno);
					
				}
				return alumnos;
			}
	}
	@Override
	public Alumno findAlumnoById(int alumnoId) throws NamingException, SQLException{
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM alumnos1 WHERE id = ?");
			) {
				declaracion.setInt(1, alumnoId);
				ResultSet rs = declaracion.executeQuery();
				if(rs.next()) {
					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					int carreraId = rs.getInt("carreras1_id");
					Carrera carrera = carreraDAO.findCarreraById(carreraId);
					LocalDate fechaNacimiento = rs.getObject("fecha_nacimiento", LocalDate.class);
					return new Alumno(id, nombre, carrera, fechaNacimiento);
				} else {
					return null;
				}
		}
	}
	@Override
	public void crearAlumno(Alumno alumno)throws SQLException, NamingException {
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO alumnos1(nombre, carreras1_id, fecha_nacimiento) VALUES(?,?,?)");	
			){
				declaracion.setString(1,alumno.getNombre());
				declaracion.setInt(2,alumno.getCarrera().getId());
				declaracion.setObject(3,alumno.getFechaNacimiento());
				int filasInsertadas = declaracion.executeUpdate();
			
			}
			
	}

	@Override
	public void editarAlumno(Alumno alumno) throws SQLException, NamingException{
		String sql = "UPDATE alumnos1"
				+" SET nombre = ?, carreras1_id = ?, fecha_nacimiento = ?"
				+" WHERE id = ?";
		try (
			Connection conexion = DbUtils.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
		) {
			declaracion.setString(1, alumno.getNombre());
			declaracion.setInt(2, alumno.getCarrera().getId());
			declaracion.setObject(3, alumno.getFechaNacimiento());
			declaracion.setInt(4, alumno.getId());
			declaracion.executeUpdate();
		}
			
	}

	@Override
	public void eliminarAlumno(int alumnoId) throws SQLException, NamingException{
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("DELETE FROM alumnos1 WHERE id = ?");	
			){
				declaracion.setInt(1,alumnoId);
				int filasEliminadas = declaracion.executeUpdate();
			} 
	}

}
