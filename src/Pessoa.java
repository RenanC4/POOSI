public class Pessoa {
	private String nome;
	private String cpf;
	private Boolean sexo;

	
	public Pessoa (String nome, String cpf, Boolean sexo){
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = false;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Boolean getSexo() {
		return sexo;
	}


	public void setSexo(Boolean sexo) {
		this.sexo = sexo;
	}
	
	
}
