package com.cabinet.gestion_cabinetmedicale;

import Model.Ordonnance;
import Model.Patient;
import Model.User;
import Service.OrdonnanceService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;


public class OrdonanceController implements Initializable {

    public TableView<Ordonnance> OrdonanceTable;
    public TextField searchBar;


    public static User user;
    public ButtonBar patientbtn;
    public ButtonBar rendezvousAssis;
    public ButtonBar ordonnanceBtnAssis;
    public ButtonBar ConsultationBtnAssis;
    public ButtonBar DoctorsBtn;
    public ButtonBar rendezvousDoc;
    public ButtonBar ordonnanceBtnDoc;
    public ButtonBar ConsultationBtnDoc;
    public Button ajouterOrdonnance;
    public TableColumn<Ordonnance,Integer> idord;
    public TableColumn<Ordonnance,String>  cnePatOrd;
    public TableColumn<Ordonnance,String>  doctorNomOrd;
    public TableColumn<Ordonnance,String>  contentOrd;
    public TableColumn<Ordonnance, LocalDate>  DateOrd;
    public TableColumn actionOrd;
    public StackPane Ordonance1;

    public static void UserTransfer(User u){
        user = u;
    }
    public void onAccueilClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        DashboardController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
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
        stage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
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
        stage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Rendez-Vous");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void CloseBtn(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }

    public void searchiWriting() {
        OrdonanceTable.getItems().stream().filter(item -> item.getCnePatient().equals(searchBar.getText())).findAny().ifPresent(item -> {OrdonanceTable.getSelectionModel().select(item);OrdonanceTable.scrollTo(item);});
        if(searchBar.getText().isEmpty() ){
            OrdonanceTable.getSelectionModel().clearSelection();
        }
    }

