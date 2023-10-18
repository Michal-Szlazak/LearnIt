package com.example.bettertogether;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class FolderPaths {

//    public static final String pathToTestFolder = "com/example/bettertogether/TestFiles/";
    public static final String pathToTestFolder = "/classes/com/example/bettertogether/TestFiles/";
    public static final String pathToFXMLFolder = "fxmlFiles/";
    public static final String pathToCssFiles = "/com/example/bettertogether/css/";


    public static String getJarDirPath() {
        File jarDir = null;
        try {
            // Get the location of the JAR file
            URL jarLocation = FolderPaths.class.getProtectionDomain().getCodeSource().getLocation().toURI().toURL();
            File jarFile = new File(jarLocation.toURI());

            // Get the directory containing the JAR file
            jarDir = jarFile.getParentFile();

            System.out.println("JAR file location: " + jarFile.getAbsolutePath());
            System.out.println("Directory containing the JAR file: " + jarDir.getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return jarDir == null ? "" : jarDir.getAbsolutePath();
    }
}
