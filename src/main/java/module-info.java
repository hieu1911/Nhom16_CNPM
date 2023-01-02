module com.example.nhom16_cnpm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.main to javafx.fxml;
    exports com.example.main;
    exports com.example.controller;
    opens com.example.controller to javafx.fxml;
}