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

import modelo.Carrera;

public class CarreraDAOImp implements CarreraDAO {

	@Override
	public List<Carrera> findAllCarrera() throws SQLException, NamingException {
		try (
				Connection conexion = DbUtils.getConexion();
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

	@Override
	public Carrera findCarreraById(int carreraId)  throws SQLException, NamingException{
		try (
				Connection conexion = DbUtils.getConexion();
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

	@Override
	public void crearCarrera(Carrera carrera)  throws SQLException, NamingException{
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO carreras1(nombre, codigo) VALUES(?,?)");	
			){
				declaracion.setString(1,carrera.getNombre());
				declaracion.setString(2,carrera.getCodigo());
				int filasInsertadas = declaracion.executeUpdate();
			
			}
		}

	@Override
	public void editarCarrera(Carrera carrera) throws SQLException, NamingException{
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("UPDATE carreras1 SET nombre =?, codigo=? WHERE id = ?");	
			){
				declaracion.setString(1,carrera.getNombre());
				declaracion.setString(2,carrera.getCodigo());
				declaracion.setInt(3,carrera.getId());
				declaracion.executeUpdate();
			} 
		
	}
	
	@Override
	public void eliminarCarrera(int carreraId) throws SQLException, NamingException {
		try (
				Connection conexion = DbUtils.getConexion();
				PreparedStatement declaracion = conexion.prepareStatement("DELETE FROM Carreras1 WHERE id = ?");	
			){
				declaracion.setInt(1,carreraId);
				int filasEliminadas = declaracion.executeUpdate();
			} 
	}

}
