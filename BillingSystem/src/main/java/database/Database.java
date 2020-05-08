package database;

import entities.Users;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private final String url = "jdbc:postgresql://localhost:5432/billing";
    private final String user = "postgres";
    private final String password = "1";

    private Connection connection = null;
    private PreparedStatement preparedStatment = null;
    private ResultSet result = null;
    private String sqlCommand;

    boolean operation = false;

    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is made successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    public boolean addCustomer(Users user) {
        try {
            connect();
            sqlCommand = "insert into users (name,nid ,dial_number,address,email,profile) values(?,?,?,?,?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, user.getName());
            preparedStatment.setString(2, user.getNid());
            preparedStatment.setString(3, user.getDialNumber());
            preparedStatment.setString(4, user.getAddress());
            preparedStatment.setString(5, user.getEmail());
            preparedStatment.setString(6, user.getProfile());
            
            preparedStatment.executeQuery();

//            while (result.next()) {
//                operation = result.getBoolean(1);
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            stop();
            return operation;
        }
    }
    
    
     public Users getUserInfo(Users user) {
        try {
            connect();
            sqlCommand = "SELECT * FROM users WHERE dial_number = ? ";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setString(1, user.getDialNumber());
            result = preparedStatment.executeQuery();

            while (result.next()) {
                user.setName(result.getString(2));
                user.setNid(result.getString(3));
                user.setAddress(result.getString(4));
                user.setEmail(result.getString(5));
                user.setProfile(result.getString(6));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            stop();
            return user;
        }
    }
     
     
    public Vector<Users> retrieveAllCustomers() {
        Vector<Users> user = new Vector();
        try {
            connect();
            sqlCommand = "select * from users";
            preparedStatment = connection.prepareStatement(sqlCommand);
            result = preparedStatment.executeQuery();
            while (result.next()) {
                user.add(new Users(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
            return user;
        }
    }
    private void stop() {
        try {
            connection.close();
            System.out.println("Database is stopped");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
