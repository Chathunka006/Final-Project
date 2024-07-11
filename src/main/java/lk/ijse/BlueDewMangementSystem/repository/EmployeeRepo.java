package lk.ijse.BlueDewMangementSystem.repository;

import javafx.scene.control.Alert;
import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static boolean DELETE(String id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE employee_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> empList = new ArrayList<>();

        while (resultSet.next()) {
            String EmployeeId = resultSet.getString(1);
            String EmployeeName = resultSet.getString(2);
            int Age = Integer.parseInt(resultSet.getString(3));
            double Salary = Double.parseDouble(resultSet.getString(4));
            Date Date = java.sql.Date.valueOf(resultSet.getString(5));
            String Attendence = resultSet.getString(6);
            int Contact = Integer.parseInt(resultSet.getString(7));
            String Description = resultSet.getString(8);


            Employee employee = new Employee(EmployeeId, EmployeeName, Age, Salary, Date, Attendence, Contact, Description);
            empList.add(employee);
        }
        return empList;
    }

    public static boolean UPDATE(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET employeename = ?, Age = ?, Salary = ?,date = ? , attendence = ? ,contact_number = ? ,description = ?  WHERE employee_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getEmployeeName());
        pstm.setObject(2, employee.getAge());
        pstm.setObject(3, employee.getSalary());
        pstm.setObject(4, employee.getDate());
        pstm.setObject(5, employee.getAttendence());
        pstm.setObject(6, employee.getContact());
        pstm.setObject(7, employee.getDescription());
        pstm.setObject(8, employee.getEmployeeId());


        return pstm.executeUpdate() > 0;
    }

    public static Employee SEARCH(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE employee_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String EmployeeId = resultSet.getString(1);
            String EmployeeName = resultSet.getString(2);
            int Age = Integer.parseInt(resultSet.getString(3));
            double Salary = Double.parseDouble(resultSet.getString(4));
            Date Date = java.sql.Date.valueOf(resultSet.getString(5));
            String Attendence = resultSet.getString(6);
            int Contact = Integer.parseInt(resultSet.getString(7));
            String Description = resultSet.getString(8);


            Employee employee = new Employee(EmployeeId, EmployeeName,Age,Salary,Date,Attendence,Contact,Description);

            return employee;
        }

        return null;
    }

    public static void SAVE(String EmployeeId, String EmployeeName, int Age, double Salary, Date Date, String Attendence, int Contact, String Description) throws SQLException {
        String sql = "INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, EmployeeId);
        pstm.setObject(2, EmployeeName);
        pstm.setObject(5, Date);
        pstm.setObject(3, Age);
        pstm.setObject(7, Contact);
        pstm.setObject(4, Salary);
        pstm.setObject(6, Attendence);
        pstm.setObject(8, Description);


        int effectedRows = pstm.executeUpdate();
        if (effectedRows > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Employee save successfully!!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't save this Employee").show();
        }

    }

    public static String getCurrentId() throws SQLException {

        String sql = "SELECT employee_id FROM Employee ORDER BY employee_id DESC LIMIT 1";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String EmployeeId = resultSet.getString(1);
            return EmployeeId;
        }
        return null;
    }
}


