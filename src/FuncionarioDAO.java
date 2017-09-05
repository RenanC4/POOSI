import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private String conectaBanco;
    
    public void cadastrarFuncionario(Funcionario funcionario) {
        Connection connection = ConnectionFactory.getConnection();
        conectaBanco = "INSERT INTO cliente (id, senha, cpf, sexo, nome)"
              + " VALUES (DEFAULT, ?, ?, ?, ?,?);";
        
        try {
            preparedStatement = connection.prepareStatement(conectaBanco);
            preparedStatement.setString(1, funcionario.getId());
            preparedStatement.setString(2, funcionario.getSenha());
            preparedStatement.setString(3, funcionario.getCpf());
            preparedStatement.setBoolean(4, funcionario.getSexo());
            preparedStatement.setString(4, funcionario.getNome());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ConnectionFactory.closeConnection(connection, preparedStatement);
    }
}