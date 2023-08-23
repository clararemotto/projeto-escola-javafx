module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    //permitindo o sql no modulo
    requires java.sql;

    opens com.example to javafx.fxml;
    exports com.example;
}
