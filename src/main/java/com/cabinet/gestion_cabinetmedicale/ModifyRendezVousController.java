package com.cabinet.gestion_cabinetmedicale;

import Model.Consultation;
import Model.Patient;
import Model.RendezVous;
import Service.ConsultationService;
import Service.PatientService;
import Service.RendezVousService;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifyRendezVousController implements Initializable {
    public DatePicker dateField;
    public TextField cneField;
    public ComboBox<String> Hours;
    public ComboBox<String> minutes;
    public ComboBox stateField;
    public RendezVous rendezVous;
    public void setDataOfInputs(RendezVous rendezVous){
        this.rendezVous  = rendezVous;
        String[] arrOfStr = rendezVous.getTime().split(":", 2);
        cneField.setText(rendezVous.getCnePatient());
        cneField.setEditable(false);
        dateField.setValue(rendezVous.getDate_rendezVous());
        Hours.setValue(arrOfStr[0]);
        minutes.setValue(arrOfStr[1]);
        stateField.setValue(rendezVous.getState());
    }
    public void CloseBtn(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void addRendezvous(MouseEvent mouseEvent) {
        System.out.println(rendezVous.getId());
        RendezVousService.updateRdv(new RendezVous(rendezVous.getId(),dateField.getValue(), Hours.getValue() +":"+ minutes.getValue(),stateField.getValue().toString(),0));
        ConsultationService.UpdateConsultation(new Consultation(rendezVous.getId(),dateField.getValue(), Hours.getValue() +":"+ minutes.getValue(),stateField.getValue().toString(),0));
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] arr1 = {"00","15","30","45"};
        minutes.getItems().addAll(arr1);
        for (int i=8;i<19;i++){
            Hours.getItems().add(String.valueOf(i));
        }
        stateField.getItems().add("Not yet");
        stateField.getItems().add("Done");
        stateField.getItems().add("Cancelled");
        dateField.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
    }
}
