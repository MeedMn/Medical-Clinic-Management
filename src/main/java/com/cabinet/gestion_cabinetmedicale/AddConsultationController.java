package com.cabinet.gestion_cabinetmedicale;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddConsultationController implements Initializable {
    public TextField cneField;
    public ComboBox Hours;
    public ComboBox minutes;

    public void CloseBtn(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }
    public void addConsultation(MouseEvent mouseEvent) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] arr1 = {"00","15","30","45"};
        minutes.getItems().addAll(arr1);
        for (int i=8;i<19;i++){
            Hours.getItems().add(String.valueOf(i));
        }

    }
}
