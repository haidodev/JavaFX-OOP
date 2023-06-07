module com.app.dict {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.app.dict to javafx.fxml;
    exports com.app.dict;
    exports com.app.dict.controllers;
    opens com.app.dict.controllers to javafx.fxml;
}