module com.theorygameapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.sql;
    requires com.sandec.mdfx;
    requires MaterialFX;
    requires java.base;
    requires mysql.connector.j;


    opens com.theorygameapplication to javafx.fxml;
    exports com.theorygameapplication;
    exports com.test;
    opens com.test to javafx.fxml;
}