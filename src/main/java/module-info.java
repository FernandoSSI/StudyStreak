module com.example.studystreak {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.studystreak to javafx.fxml;
    exports com.example.studystreak;
    exports com.example.studystreak.controllers;
    opens com.example.studystreak.controllers to javafx.fxml;
}