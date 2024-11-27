module tv.safte.truemytunes {
    requires javafx.controls;
    requires javafx.fxml;


    opens tv.safte.truemytunes to javafx.fxml;
    exports tv.safte.truemytunes;
}