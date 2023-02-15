package Data;

import Model.*;
import com.cabinet.gestion_cabinetmedicale.DashboardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Database {

    public static Connection Connecte() {
        Connection con = null;
        try {
            if(con == null){
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_cabinet", "root", "");
            }else{
                return  con;
            }
            Statement statement = con.createStatement();
        } catch (Exception error) {
            System.out.println("Failed to connecte | start apache service and Mysql");
        }
        return con;
    }
    public static void insert(Patient patient){
        Connection connection = Database.Connecte();
        DatabaseMapping.fromPatientToDatabaseInfo(
                patient,
                "INSERT INTO patient (cne,nom,prenom,age,numero,date_naissance,sexe) VALUES (?,?,?,?,?,?,?)",
                DATABASE_EVENT.ADD
        );
    }
    public static User Login(String cne,String password) throws SQLException {
        Connection connection = Connecte();
        final User user = new User();
        try {
            String query = "SELECT * FROM user WHERE cne = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,cne);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setCne(rs.getString("cne"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setAge(rs.getInt("age"));
                user.setEmail(rs.getString("email"));
                user.setNumero(rs.getString("numero"));
                user.setRole(rs.getString("role"));
                user.setState(rs.getString("state"));
            }
            if(Objects.equals(cne,user.getCne()) && Objects.equals(password, user.getPassword())){
                return user;
            }
        }catch (Exception error){
            error.printStackTrace();
        }
        return null;
    }
    public static ArrayList<User> getAllDoctors(){
        Connection connection = Connecte();
        final ArrayList<User> doctors = new ArrayList<>();
        String query = "SELECT * FROM user WHERE role = 'doctor'";
        try {
            PreparedStatement prepare = connection.prepareStatement(query);
            ResultSet rs = prepare.executeQuery();
            while(rs.next()) {
                User user1 = new User();
                user1.setId(rs.getInt("id"));
                user1.setCne(rs.getString("cne"));
                user1.setPassword(rs.getString("password"));
                user1.setNom(rs.getString("nom"));
                user1.setPrenom(rs.getString("prenom"));
                user1.setAge(rs.getInt("age"));
                user1.setEmail(rs.getString("email"));
                user1.setNumero(rs.getString("numero"));
                user1.setRole(rs.getString("role"));
                user1.setState(rs.getString("state"));
                doctors.add(user1);
            }
        }catch(Exception e) {
            System.err.println("Error : "+e);
        }
        return doctors;
    }
    public static void updatePatient(Patient patient){
        Connection connection = Database.Connecte();
        DatabaseMapping.fromPatientToDatabaseInfo(
                patient,
                "UPDATE patient SET cne = ? , nom = ? , prenom = ? , age = ? , numero = ? , date_naissance = ? ,sexe = ? WHERE id = ?",
                DATABASE_EVENT.UPDATE
        );
    }
    public static ObservableList<Patient> select(){
        final ObservableList<Patient> patients = FXCollections.observableArrayList() ;
        Connection connection = Database.Connecte();
        String query = "SELECT * FROM patient";
        PreparedStatement prepare = null ;
        ResultSet result = null;
        try {
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();
            while(result.next()) {
                int id = result.getInt("id");
                String cne = result.getString("cne");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                int age = result.getInt("age");
                String numero = result.getString("numero");
                String sexe = result.getString("sexe");
                patients.add(new Patient(id,cne,nom,prenom, age,numero,result.getDate("date_naissance").toLocalDate(),sexe));
            }
        }catch(Exception e) {
            System.err.println("Error : "+e);
        }
        return patients;
    }

    public static void deletePatient(Patient patient){
        DatabaseMapping.fromPatientToDatabaseInfo(
                patient,
                "DELETE FROM patient WHERE id = ?",
                DATABASE_EVENT.DELETE
        );
    }
    public static void insertRendezVous(RendezVous rv){
        Connection connection = Database.Connecte();
        DatabaseMapping.FromRendezVousToDataBase(
                rv,
                "INSERT INTO rendezvous (id_patient,date_rendezvous,time_rendezvous,state) VALUES (?,?,?,?)",
                DATABASE_EVENT.ADD
        );
    }
    public static ObservableList<String> selectPatientName(){
        final ObservableList<String> patientsNames = FXCollections.observableArrayList() ;
        Connection connection = Database.Connecte();
        String query = "SELECT cne FROM patient ;";
        PreparedStatement prepare = null ;
        ResultSet result = null;
        try {
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();
            while(result.next()) {

                String cne = result.getString("cne");

                patientsNames.add(cne);
            }
        }catch(Exception e) {
            System.err.println("Error : "+e);
        }
        return patientsNames;
    }
    public static int checkifpatientexist(String namepatient){
        String query = "SELECT * FROM patient WHERE cne = ?";
        ResultSet rs = null;
        int db_id = -1;
        String db_cne = "";
        try {
            PreparedStatement preparedStatement = Connecte().prepareStatement(query,0);
            preparedStatement.setString(1,namepatient);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                db_cne = rs.getString("cne");
                System.err.println("db_cne : " + db_cne);
                if(Objects.equals(namepatient, db_cne)){
                    db_id = rs.getInt("id");
                    return db_id ;
                }
            }
        }catch (Exception err){
            err.printStackTrace();
        }
        return db_id;
    }

    public static ObservableList<RendezVous> getAllRdv(){
        String query = "SELECT r.id,p.cne,r.date_rendezvous,r.time_rendezvous,r.state From patient p JOIN rendezvous r on r.id_patient = p.id";
        int id;
        String cnePatient="",time="",state="";
        LocalDate localDate = null;
        final ObservableList<RendezVous> rv = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = Database.Connecte().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
                cnePatient = resultSet.getString("cne");
                localDate = resultSet.getDate("date_rendezvous").toLocalDate();
                time = resultSet.getString("time_rendezvous");
                state = resultSet.getString("state");
                rv.add(new RendezVous(id,cnePatient,localDate,time,state));
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rv;
    }
    public static void updateRendezVous(RendezVous rv){
        Connection connection = Database.Connecte();
        DatabaseMapping.FromRendezVousToDataBase(
                rv,
                "UPDATE rendezvous SET date_rendezvous  = ? , time_rendezvous = ? , state = ? WHERE id = ?",
                DATABASE_EVENT.UPDATE
        );
    }
    //------------------------------------------------- Doctor -------------------------------
    public static void updateDoctor(User user){
        Connection connection = Database.Connecte();
        DatabaseMapping.FromDoctorToDatabase(
                user,
                "UPDATE user SET state = ? WHERE id = ?",
                DATABASE_EVENT.UPDATE
        );
    }
    //------------------------------------------------- Consultation Section -------------------------------------------------
    public static void insertConsultation(Consultation consultation){
        Connection connection = Database.Connecte();
        DatabaseMapping.fromConsultationToDatabase(
                consultation,
                "INSERT INTO consultation (id_patient,date_consultation,time_consultation,state) VALUES (?,?,?,?)",
                DATABASE_EVENT.ADD
        );
    }
    public static ObservableList<Consultation> getAllConsultations(){
        String query = "SELECT c.id,p.cne,c.date_consultation,c.time_consultation,c.state From patient p JOIN consultation c on c.id_patient = p.id";
        int id;
        String cnePatient="",time="",state="";
        LocalDate localDate = null;
        final ObservableList<Consultation> cn = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = Database.Connecte().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
                cnePatient = resultSet.getString("cne");
                localDate = resultSet.getDate("date_consultation").toLocalDate();
                time = resultSet.getString("time_consultation");
                state = resultSet.getString("state");
                cn.add(new Consultation(id,cnePatient,localDate,time,state));
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return cn;
    }
    public static void updateConsultation(Consultation cs){
        Connection connection = Database.Connecte();
        DatabaseMapping.fromConsultationToDatabase(
                cs,
                "UPDATE consultation SET date_consultation  = ? , time_consultation = ? , state = ? WHERE id = ?",
                DATABASE_EVENT.UPDATE
        );
    }
    //------------------------------------------------- Ordonnance Section -------------------------------------------------

    public static void InsertOrdonance(Ordonnance ordonnance){
        DatabaseMapping.FromOrdonnanceToDatabse(
                ordonnance,
                "INSERT INTO ordonnance (id_patient,doctor,content,date) VALUES (?,?,?,?)",
                DATABASE_EVENT.ADD
        );
    }
    public static int getIdByCne(String cne){
        int id = 0;
        Connection connection = Connecte();
        try{
            PreparedStatement  preparedStatement= connection.prepareStatement("select id from patient where cne = ?");
            preparedStatement.setString(1,cne);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                id = resultSet.getInt("id");
                return id;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return id;
    }
    public static ObservableList<Ordonnance> GetOrdonnanceData(){
        final ObservableList<Ordonnance> ordonnances = FXCollections.observableArrayList();
        //select ord.id,p.cne,ord.doctor,ord.content,ord.date from ordonnance ord join patient p on p.id = ord.id_patient
        String query = "select ord.id , p.cne , ord.doctor , ord.content , ord.date from ordonnance ord join patient p on p.id = ord.id_patient";
        int id = 0;
        String cne = "";
        String doctor_name = "";
        String content = "";
        LocalDate date = null;
        Connection connection = Database.Connecte();
        PreparedStatement statement = null;
        try{

            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
                cne = resultSet.getString("cne");
                doctor_name = resultSet.getString("doctor");
                content = resultSet.getString("content");
                date = resultSet.getDate("date").toLocalDate();
                ordonnances.add(new Ordonnance(id,cne,doctor_name,content,date));
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return ordonnances;
    }
    public static Patient getPatientByCne(String cne){
        Connection connection = Connecte();
        Patient patient = new Patient(0,"a","a","a",0,"a",LocalDate.now(),"s");
        try{
            PreparedStatement  preparedStatement= connection.prepareStatement("select nom,prenom,age,sexe from patient where cne = ?");
            preparedStatement.setString(1,cne);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                patient.setCne(cne);
                patient.setNom(resultSet.getString("nom"));
                patient.setPrenom(resultSet.getString("prenom"));
                patient.setAge(resultSet.getInt("age"));
                patient.setSexe(resultSet.getString("sexe"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return patient;
    }
}
