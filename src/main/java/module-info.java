module com.example.harrypotter {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.harrypotter to javafx.fxml;
    exports com.example.harrypotter;
    exports com.example.harrypotter.Controller;
    opens com.example.harrypotter.Controller to javafx.fxml;
}