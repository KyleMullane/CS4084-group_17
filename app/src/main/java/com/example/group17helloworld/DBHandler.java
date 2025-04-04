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
                DESTINATION_COLUMN + " VARCHAR(50), "
                + DEPARTURE_DATE_COLUMN + " DATE, " + RETURN_DATE_COLUMN + " DATE, " +
                BUDGET_COLUMN + " DOUBLE(10,2))";

        String query2 = "CREATE TABLE " + ACCOMMODATION_TABLE +
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
                + "FOREIGN KEY (tripID) REFERENCES Trips(tripID))";

        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
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

    public Trip selectTripByID(int ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TRIP_TABLE + " WHERE "+TRIP_ID_COLUMN+" = "+ID, null);
        if (cursor.moveToFirst())
        {
            return new Trip(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5));
        }
        else
        {
            Log.d("DBHandler","No trip found with specified ID");
        }
        return new Trip(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5));
    }

    /*public void deleteAllTrips()
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

    public void changeTripDestination(Integer tripID, String destination){
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

