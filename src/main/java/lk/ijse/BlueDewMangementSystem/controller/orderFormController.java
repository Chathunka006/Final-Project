package lk.ijse.BlueDewMangementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.Order;
import lk.ijse.BlueDewMangementSystem.model.tm.OrderTm;
import lk.ijse.BlueDewMangementSystem.repository.CustomerRepo;
import lk.ijse.BlueDewMangementSystem.repository.OrderRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class orderFormController {

    public TextField txtOrderID;


    public TableView tblOrder;
    public Button btnDetails;
    @FXML
    private ComboBox<String> combCustomerID;

    @FXML
    private Button btnBACK;

    @FXML
    private Button btnCLEAR;

    @FXML
    private Button btnDELETE;

    @FXML
    private Button btnSAVE;

    @FXML
    private Button btnSEARCH;

    @FXML
    private Button btnUPDATE;



    @FXML
    private TableColumn<?, ?> colDATE;



    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colPRICE;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private AnchorPane rootNode;




    @FXML
    private TextField txtDATE;



    @FXML
    private TextField txtPRICE;

    @FXML
    private TextField txtQty;



    public void initialize() {
        setDate();
        setCellValueFactory();
        loadAllOrders();
        getCustomerId();

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDATE.setText(String.valueOf(now));
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colDATE.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colPRICE.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
    }

    private void loadAllOrders() {
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();

        try {
            List<Order> orderList = OrderRepo.getAll();
            for (Order order : orderList) {
                OrderTm tm = new OrderTm(
                        order.getOrderId(),
                        order.getDate(),
                        order.getCustomerId(),
                        order.getPrice(),
                        order.getQty()

                );

                obList.add(tm);
            }

            tblOrder.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void btnBACKOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OwnerDashboardForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Owner Dashboard Form");
        stage.centerOnScreen();


    }

    @FXML
    void btnCLEAROnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtOrderID.setText("");
        txtDATE.setText("");
        combCustomerID.setValue("");
        txtPRICE.setText("");
        txtQty.setText("");




    }

    @FXML
    void btnDELETEOnAction(ActionEvent event) {
        String OrderId = txtOrderID.getText();

        try {
            boolean isDeleted = OrderRepo.DELETE(OrderId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSAVEOnAction(ActionEvent event) {
        String OrderID = txtOrderID.getText();
        Date date = Date.valueOf(LocalDate.now());
        String CustomerId = combCustomerID.getValue().toString();
        double Price = Double.parseDouble(txtPRICE.getText());
        int Qty = Integer.parseInt(txtQty.getText());



        try {
            OrderRepo.SAVE(OrderID,date,CustomerId,Price,Qty);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void getCustomerId(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> customerList = CustomerRepo.getIds();
            for (String id : customerList) {
                obList.add(id);
            }
            combCustomerID.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSEARCHOnAction(ActionEvent event) throws SQLException {
        String id = txtOrderID.getText();

        Order order = OrderRepo.SEARCH(id);
        if (order != null) {
            txtOrderID.setText(order.getOrderId());
            txtDATE.setText(String.valueOf(order.getDate()));
            combCustomerID.setValue(order.getCustomerId());
            txtPRICE.setText(String.valueOf(order.getPrice()));
            txtQty.setText(String.valueOf(order.getQty()));



        } else {
            new Alert(Alert.AlertType.INFORMATION, "Order not found!").show();
        }


    }

    @FXML
    void btnUPDATEOnAction(ActionEvent event) {
        String OrderId = txtOrderID.getText();
        Date date = java.sql.Date.valueOf(LocalDate.now());
        String CustomerId  = combCustomerID.getValue().toString();
        double Price = Double.parseDouble(txtPRICE.getText());
        int Qty = Integer.parseInt(txtQty.getText());





        Order room = new Order(OrderId,date,CustomerId,Price,Qty);

        try {
            boolean isUpdated = OrderRepo.UPDATE(room);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Desktop\\BLUE DEW AQUARIUM\\src\\main\\resources\\Report\\OrderReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void tblOrderOnAction(MouseEvent mouseEvent) {
        Integer index = tblOrder.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtOrderID.setText(colID.getCellData(index).toString());
        txtDATE.setText(colDATE.getCellData(index).toString());
        combCustomerID.setValue(colCustomerID.getCellData(index).toString());
        txtPRICE.setText(colPRICE.getCellData(index).toString());
        txtQty.setText(colQty.getCellData(index).toString());

    }


}



