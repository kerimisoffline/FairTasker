/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.fairTasker.gg.util;

import android.content.Context;

import java.util.List;

import app.fairTasker.gg.model.Cache;
import app.fairTasker.gg.model.Job;
import app.fairTasker.gg.model.Person;

public class Data {
    public static List<Person> selectedPersons;
    public static List<Job> selectedJobs;
    private static Cache cache;

    public static Cache getCache(Context context){
        if(cache == null)
            cache = new Cache(context);

        return cache;
    }

}
