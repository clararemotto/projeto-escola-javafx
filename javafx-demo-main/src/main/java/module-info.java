module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    //permitindo o sql no modulo
    requires java.sql;

    opens com.example to javafx.fxml;
    opens com.example.controller to javafx.fxml;
    exports com.example;
    exports com.example.controller;
    //arrumar caminho da pasta
}
