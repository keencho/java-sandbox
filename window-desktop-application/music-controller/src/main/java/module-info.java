module com.keencho.musiccontroller {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires java.desktop;

    opens com.keencho.musiccontroller to javafx.fxml;
    exports com.keencho.musiccontroller;
}