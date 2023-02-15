package com.cabinet.gestion_cabinetmedicale;

import Model.Patient;
import Service.PatientService;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPatientFormController implements Initializable {

    public Patient patient;
    public TextField nomField;
    public TextField prenomField;
    public TextField cneField;
    public TextField ageField;
    public TextField telField;
    public DatePicker dateField;
    public ComboBox<String> sexeBox;



    public void setDataOfInputs(Patient patient){
                this.patient  = patient;
                cneField.setText(patient.getCne());
                nomField.setText(patient.getNom());
                prenomField.setText(patient.getPrenom());
                ageField.setText(Integer.toString(patient.getAge()));
                telField.setText(patient.getNumero());
                dateField.setValue(patient.getDate_naissance());
                sexeBox.setValue(patient.getSexe());
    }
    public void CloseBtn(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void ModifierPatient(MouseEvent mouseEvent) {
        System.out.println(patient.getId());
        PatientService.UpdatePatient(new Patient(
                patient.getId(),
                cneField.getText(),
                nomField.getText(),
                prenomField.getText(),
                Integer.parseInt(ageField.getText()),
                telField.getText(),
                dateField.getValue(),
                sexeBox.getValue().toString()
        ));
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexeBox.getItems().add("HOMME");
        sexeBox.getItems().add("FEMME");
    }
}
