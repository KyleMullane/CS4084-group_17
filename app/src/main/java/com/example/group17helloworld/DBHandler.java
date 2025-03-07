package com.example.group17helloworld;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper
{
    public static final String USER_TABLE = "users";
    public static final String ID = "id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";
    private static final String DB_NAME = "travelappdb";
    // This may be used for migration in the future.
    private static final int DB_VERSION = 1;



    public DBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE USER_TABLE ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "USERNAME_COLUMN TEXT NOT NULL UNIQUE, "
                + "PASSWORD_COLUMN TEXT NOT NULL UNIQUE)";
        db.execSQL(query);
    }

    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COLUMN, user.getUsername());
        values.put(PASSWORD_COLUMN, user.getPassword());
        db.insert(USER_TABLE, null, values);
        db.close();
    }

    // Get the whole list of names.
    public ArrayList<User> getUsers()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<User> users = new ArrayList<User>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                users.add(new User(cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public void deleteAllUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM USER_TABLE");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,int num1,int num2)
    {

    }
}

