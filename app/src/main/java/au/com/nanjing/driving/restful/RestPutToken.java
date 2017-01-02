package au.com.nanjing.driving.restful;

import android.os.AsyncTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import au.com.nanjing.driving.Constants;
import au.com.nanjing.driving.Functions;
import au.com.nanjing.driving.GlobalStatic;
import au.com.nanjing.driving.entity.Token;

/**
 * Created by tonyliu on 31/12/2016.
 * //http://www.concretepage.com/spring/spring-mvc/spring-rest-client-resttemplate-consume-restful-web-service-example-xml-json
 */

public class RestPutToken extends AsyncTask<Object, Object, ResponseEntity<Token>> {
    private static final String tag  = "RestPutToken";
    @Override
    protected ResponseEntity<Token> doInBackground(Object... params){
        ResponseEntity<Token> response = null;
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<Object> requestEntityWithHeader = Functions.createLTKHttpEntity(GlobalStatic.AUTH_TOKEN, GlobalStatic.FIRE_TOKEN);

            Token token = new Token();
            token.setToken(GlobalStatic.FIRE_TOKEN);

            response = restTemplate.exchange(Constants.API_URL_SET_TOKEN, HttpMethod.POST, requestEntityWithHeader, Token.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(ResponseEntity<Token> token){
    }
}
