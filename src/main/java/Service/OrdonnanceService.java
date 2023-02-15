package Service;

import Data.Database;
import Model.Ordonnance;
import Model.Patient;
import javafx.collections.ObservableList;

public class OrdonnanceService {
    public static void InsertOrdonnaceIntoDatabase(Ordonnance ord){
        Database.InsertOrdonance(ord);
    }
    public static int checkCneOfPatient(String cne){
        return Database.getIdByCne(cne);
    }
    public static ObservableList<Ordonnance> SelectAll(){
        return Database.GetOrdonnanceData();
    }
    public static Patient getPatientByCne(String cne){
        return Database.getPatientByCne(cne);
    }
}
