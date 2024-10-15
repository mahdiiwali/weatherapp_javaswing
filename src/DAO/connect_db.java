package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect_db {
    static Connection connection;
   private connect_db() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/login?useLegacyAuth=true&useSSL=false";

        String user = "root";
        String pass = "mahdi1234";



        Class.forName("com.mysql.cj.jdbc.Driver");


        connection = DriverManager.getConnection(url, user, pass);
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection==null){
            new connect_db();
        }
        return connection;
    }
}
