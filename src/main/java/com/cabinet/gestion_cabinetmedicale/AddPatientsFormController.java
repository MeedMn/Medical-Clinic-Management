package com.cabinet.gestion_cabinetmedicale;

import Model.Patient;
import Service.PatientService;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddPatientsFormController implements Initializable {
    public TextField nomField;
    public TextField prenomField;
    public TextField cneField;
    public TextField ageField;
    public TextField telField;
    public DatePicker dateField;
    public ComboBox<String> sexeBox;

    public void CloseBtn(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void addPatient(MouseEvent mouseEvent) {
        LocalDate converted_date = dateField.getValue();
        String sexe = sexeBox.getValue().toString();
        PatientService.AjouterPatient(new Patient(cneField.getText(), nomField.getText(), prenomField.getText(), Integer.parseInt(ageField.getText()), telField.getText(), converted_date,sexe));
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexeBox.getItems().add("FEMME");
        sexeBox.getItems().add("HOMME");
    }
}
