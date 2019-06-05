package controllers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domains.User;
import services.UsersService;
import spark.*;

public class UsersController   {

    private UsersService usersService = UsersService.getInstance();

    private Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public String create(Request req, Response res)  {

        String jsonBody = req.body();
        User newUser = gson.fromJson( jsonBody, User.class );

        usersService.saveUser(newUser.getAlias(), jsonBody);

        return "creado";
    }



}
