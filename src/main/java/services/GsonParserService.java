package services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonParserService {
    private Gson gson;

    private GsonParserService(){
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
    private static GsonParserService instance = new GsonParserService();

    public static Gson getGson(){
        return instance.gson;
    }
}
