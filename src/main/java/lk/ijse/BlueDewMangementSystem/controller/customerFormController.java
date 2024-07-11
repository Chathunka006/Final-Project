package lk.ijse.BlueDewMangementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.Customer;
import lk.ijse.BlueDewMangementSystem.model.tm.CustomerTm;
import lk.ijse.BlueDewMangementSystem.repository.CustomerRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.List;

public class customerFormController {


    public Button btnDetails;
    // public TextField txtAGE;
   // public TableColumn colAGE;
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
    private TableColumn<?, ?> colADDRESS;

    @FXML
    private TableColumn<?, ?> colCONTACT;

    @FXML
    private TableColumn<?, ?> colAGE;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colNAME;

    @FXML
    private TableColumn<?, ?> colUSERID;
    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private ComboBox<String> combUSERID;

    @FXML
    private ComboBox<String> combMEALID;

    @FXML
    private ComboBox<String> combROOMID;

    @FXML
    private TableColumn<?, ?> colMEALID;

    @FXML
    private TableColumn<?, ?> colMEALID2;

    @FXML
    private TableColumn<?, ?> colMEALNAME;

    @FXML
    private TableColumn<?, ?> colPRICE;

    @FXML
    private TableColumn<?, ?> colQUANTITY;




    @FXML
    private TableColumn<?, ?> colROOMID;

    @FXML
    private TableColumn<?, ?> colTOTAL;


    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<CustomerTm> tblCUSTOMER;

    @FXML
    private TextField txtAGE;

    @FXML
    private TextField txtCONTACT;

    @FXML
    private TextField txtCUSTOMERID;



    @FXML
    private TextField txtCUSTOMERNAME;

    @FXML
    private TextField txtDATE;

    @FXML
    private TextField txtNIC;







    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllCustomers();
        getCurrentCustomerId();
    }








    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colNAME.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        colAGE.setCellValueFactory(new PropertyValueFactory<>("Age"));
        colCONTACT.setCellValueFactory(new PropertyValueFactory<>("Contact"));



    }

    private void getCurrentCustomerId() throws SQLException {
        String currentId = CustomerRepo.getCurrentId();

        String nextOrderId = generateNextCustomerId(currentId);
        txtCUSTOMERID.setText(nextOrderId);
    }

    private String generateNextCustomerId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("C");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "C" + ++idNum;
        }
        return "C1";
    }
    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<Customer> customerList = CustomerRepo.getAll();
            for (Customer customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getAge(),
                        customer.getContact()

                );

                obList.add(tm);
            }

            tblCUSTOMER.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnCLEAROnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtCUSTOMERID.setText("");
        txtCUSTOMERNAME.setText("");
        txtAGE.setText("");
        txtCONTACT.setText("");


    }

    @FXML
    void btnDELETEOnAction(ActionEvent event) {
        String CustomerId = txtCUSTOMERID.getText();
        String CustomerName = txtCUSTOMERNAME.getText();
        int Age = Integer.parseInt(txtAGE.getText());
        int Contact = Integer.parseInt(txtCONTACT.getText());

        try {
            boolean isDeleted = CustomerRepo.DELETE(CustomerId,CustomerName,Age,Contact);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnSAVEOnAction(ActionEvent event) {
        String CustomerId = txtCUSTOMERID.getText();
        String CustomerName = txtCUSTOMERNAME.getText();
        int Age = Integer.parseInt(txtAGE.getText());
        int Contact = Integer.parseInt(txtCONTACT.getText());





        try {
            CustomerRepo.SAVE(CustomerId, CustomerName,Age,Contact);
            initialize();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSEARCHOnAction(ActionEvent event) throws SQLException {
        String id = txtCUSTOMERID.getText();

        Customer customer = CustomerRepo.SEARCH(id);
        if (customer != null) {
            txtCUSTOMERID.setText(customer.getCustomerId());
            txtCUSTOMERNAME.setText(customer.getCustomerName());
            txtAGE.setText(String.valueOf(customer.getAge()));
            txtCONTACT.setText(String.valueOf(customer.getContact()));


        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }

    }

    @FXML
    void btnUPDATEOnAction(ActionEvent event) {
        String CustomerId = txtCUSTOMERID.getText();
        String CustomerName = txtCUSTOMERNAME.getText();
        int Age = Integer.parseInt(txtAGE.getText());
        int Contact = Integer.parseInt(txtCONTACT.getText());





        Customer customer = new Customer(CustomerId,CustomerName,Age,Contact );

        try {
            boolean isUpdated = CustomerRepo.UPDATE(customer);
            if(isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void tblCUSTOMEROnAction(MouseEvent mouseEvent) {
        Integer index = tblCUSTOMER.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtCUSTOMERID.setText(colID.getCellData(index).toString());
        txtCUSTOMERNAME.setText(colNAME.getCellData(index).toString());
        txtAGE.setText(colAGE.getCellData(index).toString());
        txtCONTACT.setText(colCONTACT.getCellData(index).toString());


    }



    public void btnDetailsOnAction(ActionEvent actionEvent) throws SQLException, JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Desktop\\BLUE DEW AQUARIUM\\src\\main\\resources\\Report\\CustomerReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }



}


