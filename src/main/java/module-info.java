module com.example.nhom16_cnpm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.main to javafx.fxml;
    exports com.example.main;
    opens com.example.model to javafx.fxml;
    exports com.example.model;
    opens com.example.controller to javafx.fxml;
    exports com.example.controller;
}