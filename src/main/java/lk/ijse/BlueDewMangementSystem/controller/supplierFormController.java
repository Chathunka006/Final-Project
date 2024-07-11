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
import lk.ijse.BlueDewMangementSystem.model.Supplier;
import lk.ijse.BlueDewMangementSystem.model.tm.SupplierTm;
import lk.ijse.BlueDewMangementSystem.repository.SupplierRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class supplierFormController {




    public Button btnDetails;
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
    private TableColumn<?, ?> colDATE;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colNAME;

    @FXML
    private TableColumn<?, ?> colDescriptiom;

    @FXML
    private TableColumn<?, ?> colQUANTITY;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<SupplierTm> tblSUPPLIER;

    @FXML
    private TextField txtADDRESS;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDATE;

    @FXML
    private TextField txtPRODUCTNAME;

    @FXML
    private TextField txtQUANTITY;

    @FXML
    private TextField txtSupplierID;

    @FXML
    private TextField txtSupplierName;

    public void initialize() {
        setDate();
        setCellValueFactory();
        loadAllSupplier();
    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDATE.setText(String.valueOf(now));
    }
    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        colNAME.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colDATE.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }
    private void loadAllSupplier() {
        ObservableList<SupplierTm> supList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = SupplierRepo.getAll();
            for (Supplier supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierId(),
                        supplier.getSupplierName(),
                        supplier.getDescription(),
                        supplier.getContact(),
                        supplier.getDate()
                );

                supList.add(tm);
            }

            tblSUPPLIER.setItems(supList);
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
        txtSupplierID.setText("");
        txtSupplierName.setText("");
        txtDescription.setText("");
        txtContact.setText("");
        txtDATE.setText("");



    }

    @FXML
    void btnDELETEOnAction(ActionEvent event) {
        String SupplierId = txtSupplierID.getText();

        try {
            boolean isDeleted = SupplierRepo.DELETE(SupplierId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSAVEOnAction(ActionEvent event) {
        String SupplierId = txtSupplierID.getText();
        String SupplierName = txtSupplierName.getText();
        String Description = txtDescription.getText();
        int Contact = Integer.parseInt(txtContact.getText());
        Date date = Date.valueOf(LocalDate.now());



        try {
            SupplierRepo.SAVE(SupplierId,SupplierName, Description,Contact,date);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnSEARCHOnAction(ActionEvent event) throws SQLException {
        String id = txtSupplierID.getText();

        Supplier supplier = SupplierRepo.SEARCH(id);
        if (supplier != null) {
            txtSupplierID.setText(supplier.getSupplierId());
            txtSupplierName.setText(supplier.getSupplierName());
            txtDescription.setText(supplier.getDescription());
            txtContact.setText(String.valueOf(supplier.getContact()));
            txtDATE.setText(String.valueOf(supplier.getDate()));

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
        }


    }

    @FXML
    void btnUPDATEOnAction(ActionEvent event) {
        String SupplierId = txtSupplierID.getText();
        String SupplierName = txtSupplierName.getText();
        String Description = txtDescription.getText();
        int Contact = Integer.parseInt(txtContact.getText());
        Date date = java.sql.Date.valueOf(LocalDate.now());


        Supplier supplier = new Supplier(SupplierId,SupplierName, Description,Contact,date);

        try {
            boolean isUpdated = SupplierRepo.UPDATE(supplier);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Desktop\\BLUE DEW AQUARIUM\\src\\main\\resources\\Report\\SupplierReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }


    public void btnSupplierOnAction(MouseEvent mouseEvent) {
        Integer index = tblSUPPLIER.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtSupplierID.setText(colID.getCellData(index).toString());
        txtSupplierName.setText(colNAME.getCellData(index).toString());
        txtDescription.setText(colDescription.getCellData(index).toString());
        txtContact.setText(colContact.getCellData(index).toString());
        txtDATE.setText(colDATE.getCellData(index).toString());
    }
}





