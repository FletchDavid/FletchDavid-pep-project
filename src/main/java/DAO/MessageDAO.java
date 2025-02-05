package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;

public class MessageDAO {
    /*
     * Requirement 3: Posting a new message
     */
    public Message postMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO Message(posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int generatedID = (int) resultSet.getLong(1);
                return new Message(generatedID, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean accountIDExists(int id){
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "SELECT * FROM Account WHERE account_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*
     * Requirement 4: Getting all messages
     */
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try{
            String sql = "SELECT * FROM Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Message message = new Message(
                    resultSet.getInt("message_id"),
                    resultSet.getInt("posted_by"),
                    resultSet.getString("message_text"),
                    resultSet.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    /*
     * Requirement 5: Get messages via their ID
     */
    public Message getMessageByID(int id){
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM Message WHERE message_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Message message = new Message(
                    id,
                    resultSet.getInt("posted_by"),
                    resultSet.getString("message_text"),
                    resultSet.getLong("time_posted_epoch")
                );
                return message;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*
     * Requirement 6: Delete Message using its ID 
     */
    public void deleteMessageByID(int id){
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "DELETE FROM Message WHERE message_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeQuery();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*
     * Requirement 7: Update message by ID
     */
    public boolean updateMessageByID(Message message){
        Connection connection = ConnectionUtil.getConnection();
        int updated = 0;
        try{
            String sql = "UPDATE Message SET message_text=? WHERE message_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, message.getMessage_id());

            updated = preparedStatement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        if(updated>0) return true;
        else return false;
    }

    /*
     * Requirement 8: Get all messages by a user by its User ID
     */
    public List<Message> getMessagesByUser(int id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages= new ArrayList<>();

        try{
            String sql = "SELECT * FROM Message WHERE posted_by=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Message message = new Message(
                    resultSet.getInt("message_id"),
                    resultSet.getInt("posted_by"),
                    resultSet.getString("message_text"),
                    resultSet.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
}
