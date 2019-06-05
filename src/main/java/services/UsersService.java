package services;

public class UsersService {
    private static UsersService instance = new UsersService();
    private UsersService(){}
    public static UsersService getInstance(){return instance;}
    private static RedisService redisService = RedisService.getInstance();

    public void saveUser(String id, String data){
        redisService.setValue("user:"+id,data);
    }
}
