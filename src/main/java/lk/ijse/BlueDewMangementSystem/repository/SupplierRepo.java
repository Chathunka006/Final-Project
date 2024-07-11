package lk.ijse.BlueDewMangementSystem.repository;

import javafx.scene.control.Alert;
import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static boolean DELETE(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supList = new ArrayList<>();

        while (resultSet.next()) {
            String SupplierId = resultSet.getString(1);
            String SupplierName = resultSet.getString(2);
            String Description = resultSet.getString(3);
            int Contact = Integer.parseInt(resultSet.getString(4));
            Date Date = java.sql.Date.valueOf(resultSet.getString(5));

            Supplier supplier = new Supplier(SupplierId,SupplierName,Description,Contact,Date);
            supList.add(supplier);
        }
        return supList;
    }

    public static boolean UPDATE(Supplier supplier) throws SQLException {
        String sql = "UPDATE Supplier SET suppliername = ?, description = ?, contact_number = ?  ,Date = ? WHERE supplier_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getSupplierName());
        pstm.setObject(2, supplier.getDescription());
        pstm.setObject(3,supplier.getContact());
        pstm.setObject(4,supplier.getDate());
        pstm.setObject(5, supplier.getSupplierId());
        return pstm.executeUpdate() > 0;
    }

    public static Supplier SEARCH(String id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE supplier_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String SupplierId = resultSet.getString(1);
            String SupplierName = resultSet.getString(2);
            String Description = resultSet.getString(3);
            int Contact = Integer.parseInt(resultSet.getString(4));
            Date Date = java.sql.Date.valueOf(resultSet.getString(5));


            Supplier supplier = new Supplier(SupplierId,SupplierName,Description,Contact,Date);

            return supplier;
        }

        return null;
    }
    public static void SAVE(String SupplierId, String SupplierName, String Description, int Contact, Date Date) throws SQLException {
        String sql = "INSERT INTO supplier VALUES(?, ?,?,?,?)";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, SupplierId);
        pstm.setObject(2, SupplierName);
        pstm.setObject(3, Description);
        pstm.setObject(4, Contact);
        pstm.setObject(5, Date);

        int effectedRows = pstm.executeUpdate();
        if (effectedRows > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier save successfully!!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't save this Supplier").show();
        }
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT supplier_id FROM supplier";

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
}

