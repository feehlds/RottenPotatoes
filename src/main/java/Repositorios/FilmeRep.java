package Repositorios;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import DataBase.Database;

import java.sql.ResultSet;


import Entidades.Filme;
import Entidades.Usuario;

public class FilmeRep implements Repository<Object> {
	
	Connection connection = Database.connection;
	
	@Override
	public void add(Object item) {
		String insertion = "INSERT INTO filmes VALUES(?,?,?,?,?,?)";
		Filme filme = (Filme) item;
		int x = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement(insertion);
			for(int i = 1; ;i++){
				if(getFilmesById(i) == null){
					x = i;
					break;
				}
			}
			stmt.setInt(1, x);
			stmt.setString(2, filme.getNome());
			stmt.setInt(3, filme.getAno());
			stmt.setString(4, filme.getDiretor());
			stmt.setInt(5, filme.getNumeroFans());
			stmt.setString(6, filme.getGenero());
			stmt.execute();
			loadFilmeRep().put(x, filme);
			System.out.println("Adicionado na base de dados!");
			
		}	catch(SQLException e) {
			System.out.println("Exception Em Filmerep add" +e);
		}
		
	}

	@Override
	public void remove(Object item) {
		Filme filme = (Filme)item;
		HashMap<Integer, Filme> filmes = loadFilmeRep();
		String delete = "Delete FROM filmes WHERE id_filme=?";
		
		for(Map.Entry<Integer, Filme> entry : filmes.entrySet()){
			if(entry.getValue().equals(filme)){
				int id = entry.getKey();				
				try {
					PreparedStatement stmt = connection.prepareStatement(delete);
					stmt.setString(1, "" + id);
					stmt.execute();
					filmes.remove(entry.getKey());
					System.out.println("Deletado com sucesso");
					
				}	catch(SQLException e) {
					System.out.println("Exception Em Filmerep remove "+e);
				}
				return;
			}
		}
	}
	
	public void updateFns(int i) {
		
	}
	
	public HashMap<Integer, Filme> loadFilmeRep() {
		HashMap<Integer, Filme> filmes = new HashMap<>();
		Filme filme = null;
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM filmes");
			ResultSet consulta = stmt.executeQuery();
			
			while(consulta.next()){
				int id = consulta.getInt("id_filme");
				String nome = consulta.getString("nome_filme");
				int ano = consulta.getInt("ano_filme");
				String genero = consulta.getString("genero");
				String diretor = consulta.getString("diretor");
				int numeroFans = consulta.getInt("numero_favoritos");
				
				filme = new Filme(nome, ano, genero, diretor);
				filme.setNumeroFans(numeroFans);
				filmes.put(id , filme);
			}
			consulta.close();
			return filmes;
		}	catch(SQLException e) {
			System.out.println("Exception Em FilmerepLoad " + e);
			return null;
		}
	}
	
	public int getId(Filme filme) {
		HashMap<Integer, Filme> filmes = loadFilmeRep();

		for(Map.Entry<Integer, Filme> entry : filmes.entrySet()) {
			if(entry.getValue().equals(filme)) {
				return entry.getKey();
			}
		}
		return 0;
	}

	public Filme getFilmesById(int id) {
		HashMap<Integer, Filme> filmes = new HashMap<>();
		filmes = loadFilmeRep();
		
		return filmes.get(id);
	}
}