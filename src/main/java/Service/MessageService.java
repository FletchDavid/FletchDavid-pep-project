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
        if(0 < message.getMessage_text().length() && message.getMessage_text().length() <= 255 && messageDAO.accountIDExists(message.getPosted_by())){
            return messageDAO.postMessage(message);
        }else return null;
    }

    /*
     * Requirement 4: Getting all messages
     */
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    /*
     * Requirement 5: Get messages via their ID
     */
    public Message getMessageByID(int id){
        return messageDAO.getMessageByID(id);
    }

    /*
     * Requirement 6: Delete Message using its ID 
     */
    public Message deleteMessageByID(int id){
        Message message = messageDAO.getMessageByID(id);
        messageDAO.deleteMessageByID(id);
        return message;
    }

    /*
     * Requirement 7: Update message by ID
     */
    public Message updateMessageByID(Message message){
        if(message.getMessage_text().length()>255 || message.getMessage_text() == "") return null;
        else if(messageDAO.getMessageByID(message.getMessage_id())!=null){
            //try to update the database. return the message if successful and return null if not.
            if(messageDAO.updateMessageByID(message)){
                return messageDAO.getMessageByID(message.getMessage_id());
            }
        }
        return null;
    }

    /*
     * Requirement 8: Get all messages by a user by its User ID
     */
    public List<Message> getMessagesByUser(int id){
        return messageDAO.getMessagesByUser(id);
    }
}
