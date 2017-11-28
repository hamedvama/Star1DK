package fr.istic.star1DK;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import fr.istic.star1DK.tables.BusroutesC;

/**
 * Created by hamed on 23/11/2017.
 */

public class ContentProvider extends android.content.ContentProvider {

    private static final String nombase = "STARBASE";

    public BusroutesC getRoute(String shortname){

        DataHelper dataHelper = new DataHelper(getContext(),nombase);
        SQLiteDatabase db = dataHelper.getReadableDatabase();

        Cursor lecursor = db.query(StarContract.BusRoutes.CONTENT_PATH, new String[]{StarContract.BusRoutes.BusRouteColumns.SHORT_NAME, StarContract.BusRoutes.BusRouteColumns.LONG_NAME,
                        StarContract.BusRoutes.BusRouteColumns.DESCRIPTION, StarContract.BusRoutes.BusRouteColumns.COLOR, StarContract.BusRoutes.BusRouteColumns.TYPE, StarContract.BusRoutes.BusRouteColumns.TEXT_COLOR},
                StarContract.BusRoutes.BusRouteColumns.SHORT_NAME+ "=?", new String[]{String.valueOf(shortname)}, null, null, null, null);

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

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
