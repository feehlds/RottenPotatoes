package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.Database;

public class FilmesImg {
	
	Connection connection = Database.connection;
	
	public String getUrl(int id){
		String select = "SELECT * FROM filmeImg where id = ?";	
		try {
			PreparedStatement stmt = connection.prepareStatement(select);
			stmt.setInt(1, id);
			ResultSet consulta;
			consulta = stmt.executeQuery();
			return consulta.getString("url");
		}	catch(SQLException exc) {
				return null;
		}
	}
	
}
