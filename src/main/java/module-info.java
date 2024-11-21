module com.hondaamartha {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires transitive java.sql;

    opens com.hondaamartha.model to javafx.base;
    opens com.hondaamartha to javafx.fxml;
    exports com.hondaamartha;
}
