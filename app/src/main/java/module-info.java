module com.example.bettertogether {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports com.example.bettertogether;

    opens com.example.bettertogether to javafx.fxml;
    opens com.example.bettertogether.DB to com.fasterxml.jackson.databind;
    opens com.example.bettertogether.Test to com.fasterxml.jackson.databind;
}