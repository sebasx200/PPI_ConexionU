module com.example.ppi_conexionu {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.ppi_conexionu to javafx.fxml;
    exports com.ppi_conexionu;
    exports com.clases_controladoras;
    opens com.clases_controladoras to javafx.fxml;
    exports com.clases_controladoras.funcionalidades_menu;
    opens com.clases_controladoras.funcionalidades_menu to javafx.fxml;
}