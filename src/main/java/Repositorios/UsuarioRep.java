package Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import DataBase.Database;
import Entidades.Usuario;

public class UsuarioRep implements Repository<Object>{
	Connection connection = Database.connection;
	
	@Override
	public void add(Object item) {
		String insertion = "INSERT INTO usuario VALUES(?, ?, ?, ?)";
		Usuario usuario = (Usuario) item;
		int x = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(insertion);
			for(int i = 1; ;i++){
				if(getUserById(i) == null){
					x = i;
					break;
				}
			}		
			stmt.setInt(1, x);
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getPswd());
			stmt.setInt(4, usuario.getIdPerfil());
			stmt.execute();
			loadUserRep().put(x, usuario);
			
		}	catch(SQLException e) {
			System.out.println("Exceção em addUsuario" + e);
		}
	}

	@Override
	public void remove(Object item) {
		
		HashMap<Integer, Usuario> usuarios = loadUserRep();
		Usuario usuario = (Usuario) item;
		String delete = "DELETE FROM usuario WHERE id_user =?";
		
		for(Map.Entry<Integer, Usuario> entry : usuarios.entrySet()){
			if(usuario.equals(entry.getValue())){
				try {
					PreparedStatement stmt = connection.prepareStatement(delete);
					stmt.setInt(1, entry.getKey());
					stmt.execute();
					usuarios.remove(entry.getKey());
					System.out.println("Deletado");
		
				}	catch(SQLException e) {
					System.out.println("Exceção em removeUser " +e);
				}	
			}
		}
		
	}
	
	public HashMap<Integer, Usuario> loadUserRep() {
		
		HashMap<Integer, Usuario> usuarios = new HashMap<>();
		Usuario usuario = null;
		
		try{
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM usuario");
			ResultSet consulta = stmt.executeQuery();
			
			while(consulta.next()) {
				int id = consulta.getInt("id_user");
				String email = consulta.getString("email");
				String senha = consulta.getString("senha");
				int idPefil = consulta.getInt("id_perfil");
				
				usuario = new Usuario(email, senha, idPefil);
				usuarios.put(id, usuario);
			}
			consulta.close();
			return usuarios;
			
		}	catch(SQLException e) {
			System.out.println("Exceção em LoudUserrep " +e);
			return null;
		}
	}
	
	public Usuario validarLogin(String email, String pass) {
		
		HashMap<Integer, Usuario> usuarios = new HashMap<>();
		usuarios = loadUserRep();
		
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM usuario WHERE email = ? ");
			stmt.setString(1, "" +  email);
			ResultSet consulta = stmt.executeQuery();
			
			if(usuarios.get(consulta.getInt("id_user")).getPswd().equals(pass)){
				return usuarios.get(consulta.getInt("id_user"));
			}
			else {
				return null;
			}
			
		}	catch(SQLException e) {
			System.out.println("Exceção em validar login " + e);
			return null;
		}
	}
	
	public Usuario getUserById(int id) {
		
		HashMap<Integer, Usuario> usuarios = new HashMap<>();
		usuarios = loadUserRep();
		
		return usuarios.get(id);
	}
    
}
