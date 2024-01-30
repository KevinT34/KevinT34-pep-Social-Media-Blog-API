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
            || newMsg.getMessage_text().length() >= 255
            || accountService.findAccountById(newMsg.getPosted_by()) == null) { 
            return null;
        } else {
            return messageDAO.postMessage(newMsg);
        }

        // if (newMsg.getMessage_text().isBlank() || newMsg.getMessage_text().length() >= 255) {
        //     return null;
        // }
        //return messageDAO.postMessage(newMsg);


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

    /*
     * 
     */
    public Message deleteMessage(Message messageToDelete) {
        return messageDAO.deleteMessage(messageToDelete);
    }
}
