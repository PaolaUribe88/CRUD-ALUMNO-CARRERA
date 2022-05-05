package daoService;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtils {
	public static Connection getConexion() throws NamingException, SQLException {
		InitialContext contextoInicial = new InitialContext();	
		DataSource dataSource = (DataSource) contextoInicial.lookup("java:comp/env/jdbc/postgres");
		return dataSource.getConnection();	
	}

}
