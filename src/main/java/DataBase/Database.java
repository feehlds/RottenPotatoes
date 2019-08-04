package DataBase;

import java.sql.*;

import Repositorios.*;

public class Database {

	public static Connection connection = null;
	
	public FilmeRep filmeRep = null;
	public UsuarioRep usuarioRep = null;
	public PerfilRep perfilRep = null;
	public FilmesFav filmesFav = null;
	public FilmesImg filmeImg = null;
	
	public void connectDb() throws Exception{

		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:rottentomatoes.db");
		} catch(Exception e){
			System.out.println(e);
		}
	}

	public void disconnctDb() throws Exception{
		if(connection != null){
			try {
				connection.close();
				System.out.println("DataBase Desconectado!");
				
			}	catch(SQLException exc) {
				System.out.println(exc);
			}
		}
	}
	
	public static Database runDb() throws Exception {
		try {
			Database db = new Database();
			db.connectDb();
			db.filmeRep = new FilmeRep();
			db.usuarioRep = new UsuarioRep();
			db.perfilRep = new PerfilRep();
			db.filmesFav = new FilmesFav();
			db.filmeImg = new FilmesImg();
			return db;
		}	catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
