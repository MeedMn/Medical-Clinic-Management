package com.cabinet.gestion_cabinetmedicale;

import Model.Consultation;
import Model.RendezVous;
import Service.ConsultationService;
import Service.RendezVousService;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddRendezVousFormController implements Initializable {
    public DatePicker dateField;
    public TextField cneField;
    public ComboBox<String> Hours;
    public ComboBox<String> minutes;
    public ComboBox stateField;

    public void CloseBtn(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void addRendezvous(MouseEvent mouseEvent) {
        int id = RendezVousService.checkexistenceofpatient(cneField.getText());
        System.err.println("cne : " + cneField.getText() + " | id : " + id);
        if(RendezVousService.checkexistenceofpatient(cneField.getText()) != -1){
            RendezVousService.AjouterRendezVous(new RendezVous(
                    id,dateField.getValue(),Hours.getValue()+":"+minutes.getValue(),stateField.getValue().toString()
            ));
            ConsultationService.AjouterConsultation(new Consultation(
                    id,dateField.getValue(),Hours.getValue()+":"+minutes.getValue(),stateField.getValue().toString()
            ));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Patient not found");
            alert.show();
        }
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
