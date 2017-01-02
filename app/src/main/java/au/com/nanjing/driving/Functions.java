package au.com.nanjing.driving;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import au.com.nanjing.driving.entity.Token;
import au.com.nanjing.driving.restful.RestGetToken;
import au.com.nanjing.driving.restful.RestPutToken;

/**
 * Created by Tony Liu on 2016/12/18.
 */

public class Functions {

    public static boolean putToken(){
        try{
            RestPutToken restfulRequest = new RestPutToken();
            restfulRequest.execute();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean getToken(){
        try{
            RestGetToken restfulRequest = new RestGetToken();
            restfulRequest.execute();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static HttpEntity<Object> createLMIHttpEntity(String username, String password) {
        MultiValueMap<String, String> headers =  new LinkedMultiValueMap<String, String>();
        headers.add("Accept", "application/json");
        headers.add(Constants.X_USERNAME, username);
        headers.add(Constants.X_PASSWORD, password);
        return new HttpEntity<Object>(headers);
    }

    public static HttpEntity<Object> createLTKHttpEntity(String springAuthToken, String firebaseToken) {
        MultiValueMap<String, String> headers =  new LinkedMultiValueMap<String, String>();
        headers.add("Accept", "application/json");
        headers.add(Constants.X_TOKEN, springAuthToken);
        Token tokenobj = new Token();
        tokenobj.setToken(firebaseToken);
        return new HttpEntity<Object>(tokenobj, headers);
    }

}