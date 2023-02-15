package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Ordonnance {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty id_patient;
    private SimpleStringProperty nom_doctor;
    private SimpleStringProperty cnePatient;
    private SimpleStringProperty content;
    private LocalDate date ;

    public Ordonnance(int id_patient, String nom_doctor, String content, LocalDate date){
        this.id_patient = new SimpleIntegerProperty(id_patient);
        this.nom_doctor = new SimpleStringProperty(nom_doctor);
        this.content = new SimpleStringProperty(content);
        this.date = date;
    }
    public Ordonnance(int id, int id_patient, String nom_doctor, String content, LocalDate date){
        this.id = new SimpleIntegerProperty(id);
        this.id_patient = new SimpleIntegerProperty(id_patient);
        this.nom_doctor = new SimpleStringProperty(nom_doctor);
        this.content = new SimpleStringProperty(content);
        this.date = date;
    }
    public Ordonnance(int id, String cnePatient, String nom_doctor, String content, LocalDate date){
        this.id = new SimpleIntegerProperty(id);
        this.cnePatient = new SimpleStringProperty(cnePatient);
        this.nom_doctor = new SimpleStringProperty(nom_doctor);
        this.content = new SimpleStringProperty(content);
        this.date = date;
    }
    public int getId() {
        return id.get();
    }


    public void setId(int id) {
        this.id.set(id);
    }

    public int getId_patient() {
        return id_patient.get();
    }



    public void setId_patient(int id_patient) {
        this.id_patient.set(id_patient);
    }

    public String getNom_doctor() {
        return nom_doctor.get();
    }



    public void setNom_doctor(String nom_doctor) {
        this.nom_doctor.set(nom_doctor);
    }

    public String getContent() {
        return content.get();
    }



    public void setContent(String content) {
        this.content.set(content);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCnePatient() {
        return cnePatient.get();
    }

    public void setCnePatient(String cnePatient) {
        this.cnePatient.set(cnePatient);
    }
}
