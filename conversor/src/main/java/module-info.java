module com.unisul.conversor {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.unisul.conversor to javafx.fxml;
    exports com.unisul.conversor;
}
