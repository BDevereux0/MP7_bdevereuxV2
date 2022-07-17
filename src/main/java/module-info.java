module com.example.mp7_bdevereuxv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mp7_bdevereuxv2 to javafx.fxml;
    exports com.example.mp7_bdevereuxv2;
}