    public void onConsultationClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        ConsultationController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Consultations.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Consultations");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void addOrdonance() throws IOException {
        AddOrdonanceFormController.UserTransfer(user);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddOrdonanceForm.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(" Medicated | Add Ordonnance");
        stage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        Ordonance1.setEffect(new BoxBlur());
        stage.show();
    }

    public void onMouseOver() {
        Ordonance1.setEffect(null);
        OrdonanceTable.setItems(OrdonnanceService.SelectAll());
    }

    public void onOrdonanceClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ordonance.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Ordonnances");
        stage.setScene(new Scene(parent));
        stage.show();
    }
    public void Visibility(String role){
        boolean b1= true;
        boolean b2= false;
        if(role.equals("doctor")){
            b1 = false;b2 = true;
        }
        ajouterOrdonnance.setVisible(b2);
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
        idord.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnePatOrd.setCellValueFactory(new PropertyValueFactory<>("cnePatient"));
        doctorNomOrd.setCellValueFactory(new PropertyValueFactory<>("nom_doctor"));
        contentOrd.setCellValueFactory(new PropertyValueFactory<>("content"));
        DateOrd.setCellValueFactory(new PropertyValueFactory<>("date"));
        Callback<TableColumn<Ordonnance,String>, TableCell<Ordonnance,String>> cellCallback = ordonnanceStringTableColumn -> {
            return new TableCell<Ordonnance,String>(){
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty){setGraphic(null);
                    }
                    else{
                        final Button printBtn = new Button("Print");
                        printBtn.setPrefWidth(120);
                        printBtn.setStyle("-fx-background-color: #d3d3d3;");
                        Ordonnance ord = getTableView().getItems().get(getIndex());
                        Patient patient = OrdonnanceService.getPatientByCne(ord.getCnePatient());
                        printBtn.setOnAction(actionEvent -> {
                            String dest = "C:/Users/menfa/Desktop//test/Ordonnance_"+patient.getCne()+".pdf";
                                    try {
                                        Document document = new Document();
                                        PdfWriter.getInstance(document, new FileOutputStream(dest));
                                        document.open();
                                        String pth = "C:/Users/menfa/IdeaProjects/Gestion_CabinetMedicale/src/main/resources/media/";


                                        // Adding logo
                                        Image logo = Image.getInstance(pth+"Pharmacy.png");
                                        logo.setAlignment(Image.ALIGN_CENTER);
                                        logo.scaleToFit(60, 60);
                                        document.add(logo);
                                        Paragraph Infos = new Paragraph();
                                        Infos.setTabSettings(new TabSettings(350));
                                        Infos.add(new Chunk("M e d i c a t e d"));
                                        Infos.add(Chunk.TABBING);
                                        Infos.add(new Chunk("Dr. "+ord.getNom_doctor()));
                                        Infos.add("\n");
                                        Infos.add(new Chunk("Addresse : Marrakech"));
                                        Infos.add(Chunk.TABBING);
                                        Infos.add(new Chunk("Tel : "+user.getNumero()));
                                        Infos.add("\n");
                                        Infos.add(new Chunk("Fax : 05 21 32 65 95"));
                                        Infos.add(Chunk.TABBING);
                                        Infos.add(new Chunk("Email : "+user.getEmail()));
                                        Infos.add("\n");
                                        Infos.add(new Chunk("www.medicated.com"));
                                        Infos.setAlignment(Paragraph.ALIGN_LEFT);
                                        Infos.setAlignment(Paragraph.ALIGN_TOP);
                                        document.add(Infos);

                                        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 36, Font.BOLD);
                                        Paragraph title = new Paragraph("Doctor Prescription\n", titleFont);
                                        title.setAlignment(Paragraph.ALIGN_CENTER);
                                        document.add(title);

                                        // Adding patient information
                                        Font patientFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                                        Paragraph patientName = new Paragraph("Patient Nom et Prenom : "+patient.getNom()+" "+patient.getPrenom(), patientFont);
                                        document.add(patientName);
                                        document.add(new Paragraph("Age : "+patient.getAge()));
                                        document.add(new Paragraph("Sexe : "+patient.getSexe()));

                                        // Adding diagnosis
                                        Font diagnosisFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                                        Paragraph diagnosis = new Paragraph("Prescription", diagnosisFont);
                                        document.add(diagnosis);

                                        PdfPTable table = new PdfPTable(1); // 3 columns.
                                        table.setWidthPercentage(100); // Width 100%
                                        table.setSpacingBefore(10f); // Space before table
                                        table.setSpacingAfter(10f); // Space after table

                                        // Set Column widths
                                        float[] columnWidths = {6f};
                                        table.setWidths(columnWidths);


                                        PdfPCell cell1 = new PdfPCell(new Phrase("Medicaments"));
                                        cell1.setBorderColor(BaseColor.BLACK);
                                        cell1.setPaddingLeft(10);
                                        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        table.addCell(cell1);


                                        table.addCell(ord.getContent());
                                        document.add(table);
                                        document.add(new Paragraph("Date Ordonnance : "+ord.getDate()));

                                        // Adding doctor signature
                                        Image signature = Image.getInstance(pth+"Signature.png");
                                        signature.setAlignment(Image.ALIGN_RIGHT);
                                        signature.scaleToFit(100, 100);
                                        document.add(signature);

                                        // Closing the document
                                        document.close();
                                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                                        a.setTitle("PDF");
                                        a.setContentText("");
                                        a.setHeaderText("You Pdf is generated successfully");
                                        a.show();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                            System.out.println("PDF Created");
                        });
                        HBox managebtns = new HBox(printBtn);
                        managebtns.setStyle("-fx-alignment: center");
                        setGraphic(managebtns);
                    }
                    setText(null);
                }
            };
        };
        actionOrd.setCellFactory(cellCallback);
        OrdonanceTable.setItems(OrdonnanceService.SelectAll());
    }

    public void onDoctorsClick(MouseEvent mouseEvent) throws IOException {
        Stage stage;
        Parent parent;
        DoctorsController.UserTransfer(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Doctors.fxml"));
        parent = loader.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
        stage.setTitle(" Medicated | Doctors ");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
