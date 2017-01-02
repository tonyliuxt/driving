package au.com.nanjing.driving.restful;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import au.com.nanjing.driving.Constants;
import au.com.nanjing.driving.Functions;
import au.com.nanjing.driving.GlobalStatic;

/**
 * Created by tonyliu on 2/01/2017.
 */

public class RestGetToken extends AsyncTask<Object, Object, ResponseEntity<String>> {
    private static final String tag  = "RestGetToken";
    @Override
    protected ResponseEntity<String> doInBackground(Object... params){
        ResponseEntity<String> response = null;
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpEntity<Object> header = Functions.createLMIHttpEntity(GlobalStatic.AUTH_USERNAME, Constants.AUTH_PASSWORD);

            response = restTemplate.exchange(Constants.API_URL_AUTH, HttpMethod.POST, header, String.class);
            if(response != null){
                HttpHeaders rspheader = response.getHeaders();
                if(rspheader != null){
                    boolean valid = Boolean.parseBoolean(rspheader.get(Constants.X_VALIDE).get(0));
                    if(valid){
                        GlobalStatic.AUTH_TOKEN = rspheader.get(Constants.X_TOKEN).get(0);
                    }
                    Log.v("getToken", "valid:" + valid +"--authtoken:"+GlobalStatic.AUTH_TOKEN);
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(ResponseEntity<String> token){
        // 3. After authenticated, put Firebase token to PigeonBooking Server
        Functions.putToken();
    }
}
