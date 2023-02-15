package com.cabinet.gestion_cabinetmedicale;

import Data.Database;
import Model.Consultation;
import Model.RendezVous;
import Model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {
    public TableView<Consultation> ConsultationsTable;
    public TextField searchBar;
    public StackPane Consultation1;


    public static User user;
    public ButtonBar patientbtn;
    public ButtonBar rendezvousAssis;
    public ButtonBar ordonnanceBtnAssis;
    public ButtonBar ConsultationBtnAssis;
    public ButtonBar DoctorsBtn;
    public ButtonBar rendezvousDoc;
    public ButtonBar ordonnanceBtnDoc;
    public ButtonBar ConsultationBtnDoc;
    public Button AjouterConsultations;
    public TableColumn<Consultation,Integer> idCol;
    public TableColumn<Consultation,String> cneCol;
    public TableColumn<Consultation, LocalDate> dateCol;
    public TableColumn<Consultation,String> timeCol;
    public TableColumn stateCol;
    private ObservableList<Consultation> consultationObservableList;

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

    public void searchiWriting(KeyEvent keyEvent) {
        ConsultationsTable.getItems().stream().filter(item -> item.getCnePatient().equals(searchBar.getText())).findAny().ifPresent(item -> {ConsultationsTable.getSelectionModel().select(item);ConsultationsTable.scrollTo(item);});
        if(searchBar.getText().isEmpty() ){
            ConsultationsTable.getSelectionModel().clearSelection();
        }
    }

    public void addConsultation(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddConsultation.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(" Medicated | Ajouter Consultation");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void onMouseOver(MouseEvent mouseEvent) {
        getConsultationsOfDay();
    }

    public void onConsultationClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
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
        AjouterConsultations.setVisible(false);
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
        Visibility(user.getRole());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cneCol.setCellValueFactory(new PropertyValueFactory<>("cnePatient"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date_Consultation"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        Callback<TableColumn<Consultation,String>, TableCell<Consultation,String>> cellCallback = consultationStringTableColumn -> {
            return new TableCell<Consultation,String>(){
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if(empty) { setGraphic(null);setText(null);}
                    else{
                        final ComboBox<String> stateField = new ComboBox<>();
                        stateField.setPrefWidth(120);
                        stateField.getItems().add("Not yet");
                        stateField.getItems().add("Done");
                        stateField.getItems().add("Cancelled");
                        Consultation consultation = getTableView().getItems().get(getIndex());
                        stateField.setOnAction(e->{
                            Consultation c = getTableView().getItems().get(getIndex());
                            c.setState(stateField.getValue());
                            Database.updateConsultation(c);
                        });
                        stateField.setValue(consultation.getState());
                        HBox manageCombo = new HBox(stateField);
                        manageCombo.setStyle("-fx-alignment: center");
                        setGraphic(manageCombo);
                        setText(null);
                    };
                }
            };
        };
        stateCol.setCellFactory(cellCallback);
        getConsultationsOfDay();
        ConsultationsTable.setItems(consultationObservableList);
    }
    public void getConsultationsOfDay(){
        consultationObservableList = Database.getAllConsultations();
        consultationObservableList = consultationObservableList.filtered(item-> item.getDate_Consultation().equals(LocalDate.now()));
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
