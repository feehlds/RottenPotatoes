package Entidades;

public class Filme {
    //VARI�VEIS

    private String nome;
    private int ano;
    private String genero;
    private String diretor;
    private int numeroFans;
    //private HashSet<Usuario> fans;

    //CONSTRUCTOR
    public Filme(String nome, int ano, String genero, String diretor){
    	this.nome = nome;
        this.ano = ano;
        this.genero = genero;
        this.diretor = diretor;
        this.numeroFans = 0;
       // this.fans = new HashSet();
    }


    
    //GETTERS AND SETTERS


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getNumeroFans() {
        return numeroFans;
    }

    public void setNumeroFans(int numeroFans) {
        this.numeroFans = numeroFans;
    }
    
	@Override
	public String toString() {
		return "Filme [Nome: " + nome + ", Ano: " + ano + ", Gênero: " + genero + ", Diretor: " + diretor + ", Favoritos: "
				+ numeroFans + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + ((diretor == null) ? 0 : diretor.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Filme other = (Filme) obj;
		if (ano != other.ano)
			return false;
		if (diretor == null) {
			if (other.diretor != null)
				return false;
		} else if (!diretor.equals(other.diretor))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
