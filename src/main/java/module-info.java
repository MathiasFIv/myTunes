module tv.safte.truemytunes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires javafx.media;
    requires java.naming;

    opens tv.safte.truemytunes to javafx.fxml;
    exports tv.safte.truemytunes.GUI;
    opens tv.safte.truemytunes.GUI to javafx.fxml;
    exports tv.safte.truemytunes.GUI.Controller;
    opens tv.safte.truemytunes.GUI.Controller to javafx.fxml;
}