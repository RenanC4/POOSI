import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author RenanC
*/

	public class ClienteDAO {
	    private Connection connection;
	    private PreparedStatement preparedStatement;
	    private String conectaBanco;

	    public ClienteDAO() {
	    }
	    
	    public void cadastrarCliente(Cliente cliente) {
	       connection = ConnectionFactory.getConnection();
	        conectaBanco = "INSERT INTO cliente (id, nome, senha, cpf, sexo)"
	              + " VALUES (DEFAULT, ?, ?, ?, ?,?);";
	        
	        try {
	            preparedStatement = connection.prepareStatement(conectaBanco);	          
	            preparedStatement.setString(1, cliente.getId());
	            preparedStatement.setString(2, cliente.getNome());
	            preparedStatement.setString(3, cliente.getSenha());
	            preparedStatement.setString(2, cliente.getCpf());
	            preparedStatement.setBoolean(2, cliente.getSexo());
	            preparedStatement.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        ConnectionFactory.closeConnection(connection, preparedStatement);
	    }
	}


