
class Funcionario extends Pessoa {
	

	private String id;
	private String senha;
	
	
	
	public Funcionario(String nome, String cpf, Boolean sexo) {
		super();
		// TODO Auto-generated constructor stub
	}

	

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

	public double aplicarDesconto(double preco , int porcentagem){
		return preco = preco*(porcentagem/100) ;
		
		
	}
		
	public boolean autentica(String senha) {
	    if (senha.equals(senha)) {
	      System.out.println("Acesso Permitido!");
	      return true;
	    } else {
	      System.out.println("Acesso Negado!");
	      return false;
	    }
	  }
	
	public double Desconta(double Preco, double Desconto){
		double preco = Preco-Desconto;
		
		return preco;
		
	
	}
	
	public double Desconta(double Preco, double Desconto, double codDesconto){
		double preco = (Preco-Desconto) - codDesconto;
		
		return preco;
		
	
	
}
	}
