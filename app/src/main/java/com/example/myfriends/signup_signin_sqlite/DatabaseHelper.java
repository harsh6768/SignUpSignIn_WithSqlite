package com.example.myfriends.signup_signin_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="info.db";
    private static final String TABLE_NAME="details";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        SQLiteDatabase database=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE table "+TABLE_NAME+"(ID INT PRIMARY KEY,USENAME VARCHAR(20) NOT NULL,EMAIL VARCHAR(20) NOT NULL,PASSWORD VARCHAR(20) NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean onInsert(String username,String email,String password)
    {
           SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("USENAME",username);
        contentValues.put("EMAIL",email);
        contentValues.put("PASSWORD",password);


        long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean onRetrive(String email,String password)
    {
           int log=0;

            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

            String query="select *from "+TABLE_NAME;

            Cursor  cursor=sqLiteDatabase.rawQuery(query,null);

           while(cursor.moveToNext())
           {
               String myEmail=cursor.getString(2);
               String myPass=cursor.getString(3);

               if(email.equals(myEmail) && password.equals(myPass))
               {
                    log=1;
               }

           }
           if(log==1)
           {
               return true;
           }
           else
           {
               return false;
           }
    }

    public boolean onEmailCheck(String email)
    {
        int log=0;

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        String query="select *from "+TABLE_NAME;

        Cursor  cursor=sqLiteDatabase.rawQuery(query,null);

        while(cursor.moveToNext())
        {
            String myEmail=cursor.getString(2);


            if(email.equals(myEmail))
            {
                log=1;
            }

        }
        if(log==1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
