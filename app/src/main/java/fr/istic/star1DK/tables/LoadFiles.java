package fr.istic.star1DK.tables;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by hamed on 22/11/2017.
 */

public class LoadFiles {


    public ArrayList<BusroutesC> loadBusRoutesData(BufferedReader fuffer) throws IOException {
        ArrayList<BusroutesC> lisbusRoutes = new ArrayList<>();

        int nbre = 0;
        File file = null;
        String path = "";
        file = new File("file:///android_assets/routes.txt");
        path = file.getCanonicalPath();
        FileReader fileReader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(fileReader);
        String line = "";
        while ((line = buffer.readLine()) != null) {
            nbre++;
            String[] str = line.split(",");
            lisbusRoutes.add(new BusroutesC(str[0], str[1], str[2], str[3], str[4], str[5]));
        }

        Log.e("Base", "nombre de ligne" + nbre);
        return lisbusRoutes;
    }
}
