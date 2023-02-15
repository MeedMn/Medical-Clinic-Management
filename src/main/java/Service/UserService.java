package Service;

import Data.Database;
import Model.RendezVous;
import Model.User;

public class UserService {
    public static void updateDoctor(User user){
        Database.updateDoctor(user);
    }
}
