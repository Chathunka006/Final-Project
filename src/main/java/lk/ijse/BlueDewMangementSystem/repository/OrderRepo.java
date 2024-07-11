package lk.ijse.BlueDewMangementSystem.repository;

import javafx.scene.control.Alert;
import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {
    public static boolean DELETE(String id) throws SQLException {
        String sql = "DELETE FROM orders WHERE order_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Order> getAll() throws SQLException {
        String sql = "SELECT * FROM orders";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Order> rmList = new ArrayList<>();

        while (resultSet.next()) {
            String OrderId = resultSet.getString(1);
            Date Date = java.sql.Date.valueOf(resultSet.getString(2));
            String CustomerId = resultSet.getString(3);
            double Price = Double.parseDouble(resultSet.getString(4));
            int Qty = Integer.parseInt(resultSet.getString(5));




            Order order = new Order(OrderId,Date,CustomerId,Price,Qty);
            rmList.add(order);
        }
        return rmList;
    }

    public static boolean UPDATE(Order order) throws SQLException {
        String sql = "UPDATE orders SET order_date = ?, customer_id = ? ,price = ?,Qty = ? WHERE order_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, order.getDate());
        pstm.setObject(2, order.getCustomerId());
        pstm.setObject(3, order.getPrice());
        pstm.setObject(4, order.getQty());
        pstm.setObject(5, order.getOrderId());
        return pstm.executeUpdate() > 0;
    }

    public static Order SEARCH(String id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String OrderId = resultSet.getString(1);
            Date Date = java.sql.Date.valueOf(resultSet.getString(2));
            String CustomerId = resultSet.getString(3);
            double Price = Double.parseDouble(resultSet.getString(4));
            int Qty = Integer.parseInt(resultSet.getString(5));





            Order order = new Order(OrderId,Date,CustomerId,Price,Qty);

            return order;
        }

        return null;
    }
    public static void SAVE(String OrderId,Date Date, String CustomerId,double Price,int Qty ) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?,?)";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,OrderId );
        pstm.setObject(2, Date);
        pstm.setObject(3,CustomerId );
        pstm.setObject(4, Price);
        pstm.setObject(5,Qty );



        int effectedRows = pstm.executeUpdate();
        if (effectedRows > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order save successfully!!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't save this Order").show();
        }
    }


    // place order save
    public static Boolean SaveOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?,?)";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,order.getOrderId() );
        pstm.setObject(2, order.getDate());
        pstm.setObject(3,order.getCustomerId() );
        pstm.setObject(4, order.getPrice());
        pstm.setObject(5,order.getQty());



        int effectedRows = pstm.executeUpdate();
        if (effectedRows > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order save successfully!!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't save this Order").show();
        }
        return pstm.executeUpdate() > 0;
    }


    public static List<String> getOrderId() throws SQLException {
        String sql = "SELECT order_id FROM orders";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> TankIdList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            TankIdList.add(id);
        }
        return TankIdList;

    }
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT order_id FROM Orders ORDER BY Order_id DESC LIMIT 1";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String OrderId = resultSet.getString(1);
            return OrderId;
        }
        return null;
    }
}
