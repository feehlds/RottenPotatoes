package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DataBase.Database;
import Entidades.Filme;
import Rotten.App;

public class FilmesFav implements Repository<Object>{
	
	Connection connection = Database.connection;


	public void add(Object item, int i) {
		String insert = "INSERT INTO usuario_filmes VALUES (?, ?)";
		Filme filme = (Filme)item;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setInt(1, App.db.filmeRep.getId(filme));
			stmt.setInt(2, i);
			stmt.execute();
		}	catch(SQLException exc) {
			exc.printStackTrace();
		}
	}

	@Override
	public void remove(Object item) {
		// TODO Auto-generated method stub
		
	}
	public LinkedHashSet<Filme> loadFilmesFav(int idUser){
		String select = "SELECT * FROM usuario_filmes WHERE id_user = ?";
		LinkedHashSet<Filme> favoritos = new LinkedHashSet<Filme>();
		try {
			PreparedStatement stmt = connection.prepareStatement(select);
			stmt.setInt(1, idUser);
			ResultSet consulta = stmt.executeQuery();
			while(consulta.next()) {
				favoritos.add(App.db.filmeRep.getFilmesById(consulta.getInt("id_filme")));
			}
			return favoritos;
		}	catch(SQLException e) {
			System.out.println("Ta aqui");
			return favoritos;
		}	
	}

	@Override
	public void add(Object item) {
		
	}
	
}
