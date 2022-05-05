package daoService;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.Carrera;

public interface CarreraDAO {
	
	public List<Carrera> findAllCarrera() throws SQLException, NamingException;
	public Carrera findCarreraById(int carreraId) throws SQLException, NamingException;
	public void crearCarrera(Carrera carrera) throws SQLException, NamingException;
	public void editarCarrera(Carrera carrera) throws SQLException, NamingException;
	public void eliminarCarrera(int id) throws SQLException, NamingException;
		

}
