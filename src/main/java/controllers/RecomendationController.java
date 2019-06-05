package controllers;

import com.google.gson.Gson;
import domains.RecomendationRequest;
import services.GsonParserService;
import services.SessionService;
import spark.Request;
import spark.Response;

public class RecomendationController {
    private Gson gson = GsonParserService.getGson();
    private SessionService sessionService = SessionService.getInstance();

    public String find(Request req, Response res){
        String token = req.headers("X-Auth");

        RecomendationRequest recomendationRequest = gson.fromJson(req.body(), RecomendationRequest.class);
        recomendationRequest.setToken(token);
        if (recomendationRequest.getToken() == null ||
                recomendationRequest.getAlias() == null ||
                sessionService.validateSession(recomendationRequest.getAlias(),recomendationRequest.getToken()) == null){

            res.status(403);
            return "{\"status\":\"invalid_credentials\"}";
        }

        return "{\"recomended\":[]}";
    }
}
