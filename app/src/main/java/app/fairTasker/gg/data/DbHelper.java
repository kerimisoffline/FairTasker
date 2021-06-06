package app.fairTasker.gg.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context c){
        super(c, "ftasker", null, 1);
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        String sql_p = "CREATE TABLE person ( id integer primary key autoincrement" +
                ", name text not null , surname text not null)";
        db.execSQL(sql_p);

        String sql_j = "CREATE TABLE job ( id integer primary key autoincrement" +
                ", job_name text not null , hardness int not null)";
        db.execSQL(sql_j);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists person");
        db.execSQL("DROP TABLE if exists job");
        onCreate(db);

    }
}
