package fr.istic.star1DK;
//Importation des packages dont on va se servir

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by hamed on 19/11/2017.
 */

public class DeZiper {

    public static void main(String[] args) throws Exception {
        String direct1 = "C:\\Users\\hamed\\AndroidStudioProjects\\StartProviderDK\\GTFS_2017.3.0_2017-11-06_2017-12-03.zip";
        String direct2 = "C:\\Users\\hamed\\AndroidStudioProjects\\StartProviderDK\\GTFS_2017.3.0_2017-11-06_2017-12-03\\";

        File zipfile = new File(direct1);
        File folder = new File(direct2);

        DeZiper.unzip(zipfile, folder);
    }
    /**
     * décompresse le fichier zip dans le répertoire donné
     *
     * @param folder  le répertoire où les fichiers seront extraits
     * @param zipfile le fichier zip à décompresser
     * @throws FileNotFoundException
     * @throws IOException
     */

    public static void unzip(File zipfile, File folder) throws FileNotFoundException, IOException {

        // création de la ZipInputStream qui va servir à lire les données du fichier zip
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipfile.getCanonicalFile())));

        // extractions des entrées du fichiers zip (i.e. le contenu du zip)
        ZipEntry ze = null;
        try {
            while ((ze = zis.getNextEntry()) != null) {

                // Pour chaque entrée, on crée un fichier
                // dans le répertoire de sortie "folder"
                File f = new File(folder.getCanonicalPath(), ze.getName());

                // Si l'entrée est un répertoire,
                // on le crée dans le répertoire de sortie
                // et on passe à l'entrée suivante (continue)
                if (ze.isDirectory()) {
                    f.mkdirs();
                    continue;
                }

                // L'entrée est un fichier, on crée une OutputStream
                // pour écrire le contenu du nouveau fichier
                f.getParentFile().mkdirs();
                OutputStream fos = new BufferedOutputStream(new FileOutputStream(f));

                // On écrit le contenu du nouveau fichier
                // qu'on lit à partir de la ZipInputStream
                // au moyen d'un buffer (byte[])
                try {
                    try {
                        final byte[] buf = new byte[8192];
                        int bytesRead;
                        while (-1 != (bytesRead = zis.read(buf)))
                            fos.write(buf, 0, bytesRead);
                       // Log.e("ZIP"," le nom du ZipInputStream"+fos.toString());

                    } finally {
                        fos.close();
                    }
                } catch (final IOException ioe) {
                    // en cas d'erreur on efface le fichier
                    f.delete();
                    throw ioe;
                }
            }
        } finally {
            // fermeture de la ZipInputStream
            zis.close();
        }



    }

}
