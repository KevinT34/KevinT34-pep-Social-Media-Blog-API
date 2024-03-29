package Controller;

import Service.AccountService;
import Service.MessageService;
import Model.Account;
import Model.Message;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accService;
    MessageService msgService;

    public SocialMediaController(){
        this.accService = new AccountService();
        this.msgService = new MessageService();
    }


    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        
        app.post("/register", this::postNewAccountHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postNewMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccountIdHandler);

        return app;
    }


    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


    /*
     * 
     */
    private void postNewAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accService.addAccount(account);
        if (addedAccount != null) {
            ctx.json(mapper.writeValueAsString(addedAccount));
        } else {
            ctx.status(400);
        }
    }


    /*
     * 
     */
    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);

        Account returnedAccount  = accService.validateAccount(account);
        if (returnedAccount != null) {
            ctx.json(mapper.writeValueAsString(returnedAccount));
        } else {
            ctx.status(401);
        }
    }


    /*
     * Needs work
     */
    private void postNewMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message newMsg = mapper.readValue(ctx.body(), Message.class);

        Message postedMsg = msgService.postMessage(newMsg);
        if (postedMsg != null) {
            ctx.json(mapper.writeValueAsString(postedMsg));
        } else {
            ctx.status(400);
        }

    }


    /*
     * 
     */
    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = msgService.getAllMessages();
        ctx.json(messages);
    }


    /*
     * 
     */
    private void getMessageByIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        int messageId = Integer.parseInt(ctx.pathParam("message_id"));

        Message message = msgService.getMessageById(messageId);
        if (message != null) {
            ctx.json(mapper.writeValueAsString(message));
        } else {
            ctx.status(200);
        }
        
    }


    /*
     * 
     */
    private void deleteMessageByIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = msgService.getMessageById(messageId);
        if (message != null) {
            Message deletedMsg = msgService.deleteMessage(message);
            ctx.json(mapper.writeValueAsString(deletedMsg));
        } else {
            ctx.status(200);
        }
        
        
    }


    /*
     *  
     */
    private void patchMessageByIdHandler(Context ctx) throws  JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message newMsg = mapper.readValue(ctx.body(), Message.class);
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));

        Message updatedMessage = msgService.updateMessage(messageId, newMsg);
        if (updatedMessage == null) {
            ctx.status(400);
        } else {
            ctx.json(mapper.writeValueAsString(updatedMessage));
        }

    }


    /*
     * 
     */
    private void getMessagesByAccountIdHandler(Context ctx) {
        int accountId = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = msgService.getMessagesByAccountId(accountId);
        ctx.json(messages);

    }
}