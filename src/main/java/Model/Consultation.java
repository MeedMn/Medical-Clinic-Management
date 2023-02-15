package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Consultation {
    private SimpleIntegerProperty id ;
    private SimpleIntegerProperty patient_id;
    private LocalDate date_Consultation;
    private SimpleStringProperty state;
    private SimpleStringProperty time;
    private SimpleStringProperty cnePatient;

    public Consultation(){}
    public Consultation(int id, int patient_id, LocalDate date_Consultation, String state, String time, String cnePatient) {
        this.id = new SimpleIntegerProperty(id);
        this.patient_id = new SimpleIntegerProperty(patient_id);
        this.date_Consultation = date_Consultation;
        this.state = new SimpleStringProperty(state);
        this.time = new SimpleStringProperty(time);
        this.cnePatient = new SimpleStringProperty(cnePatient);
    }
    public Consultation(int patient_id, LocalDate date_Consultation, String state, String time, String cnePatient) {
        this.patient_id = new SimpleIntegerProperty(patient_id);
        this.date_Consultation = date_Consultation;
        this.state = new SimpleStringProperty(state);
        this.time = new SimpleStringProperty(time);
        this.cnePatient = new SimpleStringProperty(cnePatient);
    }
    public Consultation(int id,String cne,LocalDate date_Consultation,String time,String state){
        this.id = new SimpleIntegerProperty(id);
        this.date_Consultation = date_Consultation;
        this.state = new SimpleStringProperty(state);
        this.time = new SimpleStringProperty(time);
        this.cnePatient = new SimpleStringProperty(cne);
    }
    public Consultation(int id,LocalDate date_Consultation,String time,String state,int i) {
        this.id = new SimpleIntegerProperty(id);
        this.date_Consultation = date_Consultation;
        this.time=new SimpleStringProperty(time);
        this.state = new SimpleStringProperty(state);
    }
    public Consultation(int patient_id, LocalDate date_Consultation, String time,String state) {
        this.patient_id = new SimpleIntegerProperty(patient_id);
        this.date_Consultation = date_Consultation;
        this.state = new SimpleStringProperty(state);
        this.time = new SimpleStringProperty(time);
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

    public LocalDate getDate_Consultation() {
        return date_Consultation;
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

    public void setCnePatient(String cnePatient) {
        this.cnePatient.set(cnePatient);
    }
}
