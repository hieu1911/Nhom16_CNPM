module com.example.nhom16_cnpm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nhom16_cnpm to javafx.fxml;
    exports com.example.nhom16_cnpm;
}