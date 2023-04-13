package com.example.bettertogether.MainMenuGUI;

import com.example.bettertogether.FolderPaths;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;

public class TestRemover {

    private String testFileName;

    public boolean removeTest(String testFileName) {
        if(testFileName == null) {
            alertFailedToDeleteFile();
            return false;
        }
        this.testFileName = testFileName;
        return showAlertBeforeDeletion();
    }

    private boolean showAlertBeforeDeletion() {
        Alert alertBeforeDeletion = new Alert(Alert.AlertType.CONFIRMATION);
        alertBeforeDeletion.setTitle("Deletion alert");
        alertBeforeDeletion.setHeaderText("Are you sure you want to delete this test?");
        alertBeforeDeletion.setContentText("If you will delete this test it will be lost for ever.");
        alertBeforeDeletion.showAndWait();

        ButtonType result = alertBeforeDeletion.getResult();

        if(result == ButtonType.OK) {
            if(!deleteTestFile(testFileName)) {
                alertFailedToDeleteFile();
                return false;
            }
        }
        return true;
    }

    private boolean deleteTestFile(String testFileName) {
        File file = new File(FolderPaths.pathToTestFolder + testFileName);

        if(file.delete()) {
            return true;
        }
        return false;
    }

    private void alertFailedToDeleteFile() {
        Alert failedDeletionAlert = new Alert(Alert.AlertType.ERROR);
        failedDeletionAlert.setTitle("Deletion error");
        failedDeletionAlert.setHeaderText("Failed to delete given test");
        failedDeletionAlert.show();
    }
}
