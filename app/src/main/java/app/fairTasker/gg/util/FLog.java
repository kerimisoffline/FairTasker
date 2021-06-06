/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.fairTasker.gg.util;

import android.util.Log;

public class FLog {

    public static final String TAG = "FairTasker";
    public static final Boolean has_log = false;

    public static void setLog(String message){
        if(!has_log || message == null || message.equals(""))
            return;

        Log.d(TAG, message);
    }
}
