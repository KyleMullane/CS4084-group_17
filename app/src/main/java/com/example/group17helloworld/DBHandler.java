package com.example.group17helloworld;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
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
    public static final String FAVORITE_COLUMN = "isFavorite";
    private static final String DB_NAME = "travelappdb";

    //Accommodation Table
    public static final String ACCOMMODATION_TABLE = "Accommodations";
    public static final String ACCOMMODATION_ID_COLUMN = "accommodationID";
    public static final String ACCOMMODATION_NAME_COLUMN = "name";
    public static final String ACCOMMODATION_TYPE_COLUMN = "type";
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
    public static final String ACTIVITY_DESCRIPTION_COLUMN = "description";
    public static final String ACTIVITY_TRIPID_COLUMN = "tripID";

    //Transportation Table
    public static final String TRANSPORTATION_TABLE = "Transportation";
    public static final String TRANSPORTATION_ID_COLUMN = "transportationID";
    public static final String TRANSPORTATION_DEPARTURE_LOCATION_COLUMN = "departure_location";
    public static final String TRANSPORTATION_DESTINATION_COLUMN = "destination";
    public static final String TRANSPORTATION_DATE_COLUMN = "date";
    public static final String TRANSPORTATION_DEPARTURE_TIME_COLUMN = "departure_time";
    public static final String TRANSPORTATION_ARRIVAL_DATE_COLUMN = "arrival_date";
    public static final String TRANSPORTATION_ARRIVAL_TIME_COLUMN = "arrival_time";
    public static final String TRANSPORTATION_TYPE_COLUMN = "type";
    public static final String TRANSPORTATION_PRICE_COLUMN = "price";
    public static final String TRANSPORTATION_TRIPID_COLUMN = "tripID";

    //BucketList Table
    public static final String BUCKET_LIST_TABLE = "BucketList";
    public static final String ITEM_ID_COLUMN = "itemID";
    public static final String ITEM_NAME_COLUMN = "itemName";
    public static final String STATUS_COLUMN = "status";

    //Comments Table
    public static final String COMMENTS_TABLE = "FavoriteComments";
    public static final String COMMENT_ID_COLUMN = "commentID";
    public static final String TRIP_ID_COMMENT_COLUMN = "tripID";
    public static final String COMMENT_COLUMN = "comment";
    public static final String LOCKED_COLUMN = "isLocked";

    // This may be used for migration in the future.
    private static final int DB_VERSION = 11;



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d("MainActivity", "OnCreate Called");
        String createTripQuery = "CREATE TABLE " + TRIP_TABLE +
                " (" + TRIP_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DEPARTURE_COLUMN + " TEXT NOT NULL,"
                + DESTINATION_COLUMN + " TEXT NOT NULL,"
                + DEPARTURE_DATE_COLUMN + " TEXT NOT NULL,"
                + RETURN_DATE_COLUMN + " TEXT,"
                + BUDGET_COLUMN + " REAL, "
                + FAVORITE_COLUMN + " INTEGER)";
        db.execSQL(createTripQuery);


        String accommodationQuery = "CREATE TABLE " + ACCOMMODATION_TABLE +
                " (" + ACCOMMODATION_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACCOMMODATION_NAME_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_TYPE_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_ADDRESS_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_CHECKIN_DATE_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_CHECKOUT_DATE_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_PRICE_COLUMN + " REAL NOT NULL, "
                + ACCOMMODATION_TRIPID_COLUMN + " INTEGER, "
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";
        db.execSQL(accommodationQuery);

        String activitiesQuery = "CREATE TABLE " + ACTIVITIES_TABLE +
                " (" + ACTIVITY_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACTIVITY_NAME_COLUMN + " TEXT NOT NULL, "
                + ACTIVITY_LOCATION_COLUMN + " TEXT NOT NULL, "
                + ACTIVITY_DATE_COLUMN + " TEXT NOT NULL, "
                + ACTIVITY_TIME_COLUMN + " TEXT NOT NULL, "
                + ACTIVITY_PRICE_COLUMN + " REAL NOT NULL, "
                + ACTIVITY_DESCRIPTION_COLUMN + " TEXT, "
                + ACTIVITY_TRIPID_COLUMN + " INTEGER, "
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";
        db.execSQL(activitiesQuery);

        String transportationQuery = "CREATE TABLE " + TRANSPORTATION_TABLE +
                " (" + TRANSPORTATION_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRANSPORTATION_DEPARTURE_LOCATION_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_DESTINATION_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_DATE_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_DEPARTURE_TIME_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_ARRIVAL_DATE_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_ARRIVAL_TIME_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_TYPE_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_PRICE_COLUMN + " REAL NOT NULL, "
                + TRANSPORTATION_TRIPID_COLUMN + " INTEGER NOT NULL, "
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";
        db.execSQL(transportationQuery);

        String createBucketListQuery = "CREATE TABLE " + BUCKET_LIST_TABLE +
                " (" + ITEM_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ITEM_NAME_COLUMN + " TEXT NOT NULL, "
                + STATUS_COLUMN + " INTEGER)"; //WILL BE 1- COMPLETED OR 0- NOT COMPLETED
        db.execSQL(createBucketListQuery);

        String createCommentsQuery = "CREATE TABLE " + COMMENTS_TABLE +
                " (" + COMMENT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRIP_ID_COMMENT_COLUMN + " INTEGER NOT NULL,"
                + COMMENT_COLUMN + " TEXT NOT NULL,"
                + LOCKED_COLUMN + " INTEGER DEFAULT 0)";
        db.execSQL(createCommentsQuery);
    }

    //**********TRIPS**********

    public void createTripTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String createTripQuery = "CREATE TABLE " + TRIP_TABLE +
                " (" + TRIP_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DEPARTURE_COLUMN + " TEXT NOT NULL,"
                + DESTINATION_COLUMN + " TEXT NOT NULL,"
                + DEPARTURE_DATE_COLUMN + " TEXT NOT NULL,"
                + RETURN_DATE_COLUMN + " TEXT,"
                + BUDGET_COLUMN + " REAL, "
                + FAVORITE_COLUMN + " INTEGER)";
        db.execSQL(createTripQuery);
        db.close();
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
        values.put(FAVORITE_COLUMN, trip.getIsFavorite() ? 1 : 0);
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
            do
            {
                trips.add(new Trip(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return trips;
    }

    public Trip selectTripByID(int ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRIP_TABLE + " WHERE "+TRIP_ID_COLUMN+" = "+ID, null);
        if (cursor.moveToFirst())
        {
            return new Trip(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5), cursor.getInt(6));
        }
        else
        {
            Log.d("DBHandler","No trip found with specified ID");
        }
        cursor.close();
        db.close();
        return new Trip(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4), cursor.getInt(5));
    }

    public ArrayList<Trip> getFavoriteTrips()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Trip> trips = new ArrayList<Trip>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRIP_TABLE + " WHERE " + FAVORITE_COLUMN + " = 1", null);
        if (cursor.moveToFirst())
        {
            do
            {
                trips.add(new Trip(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
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
    public void deleteTrip(Integer tripID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Trips WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, tripID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void favoriteTrip(Trip trip){
        SQLiteDatabase db = this.getWritableDatabase();

        // Prepare the content values to update the status
        ContentValues values = new ContentValues();
        //values.put("item", item.getItem());
        values.put("isFavorite", trip.getIsFavorite());
        // might need to change to trip.getIsFavorite() ? 1 : 0, & change method to return a boolean in Trip class if doesn't work

        //Log.d("DB_UPDATE", "Updating status for " + item.getItem() + " to " + item.getStatus());

        db.update("Trips", values, "tripID = ?", new String[]{String.valueOf(trip.getTripID())});
        db.close();
    }

    // **********TRANSPORTATION**********
    public void createTransportationTable()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String transportationQuery = "CREATE TABLE " + TRANSPORTATION_TABLE +
                " (" + TRANSPORTATION_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRANSPORTATION_DEPARTURE_LOCATION_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_DESTINATION_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_DATE_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_DEPARTURE_TIME_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_ARRIVAL_DATE_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_ARRIVAL_TIME_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_TYPE_COLUMN + " TEXT NOT NULL, "
                + TRANSPORTATION_PRICE_COLUMN + " REAL NOT NULL, "
                + TRANSPORTATION_TRIPID_COLUMN + " INTEGER NOT NULL, "
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";
        db.execSQL(transportationQuery);
        db.close();
    }

    public void deleteTransportationTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + TRANSPORTATION_TABLE;
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
        values.put(TRANSPORTATION_ARRIVAL_DATE_COLUMN, transportation.getArrivalDate());
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

    public ArrayList<Transportation> getTransportations()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Transportation> transportations = new ArrayList<Transportation>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRANSPORTATION_TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                transportations.add(new Transportation(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getDouble(8), cursor.getInt(9)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return transportations;
    }


    //**********ACCOMMODATION**********
    public void createAccommodationTable()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String accommodationQuery = "CREATE TABLE " + ACCOMMODATION_TABLE +
                " (" + ACCOMMODATION_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACCOMMODATION_NAME_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_TYPE_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_ADDRESS_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_CHECKIN_DATE_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_CHECKOUT_DATE_COLUMN + " TEXT NOT NULL, "
                + ACCOMMODATION_PRICE_COLUMN + " REAL NOT NULL, "
                + ACCOMMODATION_TRIPID_COLUMN + " INTEGER, "
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";
        db.execSQL(accommodationQuery);
        db.close();
    }

    public void deleteAccommodationTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + ACCOMMODATION_TABLE;
        db.execSQL(query);
        db.close();
    }

    public ArrayList<Transportation> selectTransportationByTripID(int ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Transportation> transportations = new ArrayList<Transportation>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRANSPORTATION_TABLE + " WHERE "+TRIP_ID_COLUMN+" = "+ID, null);
        if (cursor.moveToFirst())
        {
            do
            {
                transportations.add(new Transportation(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getDouble(8), cursor.getInt(9)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return transportations;
    }

    public void addAccommodation(Accommodation accommodation) throws Exception
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACCOMMODATION_NAME_COLUMN, accommodation.getName());
        values.put(ACCOMMODATION_TYPE_COLUMN, accommodation.getType());
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
        ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOMMODATION_TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                accommodations.add(new Accommodation(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return accommodations;
    }

    public ArrayList<Accommodation> selectAccommodationByTripID(int ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOMMODATION_TABLE + " WHERE "+TRIP_ID_COLUMN+" = "+ID, null);
        if (cursor.moveToFirst())
        {
            do
            {
                accommodations.add(new Accommodation(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return accommodations;
    }

    // ***********ACTIVITIES**********
    public void createActivitiesTable()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String activitiesQuery = "CREATE TABLE " + ACTIVITIES_TABLE +
                " (" + ACTIVITY_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACTIVITY_NAME_COLUMN + " TEXT NOT NULL, "
                + ACTIVITY_LOCATION_COLUMN + " TEXT NOT NULL, "
                + ACTIVITY_DATE_COLUMN + " TEXT NOT NULL, "
                + ACTIVITY_TIME_COLUMN + " TEXT NOT NULL, "
                + ACTIVITY_PRICE_COLUMN + " REAL NOT NULL, "
                + ACTIVITY_DESCRIPTION_COLUMN + " TEXT, "
                + ACTIVITY_TRIPID_COLUMN + " INTEGER, "
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";
        db.execSQL(activitiesQuery);
        db.close();

    }

    public void deleteActivityTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + ACTIVITIES_TABLE;
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
        values.put(ACTIVITY_DESCRIPTION_COLUMN, activity.getDescription());
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
        ArrayList<Activity> activities = new ArrayList<Activity>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACTIVITIES_TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                activities.add(new Activity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5), cursor.getString(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return activities;
    }

    public ArrayList<Activity> selectActivitiesByTripID(int ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Activity> activities = new ArrayList<Activity>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACTIVITIES_TABLE + " WHERE "+TRIP_ID_COLUMN+" = "+ID, null);
        if (cursor.moveToFirst())
        {
            do
            {
                activities.add(new Activity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5), cursor.getString(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return activities;
    }

    //**********BUCKETLIST**********

    public void createBucketListTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String createBucketListTableQuery = "CREATE TABLE " + BUCKET_LIST_TABLE +
                " (" + ITEM_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ITEM_NAME_COLUMN + " TEXT NOT NULL, "
                + STATUS_COLUMN + " INTEGER)"; //WILL BE 1- COMPLETED OR 0- NOT COMPLETED
        db.execSQL(createBucketListTableQuery);
        db.close();
    }

    public void addItem(BucketListItem item) throws Exception {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME_COLUMN, item.getItem());
        values.put(STATUS_COLUMN, item.getStatus());
        long num = db.insert(BUCKET_LIST_TABLE, null, values);
        if (num == -1)
        {
            throw new Exception();
        }
        Log.d("DBHandler", "Num is = "+num);
        db.close();
    }

    public void deleteBucketListTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + BUCKET_LIST_TABLE;
        db.execSQL(query);
        db.close();
    }

    public void changeStatus(BucketListItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Prepare the content values to update the status
        ContentValues values = new ContentValues();
        //values.put("item", item.getItem());
        values.put("status", item.getStatus() ? 1 : 0);

        //Log.d("DB_UPDATE", "Updating status for " + item.getItem() + " to " + item.getStatus());

        db.update("BucketList", values, "itemName = ?", new String[]{String.valueOf(item.getItem())});
        db.close();
    }

//    public void deleteItem(Integer){
//
//    }


    public ArrayList<BucketListItem> getBucketListItems(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<BucketListItem> items = new ArrayList<BucketListItem>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BUCKET_LIST_TABLE, null);
        if (cursor.moveToFirst())
        {
            do
            {
                items.add(new BucketListItem(cursor.getString(1), cursor.getInt(2))); //not sure if this works --> I ignore the ID
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    //**********BUCKETLIST**********
    public void createCommentsTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String createCommentsQuery = "CREATE TABLE " + COMMENTS_TABLE
                + " (" + COMMENT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRIP_ID_COMMENT_COLUMN + " INTEGER NOT NULL, "
                + COMMENT_COLUMN + " TEXT NOT NULL, "
                + LOCKED_COLUMN + " INTEGER DEFAULT 0)";
        db.execSQL(createCommentsQuery);
        db.close();
    }

    public void addComment(Trip trip, String comment) throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRIP_ID_COMMENT_COLUMN, trip.getTripID());
        values.put(COMMENT_COLUMN, comment);
        values.put(LOCKED_COLUMN, 1);
            long num = db.insert(COMMENTS_TABLE, null, values);
            if (num == -1)
            {
                throw new Exception();
            }
            Log.d("DBHandler", "Num is = "+num);
            db.close();
        }

    public String getComment(int tripID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String comment = "";
        Cursor cursor = db.rawQuery("SELECT comment FROM FavoriteComments WHERE tripID = ?", new String[]{String.valueOf(tripID)});
        if (cursor.moveToFirst()) {
            comment = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return comment;
    }

    public void deleteCommentsTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + COMMENTS_TABLE;
        db.execSQL(query);
        db.close();
    }

    public boolean isCommentLocked(int tripID) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Query the database for the "isCommentLocked" column based on the tripID
        Cursor cursor = db.query("FavoriteComments", new String[]{"isLocked"}, "tripID = ?", new String[]{String.valueOf(tripID)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Get the column index for "isCommentLocked"
            int isLockedColumnIndex = cursor.getColumnIndex("isLocked");

            // Get the value from the "isCommentLocked" column
            int isLocked = cursor.getInt(isLockedColumnIndex);

            // Close the cursor
            cursor.close();

            // Return true if locked (1), false otherwise (0)
            return isLocked == 1;
        }

        // If no result was found, return false (not locked)
        if (cursor != null) {
            cursor.close();
        }
        return false;
    }


    /*public void changeTripDestination(Integer tripID, String destination){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Trips SET destination = ? WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, destination);
        statement.bindLong(2, tripID);
        statement.executeUpdateDelete();
        db.close();
    }
    public void changeTripDeparture(Integer tripID, String departureDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Trips SET departure_date = ? WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, departureDate);
        statement.bindLong(2, tripID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeTripReturnDate(Integer tripID, String returnDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Trips SET return_date = ? WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, returnDate);
        statement.bindLong(2, tripID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeTripBudget(Integer tripID, Double budget){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Trips SET budget = ? WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindDouble(1, budget);
        statement.bindLong(2, tripID);
        statement.executeUpdateDelete();
        db.close();
    }

    public Integer getTripID(String departureDate, String returnDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT tripID FROM Trips WHERE departureDate = ? AND returnDate = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, departureDate);
        statement.bindString(2, returnDate);
        long tripID = statement.simpleQueryForLong();
        db.close();
        return (int) tripID;
    }

    //change status?? idk it should automatically change & we shouldn't actually need this field



    public void deleteAccommodationTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + ACCOMMODATION_TABLE;
        db.execSQL(query);
        db.close();
    }
    public void deleteAccommodation(Integer accommodationID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Accommodations WHERE accommodationID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, accommodationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeAccommodationName(Integer accommodationID, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Accommodations SET name = ? WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, name);
        statement.bindLong(2, accommodationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeAccommodationAddress(Integer accommodationID, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Accommodations SET address = ? WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, address);
        statement.bindLong(2, accommodationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeAccommodationCheckin(Integer accommodationID, String checkinDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Accommodations SET checkin_date = ? WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, checkinDate);
        statement.bindLong(2, accommodationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeAccommodationCheckout(Integer accommodationID, String checkoutDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Accommodations SET checkout_date = ? WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, checkoutDate);
        statement.bindLong(2, accommodationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeAccommodationPrice(Integer accommodationID, Double price){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Accommodations SET price = ? WHERE tripID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindDouble(1, price);
        statement.bindLong(2, accommodationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public Integer getAccommodationID(Integer tripID, String checkinDate, String checkoutDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT accommodationID FROM Accommodations WHERE tripID = ? AND checkinDate = ? AND checkoutDate = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, tripID);
        statement.bindString(2, checkinDate);
        statement.bindString(2, checkoutDate);
        long accommodationID = statement.simpleQueryForLong();
        db.close();
        return (int) accommodationID;
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
    public void deleteActivity(Integer activityID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Activities WHERE activityID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, activityID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeActivityName(Integer activityID, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Activities SET name = ? WHERE activityID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, name);
        statement.bindLong(2, activityID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeActivityLocation(Integer activityID, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Activities SET location = ? WHERE activityID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, location);
        statement.bindLong(2, activityID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeActivityDate(Integer activityID, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Activities SET date = ? WHERE activityID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, date);
        statement.bindLong(2, activityID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeActivityTime(Integer activityID, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Activities SET time = ? WHERE activityID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, time);
        statement.bindLong(2, activityID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeActivityPrice(Integer activityID, Double price){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Activities SET price = ? WHERE activityID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindDouble(1, price);
        statement.bindLong(2, activityID);
        statement.executeUpdateDelete();
        db.close();
    }

    public Integer getActivityID(Integer tripID, String date, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT activityID FROM Activities WHERE tripID = ? AND date = ? AND time = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, tripID);
        statement.bindString(2, date);
        statement.bindString(2, time);
        long activityID = statement.simpleQueryForLong();
        db.close();
        return (int) activityID;
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
    public void deleteTransport(Integer transportID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Transportation WHERE transportationID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, transportID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeTransportationDepartureLocation(Integer transportationID, String departureLocation){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Transportation SET departure_location = ? WHERE transportationID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, departureLocation);
        statement.bindLong(2, transportationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeTransportationDestination(Integer transportationID, String destination){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Transportation SET destination = ? WHERE transportationID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, destination);
        statement.bindLong(2, transportationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeTransportationDate(Integer transportationID, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Transportation SET date = ? WHERE transportationID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, date);
        statement.bindLong(2, transportationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeTransportationDepartureTime(Integer transportationID, String departureTime){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Transportation SET departure_time = ? WHERE transportationID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, departureTime);
        statement.bindLong(2, transportationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeTransportationArrivalTime(Integer transportationID, String arrivalTime){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Transportation SET arrival_time = ? WHERE transportationID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, arrivalTime);
        statement.bindLong(2, transportationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeTransportationType(Integer transportationID, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Transportation SET type = ? WHERE transportationID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, type);
        statement.bindLong(2, transportationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public void changeTransportationPrice(Integer transportationID, Double price){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Transportation SET price = ? WHERE transportationID = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindDouble(1, price);
        statement.bindLong(2, transportationID);
        statement.executeUpdateDelete();
        db.close();
    }

    public Integer getTransportationID(Integer tripID, String date, String departureTime){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT transportationID FROM Transportation WHERE tripID = ? AND date = ? AND departure_time = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, tripID);
        statement.bindString(2, date);
        statement.bindString(2, departureTime);
        long transportationID = statement.simpleQueryForLong();
        db.close();
        return (int) transportationID;
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

    public ArrayList<Trip> getUpcomingTrips()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Trip> trips = new ArrayList<Trip>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRIP_TABLE + " WHERE departure_date > DATE('now')", null);
        if (cursor.moveToFirst())
        {
            do
            {
                trips.add(new Trip(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trips;
    }

    public ArrayList<Trip> getPastTrips()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Trip> trips = new ArrayList<Trip>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRIP_TABLE + " WHERE return_date < DATE('now')", null);
        if (cursor.moveToFirst())
        {
            do
            {
                trips.add(new Trip(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trips;
    }

    public ArrayList<Trip> getCurrentTrips()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        // This will be the result.
        ArrayList<Trip> trips = new ArrayList<Trip>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRIP_TABLE + " WHERE departure_date <= DATE('now') AND return_date >= DATE('now')", null);
        if (cursor.moveToFirst())
        {
            do
            {
                trips.add(new Trip(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trips;
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db,int num1,int num2)
    {
        Log.d("MainActivity", "OnUpgrade Called");
        // Drop all existing tables
        db.execSQL("DROP TABLE IF EXISTS " + TRIP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACCOMMODATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACTIVITIES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSPORTATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUCKET_LIST_TABLE);

        onCreate(db);
    }
}

