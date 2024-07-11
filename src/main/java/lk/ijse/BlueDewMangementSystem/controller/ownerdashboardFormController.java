package lk.ijse.BlueDewMangementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ownerdashboardFormController {

    @FXML
    private Button btnCUSTOMER;

    @FXML
    private Button btnEMPLOYEE;

    @FXML
    private Button btnEXIT;

    @FXML
    private Button btnMAKEBOOKING;

    @FXML
    private Button btnMEAL;

    @FXML
    private Button btnROOM;

    @FXML
    private Button btnSUPPLIER;

    @FXML
    private AnchorPane rootNode;

    @FXML
    void btnCUSTOMEROnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/CustomerForm.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnEMPLOYEEOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/EmployeeForm.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Employee Form");


    }

    @FXML
    void btnEXITOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");



    }

    @FXML
    void btnMAKEBOOKINGOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/ItemForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Make Booking Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnMEALOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/FishForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Meal Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnROOMOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/OrderForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Room Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnSUPPLIEROnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/SupplierForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Supplier Form");
        stage.centerOnScreen();


    }

}
