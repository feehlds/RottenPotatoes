package Entidades;

import Rotten.App;

public class Usuario {
    private String email;
    private String pswd;
    private int idPerfil;
    private Perfil perfil;


    public Usuario(String email, String pswd, int idPerfil){
    	this.email = email;
        this.pswd = pswd;
        this.idPerfil = idPerfil;
        this.perfil = App.db.perfilRep.getPerfilById(idPerfil);
      
    }

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPswd() {
		return pswd;
	}


	public void setPswd(String pswd) {
		this.pswd = pswd;
	}


	public int getIdPerfil() {
		return idPerfil;
	}


	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
		 this.perfil = App.db.perfilRep.getPerfilById(idPerfil);
	}
	
	//newThing recebe a string que é pra ser mudada 
	//whatIsNew recebe a string que indica o que deve ser mudado
	public void update(String newThing, String whatIsNew) {
		switch(whatIsNew) {
		case "email" :
			setEmail(newThing);
		break;
		case "senha" :
			setPswd(newThing);
		break;
		}
		
	}

	@Override
	public String toString() {
		return "Usuario [email: " + email + "]" + pswd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	
}
