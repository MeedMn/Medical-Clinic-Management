module com.cabinet.gestion_cabinetmedicale {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires kernel;
    requires layout;
    requires io;
    requires itextpdf;
    opens com.cabinet.gestion_cabinetmedicale to javafx.fxml;
    exports com.cabinet.gestion_cabinetmedicale;
    exports Model;
    exports Service;
    exports Data;
}