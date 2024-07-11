package lk.ijse.BlueDewMangementSystem.repository;

import javafx.scene.control.Alert;
import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static boolean DELETE(String id, String customerName, int age, int contact) throws SQLException {
        String sql = "DELETE FROM Customer WHERE customer_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM Customer";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String CustomerId = resultSet.getString(1);
            String CustomerName = resultSet.getString(2);
            int Age = Integer.parseInt(resultSet.getString(3));
            int Contact = Integer.parseInt(resultSet.getString(4));



            Customer customer = new Customer(CustomerId, CustomerName,Age, Contact);
            cusList.add(customer);
        }
        return cusList;
    }

    public static boolean UPDATE(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET CustomerName = ?, Age = ?, contact_number = ?  WHERE customer_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getCustomerName());
        pstm.setObject(2, customer.getAge());
        pstm.setObject(3, customer.getContact());
        pstm.setObject(4, customer.getCustomerId());


        return pstm.executeUpdate() > 0;
    }

    public static Customer SEARCH(String id) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE customer_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String CustomerId = resultSet.getString(1);
            String CustomerName = resultSet.getString(2);
            int Age = resultSet.getInt(3);
            int Contact = resultSet.getInt(4);



            Customer customer = new Customer(CustomerId, CustomerName,Age, Contact);

            return customer;
        }

        return null;
    }

    public static void SAVE(String CustomerId, String CustomerName, int Age,int Contact) throws SQLException {
        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, CustomerId);
        pstm.setObject(2, CustomerName);
        pstm.setObject(3, Age);
        pstm.setObject(4, Contact);



        int effectedRows = pstm.executeUpdate();
        if (effectedRows > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Customer save successfully!!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't save this customer").show();
        }
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT customer_id FROM Customer";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);

            idList.add(id);
        }
        return idList;
    }

    public static String getCurrentId() throws SQLException {

        String sql = "SELECT customer_id FROM Customer ORDER BY customer_id DESC LIMIT 1";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String CustomerId = resultSet.getString(1);
            return CustomerId;
        }
        return null;
    }


    public static List<Integer> getContact() throws SQLException {
        String sql = "SELECT contact_number FROM Customer";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<Integer> contactList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString(1));
            contactList.add(id);
        }
        return contactList;

    }

    public static lk.ijse.BlueDewMangementSystem.model.ToEsier.Customer getIdandName(int value) throws SQLException {
        String sql = "SELECT customername,customer_id FROM Customer WHERE contact_number = ?";
        PreparedStatement preparedStatement = dbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1,value);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String customername = resultSet.getString("customername");
            String customerId = resultSet.getString("customer_id");
            lk.ijse.BlueDewMangementSystem.model.ToEsier.Customer customer = new lk.ijse.BlueDewMangementSystem.model.ToEsier.Customer(customername, customerId);
            return customer;

        }
        return null;
    }
}