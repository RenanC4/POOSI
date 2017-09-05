import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ConnectionFactory {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/Gamestore";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } 
        catch (ClassNotFoundException | SQLException exception) {
            throw new RuntimeException("Error", exception);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } 
        catch(SQLException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

        
    public static void closeConnection(Connection connection, Statement statement) {
        closeConnection(connection);
        
        try {
            if(statement != null) {
                statement.close();
            }
        } 
        catch(SQLException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement preparedStatement) {
        closeConnection(connection);
        
        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        } 
        catch(SQLException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

     public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        closeConnection(connection, statement);
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } 
        catch (SQLException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        closeConnection(connection, preparedStatement);
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } 
        catch (SQLException exception) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
}

