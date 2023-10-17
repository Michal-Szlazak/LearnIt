package com.example.bettertogether.MainMenuGUI;

import com.example.bettertogether.FolderPaths;
import javafx.scene.control.ListView;
import java.io.File;

public class TestLoader {

    private final ListView<String> testListView;

    public TestLoader(ListView<String> testListView) {
        this.testListView = testListView;
    }

    public void load() {
        String pathToTestFolder = FolderPaths.pathToTestFolder;
        File testDir = new File(FolderPaths.getJarDirPath() + pathToTestFolder);
        File[] testFiles = testDir.listFiles();

        if(testFiles == null) {
            return;
        }
        String[] fileNames = new String[testFiles.length];
        int index = 0;
        for(File file : testFiles) {
            fileNames[index++] = file.getName().replace(".json", "");
        }

        testListView.getItems().clear();
        testListView.getItems().addAll(fileNames);
    }

}
