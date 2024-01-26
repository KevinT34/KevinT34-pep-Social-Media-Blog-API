package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;


public class MessageService {
    
    private MessageDAO messageDAO;

    /*
     * 
     */
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    /*
     * 
     */
    public MessageService(MessageDAO msgDAO) {
        this.messageDAO = msgDAO;
    }

    /*
     * get all messages
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    } 

    /*
     * 
     */
    public Message getMessageById(int messageId) {
        return messageDAO.getMessageById(messageId);
    }
}