module com.app.dict {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.google.gson;


    opens com.app.dict to javafx.fxml;
    exports com.app.dict;
    exports com.app.dict.controllers;
    opens com.app.dict.controllers to javafx.fxml;
    opens com.app.dict.base to com.google.gson;

}