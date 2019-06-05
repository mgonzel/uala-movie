package controllers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domains.User;
import services.GsonParserService;
import services.UsersService;
import spark.*;

public class UsersController   {

    private UsersService usersService = UsersService.getInstance();

    private Gson gson = GsonParserService.getGson();

    public String create(Request req, Response res)  {

        String jsonBody = req.body();
        User newUser = gson.fromJson( jsonBody, User.class );

        usersService.saveUser(newUser.getAlias(), jsonBody);

        return "creado";
    }


    public String login(Request req, Response res) {
        //String response = "";

        String jsonBody = req.body();
        User newUser = gson.fromJson( jsonBody, User.class );

        if (newUser==null || newUser.getAlias() == null || newUser.getPassword() == null){
            res.status(400);
            return "{\"status\": \"invalid login\", \"message\": \"Some params missing\"}";
        }

        User userData = usersService.findAndLogin(newUser);

        if (userData==null){
            res.status(403);
            return "{\"status\": \"invalid login\", \"message\": \"Invalid user / password\"}";
        }

        return "{\"status\":\"OK\", \"token\":\"abcd\"}";
    }
}
