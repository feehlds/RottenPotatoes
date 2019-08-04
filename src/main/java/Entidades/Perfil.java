package Entidades;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

import Rotten.App;



public class Perfil {
    private int id_user;
    private String name;
    private String sobrenome;
    private int age; //tirar idade de birthday
    private int id_perfil;
    private HashMap<Integer, Perfil> contatos;
    private LinkedHashSet<Filme> filmesFavoritos;

 
	public Perfil(String name, String sobrenome, int id_perfil){
        this.name = name;
        this.setSobrenome(sobrenome);
        this.setId_perfil(id_perfil);
        this.id_user = id_perfil;
        this.contatos = new HashMap<Integer, Perfil>();
        this.filmesFavoritos = App.db.filmesFav.loadFilmesFav(id_user);
    }

    //gestão de contatos e filmes
    public void adicionarContato(Perfil usuario, int idContato){
        contatos.put(idContato ,usuario);
        //database.update (implementar)
    }
    public void removerContato(Perfil contato){
    	for(Entry<Integer, Perfil> entry : this.contatos.entrySet()){
    		if(entry.getValue().equals(contato)){
    			this.contatos.remove(entry.getKey());
    		}
    	}
    }

    public void favoritarFilme(Filme filme){
    	//TODO
    	if(filmesFavoritos.contains(filme))
    		return;
    	else {
    		int numFans = filme.getNumeroFans();
    		filme.setNumeroFans(numFans + 1);
    		App.db.filmesFav.add(filme);
    	}
    }
    public void desfavoritarFilme(Filme filme){
        filmesFavoritos.remove(filme);
        int numFans = filme.getNumeroFans();
        filme.setNumeroFans(numFans - 1);
        //database.update (implementar)
    }

    
	public void update(String newThing, String whatIsNew) {
		switch(whatIsNew) {
		case "nome" :
			setName(newThing);
		break;
		case "sobrenome" :
			setSobrenome(newThing);
		break;
		}	
	}
    //getters+setters

	public LinkedHashSet<Filme> getFilmesFavoritos() {
			return filmesFavoritos;
	}

	
    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id) {
        this.id_user = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	public int getId_perfil() {
		return id_perfil;
	}

	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
}
