package Service;

import Data.Database;
import Model.Consultation;
public class ConsultationService {
    public static void AjouterConsultation(Consultation consultation){
        Database.insertConsultation(consultation);
    }
    public static void UpdateConsultation(Consultation consultation){
        Database.updateConsultation(consultation);
    }

}
