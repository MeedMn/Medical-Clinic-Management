package com.cabinet.gestion_cabinetmedicale;

import Data.Database;
import Model.User;
import Service.PatientService;
import Service.RendezVousService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {


    public BorderPane grp;
    public Label trythisshit;
    public Label nameOfUser;
    public Label nameOfUser1;
    public ButtonBar patientbtn;
    public ButtonBar rendezvousAssis;
    public ButtonBar ordonnanceBtnAssis;
    public ButtonBar ConsultationBtnAssis;
    public ButtonBar DoctorsBtn;
    public ButtonBar rendezvousDoc;
    public ButtonBar ordonnanceBtnDoc;
    public ButtonBar ConsultationBtnDoc;

    public static User user;
    public Label nbrPatients;
    public Label nbrRendezVous;

    public static void UserTransfer(User u){
        user = u;
    }

    @FXML
    public void CloseBtn() {
        Platform.exit();
    }
    @FXML
    public void onMouseOver() {
        nbrPatients.setText(String.valueOf(PatientService.SelectData().size()));
        nbrRendezVous.setText(String.valueOf(RendezVousService.getAllRendez_vous().size()));
    }
    @FXML
    public void onPatientsClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        PatientsController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Patients.fxml"));
        parent = loader.load();
        PatientsController patientsController = loader.getController();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Patients");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate t = LocalDate.now();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Days");
        NumberAxis yAxis = new NumberAxis();
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        series.setName("No of Consultaions in the last 4 days");
        for(int i = 4;i>=0;i--){
            int finalI = i;
            int nbrPat = Database.getAllConsultations().filtered(item-> item.getDate_Consultation().equals(t.minusDays(finalI))).size();
            series.getData().add(new XYChart.Data<>(t.minusDays(i).toString(),nbrPat));
        }


        LineChart linechart = new LineChart(xAxis,yAxis);
        linechart.getData().add(series);

        grp.setCenter(linechart);
        Visibility(user.getRole());
        String st = user.getNom().substring(0, 1).toUpperCase() + user.getNom().substring(1);
        String calling="Mme. ";
        if(user.getRole().equals("doctor"))
            calling = "Dr. ";
        nameOfUser.setText(calling+st);
        nameOfUser1.setText(calling+st);
        nbrPatients.setText(String.valueOf(PatientService.SelectData().size()));
        nbrRendezVous.setText(String.valueOf(RendezVousService.getAllRendez_vous().size()));
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

    public void onConsultationClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        ConsultationController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Consultations.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Consultations");
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
    public void onAccueilClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        parent = loader.load();
        Rendez_VousController.UserTransfer(user);
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Dashboard");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onDoctorsClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        DoctorsController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Doctors.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Doctors ");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onDisconnect(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Login ");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
