package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerUserHandler);
        app.post("/login", this::loginUserHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getAMessageHandler);
        app.delete("/messages/{message_id}", this::deleteAMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getUserMessagesHandler);

        return app;
    }

    
    private void registerUserHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Account user = objectMapper.readValue(ctx.body(), Account.class);
        Account addedUser = accountService.addUser(user);
        if(addedUser != null) ctx.json(objectMapper.writeValueAsString(addedUser));
        else ctx.status(400);
    }

    private void loginUserHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Account user = objectMapper.readValue(ctx.body(), Account.class);
        Account loggedUser = accountService.loginUser(user);
        if(loggedUser != null) ctx.json(objectMapper.writeValueAsString(loggedUser));
        else ctx.status(401);
    }

    private void postMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(ctx.body(), Message.class);
        Message postedMessage = messageService.postMessage(message);
        if(postedMessage != null) ctx.json(objectMapper.writeValueAsString(postedMessage));
        else ctx.status(400);
    }

    private void getAllMessagesHandler(Context ctx) throws JsonProcessingException{
        List<Message> allMessages = messageService.getAllMessages();
        ctx.json(allMessages);
    }

    private void getAMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        int id = objectMapper.readValue(ctx.body(), int.class);
        Message message = messageService.getMessageByID(id);
        ctx.json(objectMapper.writeValueAsString(message));
    }

    private void deleteAMessageHandler(Context ctx) throws JsonProcessingException{
        //TODO
    }

    private void updateMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(ctx.body(), Message.class);
        Message postedMessage = messageService.updateMessageByID(message);
        if(postedMessage != null) ctx.json(objectMapper.writeValueAsString(postedMessage));
        else ctx.status(400);
    }

    private void getUserMessagesHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        int id = objectMapper.readValue(ctx.body(), int.class);
        List<Message> userMessages= messageService.getMessagesByUser(id);
        ctx.json(userMessages);
    }
    
}