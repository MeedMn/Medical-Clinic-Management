package Service;

import Data.Database;
import Model.Patient;
import javafx.collections.ObservableList;

public class PatientService {
    public static void AjouterPatient(Patient patient){
        Database.insert(patient);
    }
    public static ObservableList<Patient> SelectData(){
        return Database.select();
    }
    public static void UpdatePatient(Patient patient){
        Database.updatePatient(patient);
    }
    public static void Deletepatient(Patient patient){
        Database.deletePatient(patient);
    }
}
