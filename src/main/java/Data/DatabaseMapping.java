package Data;

import Model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class DatabaseMapping {
    public static void FromOrdonnanceToDatabse(Ordonnance ord , String query, DATABASE_EVENT event){
        switch(event){
            case ADD -> {
                //query = INSERT INTO ordonnance (id_patient,nom_doctor,content,date) VALUES (?,?,?,?);
                Connection connection = Database.Connecte();
                PreparedStatement statement = null;
                try{
                    statement = connection.prepareStatement(query);
                    statement.setInt(1,ord.getId_patient());
                    statement.setString(2,ord.getNom_doctor());
                    statement.setString(3,ord.getContent());
                    statement.setDate(4, Date.valueOf(ord.getDate()));
                    int verify = statement.executeUpdate();
                    if(verify > 0){System.err.println("Ordonnance Inserted");}
                    else{System.err.println("Ordonnance Not Inserted");}
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void fromConsultationToDatabase(Consultation consultation,String query,DATABASE_EVENT event){
        switch (event){
            case ADD -> {
                try{
                    //"INSERT INTO consultation (idp,date,time,state) VALUES (?,?,?)"
                    PreparedStatement preparedStatement = Database.Connecte().prepareStatement(query);
                    preparedStatement.setInt(1,consultation.getPatient_id());
                    preparedStatement.setDate(2,Date.valueOf(consultation.getDate_Consultation()));
                    preparedStatement.setString(3,consultation.getTime());
                    preparedStatement.setString(4,consultation.getState());
                    if(preparedStatement.executeUpdate() > 0){
                        System.err.println("CONSULTATION INSERTED TO DATABASE");
                    }
                }catch (Exception err){err.printStackTrace();}
            }
            case UPDATE -> {
                try {
                    //UPDATE `rendezvous` SET `date_rendezvous`= ?',`state` ? WHERE id = ?
                    PreparedStatement preparedStatement = Database.Connecte().prepareStatement(query);
                    preparedStatement.setDate(1, Date.valueOf(consultation.getDate_Consultation()));
                    preparedStatement.setString(2,consultation.getTime());
                    preparedStatement.setString(3,consultation.getState());
                    preparedStatement.setInt(4,consultation.getId());
                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        System.err.println("CONSULTATION UPDATED");
                    }
                }catch(Exception err){
                    err.printStackTrace();
                }
            }
        }
    }
    public static void fromPatientToDatabaseInfo(Patient patient, String query, DATABASE_EVENT event) {
        switch (event) {
            case ADD -> {
                try {
                    PreparedStatement prepared = Database.Connecte().prepareStatement(query, 0);
                    prepared.setString(1, patient.getCne());
                    prepared.setString(2, patient.getNom());
                    prepared.setString(3, patient.getPrenom());
                    prepared.setInt(4, patient.getAge());
                    prepared.setString(5, patient.getNumero());
                    prepared.setDate(6, Date.valueOf(patient.getDate_naissance()));
                    prepared.setString(7, patient.getSexe());
                    int result = prepared.executeUpdate();
                    if (result == 1) {
                        System.out.println("done");
                    }
                } catch (Exception error) {
                    error.printStackTrace();
                }
            }
            case UPDATE -> {
                try {
                    PreparedStatement preparedStatement = Database.Connecte().prepareStatement(query);
                    preparedStatement.setString(1, patient.getCne());
                    preparedStatement.setString(2, patient.getNom());
                    preparedStatement.setString(3, patient.getPrenom());
                    preparedStatement.setInt(4, patient.getAge());
                    preparedStatement.setString(5, patient.getNumero());
                    preparedStatement.setDate(6, Date.valueOf(patient.getDate_naissance()));
                    preparedStatement.setString(7, patient.getSexe());
                    preparedStatement.setInt(8, patient.getId());
                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        System.out.println("Updated successfully");
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                }

            }
            case DELETE -> {
                try{
                    PreparedStatement preparedStatement = Database.Connecte().prepareStatement(query);
                    preparedStatement.setInt(1,patient.getId());
                    if(preparedStatement.executeUpdate()>0){
                        System.out.println("Deleted successfully");
                    }
                }catch(Exception err){
                    err.printStackTrace();
                }
            }
        }
    }
    public static void FromRendezVousToDataBase(RendezVous rv,String query,DATABASE_EVENT event){
        switch (event){
            case ADD -> {
                try{
                    //"INSERT INTO rendezvous (idp,date,time,state) VALUES (?,?,?)"
                    PreparedStatement preparedStatement = Database.Connecte().prepareStatement(query);
                    preparedStatement.setInt(1,rv.getPatient_id());
                    preparedStatement.setDate(2,Date.valueOf(rv.getDate_rendezVous()));
                    preparedStatement.setString(3,rv.getTime());
                    preparedStatement.setString(4,rv.getState());
                    if(preparedStatement.executeUpdate() > 0){
                        System.err.println("RENDEZ VOUS INSERTED TO DATABASE");
                    }
                }catch (Exception err){err.printStackTrace();}
            }
            case UPDATE -> {
                try {
                    //UPDATE `rendezvous` SET `date_rendezvous`= ?',`state` ? WHERE id = ?
                    PreparedStatement preparedStatement = Database.Connecte().prepareStatement(query);
                    preparedStatement.setDate(1, Date.valueOf(rv.getDate_rendezVous()));
                    preparedStatement.setString(2,rv.getTime());
                    preparedStatement.setString(3,rv.getState());
                    preparedStatement.setInt(4,rv.getId());
                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        System.err.println("RENDEZ VOUS UPDATED");
                    }
                }catch(Exception err){
                    err.printStackTrace();
                }

            }

        }
    }
    public static void FromDoctorToDatabase(User us,String query,DATABASE_EVENT event){
        switch (event){
            case UPDATE -> {
                try {
                    //UPDATE `user` SET `state`= ?' WHERE id = ?
                    PreparedStatement preparedStatement = Database.Connecte().prepareStatement(query);
                    preparedStatement.setString(1, us.getState());
                    preparedStatement.setInt(2,us.getId());
                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        System.err.println("DOCTOR UPDATED");
                    }
                }catch(Exception err){
                    err.printStackTrace();
                }
                }
            }
        }
}
