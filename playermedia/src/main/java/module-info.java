module com.example.playermedia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.playermedia to javafx.fxml;
    exports com.example.playermedia;
}