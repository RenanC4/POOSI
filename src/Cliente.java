	
class Cliente extends Pessoa {

	
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	private String senha;

	public Cliente(String nome, String cpf, Boolean sexo) {
		super(nome, cpf, sexo);
		
	}

	
	
}
