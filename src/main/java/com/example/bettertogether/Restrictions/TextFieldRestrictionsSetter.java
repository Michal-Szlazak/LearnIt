package com.example.bettertogether.Restrictions;

import com.example.bettertogether.FolderPaths;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class TextFieldRestrictionsSetter {

    private String pathToCss = FolderPaths.pathToCssFiles;
    public TextFieldRestrictionsSetter(TextField textField) {
        addChangeListener(textField);
    }

    private void addChangeListener(TextField textField) {

        if(textField.getText().length() == 0) {
            textField.getStylesheets().add(getClass().getResource(pathToCss + "restrictedAnswer.css").toExternalForm());
            textField.setTooltip( new Tooltip("You have to add at least one letter."));
        }

        textField.textProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old, String newVal) {
                if (newVal.length() == 0) {
                    if (!textField.getStylesheets().contains(getClass()
                            .getResource(pathToCss + "restrictedAnswer.css").toExternalForm())) {
                        textField.getStylesheets().add(getClass().getResource(pathToCss + "restrictedAnswer.css").toExternalForm());
                    }
                    textField.setTooltip(new Tooltip("You have to add at least one letter."));
                } else if (newVal.length() > 200) {
                    if (!textField.getStylesheets().contains(getClass()
                            .getResource(pathToCss + "restrictedAnswer.css").toExternalForm())) {
                        textField.getStylesheets().add(getClass().getResource(pathToCss + "restrictedAnswer.css").toExternalForm());
                    }
                    textField.setTooltip(new Tooltip("You cannot add more than 200 letters."));
                } else {
                    textField.getStylesheets().remove(getClass().getResource(pathToCss + "restrictedAnswer.css").toExternalForm());
                    textField.setTooltip(null);
                }
            }
        });
    }

}
