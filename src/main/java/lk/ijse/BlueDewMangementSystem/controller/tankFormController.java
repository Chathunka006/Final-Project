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
import lk.ijse.BlueDewMangementSystem.model.Tank;
import lk.ijse.BlueDewMangementSystem.model.tm.TankTm;
import lk.ijse.BlueDewMangementSystem.repository.TankRepo;
import lk.ijse.BlueDewMangementSystem.repository.UserRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class tankFormController {


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
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colDesign;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colUserId;




    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<TankTm> tblTank;

    @FXML
    private TextField txtTankId;

    @FXML
    private TextField txtDesign;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtSize;

    @FXML
    private ComboBox<String> combUserId;


    public void initialize() {
        setCellValueFactory();
        loadAllSupplier();
        getUserId();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("TankId"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("Size"));
        colDesign.setCellValueFactory(new PropertyValueFactory<>("Desing"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
    }
    private void loadAllSupplier() {
        ObservableList<TankTm> supList = FXCollections.observableArrayList();

        try {
            List<Tank> tankList = TankRepo.getAll();
            for (Tank tank : tankList) {
                TankTm tm = new TankTm(
                        tank.getTankId(),
                        tank.getSize(),
                        tank.getDesing(),
                        tank.getDescription(),
                        tank.getUserId()
                );

                supList.add(tm);
            }

            tblTank.setItems(supList);
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
        txtTankId.setText("");
        txtSize.setText("");
        txtDesign.setText("");
        txtDescription.setText("");
        combUserId.setValue("");



    }

    @FXML
    void btnDELETEOnAction(ActionEvent event) {
        String TankId = txtTankId.getText();

        try {
            boolean isDeleted = TankRepo.DELETE(TankId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Tank deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSAVEOnAction(ActionEvent event) {
        String TankId = txtTankId.getText();
        String Size = txtSize.getText();
        String Desing = txtDesign.getText();
        String Description = txtDescription.getText();
        String UserId = combUserId.getValue();




        try {
            TankRepo.SAVE(TankId,Size,Desing,Description,UserId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void getUserId(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> userList = UserRepo.getIds();
            for (String id : userList) {
                obList.add(id);
            }
            combUserId.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSEARCHOnAction(ActionEvent event) throws SQLException {
        String TankId = txtTankId.getText();

        Tank tank = TankRepo.SEARCH(TankId);
        if (tank != null) {
            txtTankId.setText(tank.getTankId());
            txtSize.setText(tank.getSize());
            txtDesign.setText(tank.getDesing());
            txtDescription.setText(tank.getDescription());
            combUserId.setValue(tank.getUserId());


        } else {
            new Alert(Alert.AlertType.INFORMATION, "Tank not found!").show();
        }


    }

    @FXML
    void btnUPDATEOnAction(ActionEvent event) {
        String TankId = txtTankId.getText();
        String Size = txtSize.getText();
        String Desing = txtDesign.getText();
        String Description = txtDescription.getText();
        String UserId = combUserId.getValue();


        Tank tank = new Tank(TankId,Size,Desing,Description,UserId);

        try {
            boolean isUpdated = TankRepo.UPDATE(tank);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void combTankIdOnAction(ActionEvent actionEvent) {
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Desktop\\BLUE DEW AQUARIUM\\src\\main\\resources\\Report\\TankReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void tblTankOnAction(MouseEvent mouseEvent) {
        Integer index = tblTank.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtTankId.setText(colID.getCellData(index).toString());
        txtSize.setText(colSize.getCellData(index).toString());
        txtDesign.setText(colDesign.getCellData(index).toString());
        txtDescription.setText(colDescription.getCellData(index).toString());
        combUserId.setValue(colUserId.getCellData(index).toString());
    }
}





