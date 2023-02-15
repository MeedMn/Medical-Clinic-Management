package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Patient {
    private SimpleIntegerProperty id;
    private SimpleStringProperty cne;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleIntegerProperty age ;
    private SimpleStringProperty numero;
    private LocalDate date_naissance;
    private SimpleStringProperty sexe;
    public Patient(){}

    public Patient(String cne,String nom,String prenom,int age,String numero,LocalDate date_naissance,String sexe) {
        this.cne = new SimpleStringProperty(cne);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.age = new SimpleIntegerProperty(age);
        this.numero = new SimpleStringProperty(numero);
        this.date_naissance = date_naissance;
        this.sexe = new SimpleStringProperty(sexe);
    }
    public Patient(Integer id,String cne,String nom,String prenom,int age,String numero,LocalDate date_naissance,String sexe) {
        this.id = new SimpleIntegerProperty(id);
        this.cne = new SimpleStringProperty(cne);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.age = new SimpleIntegerProperty(age);
        this.numero = new SimpleStringProperty(numero);
        this.date_naissance = date_naissance;
        this.sexe = new SimpleStringProperty(sexe);
    }


    public int getId() {
        return id.get();
    }


    public void setId(int id) {
        this.id.set(id);
    }

    public String getCne() {
        return cne.get();
    }



    public void setCne(String cne) {
        this.cne.set(cne);
    }

    public String getNom() {
        return nom.get();
    }



    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }



    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public int getAge() {
        return age.get();
    }



    public void setAge(int age) {
        this.age.set(age);
    }

    public String getNumero() {
        return numero.get();
    }



    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getSexe() {
        return sexe.get();
    }

    public void setSexe(String sexe) {
        this.sexe.set(sexe);
    }
}
