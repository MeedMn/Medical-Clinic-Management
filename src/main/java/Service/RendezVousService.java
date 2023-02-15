package Service;

import Data.Database;
import Model.RendezVous;
import javafx.collections.ObservableList;

public class RendezVousService {
    public static void AjouterRendezVous(RendezVous rv){
        Database.insertRendezVous(rv);
    }
    public static ObservableList<String> getPatientNamesRendezVous(){
        return Database.selectPatientName();
    }
    public static int checkexistenceofpatient(String cne){
        return Database.checkifpatientexist(cne);
    }
    public static ObservableList<RendezVous> getAllRendez_vous(){
        return Database.getAllRdv();
    }
    public static void updateRdv(RendezVous rv){
        Database.updateRendezVous(rv);
    }

}
