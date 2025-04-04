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

    //Trip Table
    public static final String TRIP_TABLE = "Trips";
    public static final String TRIP_ID_COLUMN = "tripID";

    public static final String DEPARTURE_COLUMN = "departure";
    public static final String DESTINATION_COLUMN = "destination";
    public static final String DEPARTURE_DATE_COLUMN = "departure_date";
    public static final String RETURN_DATE_COLUMN = "return_date";
    public static final String BUDGET_COLUMN = "budget";
    private static final String DB_NAME = "travelappdb";

    //Accommodation Table
    public static final String ACCOMMODATION_TABLE = "Accommodations";
    public static final String ACCOMMODATION_ID_COLUMN = "accommodationID";
    public static final String ACCOMMODATION_NAME_COLUMN = "name";
    public static final String ACCOMMODATION_ADDRESS_COLUMN = "address";
    public static final String ACCOMMODATION_CHECKIN_DATE_COLUMN = "checkin_date";
    public static final String ACCOMMODATION_CHECKOUT_DATE_COLUMN = "checkout_date";
    public static final String ACCOMMODATION_PRICE_COLUMN = "price";
    public static final String ACCOMMODATION_TRIPID_COLUMN = "tripID";

    //Activities Table
    public static final String ACTIVITIES_TABLE = "Activities";
    public static final String ACTIVITY_ID_COLUMN = "activityID";
    public static final String ACTIVITY_NAME_COLUMN = "name";
    public static final String ACTIVITY_LOCATION_COLUMN = "location";
    public static final String ACTIVITY_DATE_COLUMN = "date";
    public static final String ACTIVITY_TIME_COLUMN = "time";
    public static final String ACTIVITY_PRICE_COLUMN = "price";
    public static final String ACTIVITY_TRIPID_COLUMN = "tripID";

    //Transportation Table
    public static final String TRANSPORTATION_TABLE = "Transportation";
    public static final String TRANSPORTATION_ID_COLUMN = "transportationID";
    public static final String TRANSPORTATION_DEPARTURE_LOCATION_COLUMN = "departure_location";
    public static final String TRANSPORTATION_DESTINATION_COLUMN = "destination";
    public static final String TRANSPORTATION_DATE_COLUMN = "date";
    public static final String TRANSPORTATION_DEPARTURE_TIME_COLUMN = "departure_time";
    public static final String TRANSPORTATION_ARRIVAL_TIME_COLUMN = "arrival_time";
    public static final String TRANSPORTATION_TYPE_COLUMN = "type";
    public static final String TRANSPORTATION_PRICE_COLUMN = "price";
    public static final String TRANSPORTATION_TRIPID_COLUMN = "tripID";

    // This may be used for migration in the future.
    private static final int DB_VERSION = 10;


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d("MainActivity", "OnCreate Called");
        String query1 = "CREATE TABLE " + TRIP_TABLE +
                " (" + TRIP_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DEPARTURE_COLUMN + " TEXT NOT NULL,"
                + DESTINATION_COLUMN + " TEXT NOT NULL,"
                + DEPARTURE_DATE_COLUMN + " TEXT NOT NULL,"
                + RETURN_DATE_COLUMN + " TEXT NOT NULL,"
                + BUDGET_COLUMN + " REAL)";

        /*String query2 = "CREATE TABLE " + ACCOMMODATION_TABLE +
                " (" + ACCOMMODATION_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACCOMMODATION_NAME_COLUMN + " VARCHAR(100), "
                + ACCOMMODATION_ADDRESS_COLUMN + " VARCHAR(100), "
                + ACCOMMODATION_CHECKIN_DATE_COLUMN + " DATE, "
                + ACCOMMODATION_CHECKOUT_DATE_COLUMN + " DATE, "
                + ACCOMMODATION_PRICE_COLUMN + " DOUBLE(10,2), "
                + ACCOMMODATION_TRIPID_COLUMN + " INTEGER, "
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";

        String query3 = "CREATE TABLE " + ACTIVITIES_TABLE +
                " (" + ACTIVITY_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACTIVITY_NAME_COLUMN + " VARCHAR(100), "
                + ACTIVITY_LOCATION_COLUMN + " VARCHAR(100), "
                + ACTIVITY_DATE_COLUMN + " DATE, "
                + ACTIVITY_TIME_COLUMN + " VARCHAR(30), "
                + ACTIVITY_PRICE_COLUMN + " DOUBLE(10,2), "
                + ACTIVITY_TRIPID_COLUMN + " INTEGER, "
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";

        String query4 = "CREATE TABLE " + TRANSPORTATION_TABLE +
                " (" + TRANSPORTATION_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRANSPORTATION_DEPARTURE_LOCATION_COLUMN + " VARCHAR(100), "
                + TRANSPORTATION_DESTINATION_COLUMN + " VARCHAR(100), "
                + TRANSPORTATION_DATE_COLUMN + " DATE, "
                + TRANSPORTATION_DEPARTURE_TIME_COLUMN + " VARCHAR(30), "
                + TRANSPORTATION_ARRIVAL_TIME_COLUMN + " VARCHAR(30), "
                + TRANSPORTATION_TYPE_COLUMN + "VARCHAR(50), "
                + TRANSPORTATION_PRICE_COLUMN + " DOUBLE(10,2), "
                + TRANSPORTATION_TRIPID_COLUMN + " INTEGER, "
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";*/

        db.execSQL(query1);
        /*db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);*/
    }

    public void addTrip(Trip trip) throws Exception
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DEPARTURE_COLUMN, trip.getDeparture());
        values.put(DESTINATION_COLUMN, trip.getDestination());
        values.put(DEPARTURE_DATE_COLUMN, trip.getDateDeparture());
        values.put(RETURN_DATE_COLUMN, trip.getDateReturn());
        values.put(BUDGET_COLUMN, trip.getBudget());
        long num = db.insert(TRIP_TABLE, null, values);
        if (num == -1)
        {
            throw new Exception();
        }
        Log.d("DBHandler", "Num is = "+num);
        db.close();
    }


    // Get the whole list of names.
    public ArrayList<Trip> getTrips()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Trip> trips = new ArrayList<Trip>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRIP_TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                trips.add(new Trip(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trips;
    }

    public void deleteAllTrips()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TRIP_TABLE);
        db.close();
    }
    public void deleteTripTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + TRIP_TABLE;
        db.execSQL(query);
        db.close();
    }

    public void addAccommodation(Accommodation accommodation) throws Exception
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACCOMMODATION_NAME_COLUMN, accommodation.getName());
        values.put(ACCOMMODATION_ADDRESS_COLUMN, accommodation.getAddress());
        values.put(ACCOMMODATION_CHECKIN_DATE_COLUMN, accommodation.getCheckinDate());
        values.put(ACCOMMODATION_CHECKOUT_DATE_COLUMN, accommodation.getCheckoutDate());
        values.put(ACCOMMODATION_PRICE_COLUMN, accommodation.getPrice());
        values.put(ACCOMMODATION_TRIPID_COLUMN, accommodation.getTripID());
        long num = db.insert(ACCOMMODATION_TABLE, null, values);
        if (num == -1)
        {
            throw new Exception();
        }
        Log.d("DBHandler", "Num is = "+num);
        db.close();
    }
    public ArrayList<Accommodation> getAccommodations()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOMMODATION_TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                accommodations.add(new Accommodation(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return accommodations;
    }
    public void deleteAccommodationTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + ACCOMMODATION_TABLE;
        db.execSQL(query);
        db.close();
    }

    public void addActivity(Activity activity) throws Exception
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACTIVITY_NAME_COLUMN, activity.getName());
        values.put(ACTIVITY_LOCATION_COLUMN, activity.getLocation());
        values.put(ACTIVITY_DATE_COLUMN, activity.getDate());
        values.put(ACTIVITY_TIME_COLUMN, activity.getTime());
        values.put(ACTIVITY_PRICE_COLUMN, activity.getPrice());
        values.put(ACTIVITY_TRIPID_COLUMN, activity.getTripID());
        long num = db.insert(ACTIVITIES_TABLE, null, values);
        if (num == -1)
        {
            throw new Exception();
        }
        Log.d("DBHandler", "Num is = "+num);
        db.close();
    }

    public ArrayList<Activity> getActivities()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Activity> activities = new ArrayList<Activity>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACTIVITIES_TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                activities.add(new Activity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return activities;
    }

    public void deleteActivitiesTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + ACTIVITIES_TABLE;
        db.execSQL(query);
        db.close();
    }

    public void addTransportation(Transportation transportation) throws Exception
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRANSPORTATION_DEPARTURE_LOCATION_COLUMN, transportation.getDepartureLocation());
        values.put(TRANSPORTATION_DESTINATION_COLUMN, transportation.getDestination());
        values.put(TRANSPORTATION_DATE_COLUMN, transportation.getDate());
        values.put(TRANSPORTATION_DEPARTURE_TIME_COLUMN, transportation.getDepartureTime());
        values.put(TRANSPORTATION_ARRIVAL_TIME_COLUMN, transportation.getArrivalTime());
        values.put(TRANSPORTATION_TYPE_COLUMN, transportation.getType());
        values.put(TRANSPORTATION_PRICE_COLUMN, transportation.getPrice());
        values.put(TRANSPORTATION_TRIPID_COLUMN, transportation.getTripID());
        long num = db.insert(TRANSPORTATION_TABLE, null, values);
        if (num == -1)
        {
            throw new Exception();
        }
        Log.d("DBHandler", "Num is = "+num);
        db.close();
    }

    public ArrayList<Transportation> getTranspoortations()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Transportation> transportations = new ArrayList<Transportation>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRANSPORTATION_TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                transportations.add(new Transportation(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getDouble(7), cursor.getInt(8)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return transportations;
    }

    public void deleteTransportationTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + TRANSPORTATION_TABLE;
        db.execSQL(query);
        db.close();
    }

    public Double getTotalAccommodationsCost(Integer tripID)
    {
        Double totalCost = 0.0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT SUM(price) FROM " + ACCOMMODATION_TABLE + " WHERE tripID = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(tripID)});
        if (cursor.moveToFirst()) {
            // Get the sum from the first column in the result set
            totalCost = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return totalCost;
    }

    public Double getTotalActivitiesCost(Integer tripID)
    {
        Double totalCost = 0.0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT SUM(price) FROM " + ACTIVITIES_TABLE + " WHERE tripID = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(tripID)});
        if (cursor.moveToFirst()) {
            // Get the sum from the first column in the result set
            totalCost = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return totalCost;
    }

    public Double getTotalTransportationCost(Integer tripID)
    {
        Double totalCost = 0.0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT SUM(price) FROM " + TRANSPORTATION_TABLE + " WHERE tripID = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(tripID)});
        if (cursor.moveToFirst()) {
            // Get the sum from the first column in the result set
            totalCost = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return totalCost;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int num1,int num2)
    {
        Log.d("MainActivity", "OnUpgrade Called");
        String createTripQuery = "CREATE TABLE " + TRIP_TABLE +
                " (" + TRIP_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DEPARTURE_COLUMN + " TEXT NOT NULL,"
                + DESTINATION_COLUMN + " TEXT NOT NULL,"
                + DEPARTURE_DATE_COLUMN + " TEXT NOT NULL,"
                + RETURN_DATE_COLUMN + " TEXT NOT NULL,"
                + BUDGET_COLUMN + " REAL)";
        db.execSQL(createTripQuery);
    }

}

