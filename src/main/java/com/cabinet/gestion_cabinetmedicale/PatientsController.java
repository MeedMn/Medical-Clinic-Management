package com.cabinet.gestion_cabinetmedicale;

import Model.Patient;
import Model.User;
import Service.PatientService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import javafx.util.Callback;

public class PatientsController implements Initializable {
    @FXML public TableView<Patient> patientsTable;
    @FXML public BorderPane TableHolder;
    @FXML public TableColumn<Patient,Integer> idCol;
    @FXML public TableColumn<Patient,String> nomCol;
    @FXML public TableColumn<Patient,String> prenomCol;
    @FXML public TableColumn<Patient,String> cneCol;
    @FXML public TableColumn<Patient,Integer> ageCol;
    @FXML public TableColumn<Patient,String> telCol;
    @FXML public TableColumn<Patient, LocalDate> naissanceCol;
    @FXML public TableColumn<Patient, String> actionsCol;
    @FXML public TableColumn<Patient,String> sexeCol;
    @FXML public StackPane Patients1;
    public static ObservableList<Patient> patients;
    public TextField searchBar;


    public static User user;
    public static void UserTransfer(User u){
        user = u;
    }
    @FXML
    public void CloseBtn() {
        Platform.exit();
    }
    @FXML
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

    public void onMouseOver() {
        Patients1.setEffect(null);
        patientsTable.setItems(patients = PatientService.SelectData());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexeCol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        cneCol.setCellValueFactory(new PropertyValueFactory<>("cne"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        naissanceCol.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        Callback<TableColumn<Patient,String>,TableCell<Patient,String>> cellCallback = patientStringTableColumn -> {
            return new TableCell<Patient, String>() {
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        final Button Edit = new Button("Edit");
                        Edit.setStyle("-fx-background-color: green; -fx-border-color: transparent; -fx-text-fill: white; -fx-cursor: Hand;");
                        final Button Delete = new Button("Delete");
                        Delete.setStyle("-fx-background-color: red; -fx-border-color: transparent; -fx-text-fill: white; -fx-cursor: Hand;");
                        final Button Show = new Button("Show");
                        Show.setStyle("-fx-background-color: black; -fx-border-color: transparent; -fx-text-fill: white; -fx-cursor: Hand;");
                        Edit.setPrefWidth(60);
                        Delete.setPrefWidth(60);
                        Show.setPrefWidth(60);
                        Edit.setOnMouseClicked(mouseEvent -> {
                            Patients1.setEffect(new BoxBlur());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPatientForm.fxml"));
                                Parent parent = loader.load();
                                Stage stage = new Stage();
                                Scene scene = new Scene(parent);
                                stage.setScene(scene);
                                stage.setTitle(" Medicated | Modifier Patient");
                                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
                                stage.initStyle(StageStyle.UNDECORATED);
                                stage.show();
                                Patient patient = getTableView().getItems().get(getIndex());
                                ModifyPatientFormController updateFormController = loader.getController();
                                updateFormController.setDataOfInputs(patient);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        });
                        Delete.setOnMouseClicked(mouseEvent -> {
                            Patient patient = getTableView().getItems().get(getIndex());
                            PatientService.Deletepatient(patient);
                            patients.remove(patient);
                        });
                        Show.setOnMouseClicked(mouseEvent -> {
                            Patients1.setEffect(new BoxBlur());
                            Patient patient = getTableView().getItems().get(getIndex());
                            ShowDataPatientController.setPatient(patient);
                            try {
                                Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showDataPatient.fxml")));
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setTitle(" Medicated | Affichage Patient");
                                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
                                stage.setScene(scene);
                                stage.initStyle(StageStyle.UNDECORATED);
                                stage.show();
                            }catch(Exception err){
                                err.printStackTrace();
                            }
                        });
                        HBox managebtns = new HBox(Edit,Delete,Show);
                        managebtns.setStyle("-fx-alignment: center");
                        HBox.setMargin(Edit,new Insets(2,2,0,3));
                        HBox.setMargin(Delete,new Insets(2,3,0,2));
                        HBox.setMargin(Show,new Insets(2,4,0,4));
                        setGraphic(managebtns);setText(null);
                    }

                }
            };
        };
        actionsCol.setCellFactory(cellCallback);
        patientsTable.setItems(patients = PatientService.SelectData());
    }
    public void addPatients(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPatientsForm.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(" Medicated | Add Patient");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        Patients1.setEffect(new BoxBlur());
        stage.show();

    }
    public void searchiWriting(KeyEvent keyEvent) {
        patientsTable.getItems().stream().filter(item -> item.getCne().equals(searchBar.getText())).findAny().ifPresent(item -> {patientsTable.getSelectionModel().select(item);patientsTable.scrollTo(item);});
        if(searchBar.getText().isEmpty() ){
            patientsTable.getSelectionModel().clearSelection();
        }
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

    public void onPatientsClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Patients.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Patients");
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
}
