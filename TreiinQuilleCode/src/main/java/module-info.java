module fr.esiee.easytrainfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;


    opens fr.esiee.easytrainfx to javafx.fxml;
    exports fr.esiee.easytrainfx;
}