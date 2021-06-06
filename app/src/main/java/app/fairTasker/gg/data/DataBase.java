package app.fairTasker.gg.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.fairTasker.gg.model.Job;
import app.fairTasker.gg.model.Person;

public class DataBase {
    SQLiteDatabase db;
    DbHelper dHelper;

    public DataBase(Context context) {
        dHelper = new DbHelper(context);
    }

    public void open() {
        db = dHelper.getWritableDatabase();
    }

    public void close(){
        dHelper.close();
    }

    public void addPerson(Person person){

        ContentValues val = new ContentValues();
        val.put("name",person.getName());
        val.put("surname",person.getSurname());
        db.insert("person",null,val);
    }

    public void deletePerson(Person person){
        int id = person.getId();
        db.delete("person","id="+id,null);
    }

    public List<Person> personalList(){
        String[] colums = {"id","name","surname"};
        List<Person> person_list = new ArrayList<Person>();
        Cursor cursor = db.query("person", colums,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);

            Person person = new Person(name,surname);
            person.setId(id);
            person_list.add(person);

            cursor.moveToNext();
        }
        cursor.close();
        return person_list;
    }

    public void addJob(Job job){
        ContentValues val = new ContentValues();
        val.put("job_name",job.getJob_name());
        val.put("hardness",job.getHardness());
        db.insert("job",null,val);
    }

    public void deleteJob(Job job){
        int id = job.getId();
        db.delete("job","id="+id,null);
    }


    public List<Job> jobList(){
        String[] job_columns = {"id","job_name","hardness"};
        List<Job> job_list = new ArrayList<Job>();
        Cursor c = db.query("job", job_columns,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            int id = c.getInt(0);
            String job_name = c.getString(1);
            int hardness = c.getInt(2);

            Job job = new Job(job_name,hardness);
            job.setId(id);
            job_list.add(job);

            c.moveToNext();
        }
        c.close();
        return job_list;
    }

}
