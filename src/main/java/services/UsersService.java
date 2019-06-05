package services;

import com.google.gson.Gson;
import domains.User;
import utils.Constants;

public class UsersService {
    private static UsersService instance = new UsersService();
    private UsersService(){}
    public static UsersService getInstance(){return instance;}

    private RedisService redisService = RedisService.getInstance();
    private Gson gson = GsonParserService.getGson();

    public void saveUser(String id, String data){
        redisService.setValue("user:"+id,data);
    }

    public User findAndLogin(User login) {
        String userData = find(login);

        if (userData == null || "".equals(userData)){
            return null;
        }
        User user = gson.fromJson(userData, User.class);

        if (user.getPassword().equals(login.getPassword())){
            user.setPassword(""); //erase password
            return user;
        }
        return null;
    }
    private String find(User user){
        return redisService.get("user:"+user.getAlias());
    }
}
