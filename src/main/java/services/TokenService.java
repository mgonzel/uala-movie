package services;

import utils.Constants;

import java.security.SecureRandom;

public class TokenService {
    private static String charSet = "VaJS9WLN7CZKncbsu6Ultk8AEyhHrIdXeiQ3xD5BjgzpRwTFMoGO2P1mY4fvq0";

    public static String createToken(){
        StringBuffer token = new StringBuffer();
        try {
            for (int i = 0; i < Constants.TOKEN_PARTS - 1; i++) {
                token.append(createRandomString());
                token.append('-');
            }
            token.append(createRandomString());
        } catch (Exception e){
            // logger.error("Error creating random token");
        }
        return token.toString();
    }

    private static String createRandomString() throws Exception{

        SecureRandom sr = SecureRandom.getInstanceStrong();
        StringBuffer sb = new StringBuffer();

        int maxIndex = charSet.length();


        for (int i = 0; i < Constants.TOKEN_PART_LENGHT; i++){
            sb.append(charSet.charAt(sr.nextInt(maxIndex)));
        }

        return sb.toString();
    }
}
