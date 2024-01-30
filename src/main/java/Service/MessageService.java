package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;


public class MessageService {
    
    private MessageDAO messageDAO;
    private AccountService accountService;

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
        
        if (newMsg.getMessage_text().isBlank()
            || newMsg.getMessage_text().length() > 255
            || accountService.findAccountById(newMsg.getPosted_by()) == null) { 
            return null;
        } else {
            return messageDAO.postMessage(newMsg);
        }

        // if (newMsg.getMessage_text().isBlank() || newMsg.getMessage_text().length() > 255) {
        //     return null;
        // }
        //return messageDAO.postMessage(newMsg);


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
        } else {
            return messageDAO.updateMessage(newMessage);
        }
        return null;
    }
}
