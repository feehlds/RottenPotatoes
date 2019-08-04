package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import DataBase.Database;
import Entidades.Perfil;
import Entidades.Usuario;

public class PerfilRep implements Repository {
	Connection connection = Database.connection;
	
	
	@Override
	public void add(Object item) {
		Perfil perfil = (Perfil)item;
		String insert = "INSERT INTO perfil VALUES(?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(insert);
			stmt.setInt(1, perfil.getId_perfil());
			stmt.setString(2, perfil.getName());
			stmt.setString(3, perfil.getSobrenome());
			stmt.setInt(4, perfil.getIdUser());
			stmt.execute();
			
			loadPerfilRep().put(perfil.getId_perfil(), perfil);
		
			
		}	catch(SQLException exc) {
			exc.printStackTrace();
		}
		
	}

	@Override
	public void remove(Object item) {
		Perfil perfil = (Perfil)item;
		String delete = "DELETE FROM perfil where id_prof = ?";
		HashMap<Integer, Perfil> perfis = loadPerfilRep();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(delete);
			stmt.setInt(1, perfil.getId_perfil());
			stmt.execute();
			perfis.remove(perfil.getId_perfil());
			
		}	catch(SQLException exc) {
			exc.printStackTrace();
		}
		
	}
	
	public void updateSomethingString(Object item, String newThing, String whatIsNew) {
		Perfil perfil = (Perfil)item;
		String update = "UPDATE perfil SET "+  whatIsNew + " = ? WHERE id_prof =?";
		HashMap<Integer, Perfil> perfis = loadPerfilRep();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(update);
			stmt.setString(1, newThing);
			stmt.setInt(2, perfil.getId_perfil());
			stmt.execute();
			perfil.update(newThing, whatIsNew);
		}	catch(SQLException exc) {
			exc.printStackTrace();
		}
		
	}
	
	public HashMap<Integer, Perfil> loadPerfilRep() {
		HashMap<Integer, Perfil> perfis = new HashMap<>();
		Perfil perfil = null;
		
		try{
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM perfil");
			ResultSet consulta = stmt.executeQuery();
			
			while(consulta.next()) {
				int id = consulta.getInt("id_prof");
				String nome = consulta.getString("nome");
				String sobrenome = consulta.getString("sobrenome");
				int idUser = consulta.getInt("id_user");
				
				perfil = new Perfil(nome, sobrenome, idUser);
				perfis.put(id, perfil);
			}
			consulta.close();
			return perfis;
			
		}	catch(SQLException e) {
			System.out.println("Exceção em LoudUserrep " +e);
			return null;
		}
	}
	
	
	
	public Perfil getPerfilById(int id) {
		HashMap<Integer, Perfil> perfis = new HashMap<>();
		perfis = loadPerfilRep();
		
		return perfis.get(id);
	}


}
