package com.cabinet.gestion_cabinetmedicale;

import Model.Patient;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowDataPatientController implements Initializable {
    public static Patient patient;
    public Label nomShow;
    public Label prenomShow;
    public Label cneShow;
    public Label ageShow;
    public Label telShow;
    public Label dateShow;

    public void CloseBtn(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }
    public static void setPatient(Patient p){
        patient = p;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomShow.setText(patient.getNom());
        prenomShow.setText(patient.getPrenom());
        cneShow.setText(patient.getCne());
        ageShow.setText(String.valueOf(patient.getAge()));
        telShow.setText(patient.getNumero());
        dateShow.setText(String.valueOf(patient.getDate_naissance()));
    }
}
