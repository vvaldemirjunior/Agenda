package entidades;

public class Contato {
	
	private int id;
	private String nome;
	private String fone;
	private String email;
	
	public Contato(int id, String nome, String fone, String email) {
		this.id = id;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}
	
	public Contato(String nome, String fone, String email) {		
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contato [ID: " + id + ", Nome: " + nome + ", Telefone: " + fone + ", E-mail: " + email + "]";
	}
	

	
	
	
	
	

}
