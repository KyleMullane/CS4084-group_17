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
    private static DBHandler instance;

    public DBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static synchronized DBHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DBHandler(context.getApplicationContext());
        }
        return instance;
    }
    // USER TABLE
    public static final String USER_TABLE = "users";
    public static final String ID = "id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";

    // TRIP TABLE
    public static final String TRIP_TABLE = "trips";
    public static final String DEPARTURE_LOCATION = "departureLocation";
    public static final String DESTINATION_LOCATION = "destinationLocation";
    public static final String DATE_DEPARTURE = "dateDeparture";
    public static final String DATE_RETURN = "dateReturn";
    public static final String TRIP_USERNAME = "username";
    public static final String TRIP_DESCRIPTION = "description";
    public static final String OVERALL_COST = "overallCost";
    public static final String TRIP_ID = "tripID";

    private static final String DB_NAME = "travelappdb";
    // This may be used for migration in the future.
    private static final int DB_VERSION = 6;




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

    public void addTrip(Trip trip) throws Exception
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DEPARTURE_LOCATION, trip.getDepartureLocation());
        values.put(DESTINATION_LOCATION, trip.getDestinationLocation());
        values.put(DATE_DEPARTURE, trip.getDateDeparture());
        values.put(DATE_RETURN, trip.getDateReturn());
        values.put(TRIP_USERNAME, trip.getUsername());
        values.put(TRIP_DESCRIPTION, trip.getDescription());
        values.put(OVERALL_COST, trip.getOverallCost());
        long num = db.insert(TRIP_TABLE, null, values);
        if (num == -1)
        {
            throw new Exception();
        }
        Log.d("DBHandler", "Num is = "+num);
        db.close();
    }

    public ArrayList<Trip> getTrips()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Trip> trips = new ArrayList<Trip>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRIP_TABLE, null);
        if (cursor.moveToFirst())
        {
            //String departureLocation, String destinationLocation, String dateDeparture, String dateReturn, String username, String description, double overallCost, int tripID
            do
            {
                trips.add(new Trip(cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getDouble(7), cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trips;
    }

   /*ADDING TABLE QUERIES
    USER TABLE:
        String query = "CREATE TABLE " + USER_TABLE +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COLUMN + " TEXT NOT NULL UNIQUE,"
                + PASSWORD_COLUMN + " TEXT NOT NULL UNIQUE)";
        db.execSQL(query);*/

    @Override
    public void onUpgrade(SQLiteDatabase db,int num1,int num2)
    {
        String query = "CREATE TABLE " + TRIP_TABLE +
                " (" + TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DEPARTURE_LOCATION + " TEXT NOT NULL,"
                + DESTINATION_LOCATION + " TEXT NOT NULL,"
                + DATE_DEPARTURE + " TEXT NOT NULL,"
                + DATE_RETURN + " TEXT NOT NULL,"
                + TRIP_USERNAME + " TEXT NOT NULL,"
                + TRIP_DESCRIPTION + " TEXT,"
                + OVERALL_COST + " REAL)";
        db.execSQL(query);

    }
}

