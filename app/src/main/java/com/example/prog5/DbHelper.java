package com.example.prog5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "userdata.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userdata(name TEXT,phone TEXT )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userdata");
    }
    public boolean insertdata(String name,String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("phone",phone);
        int result= (int) db.insert("userdata",null,cv);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean cknameph(String name,String phone){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from userdata where name=? and phone=?",new
                String[] {name,phone});
        if(c.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}

