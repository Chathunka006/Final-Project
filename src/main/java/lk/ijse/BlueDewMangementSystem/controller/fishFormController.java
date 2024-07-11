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
import lk.ijse.BlueDewMangementSystem.model.Fish;
import lk.ijse.BlueDewMangementSystem.model.tm.FishTm;
import lk.ijse.BlueDewMangementSystem.repository.FishRepo;
import lk.ijse.BlueDewMangementSystem.repository.SupplierRepo;
import lk.ijse.BlueDewMangementSystem.repository.TankRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class fishFormController {


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
    private TableColumn<?, ?> colCount;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colNAME;

    @FXML
    private TableColumn<?, ?> colPRICE;

    @FXML
    private TableColumn<?, ?> colTankID;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colQty;


    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<FishTm> tblFish;



    @FXML
    private TextField txtPRICE;

    @FXML
    private TextField txtFishID;

    @FXML
    private TextField txtFishNAME;

    @FXML
    private TextField txtFishCount;
    @FXML
    private TextField txtQty;

    @FXML
    private ComboBox<String> combTankID;

    @FXML
    private ComboBox<String> combSupplierId;


    public void initialize() {

        loadAllFish();
        getTankId();
        getSupplierId();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("FishId"));
        colNAME.setCellValueFactory(new PropertyValueFactory<>("FishName"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("Count"));
        colPRICE.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colTankID.setCellValueFactory(new PropertyValueFactory<>("TankId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
    }
    private void loadAllFish() {
        ObservableList<FishTm> mlList = FXCollections.observableArrayList();

        try {
            List<Fish> fishList = FishRepo.getAll();
            for (Fish fish : fishList) {
                FishTm tm = new FishTm(
                        fish.getFishId(),
                        fish.getFishName(),
                        fish.getCount(),
                        fish.getPrice(),
                        fish.getTankId(),
                        fish.getQty(),
                        fish.getSupplierId()

                );

                mlList.add(tm);
            }

            tblFish.setItems(mlList);
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
        txtFishID.setText("");
        txtFishNAME.setText("");
        txtFishCount.setText("");
        txtPRICE.setText("");
        combTankID.setValue("");
        txtQty.setText("");
        combSupplierId.setValue("");



    }

    @FXML
    void btnDELETEOnAction(ActionEvent event) {
        String FishId = txtFishID.getText();

        try {
            boolean isDeleted = FishRepo.DELETE(FishId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Fish deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnSAVEOnAction(ActionEvent event) {
        String FishID = txtFishID.getText();
        String FishName = txtFishNAME.getText();
        int Count = Integer.parseInt(txtFishCount.getText());
        double Price = Double.parseDouble(txtPRICE.getText());
        String TankId = combTankID.getValue();
        int Qty = Integer.parseInt(txtQty.getText());
        String SupplierId = (String) combSupplierId.getValue();





        try {
            FishRepo.SAVE(FishID, FishName,Count,Price,TankId,Qty,SupplierId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void getTankId(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> tankList = TankRepo.getTankIds();
            for (String id : tankList) {
                obList.add(id);
            }
            combTankID.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private void getSupplierId(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> supplierList = SupplierRepo.getIds();
            for (String id : supplierList) {
                obList.add(id);
            }
            combSupplierId.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSEARCHOnAction(ActionEvent event) throws SQLException {
        String id = txtFishID.getText();

        Fish fish = FishRepo.SEARCH(id);
        if (fish != null) {
            txtFishID.setText(fish.getFishId());
            txtFishNAME.setText(fish.getFishName());
            txtFishCount.setText(String.valueOf(fish.getCount()));
            txtPRICE.setText(String.valueOf(fish.getPrice()));
            combTankID.setValue(fish.getTankId());
            txtQty.setText(String.valueOf(fish.getQty()));
            combSupplierId.setValue(fish.getSupplierId());


        } else {
            new Alert(Alert.AlertType.INFORMATION, "FISH not found!").show();
        }

    }

    @FXML
    void btnUPDATEOnAction(ActionEvent event) {
        String FishId = txtFishID.getText();
        String FishName = txtFishNAME.getText();
        int Count = Integer.parseInt(txtFishCount.getText());
        double Price = Double.parseDouble(txtPRICE.getText());
        String TankId = combTankID.getValue();
        int Qty = Integer.parseInt(txtQty.getText());
        String SupplierId = (String) combSupplierId.getValue();



        Fish fish = new Fish(FishId, FishName,Count,Price,TankId, Qty,SupplierId);

        try {
            boolean isUpdated = FishRepo.UPDATE(fish);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Fish updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void combTankIdOnAction(ActionEvent actionEvent) {
    }
    public void combSupplierIdOnAction(ActionEvent actionEvent) {
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Desktop\\BLUE DEW AQUARIUM\\src\\main\\resources\\Report\\FishReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void tblFishOnAction(MouseEvent mouseEvent) {
        Integer index = tblFish.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtFishID.setText(colID.getCellData(index).toString());
        txtFishNAME.setText(colNAME.getCellData(index).toString());
        txtFishCount.setText(colCount.getCellData(index).toString());
        txtPRICE.setText(colPRICE.getCellData(index).toString());
        combTankID.setValue(colTankID.getCellData(index).toString());
        txtQty.setText(colQty.getCellData(index).toString());
        combSupplierId.setValue(colSupplierId.getCellData(index).toString());

    }


}


