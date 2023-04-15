module com.example.bettertogether {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.bettertogether to javafx.fxml;
    exports com.example.bettertogether;
    exports com.example.bettertogether.Test;
    opens com.example.bettertogether.Test to javafx.fxml;
    exports com.example.bettertogether.JsonMappers;
    opens com.example.bettertogether.JsonMappers to javafx.fxml;
}