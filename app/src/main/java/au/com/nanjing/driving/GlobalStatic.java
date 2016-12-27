package au.com.nanjing.driving;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import au.com.nanjing.driving.entity.ContactMessage;

/**
 * Created by Xintian on 2016/12/14.
 */

public class GlobalStatic {
    // ContactMessage list
    public static List<ContactMessage> G_List_ContactMessage = new ArrayList<ContactMessage>();

    // Internal notification message handler
    public static Handler Main_Handler;
}
