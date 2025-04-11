module fr.esiee.easytrainfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;
    requires java.desktop;


    opens fr.esiee.easytrainfx to javafx.fxml;
    exports fr.esiee.easytrainfx;
}