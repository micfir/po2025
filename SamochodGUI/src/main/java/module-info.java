module org.example.samochodgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires samochod;

    opens org.example.samochodgui to javafx.fxml;
    exports org.example.samochodgui;
}