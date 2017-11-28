package fr.istic.star1DK;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fr.istic.star1DK.tables.BusroutesC;
import fr.istic.star1DK.tables.CalendarC;
import fr.istic.star1DK.tables.StopsC;
import fr.istic.star1DK.tables.StoptimesC;
import fr.istic.star1DK.tables.TripsC;


/**
 * Created by hamed on 21/11/2017.
 */

public class DataHelper extends SQLiteOpenHelper implements StarContract {
    /**
     * le but est d'initialiser les colonnes de la table
     */
    public static final int version_base = 1;

    public DataHelper(Context context , String Nom_Base) {

        super(context, Nom_Base, null, version_base);
        context.deleteDatabase(Nom_Base);
    }

    @Override
    public void onCreate(SQLiteDatabase undatabase) {
        String CREATE_BUSROUTES = "CREATE TABLE " + BusRoutes.CONTENT_PATH + "(" +BusRoutes.BusRouteColumns.SHORT_NAME +" TEXT,"
                + BusRoutes.BusRouteColumns.LONG_NAME + " TEXT," + BusRoutes.BusRouteColumns.DESCRIPTION + " TEXT," + BusRoutes.BusRouteColumns.TYPE + " TEXT,"
                + BusRoutes.BusRouteColumns.COLOR + " TEXT," + BusRoutes.BusRouteColumns.TEXT_COLOR + " TEXT)";

        String CREATE_TRIPS = "CREATE TABLE " + Trips.CONTENT_PATH + "(" +Trips.TripColumns.ROUTE_ID +" TEXT,"+
                Trips.TripColumns.SERVICE_ID + " TEXT," + Trips.TripColumns.HEADSIGN +  " TEXT," + Trips.TripColumns.DIRECTION_ID + " TEXT,"
                + Trips.TripColumns.BLOCK_ID + " TEXT," + Trips.TripColumns.WHEELCHAIR_ACCESSIBLE + " TEXT)";

        String CREATE_STOPS = "CREATE TABLE " + Stops.CONTENT_PATH + "(" + Stops.StopColumns.NAME +" TEXT,"
                + Stops.StopColumns.DESCRIPTION + " TEXT," + Stops.StopColumns.LATITUDE + " TEXT," + Stops.StopColumns.LONGITUDE + " TEXT,"
                + Stops.StopColumns.WHEELCHAIR_BOARDING + " TEXT)";

        String CREATE_STOPSTIMES = "CREATE TABLE " + StopTimes.CONTENT_PATH + "(" + StopTimes.StopTimeColumns.TRIP_ID + " TEXT,"
                + StopTimes.StopTimeColumns.ARRIVAL_TIME + " TEXT,"+  StopTimes.StopTimeColumns.DEPARTURE_TIME + " TEXT," + StopTimes.StopTimeColumns.STOP_ID + " TEXT,"
                + StopTimes.StopTimeColumns.STOP_SEQUENCE + " TEXT)";

        String CREATE_CALENDRAR = "CREATE TABLE " + Calendar.CONTENT_PATH + "(" + Calendar.CalendarColumns.MONDAY + " TEXT," +
                Calendar.CalendarColumns.TUESDAY + " TEXT," + Calendar.CalendarColumns.WEDNESDAY + " TEXT," + Calendar.CalendarColumns.THURSDAY + " TEXT,"
                + Calendar.CalendarColumns.FRIDAY + " TEXT," + Calendar.CalendarColumns.SATURDAY + " TEXT," + Calendar.CalendarColumns.SUNDAY + " TEXT,"
                + Calendar.CalendarColumns.START_DATE + " TEXT," + Calendar.CalendarColumns.END_DATE + " TEXT)";

        undatabase.execSQL(CREATE_BUSROUTES);
        undatabase.execSQL(CREATE_TRIPS);
        undatabase.execSQL(CREATE_STOPS);
        undatabase.execSQL(CREATE_STOPSTIMES);
        undatabase.execSQL(CREATE_CALENDRAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase undatabase, int oldVersion, int newVersion) {
        //undatabase.execSQL("DROP TABLE " + BusRoutes.CONTENT_PATH);
        //onCreate(undatabase);
    }

    public void setAllBusroutes(ArrayList<BusroutesC> listBusroutes){
        SQLiteDatabase db = this.getWritableDatabase();
        for (BusroutesC routes : listBusroutes){
            ContentValues value = new ContentValues();
            value.put(BusRoutes.BusRouteColumns.SHORT_NAME, routes.getShort_name());
            value.put(BusRoutes.BusRouteColumns.LONG_NAME, routes.getLong_name());
            value.put(BusRoutes.BusRouteColumns.DESCRIPTION, routes.getDescription());
            value.put(BusRoutes.BusRouteColumns.COLOR, routes.getColor());
            value.put(BusRoutes.BusRouteColumns.TYPE, routes.getType());
            value.put(BusRoutes.BusRouteColumns.TEXT_COLOR, routes.getText_color());
            db.insert(BusRoutes.CONTENT_PATH, null, value);
        }
        db.close();

    }

    public void SetAllTrips(ArrayList<TripsC> listTrips){
        SQLiteDatabase db = this.getWritableDatabase();
        for (TripsC trips : listTrips){
            ContentValues value = new ContentValues();
            value.put(Trips.TripColumns.ROUTE_ID, trips.getRouteId());
            value.put(Trips.TripColumns.SERVICE_ID, trips.getServiceId());
            value.put(Trips.TripColumns.HEADSIGN, trips.getHeadsign());
            value.put(Trips.TripColumns.DIRECTION_ID, trips.getDirectionId());
            value.put(Trips.TripColumns.BLOCK_ID, trips.getBlockId());
            value.put(Trips.TripColumns.WHEELCHAIR_ACCESSIBLE, trips.getWheelchairAccessible());
            db.insert(Trips.CONTENT_PATH, null, value);
        }
        db.close();
    }

    public void setAllStops(ArrayList<StopsC> listStops){
        SQLiteDatabase db = this.getWritableDatabase();
        for(StopsC stops : listStops){
            ContentValues value = new ContentValues();
            value.put(Stops.StopColumns.NAME, stops.getName());
            value.put(Stops.StopColumns.DESCRIPTION, stops.getDescription());
            value.put(Stops.StopColumns.LONGITUDE, stops.getLongitude());
            value.put(Stops.StopColumns.LATITUDE, stops.getLatitude());
            value.put(Stops.StopColumns.WHEELCHAIR_BOARDING, stops.getWheelchairBoarding());
            db.insert(Stops.CONTENT_PATH, null, value);
        }
        db.close();
    }
    public  void setAllStopTimes(ArrayList<StoptimesC> listStoptimes){
        SQLiteDatabase db = this.getWritableDatabase();
        for(StoptimesC stoptimes : listStoptimes) {
            ContentValues value = new ContentValues();
            value.put(StopTimes.StopTimeColumns.TRIP_ID, stoptimes.getTripId());
            value.put(StopTimes.StopTimeColumns.ARRIVAL_TIME, stoptimes.getArrivalTime());
            value.put(StopTimes.StopTimeColumns.DEPARTURE_TIME, stoptimes.getDepartureTime());
            value.put(StopTimes.StopTimeColumns.STOP_ID, stoptimes.getStopId());
            value.put(StopTimes.StopTimeColumns.STOP_SEQUENCE, stoptimes.getStopsequence());
            db.insert(StopTimes.CONTENT_PATH, null, value);
        }
        db.close();
    }
    public void setAllCalendar(ArrayList<CalendarC> listcalendars){
        SQLiteDatabase db = this.getWritableDatabase();
        for(CalendarC calendar : listcalendars) {
            ContentValues value = new ContentValues();
            value.put(Calendar.CalendarColumns.MONDAY, calendar.getMonday());
            value.put(Calendar.CalendarColumns.FRIDAY, calendar.getTuesday());
            value.put(Calendar.CalendarColumns.WEDNESDAY, calendar.getWednesday());
            value.put(Calendar.CalendarColumns.THURSDAY, calendar.getThursday());
            value.put(Calendar.CalendarColumns.FRIDAY, calendar.getFriday());
            value.put(Calendar.CalendarColumns.SATURDAY, calendar.getSaturday());
            value.put(Calendar.CalendarColumns.SUNDAY, calendar.getSunday());
            value.put(Calendar.CalendarColumns.START_DATE, calendar.getStartdate());
            value.put(Calendar.CalendarColumns.END_DATE, calendar.getEnddate());
            db.insert(Calendar.CONTENT_PATH, null, value);
        }
        db.close();
    }

    public BusroutesC getBusRoutes(String shortname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor lecursor = db.query(BusRoutes.CONTENT_PATH, new String[]{BusRoutes.BusRouteColumns.SHORT_NAME, BusRoutes.BusRouteColumns.LONG_NAME,
                        BusRoutes.BusRouteColumns.DESCRIPTION, BusRoutes.BusRouteColumns.COLOR, BusRoutes.BusRouteColumns.TYPE, BusRoutes.BusRouteColumns.TEXT_COLOR},
                BusRoutes.BusRouteColumns.SHORT_NAME+ "=?", new String[]{String.valueOf(shortname)}, null, null, null, null);

        if (lecursor != null)
            lecursor.moveToFirst();
        String short_name = lecursor.getString(0);
        String long_name = lecursor.getString(1);
        String description = lecursor.getString(2);
        String type = lecursor.getString(3);
        String color = lecursor.getString(4);
        String text_color = lecursor.getString(5);
        BusroutesC busroutes = new BusroutesC(short_name,long_name,description,color,type,text_color);
        return busroutes;
    }

    public ArrayList<BusroutesC> getAllBusroutesC() {
        ArrayList<BusroutesC> busroutesCS = new ArrayList<BusroutesC>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + BusRoutes.CONTENT_PATH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BusroutesC busroutesC = new BusroutesC();
                busroutesC.setShort_name(cursor.getString(0));
                busroutesC.setLong_name(cursor.getString(1));
                busroutesC.setDescription(cursor.getString(2));
                busroutesC.setColor(cursor.getString(3));
                busroutesC.setType(cursor.getString(4));
                busroutesC.setText_color(cursor.getString(5));

                // Adding BusRoutes to list
                busroutesCS.add(busroutesC);
            } while (cursor.moveToNext());
        }
        // return contact list
        return busroutesCS;
    }

    public ArrayList<TripsC> getAllTrips(){
        ArrayList<TripsC> tripss = new ArrayList<TripsC>();
        //select All Querry
        String selectQuery = "SELECT  * FROM " + Trips.CONTENT_PATH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                TripsC tripsC = new TripsC();
                tripsC.setRouteId(cursor.getString(0));
                tripsC.setServiceId(cursor.getString(1));
                tripsC.setHeadsign(cursor.getString(2));
                tripsC.setDirectionId(cursor.getString(3));
                tripsC.setBlockId(cursor.getString(4));
                tripsC.setWheelchairAccessible(cursor.getString(5));
                tripss.add(tripsC);
            }while (cursor.moveToNext());
        }
        return tripss;
    }

    public ArrayList<StopsC> getAllStops(){
        ArrayList<StopsC> Stopss = new ArrayList<StopsC>();
        //select All Querry
        String selectQuery = "SELECT  * FROM " + Stops.CONTENT_PATH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                StopsC stopsC = new StopsC();
                stopsC.setName(cursor.getString(0));
                stopsC.setDescription(cursor.getString(1));
                stopsC.setLatitude(cursor.getString(2));
                stopsC.setLongitude(cursor.getString(3));
                stopsC.setWheelchairBoarding(cursor.getString(4));
                Stopss.add(stopsC);
            }while (cursor.moveToNext());
        }
        return Stopss;
    }

    public ArrayList<StoptimesC> getAllStoptimes(){
        ArrayList<StoptimesC> stoptimesCS = new ArrayList<StoptimesC>();
        //select All Querry
        String selectQuery = "SELECT  * FROM " + StopTimes.CONTENT_PATH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                StoptimesC stoptimesC = new StoptimesC();
                stoptimesC.setTripId(cursor.getString(0));
                stoptimesC.setArrivalTime(cursor.getString(1));
                stoptimesC.setDepartureTime(cursor.getString(2));
                stoptimesC.setStopId(cursor.getString(3));
                stoptimesC.setStopsequence(cursor.getString(4));
                stoptimesCS.add(stoptimesC);
            }while (cursor.moveToNext());
        }
        return stoptimesCS;
    }

    public ArrayList<CalendarC> getAllCalendars(){
        ArrayList<CalendarC> calendarCS = new ArrayList<CalendarC>();
        //select All Querry
        String selectQuery = "SELECT  * FROM " + Calendar.CONTENT_PATH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                CalendarC calendarC = new CalendarC();
                calendarC.setMonday(cursor.getString(0));
                calendarC.setTuesday(cursor.getString(1));
                calendarC.setWednesday(cursor.getString(2));
                calendarC.setThursday(cursor.getString(3));
                calendarC.setFriday(cursor.getString(4));
                calendarC.setSaturday(cursor.getString(5));
                calendarC.setSunday(cursor.getString(6));
                calendarC.setStartdate(cursor.getString(7));
                calendarC.setEnddate(cursor.getString(8));

                calendarCS.add(calendarC);

            }while (cursor.moveToNext());
        }
        return calendarCS;
    }

    public int getBusRoutesCount() {
        int nbre;
        String countQuery = "SELECT  * FROM " + BusRoutes.CONTENT_PATH;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        nbre = cursor.getCount();
        cursor.close();
        // return count
        return nbre;
    }
}
