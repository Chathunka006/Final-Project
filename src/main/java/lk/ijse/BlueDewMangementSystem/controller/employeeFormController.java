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
import lk.ijse.BlueDewMangementSystem.model.Employee;
import lk.ijse.BlueDewMangementSystem.model.tm.EmployeeTm;
import lk.ijse.BlueDewMangementSystem.repository.EmployeeRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;

public class employeeFormController {

    public Button btnDetails;
    //  public TextField txtDescription;
    // public TableColumn colDescription;
    //  public TextField txtAge;
    // public TableColumn colAge;
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
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colATTENDENCE;

    @FXML
    private TableColumn<?, ?> colCONTACT;

    @FXML
    private TableColumn<?, ?> colDATE;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colNAME;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colSALARY;

    @FXML
    private TableColumn<?, ?> colUSERID;

    @FXML
    private TableColumn<?, ?> colWORKHOURS;

    @FXML
    private ComboBox<String> combUSERID;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<EmployeeTm> tblEMPLOYEE;

    @FXML
    private TextField txtADDRESS;

    @FXML
    private TextField txtATTENDENCE;

    @FXML
    private TextField txtCONTACT;

    @FXML
    private TextField txtDATE;

    @FXML
    private TextField txtEMPLOYEEID;

    @FXML
    private TextField txtEMPLOYEENAME;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtSALARY;

    @FXML
    private TextField txtAge;


    public void initialize() throws SQLException {
        setDate();
        setCellValueFactory();
        loadAllEmployee();
        getCurrentEmployeeId();
    }


    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDATE.setText(String.valueOf(now));
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        colNAME.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        colSALARY.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colDATE.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colATTENDENCE.setCellValueFactory(new PropertyValueFactory<>("Attendence"));
        colCONTACT.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));

    }

    private void loadAllEmployee() {
        ObservableList<EmployeeTm> emList = FXCollections.observableArrayList();

        try {
            List<Employee> employeeList = EmployeeRepo.getAll();
            for (Employee employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getEmployeeId(),
                        employee.getEmployeeName(),
                        employee.getAge(),
                        employee.getSalary(),
                        employee.getDate(),
                        employee.getAttendence(),
                        employee.getContact(),
                        employee.getDescription()


                );

                emList.add(tm);
            }

            tblEMPLOYEE.setItems(emList);
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
        txtEMPLOYEEID.setText("");
        txtEMPLOYEENAME.setText("");
        txtAge.setText("");
        txtSALARY.setText("");
        txtDATE.setText("");
        txtATTENDENCE.setText("");
        txtCONTACT.setText("");
        txtDescription.setText("");


    }

    @FXML
    void btnDELETEOnAction(ActionEvent event) {
        String EmployeeId = txtEMPLOYEEID.getText();

        try {
            boolean isDeleted = EmployeeRepo.DELETE(EmployeeId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSAVEOnAction(ActionEvent event) {
        String EmployeeId = txtEMPLOYEEID.getText();
        String EmployeeName = txtEMPLOYEENAME.getText();
        int Age = parseInt(txtAge.getText());
        double Salary = Double.parseDouble(txtSALARY.getText());
        Date Date = java.sql.Date.valueOf(txtDATE.getText());
        String Attendence = txtATTENDENCE.getText();
        int Contact = parseInt(txtCONTACT.getText());
        String Description = txtDescription.getText();


        try {
            setCellValueFactory();
            loadAllEmployee();
            EmployeeRepo.SAVE(EmployeeId, EmployeeName, Age, Salary, Date, Attendence, Contact, Description);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSEARCHOnAction(ActionEvent event) throws SQLException {
        String id = txtEMPLOYEEID.getText();

        Employee employee = EmployeeRepo.SEARCH(id);
        if (employee != null) {
            txtEMPLOYEEID.setText(employee.getEmployeeId());
            txtEMPLOYEENAME.setText(employee.getEmployeeName());
            txtAge.setText(String.valueOf(employee.getAge()));
            txtSALARY.setText(String.valueOf(employee.getSalary()));
            txtDATE.setText(String.valueOf(employee.getDate()));
            txtATTENDENCE.setText(employee.getAttendence());
            txtCONTACT.setText(String.valueOf(employee.getContact()));
            txtDescription.setText(employee.getDescription());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
        }
    }

    @FXML
    void btnUPDATEOnAction(ActionEvent event) {
        String EmployeeId = txtEMPLOYEEID.getText();
        String EmployeeName = txtEMPLOYEENAME.getText();
        int Age = parseInt(txtAge.getText());
        double Salary = Double.parseDouble(txtSALARY.getText());
        Date date = java.sql.Date.valueOf(LocalDate.now());
        String Attendence = txtATTENDENCE.getText();
        int Contact = parseInt(txtCONTACT.getText());
        String Description = txtDescription.getText();


        Employee employee = new Employee(EmployeeId, EmployeeName, Age, Salary, date, Attendence, Contact, Description);

        try {
            boolean isUpdated = EmployeeRepo.UPDATE(employee);
            if (isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void btnDetailOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\User\\Desktop\\BLUE DEW AQUARIUM\\src\\main\\resources\\Report\\EmployeeReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void tblEmployeeOnAction(MouseEvent mouseEvent) {
        Integer index = tblEMPLOYEE.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtEMPLOYEEID.setText(colID.getCellData(index).toString());
        txtEMPLOYEENAME.setText(colNAME.getCellData(index).toString());
        txtAge.setText(colAge.getCellData(index).toString());
        txtSALARY.setText(colSALARY.getCellData(index).toString());
        txtDATE.setText(colDATE.getCellData(index).toString());
        txtATTENDENCE.setText(colATTENDENCE.getCellData(index).toString());
        txtCONTACT.setText(colCONTACT.getCellData(index).toString());
        txtDescription.setText(colDescription.getCellData(index).toString());
    }
    private void getCurrentEmployeeId() throws SQLException {
        String currentId = EmployeeRepo.getCurrentId();

        String nextOrderId = generateNextEmployeeId(currentId);
        txtEMPLOYEEID.setText(nextOrderId);
    }

    private String generateNextEmployeeId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("E");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "E" + ++idNum;
        }
        return "E1";
    }


}



