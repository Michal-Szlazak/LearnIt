package com.example.bettertogether.QuestionCreatorGUI;

import com.example.bettertogether.Restrictions.TextFieldRestrictionsSetter;
import javafx.scene.control.TextField;

public class QuestionTextField {
    public QuestionTextField(TextField questionTextField) {
        new TextFieldRestrictionsSetter(questionTextField);
    }

}
