package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    
    /*
     * Requirement 1: New User Registrations
     */
    public Account addUser(Account user){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO Account(username, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

            ResultSet results = preparedStatement.getGeneratedKeys();
            if(results.next()){
                int generatedID = (int) results.getLong(1);
                return new Account(generatedID, user.getUsername(), user.getPassword());
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account searchUsername(Account user){
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "SELECT * FROM Account WHERE username=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Account logged = new Account(
                    resultSet.getInt("account_id"),
                    user.getUsername(),
                    user.getPassword()
                ) ;
                return logged;
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
     * Requirement 2: User Login
     */
    public Account loginUser(Account user){
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "SELECT * FROM Account WHERE username=? AND password=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Account logged = new Account(
                    resultSet.getInt("account_id"),
                    user.getUsername(),
                    user.getPassword()
                ) ;
                return logged;
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
