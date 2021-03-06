/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.fairTasker.gg;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDexApplication;

import app.fairTasker.gg.util.Config;
import butterknife.ButterKnife;

public class FairTaskerApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = FairTaskerApplication.class.getSimpleName();
    @Override
    public void onCreate(){
        super.onCreate();
        ButterKnife.setDebug(true);
        checkFirstRun();

    }

    private AppCompatActivity mCurrentActivity = null;
    public AppCompatActivity getCurrentActivity(){
        return mCurrentActivity;
    }
    public void setCurrentActivity(AppCompatActivity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }

    private void checkFirstRun() {

        SharedPreferences sharedPreferences = getSharedPreferences("app.fairTasker.gg", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("firstRun", true)) {

            clearCookie();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();

        }
    }

    private void clearCookie(){
        SharedPreferences sharedPreferences = getSharedPreferences("CookiePersistence", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        if(activity != null && activity.getClass() != null && activity.getClass().getName() != null && activity.getClass().getName().equals("app.fairTasker.gg.activity.MainActivity")){
            try{
                /*
                if(MainActivity.Current != null)
                    MainActivity.Current.DestroyPeer();

                if(mCurrentActivity != null)
                    mCurrentActivity.finish();

                if(Config.CallSSeTask != null){
                    Config.CallSSeTask.sseClose();
                    Config.CallSSeTask.close_connection();
                    Config.CallSSeTask = null;
                }
                Config.cancelAllDataReceiver(getApplicationContext());
                Config.cancelCheckAvailableMembersReceiver(getApplicationContext());
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.cancel(Config.notificationID);
                android.os.Process.killProcess(android.os.Process.myPid());

                 */
            }catch (Exception ex){
                //
            }
        }
    }
}
