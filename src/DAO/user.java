package DAO;

import javax.swing.*;
import java.sql.*;

public class user {

    Connection connection = connect_db.getConnection();

    public user() throws SQLException, ClassNotFoundException {
    }

    public int verifregister(String username, String password) throws ClassNotFoundException, SQLException {
        int result = 0;
        String selectSql = "SELECT * FROM users WHERE username = ?";
        String insertSql = "INSERT INTO users VALUES (null, ?, ?)";


        PreparedStatement selectStatement = connection.prepareStatement(selectSql);
        selectStatement.setString(1, username);


        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            JOptionPane.showMessageDialog(null, "Username already used!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);


            result = insertStatement.executeUpdate();
            return result;
        }
        return result;
    }


    public ResultSet verifLogin(String username,String password) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

                 // Step 3: Create a prepared statement
                 PreparedStatement statement = connection.prepareStatement(sql);
                // Step 4: Set parameters and execute SQL query
                statement.setString(1, username);
                statement.setString(2, password);
                // Step 5: Process the result
                ResultSet resultSet = statement.executeQuery();

                return resultSet;
    }

}
