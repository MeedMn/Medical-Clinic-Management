package com.cabinet.gestion_cabinetmedicale;

import Model.Patient;
import Model.RendezVous;
import Model.User;
import Service.RendezVousService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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

public class Rendez_VousController implements Initializable {

    public TableView<RendezVous> rendez_vousTable;

    public TextField searchBar;
    public StackPane RendezVous1;

    public static User user;
    public ButtonBar patientbtn;
    public ButtonBar rendezvousAssis;
    public ButtonBar ordonnanceBtnAssis;
    public ButtonBar ConsultationBtnAssis;
    public ButtonBar DoctorsBtn;
    public ButtonBar rendezvousDoc;
    public ButtonBar ordonnanceBtnDoc;
    public ButtonBar ConsultationBtnDoc;
    public Button AjouterRendezVous;
    public TableColumn<RendezVous,Integer> idCol;
    public TableColumn<RendezVous,String> cneCol;
    public TableColumn<RendezVous, LocalDate> dateCol;
    public TableColumn<RendezVous,String> timeCol;
    public TableColumn<RendezVous,String> stateCol;
    public TableColumn actionCol;
    private ObservableList<RendezVous> rendezVousObservableList;

    public static void UserTransfer(User u){
        user = u;
    }

    public void onMouseOver(MouseEvent mouseEvent) {
        RendezVous1.setEffect(null);
        rendez_vousTable.setItems(rendezVousObservableList= RendezVousService.getAllRendez_vous());
    }

    public void onAccueilClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        DashboardController.UserTransfer(user);
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

    public void CloseBtn(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void searchiWriting(KeyEvent keyEvent) {
        rendez_vousTable.getItems().stream().filter(item -> item.getCnePatient().equals(searchBar.getText())).findAny().ifPresent(item -> {rendez_vousTable.getSelectionModel().select(item);rendez_vousTable.scrollTo(item);});
        if(searchBar.getText().isEmpty() ){
            rendez_vousTable.getSelectionModel().clearSelection();
        }
    }

    public void addRendezVous(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddRendezVousForm.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(" Medicated | Ajouter Rendez-Vous");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        RendezVous1.setEffect(new BoxBlur());
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

    public void onRendezClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rendez-Vous.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Rendez-Vous");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Visibility(user.getRole());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cneCol.setCellValueFactory(new PropertyValueFactory<>("cnePatient"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date_rendezVous"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        Callback<TableColumn<RendezVous,String>, TableCell<RendezVous,String>> cellCallback = rendezVousStringTableColumn -> {
            return new TableCell<RendezVous,String>(){
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if(empty) { setGraphic(null);setText(null);}
                    else{
                        final Button editbtn = new Button("Edit");
                        editbtn.setPrefWidth(120);
                        editbtn.setStyle("-fx-background-color: green; -fx-border-color: transparent; -fx-text-fill: white; -fx-cursor: Hand;");
                        RendezVous rv = getTableView().getItems().get(getIndex());
                        editbtn.setOnAction(actionEvent -> {
                            try {
                                RendezVous r = getTableView().getItems().get(getIndex());
                                AddRendezVousFormController rvs = new AddRendezVousFormController();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyRendezVousForm.fxml"));
                                Parent parent = loader.load();
                                Stage stage = new Stage();
                                Scene scene = new Scene(parent);
                                stage.setScene(scene);
                                stage.setTitle("Update Form");
                                stage.show();
                                ModifyRendezVousController updateFormController = loader.getController();
                                updateFormController.setDataOfInputs(r);
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }


                        });
                        HBox managebtns = new HBox(editbtn);
                        managebtns.setStyle("-fx-alignment: center");
                        setGraphic(managebtns);
                        setText(null);
                    };
                }
            };
        };
        actionCol.setCellFactory(cellCallback);
        rendez_vousTable.setItems(rendezVousObservableList= RendezVousService.getAllRendez_vous());
    }
    public void Visibility(String role){
        boolean b1= true;
        boolean b2= false;
        if(role.equals("doctor")){
            b1 = false;b2 = true;
        }
        AjouterRendezVous.setVisible(b1);
        DoctorsBtn.setVisible(b1);
        ConsultationBtnAssis.setVisible(b1);
        patientbtn.setVisible(b1);
        rendezvousAssis.setVisible(b1);
        ordonnanceBtnAssis.setVisible(b1);
        ConsultationBtnDoc.setVisible(b2);
        rendezvousDoc.setVisible(b2);
        ordonnanceBtnDoc.setVisible(b2);
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
