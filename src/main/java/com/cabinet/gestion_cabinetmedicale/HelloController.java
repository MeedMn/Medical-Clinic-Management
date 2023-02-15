package com.cabinet.gestion_cabinetmedicale;


import Data.Database;
import Model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;


public class HelloController {
    public TextField usernameInput;
    public PasswordField passwordInput;
    public Label errorLabel;

    @FXML
    public void CloseBtn(MouseEvent mouseEvent) {
        Platform.exit();
    }
    @FXML
    public void LoginBtn(MouseEvent mouseEvent) throws IOException {

        try {
            User u = Database.Login(usernameInput.getText(),passwordInput.getText());
            if(u != null){
                String st = u.getNom().substring(0, 1).toUpperCase() + u.getNom().substring(1);
                DashboardController.UserTransfer(u);
                Stage stage;
                Parent parent;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                parent = loader.load();
                stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/icon.png"))));
                errorLabel.setVisible(false);
                stage.setTitle(" Medicated | Dashboard");
                stage.setScene(new Scene(parent));
                stage.show();
            }else{
                errorLabel.setVisible(true);
            }
        }catch (Exception error){
            error.printStackTrace();
        }
    }
}