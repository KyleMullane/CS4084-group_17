package com.example.group17helloworld;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper
{
    public static final String USER_TABLE = "users";
    public static final String ID = "id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";
    private static final String DB_NAME = "travelappdb";
    // This may be used for migration in the future.
    private static final int DB_VERSION = 5;



    public DBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + USER_TABLE +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COLUMN + " TEXT,"
                + PASSWORD_COLUMN + " TEXT)";
        db.execSQL(query);
    }

    public void addUser(User user) throws Exception
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COLUMN, user.getUsername());
        values.put(PASSWORD_COLUMN, user.getPassword());
        long num = db.insert(USER_TABLE, null, values);
        if (num == -1)
        {
            throw new Exception();
        }
        Log.d("DBHandler", "Num is = "+num);
        db.close();
    }

    public boolean isLoginValid(User user)
    {
        boolean exists = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + USERNAME_COLUMN + " = ? AND " + PASSWORD_COLUMN + " = ?", new String[]{user.getUsername(), user.getPassword()});

        if (cursor.moveToFirst())
        {
            exists = true;
        }
        cursor.close();
        return exists;
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
        db.execSQL("DELETE FROM " + USER_TABLE);
        db.close();
    }

    public void deleteUserTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + USER_TABLE;
        db.execSQL(query);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int num1,int num2)
    {
        String query = "CREATE TABLE " + USER_TABLE +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COLUMN + " TEXT NOT NULL UNIQUE,"
                + PASSWORD_COLUMN + " TEXT NOT NULL UNIQUE)";
        db.execSQL(query);
    }
}

