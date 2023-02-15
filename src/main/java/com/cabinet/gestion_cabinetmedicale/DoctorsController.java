package com.cabinet.gestion_cabinetmedicale;

import Data.Database;
import Model.User;
import Service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DoctorsController implements Initializable {

    public static User user;
    public ButtonBar patientbtn;
    public ButtonBar rendezvousAssis;
    public ButtonBar ordonnanceBtnAssis;
    public ButtonBar ConsultationBtnAssis;
    public ButtonBar DoctorsBtn;
    public ButtonBar rendezvousDoc;
    public ButtonBar ordonnanceBtnDoc;
    public ButtonBar ConsultationBtnDoc;
    public Label nomDoc1;
    public Label prenomDoc1;
    public Label ageDoc1;
    public Label contactDoc1;
    public Label emailDoc;
    public ComboBox<String> stateDoc;
    public Label nomDoc2;
    public Label prenomDoc2;
    public Label ageDoc2;
    public Label contactDoc2;
    public Label emailDoc2;
    public ComboBox<String> stateDoc2;

    public static void UserTransfer(User u){
        user = u;
    }
    public void onAccueilClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Dashboard");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onPatientsClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        PatientsController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Patients.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Patients");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onRendezClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        Rendez_VousController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rendez-Vous.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Rendez-Vous");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    public void onOrdonanceClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        OrdonanceController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ordonance.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Ordonnances");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    public void CloseBtn(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void onConsultationClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        ConsultationController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Consultations.fxml"));
        parent = loader.load();
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Consultations");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    public void Visibility(String role){
        boolean b1= true;
        boolean b2= false;
        if(role.equals("doctor")){
            b1 = false;b2 = true;
        }
        DoctorsBtn.setVisible(b1);
        ConsultationBtnAssis.setVisible(b1);
        patientbtn.setVisible(b1);
        rendezvousAssis.setVisible(b1);
        ordonnanceBtnAssis.setVisible(b1);
        ConsultationBtnDoc.setVisible(b2);
        rendezvousDoc.setVisible(b2);
        ordonnanceBtnDoc.setVisible(b2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] str = {"unknown","exist","absent"};
        stateDoc.getItems().addAll(str);
        stateDoc2.getItems().addAll(str);
        ArrayList<User> doc = new ArrayList<>();
        doc = Database.getAllDoctors();
        Visibility(user.getRole().toString());
        nomDoc1.setText(doc.get(0).getNom());
        nomDoc2.setText(doc.get(1).getNom());
        prenomDoc1.setText(doc.get(0).getPrenom());
        prenomDoc2.setText(doc.get(1).getPrenom());
        ageDoc1.setText(String.valueOf(doc.get(0).getAge()));
        ageDoc2.setText(String.valueOf(doc.get(1).getAge()));
        contactDoc1.setText(doc.get(0).getNumero());
        contactDoc2.setText(doc.get(1).getNumero());
        emailDoc.setText(doc.get(0).getEmail());
        emailDoc2.setText(doc.get(1).getEmail());
        stateDoc.setValue(doc.get(0).getState());
        stateDoc2.setValue(doc.get(1).getState());
    }

    public void onMouseOver(MouseEvent mouseEvent) {

    }
    public void onDoctorsClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Doctors.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Doctors ");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    public void changevalue1(ActionEvent actionEvent) {
        ArrayList<User> doc = new ArrayList<>();
        doc = Database.getAllDoctors();
        doc.get(0).setState(stateDoc.getValue());
        UserService.updateDoctor(doc.get(0));
    }

    public void chancedoc2(ActionEvent actionEvent) {
        ArrayList<User> doc = new ArrayList<>();
        doc = Database.getAllDoctors();
        doc.get(1).setState(stateDoc2.getValue());
        UserService.updateDoctor(doc.get(1));
    }
}
