package services;

import domains.User;
import utils.Constants;

public class SessionService {
    private static SessionService instance = new SessionService();
    private SessionService(){}
    public static SessionService getInstance(){return instance;}

    private RedisService redisService = RedisService.getInstance();

    //SessionService
    public void saveSession(User user, String sessionToken){
        String key = "user:"+user.getAlias()+":session:"+sessionToken;
        saveSession(key,sessionToken);
    }

    public String validateSession(String alias, String sessionToken) {
        String key = "user:"+alias+":session:"+sessionToken;
        String session =  redisService.get("user:"+alias+":session:"+sessionToken);
        if (session!=null){
            saveSession(key, session);
        }
        return session;
    }

    public void saveSession(String key, String value){
        redisService.setExpirationValue(key, Constants.SESSION_EXPIRATION_TIME, value);
    }
}
