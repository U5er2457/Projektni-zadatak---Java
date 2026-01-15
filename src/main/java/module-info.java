module com.javafx.project.javafx_projekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jakarta.json.bind;


    opens com.javafx to javafx.fxml;
    exports com.javafx;
    exports com.javafx.controller;
    opens com.javafx.controller to javafx.fxml;
}