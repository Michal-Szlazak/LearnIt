package com.example.bettertogether.MainMenuGUI;

import com.example.bettertogether.FolderPaths;
import javafx.scene.control.ListView;

import java.io.File;

public class TestLoader {

    private final String pathToTestFolder = FolderPaths.pathToTestFolder;

    public TestLoader(ListView<String> testListView) {
        File testDir = new File(pathToTestFolder);
        File[] testFiles = testDir.listFiles();
        if(testFiles == null) {
            return;
        }
        String[] fileNames = new String[testFiles.length];
        int index = 0;
        for(File file : testFiles) {
            fileNames[index++] = file.getName().replace(".json", "");
        }
        testListView.getItems().addAll(fileNames);
    }

}
