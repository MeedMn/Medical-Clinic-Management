package com.cabinet.gestion_cabinetmedicale;

import Model.Ordonnance;
import Model.User;
import Service.OrdonnanceService;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddOrdonanceFormController {

    public TextField cneInput;
    public DatePicker dateOrd;
    public Button saveOrd;
    public TextArea contentArea;
    public static User user;
    public static void UserTransfer(User u){
        user = u;
    }

    public void CloseBtn(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void insertOrd(ActionEvent actionEvent) {

        if(checkExistenceOfCne(cneInput.getText()) != -1){
            System.err.println("ID FOUND OF " +cneInput.getText() + ":"+checkExistenceOfCne(cneInput.getText()));
            OrdonnanceService.InsertOrdonnaceIntoDatabase(new Ordonnance(checkExistenceOfCne(cneInput.getText()),user.getNom()+" "+user.getPrenom(), contentArea.getText(), dateOrd.getValue()));
        }else{
            System.err.println("ID NOT FOUND ");
        }
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
    public int checkExistenceOfCne(String cne){
        if(OrdonnanceService.checkCneOfPatient(cne) != 0){
            return OrdonnanceService.checkCneOfPatient(cne);
        }return -1;
    }
}
