package au.com.nanjing.driving.restful;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import au.com.nanjing.driving.Constants;
import au.com.nanjing.driving.GlobalStatic;
import au.com.nanjing.driving.entity.ContactMessage;

/**
 * Created by tonyliu on 31/12/2016.
 */

public class RestRetrieveContact extends AsyncTask<Void, Void, List<ContactMessage>> {
    private static final String tag  = "RestRetrieveContact";
    @Override
    protected List<ContactMessage> doInBackground(Void... params){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<ContactMessage[]> response = restTemplate.getForEntity(Constants.API_URL_GET_CONTACTS, ContactMessage[].class);
            if(response != null){
                return Arrays.asList(response.getBody());
            }else {
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<ContactMessage> bikelist){
        GlobalStatic.G_List_ContactMessage = bikelist;
        if(GlobalStatic.G_List_ContactMessage != null) Log.v(tag, "bike count returned:"+GlobalStatic.G_List_ContactMessage.size());
        if(GlobalStatic.Main_Handler != null){
            GlobalStatic.Main_Handler.sendEmptyMessage(Constants.MSG_SITES_READY);
        }
    }
}
