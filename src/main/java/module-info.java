module tv.safte.truemytunes {
    requires javafx.controls;
    requires javafx.fxml;


    opens tv.safte.truemytunes to javafx.fxml;
    exports tv.safte.truemytunes;
    exports tv.safte.truemytunes.GUI;
    opens tv.safte.truemytunes.GUI to javafx.fxml;
    exports tv.safte.truemytunes.GUI.Controller;
    opens tv.safte.truemytunes.GUI.Controller to javafx.fxml;
}