package fr.istic.star1DK;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.istic.star1DK.tables.BusroutesC;
import fr.istic.star1DK.tables.CalendarC;
import fr.istic.star1DK.tables.StopsC;
import fr.istic.star1DK.tables.StoptimesC;
import fr.istic.star1DK.tables.TripsC;


public class MainActivity extends AppCompatActivity {
    //Log de la classe MainActivity
    private String TAG = MainActivity.class.getSimpleName();
    //Url du site pour telecharger le fichier json de mise a jour
    private static String url = "http://ftp.keolis-rennes.com/opendata/tco-busmetro-horaires-gtfs-versions-td/attachments/GTFS_2017.3.0_2017-11-06_2017-12-03.zip";
    HttpHandler sh = new HttpHandler();
    DownloadManager dm;
    int nbre1 = 0, nbre2 = 0, nbre3 = 0, nbre4 = 0, nbre5 = 0;

    public static final String Nom_Base = "STARBASE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationUri(Uri.fromFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));
        long reference = dm.enqueue(request);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);*/
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("DataSet");
        request.setDescription("FilesBase");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        String filename = URLUtil.guessFileName(url, null, MimeTypeMap.getFileExtensionFromUrl(url));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
        DownloadManager manager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        File repertoire = new File(Environment.getExternalStorageDirectory().toURI());
        File[] files = repertoire.listFiles();
        for (File file : files) {
            file.getAbsolutePath();
            Log.e("Basefiles", "les fichiers " + file.getName());
            Log.e("Basefiles", " le pathAbosolue est : " + file.getAbsolutePath());

        }

        DataHelper datahelp = new DataHelper(this, Nom_Base);

        /*ArrayList<BusroutesC> listbusRoutes = new ArrayList<>();
        BufferedReader readeroutes = null;
        try {
            readeroutes = new BufferedReader(new InputStreamReader(getAssets().open("routes.txt")));
            // do reading, usually loop until end of file reading
            String item_routes;
            while ((item_routes = readeroutes.readLine()) != null) {
                //process line
                //Log.e("Base", "Read line routes : " + item_routes);
                nbre1++;
                String[] str = item_routes.split(",");
                listbusRoutes.add(new BusroutesC(str[2], str[3], str[4], str[5], str[7], str[8]));
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (readeroutes != null) {
                try {
                    readeroutes.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        Log.e("Base", "nombre de ligne dans la table routes : " + nbre1);
        datahelp.setAllBusroutes(listbusRoutes);
        ArrayList<BusroutesC> busroutesback = new ArrayList<>();
        busroutesback = datahelp.getAllBusroutesC();
        for (BusroutesC busroutebase : busroutesback) {
            Log.e("Base", " Liste des routes de la base " + busroutebase.getLong_name());
        }*/

        /**
         * charger le ficher trips et alimenter la base de données
         */

        /*ArrayList<TripsC> listTrips = new ArrayList<>();
        BufferedReader readtrips = null;
        try {
            readtrips = new BufferedReader(new InputStreamReader(getAssets().open("trips.txt")));
            // do reading, usually loop until end of file reading
            String item_trips;
            while ((item_trips = readtrips.readLine()) != null) {
                //process line
                //Log.e("Base", "Read line trips : " + item_trips);
                nbre2++;
                String[] str = item_trips.split(",");
                listTrips.add(new TripsC(str[0], str[1], str[3], str[5], str[7], str[8]));
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (readtrips != null) {
                try {
                    readtrips.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        Log.e("Base", "nombre de ligne dans la table Trips : " + nbre2);
        datahelp.SetAllTrips(listTrips);
        ArrayList<TripsC> tripsback = new ArrayList<>();
        tripsback = datahelp.getAllTrips();
        for (TripsC tripsbase : tripsback) {
            Log.e("Base", "liste des trips de la base" + tripsbase.getDirectionId());
        }

        ArrayList<StopsC> listStops = new ArrayList<>();
        BufferedReader readeStop = null;
        try {
            readeStop = new BufferedReader(new InputStreamReader(getAssets().open("stops.txt")));
            // do reading, usually loop until end of file reading
            String item_stops;
            while ((item_stops = readeStop.readLine()) != null) {
                //process line
                //Log.e("Base", "Read line stops : " + item_stops);
                nbre3++;
                String[] str = item_stops.split(",");
                listStops.add(new StopsC(str[2], str[3], str[4], str[5], str[11]));
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (readeStop != null) {
                try {
                    readeStop.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        Log.e("Base", "nombre de ligne dans la table Stops : " + nbre3);
        datahelp.setAllStops(listStops);
        ArrayList<StopsC> stopsback = new ArrayList<>();
        stopsback = datahelp.getAllStops();
        for (StopsC stops : stopsback) {
            Log.e("Base", "liste des stops de la base" + stops.getName());
        }

        ArrayList<CalendarC> listCalandars = new ArrayList<>();
        BufferedReader readecalendar = null;
        try {
            readecalendar = new BufferedReader(new InputStreamReader(getAssets().open("calendar.txt")));
            // do reading, usually loop until end of file reading
            String item_calendar;
            while ((item_calendar = readecalendar.readLine()) != null) {
                //process line
                Log.e("Base", "Read line stops : " + item_calendar);
                nbre5++;
                String[] str = item_calendar.split(",");
                listCalandars.add(new CalendarC(str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9]));
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (readecalendar != null) {
                try {
                    readecalendar.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        Log.e("Base", "nombre de ligne dans la table Calendar : " + nbre5);
        datahelp.setAllCalendar(listCalandars);
        ArrayList<CalendarC> calendarback = new ArrayList<>();
        calendarback = datahelp.getAllCalendars();
        for (CalendarC calendar : calendarback) {
            Log.e("Base", "liste des calendiers de la base   Monday : " + calendar.getMonday() + " start date : " + calendar.getStartdate() +
                    " end date : " + calendar.getEnddate());
        }*/

        ArrayList<StoptimesC> listStopTimes = new ArrayList<>();
        BufferedReader readestoptimes = null;
        try {
            readestoptimes = new BufferedReader(new InputStreamReader(getAssets().open("stop_times.txt")));
            // do reading, usually loop until end of file reading
            String item_stoptimes;
            while ((item_stoptimes = readestoptimes.readLine()) != null) {
                //process line
                //Log.e("Base", "Read line stops : " + item_stoptimes);
                nbre4++;
                Log.e("Base","nombre ligne lu : " + nbre4);
                String[] str = item_stoptimes.split(",");
                listStopTimes.add(new StoptimesC(str[0], str[1], str[2], str[3], str[4]));
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (readestoptimes != null) {
                try {
                    readestoptimes.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        Log.e("Base", "nombre de ligne dans la table Stop_times : " + nbre4);
        datahelp.setAllStopTimes(listStopTimes);
        ArrayList<StoptimesC> stoptimesback = new ArrayList<>();
        stoptimesback = datahelp.getAllStoptimes();
        for (StoptimesC stoptimes : stoptimesback) {
            Log.e("Base", "liste des stops de la base " + stoptimes.getStopId() + " heure arrivée : " + stoptimes.getArrivalTime() +
                    " heure depart : " + stoptimes.getDepartureTime());
        }
    }


}
