package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    /*
     * Requirement 3: Posting a new message
     */
    public Message postMessage(Message message){
        //TODO
        return null;
    }

    /*
     * Requirement 4: Getting all messages
     */
    public List<Message> getAllMessages(){
        //TODO
        return null;
    }

    /*
     * Requirement 5: Get messages via their ID
     */
    public Message getMessageByID(int id){
        //TODO
        return null;
    }

    /*
     * Requirement 6: Delete Message using its ID 
     */
    public Message deleteMessageByID(int id){
        //TODO
        return null;
    }

    /*
     * Requirement 7: Update message by ID
     */
    public Message updateMessageByID(Message message){
        //TODO
        return null;
    }

    /*
     * Requirement 8: Get all messages by a user by its User ID
     */
    public List<Message> getMessagesByUser(int id){
        //TODO
        return null;
    }
}
