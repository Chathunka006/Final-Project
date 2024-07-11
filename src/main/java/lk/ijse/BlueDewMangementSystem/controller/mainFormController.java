package lk.ijse.BlueDewMangementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class mainFormController {

    @FXML
    private AnchorPane anpMAIN1;

    @FXML
    private AnchorPane anpMAIN2;

    @FXML
    private AnchorPane anpMAIN3;

    @FXML
    private Button btnCUSTOMER;

    @FXML
    private Button btnDASHBOARD;

    @FXML
    private Button btnEMPLOYEE;

    @FXML
    private Button btnLOGOUT;

    @FXML
    private Button btnMEAL;

    @FXML
    private Button btnPAYMENT;

    @FXML
    private Button btnRESERVATION;

    @FXML
    private Button btnROOM;

    @FXML
    private Button btnSUPPLIER;

    public void initialize() throws IOException {
        loadDashboardForm();
    }

    private void loadDashboardForm() throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(this.getClass().getResource("/view/OwnerDashboardForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(dashboardPane);
    }

    @FXML
    void btnCUSTOMEROnAction(ActionEvent event) throws IOException {
        AnchorPane customerPane = FXMLLoader.load(this.getClass().getResource("/view/CustomerForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(customerPane);


    }

    @FXML
    void btnDASHBOARDOnAction(ActionEvent event) throws IOException {
        AnchorPane dashbordPane = FXMLLoader.load(this.getClass().getResource("/view/OwnerDashboardForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(dashbordPane);


    }

    @FXML
    void btnEMPLOYEEOnAction(ActionEvent event) throws IOException {
        AnchorPane employeePane = FXMLLoader.load(this.getClass().getResource("/view/EmployeeForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(employeePane);


    }

    @FXML
    void btnLOGOUTOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.anpMAIN3.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");



    }

    @FXML
    void btnMEALOnAction(ActionEvent event) throws IOException {
        AnchorPane mealPane = FXMLLoader.load(this.getClass().getResource("/view/FishForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(mealPane);


    }

    @FXML
    void btnTankOnAction(ActionEvent event) throws IOException {
        AnchorPane paymentPane = FXMLLoader.load(this.getClass().getResource("/view/TankForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(paymentPane);


    }

    @FXML
    void btnItemsOnAction(ActionEvent event) throws IOException {
        AnchorPane reservationPane = FXMLLoader.load(this.getClass().getResource("/view/ItemForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(reservationPane);


    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane roomPane = FXMLLoader.load(this.getClass().getResource("/view/OrderForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(roomPane);


    }

    @FXML
    void btnSUPPLIEROnAction(ActionEvent event) throws IOException {
        AnchorPane supplierPane = FXMLLoader.load(this.getClass().getResource("/view/SupplierForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(supplierPane);
    }
    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane supplierPane = FXMLLoader.load(this.getClass().getResource("/view/PlaceOrderForm.fxml"));


        anpMAIN1.getChildren().clear();
        anpMAIN1.getChildren().add(supplierPane);
    }

}


