package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Time;
import java.time.LocalDate;

public class RendezVous {
    private SimpleIntegerProperty id ;
    private SimpleIntegerProperty patient_id;
    private LocalDate date_rendezVous;
    private SimpleStringProperty state;
    private SimpleStringProperty time;
    private SimpleStringProperty cnePatient;
    public RendezVous(int patient_id, LocalDate date_rendezVous,String time) {
        this.patient_id = new SimpleIntegerProperty(patient_id);
        this.date_rendezVous = date_rendezVous;
        this.time=new SimpleStringProperty(time);
    }
    public RendezVous(int id,LocalDate date_rendezVous,String time,String state,int i) {
        this.id = new SimpleIntegerProperty(id);
        this.date_rendezVous = date_rendezVous;
        this.time=new SimpleStringProperty(time);
        this.state = new SimpleStringProperty(state);
    }
    public RendezVous(int patient_id, LocalDate date_rendezVous,String time,String state) {
        this.patient_id = new SimpleIntegerProperty(patient_id);
        this.date_rendezVous = date_rendezVous;
        this.time=new SimpleStringProperty(time);
        this.state = new SimpleStringProperty(state);
    }
    /*public RendezVous(int id,LocalDate date_rendezVous,String time,String state) {
        this.id = new SimpleIntegerProperty(id);
        this.date_rendezVous = date_rendezVous;
        this.time = new SimpleStringProperty(time);
        this.state = new SimpleStringProperty(state);
    }*/
    public RendezVous(int id,int patient_id, LocalDate date_rendezVous,String state) {
        this.id = new SimpleIntegerProperty(id);
        this.patient_id = new SimpleIntegerProperty(patient_id);
        this.date_rendezVous = date_rendezVous;
        this.state = new SimpleStringProperty(state);
    }
    public RendezVous(int id,String cnePatient, LocalDate date_rendezVous,String time,String state) {
        this.id = new SimpleIntegerProperty(id);
        this.cnePatient = new SimpleStringProperty(cnePatient);
        this.date_rendezVous = date_rendezVous;
        this.time = new SimpleStringProperty(time);
        this.state = new SimpleStringProperty(state);
    }
    public int getId() {
        return id.get();
    }


    public void setId(int id) {
        this.id.set(id);
    }


    public int getPatient_id() {
        return patient_id.get();
    }

    public void setPatient_id(int patient_id) {
        this.patient_id.set(patient_id);
    }

    public LocalDate getDate_rendezVous() {
        return date_rendezVous;
    }

    public void setDate_rendezVous(LocalDate date_rendezVous) {
        this.date_rendezVous = date_rendezVous;
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getTime() {
        return time.get();
    }


    public void setTime(String time) {
        this.time.set(time);
    }

    public String getCnePatient() {
        return cnePatient.get();
    }

    public SimpleStringProperty cnePatientProperty() {
        return cnePatient;
    }

    public void setCnePatient(String cnePatient) {
        this.cnePatient.set(cnePatient);
    }
}
