/**
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import javax.swing.JOptionPane;

	import java.sql.*;
	import javax.swing.JOptionPane;

	public class acessaBanco {
	private Connection con;
		public acessaBanco(){
			
		}
		
		public void ConectaBD(String id, String senha) {
			
			try{
				String jdbc = "jdbc:sqlserver://localhost:1433;databaseName=GAMESTORE";
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				con = DriverManager.getConnection(jdbc, id, senha);
				JOptionPane.showMessageDialog(null,"Conexao realizada com sucesso.");
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null,"N�o foi Possivel conectar no Banco de Dados.");
				
			} catch (Exception e){
				System.out.println("N�o foi possivel conectar" + e);
				
			}
			
		}
		
		public ResultSet[] ConsultarCliente(String nome, String doc){
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente WHERE nome_cliente LIKE '"+ nome +"%' or codigo_cliente = '"+ doc +"'");
				
				Statement stmt1 = con.createStatement();
				ResultSet count = stmt1.executeQuery("SELECT COUNT(*) AS tm FROM Cliente WHERE nome_cliente LIKE '"+ nome +"%' or codigo_cliente = '"+ doc +"'");
				
				ResultSet[] result = new ResultSet[]{
						rs, count
				};
				System.out.println("Consulta realizada com sucesso");
				return result;
				
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,e);
			}
			return null;
		}
		
		public ResultSet[] ConsultarEncomenda(String produto, String cliente){
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Produtos "
										+ "INNER JOIN Encomendas_cliente ON Produtos.id_produto = Encomendas_cliente.id_produto "
										+ "INNER JOIN Cliente ON Encomendas_cliente.id_cliente = Cliente.id_cliente "
										+ "WHERE Produtos.nome_produto LIKE '"+ produto +"%' AND "
										+ "Cliente.nome_cliente LIKE '"+cliente+"%'");
				
				Statement stmt1 = con.createStatement();
				ResultSet count = stmt1.executeQuery("SELECT COUNT(*) AS tm "
										+ "FROM Produtos "
										+ "INNER JOIN Encomendas_cliente ON Produtos.id_produto = Encomendas_cliente.id_produto "
										+ "INNER JOIN Cliente ON Encomendas_cliente.id_cliente = Cliente.id_cliente "
										+ "WHERE Produtos.nome_produto LIKE '"+ produto +"%' AND "
										+ "Cliente.nome_cliente LIKE '"+cliente+"%'");
						
				ResultSet[] result = new ResultSet[]{
						rs, count
				};
				return result;
				
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println(e.getErrorCode());
			}
			return null;
		}
		
		public ResultSet[] ConsultarCompra(String produto, String cliente){
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Produtos "
										+ "INNER JOIN Compras_Cliente ON Produtos.id_produto = Compras_Cliente.id_produto "
										+ "INNER JOIN Cliente ON Compras_Cliente.id_cliente = Cliente.id_cliente "
										+ "WHERE Produtos.nome_produto LIKE '"+ produto +"%' AND "
										+ "Cliente.nome_cliente LIKE '"+cliente+"%'");
				
				Statement stmt1 = con.createStatement();
				ResultSet count = stmt1.executeQuery("SELECT COUNT(*) AS tm "
										+ "FROM Produtos "
										+ "INNER JOIN Compras_Cliente ON Produtos.id_produto = Compras_Cliente.id_produto "
										+ "INNER JOIN Cliente ON Compras_Cliente.id_cliente = Cliente.id_cliente "
										+ "WHERE Produtos.nome_produto LIKE '"+ produto +"%' AND "
										+ "Cliente.nome_cliente LIKE '"+cliente+"%'");
						
				ResultSet[] result = new ResultSet[]{
						rs, count
				};
				return result;
				
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println(e.getErrorCode());
			}
			return null;
		}
		
		public ResultSet[] ConsultarProduto(String produto, int tipo){
			try{
				Statement stmt = con.createStatement();
				ResultSet rs;
				System.out.print("tipo"+tipo);
				if(tipo == 0){
						rs = stmt.executeQuery("SELECT * FROM Produtos "
							+ "LEFT JOIN Tipo_Produto ON cd_tipo_produto = cd_tipo "
							+ "WHERE nome_produto LIKE '"+ produto +"%'");
				}else{
					 	rs = stmt.executeQuery("SELECT * FROM Produtos, Tipo_Produto "
							+ "WHERE nome_produto LIKE '"+ produto +"%' AND "
							+ "cd_tipo_produto = cd_tipo AND "
							+ "cd_tipo = "+tipo+"");
				}
				Statement stmt1 = con.createStatement();
				ResultSet count = stmt1.executeQuery("SELECT COUNT(*) AS tm FROM Produtos WHERE nome_produto LIKE '"+ produto +"%'"); 
				
				ResultSet[] result = new ResultSet[]{
					rs, count	
				};
				
				System.out.println("Consulta realizada com sucesso" + produto);
				return result;
				
			}catch(SQLException e){
				System.out.print(tipo);
			}
			return null;
		}
		
		public ResultSet ConsultarTipoProduto(){
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Tipo_Produto");
				
				return rs;
			}catch(SQLException e){
				return null;
			}
			
		}
		
		public boolean Autenticar(String login, String senha){
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE login='"+login+"' AND senha='"+senha+"'");
				rs.next();
				if(rs.getRow() > 0)
					return true;
				else 
					return false;
				
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,e);
			}
			return false;
		}
		
		public void InserirProduto(String nome, String descricao, double preco, int cd_tipo){
			try{
				if(cd_tipo > 0){
				Statement stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO Produtos(descricao_produto, nome_produto, preco_produto, cd_tipo_produto) "
						+ "VALUES ('"+descricao+"', '"+nome+"', "+preco+", "+cd_tipo+")");			
				}else{
					JOptionPane.showMessageDialog(null,"Selecione um tipo");
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,e);
			}
		}
		
		public void EditarProduto(int id, String nome, String descricao, double preco, int cd_tipo){
			try{
				if(cd_tipo > 0){
					Statement stmt = con.createStatement();
					stmt.executeUpdate("UPDATE Produtos SET descricao_produto = '"+descricao+"', nome_produto='"+nome+"', preco_produto='"+preco+"', cd_tipo_produto = "+cd_tipo+" WHERE id_produto="+id+" ");
				}else{
					JOptionPane.showMessageDialog(null,"Selecione um tipo");
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,e);
			}
		}
		
		public void ExcluirProduto(int id){
			try{
				Statement stmt = con.createStatement();
				int count = stmt.executeUpdate("DELETE FROM Produtos WHERE id_produto = "+id+"");
				
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,e);
			}
		}
		
		public void SetIMG(String path, String usuario){
			try{
				Statement stmt = con.createStatement();
				stmt.executeUpdate("UPDATE usuarios SET imagem = '"+path+"' WHERE login = '"+usuario+"'");
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		public String GetIMG(String usuario){
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT imagem FROM usuarios WHERE login = '"+usuario+"'");
				rs.next();
				String img = rs.getString("imagem");
				return img;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return null;
		}
		
		public void CriarTabela(){
			try{
			String tabela_tipo_produto = 	"CREATE TABLE Tipo_Produto" +
					"(cd_tipo int NOT NULL, " +
					"descricao_tipo varchar(10) NOT NULL, " +
					"PRIMARY KEY (cd_tipo)) ";
			
			String tipo_game = "INSERT INTO Tipo_Produto VALUES (1, 'Games')";
			String tipo_console = "INSERT INTO Tipo_Produto VALUES (2, 'Consoles')";
			String tipo_acessorio = "INSERT INTO Tipo_Produto VALUES (3, 'Acessorios')";
			
			String tabela_produtos = "CREATE TABLE Produtos " +
					"(id_produto int IDENTITY(1,1) NOT NULL , " +
					"descricao_produto varchar(50) NOT NULL, " +
					"nome_produto varchar(20), " + 
					"preco_produto decimal(10,2), " +
					"cd_tipo_produto int, " +
					"PRIMARY KEY (id_produto), "
					+"CONSTRAINT fk_cd_tipo_produto FOREIGN KEY(cd_tipo_produto) "
						+"REFERENCES Tipo_Produto(cd_tipo))"; 
			
			String games = "CREATE TABLE Games" +
					 "(id_produto int NOT NULL, "+
					 "nome_game varchar(30) NOT NULL, " +
					 "memory_required int, " +
				     "numero_de_pl int, " +
					 "detalhes varchar(50) "
					+"PRIMARY KEY(id_produto) "
					+"CONSTRAINT fk_id_produto_game FOREIGN KEY(id_produto) "
					+"REFERENCES Produtos(id_produto) "
					+ "ON DELETE CASCADE)";
			
			String console = "CREATE TABLE Consoles" +
					"(id_produto int NOT NULL, " +
					"driver_type varchar(30) NOT NULL, " +
					"size int, " +
					"detalhes_consoles varchar(50)"
					+"PRIMARY KEY(id_produto) "
					+"CONSTRAINT fk_id_produto_console FOREIGN KEY(id_produto) "
					+"REFERENCES Produtos(id_produto) "
					+ "ON DELETE CASCADE)";	
			
			String acessorios = "CREATE TABLE Acessorios" + 
					"(id_produto int NOT NULL, " + 
					"nome_acessorio varchar(20) NOT NULL, " +
					"descricao_acessorio varchar(50) ," +
					"detalhes_acessorio varchar(50)"
					+"PRIMARY KEY(id_produto) "
					+"CONSTRAINT fk_id_produto_acessorio FOREIGN KEY(id_produto) "
					+"REFERENCES Produtos(id_produto) "
					+ "ON DELETE CASCADE)";
			
			String cliente = "CREATE TABLE Cliente" +
					"(id_cliente int IDENTITY(1,1) NOT NULL, " +
					"codigo_cliente varchar(15) NOT NULL, " +
					"nome_cliente varchar(20), " +
					"end_cliente varchar(20), " +
					"detalhes_cliente varchar(30), " +
					"PRIMARY KEY (id_cliente))";
			
			String pedido_cliente = "CREATE TABLE Encomendas_cliente" +
					"(id int IDENTITY(1,1) NOT NULL, " +
					"data date, " +
					"detalhes varchar(50), " +
					"id_produto int, " +
					"id_cliente int NOT NULL, " +
					"PRIMARY KEY(id), "
					+"CONSTRAINT fk_id_cliente_pedido FOREIGN KEY(id_cliente) "
					+"REFERENCES Cliente(id_cliente) "
					+ "ON DELETE CASCADE)";
		
			String compras_cliente = "CREATE TABLE Compras_Cliente" +
					"(id int IDENTITY(1,1) NOT NULL, " +
					"data date NOT NULL, " +
					"detalhes varchar(50), " +
					"id_produto int, " +
					"id_cliente int NOT NULL, " +
					"PRIMARY KEY(id), "
					+"CONSTRAINT fk_id_cliente_compra FOREIGN KEY(id_cliente) "
					+"REFERENCES Cliente(id_cliente) "
					+ "ON DELETE CASCADE)";
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(tabela_tipo_produto);
			stmt.executeUpdate(tipo_game);
			stmt.executeUpdate(tipo_console);
			stmt.executeUpdate(tipo_acessorio);
			System.out.println("Tabela Tipo_Produto criada com sucesso");
			
			stmt.executeUpdate(tabela_produtos);
			System.out.println("Tabela Produtos criada com sucesso");
			
			stmt.executeUpdate(games);
			System.out.println("Tabela games criada com sucesso");
			
			stmt.executeUpdate(console);
			System.out.println("Tabela console criada com sucesso");
			
			stmt.executeUpdate(acessorios);
			System.out.println("Tabela acessorios criada com sucesso");
			
			stmt.executeUpdate(cliente);
			System.out.println("Tabela cliente criada com sucesso");
			
			stmt.executeUpdate(pedido_cliente);
			System.out.println("Tabela pedido_cliente criada com sucesso");
			
			stmt.executeUpdate(compras_cliente);
			System.out.println("Tabela compras_cliente criada com sucesso");
			
			}catch(SQLException e){
				 JOptionPane.showMessageDialog(null,e);
			}
		}
	}


*/

