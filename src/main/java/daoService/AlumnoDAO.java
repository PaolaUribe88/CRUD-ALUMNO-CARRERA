package daoService;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.Alumno;

public interface AlumnoDAO {//INTERFAZ DE LA CLASE POJO ALUMNO.JAVA
	
		public List<Alumno> findAllAlumnos() throws SQLException, NamingException;
		public Alumno findAlumnoById(int id)throws NamingException, SQLException;
		public void crearAlumno(Alumno alumno) throws SQLException, NamingException;
		public void editarAlumno(Alumno alumno) throws SQLException, NamingException;
		public void eliminarAlumno(int id) throws SQLException, NamingException;
		
		
}
