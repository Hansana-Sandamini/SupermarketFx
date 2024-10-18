module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires java.desktop;
    requires net.sf.jasperreports.core;

    opens com.example.demo3.tm to javafx.base;
    opens com.example.demo3.controller to javafx.fxml;
    exports com.example.demo3;
}