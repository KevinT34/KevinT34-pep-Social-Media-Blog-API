package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;


public class MessageService {
    
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

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

    public Message postMessage(Message newMsg) {
        
        // if (!accountDAO.accountExistsById(newMsg.getPosted_by())) {
        //     return null;
        // }

        if (newMsg.getMessage_text().isBlank() || newMsg.getMessage_text().length() > 255) {
                return null;
        }
        return messageDAO.postMessage(newMsg);

    }

    /*
     * 
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    } 

    /*
     * 
     */
    public Message getMessageById(int messageId) {
        // if (messageDAO.getMessageById(messageId) == null) {
        //     return null;
        // }
        return messageDAO.getMessageById(messageId);
    }

    /*
     * 
     */
    public Message deleteMessage(Message messageToDelete) {
        return messageDAO.deleteMessage(messageToDelete);
    }

    /*
     * 
     */
    public Message updateMessage(int messageId, Message newMessage) {
        Message messageToUpdate = getMessageById(messageId);
        if (messageToUpdate == null || newMessage.getMessage_text().isBlank() || newMessage.getMessage_text().length() > 255) {
            return null;
        } 
        messageDAO.updateMessage(messageId, newMessage);
        return messageDAO.getMessageById(messageId);
        
    }

    /*
     * 
     */
    public List<Message> getMessagesByAccountId(int accountId) {
        //Validate the account id?
        return messageDAO.getMessagesByAccountId(accountId);
    }
}
