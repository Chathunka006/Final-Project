package lk.ijse.BlueDewMangementSystem.repository;

import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {

    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection connection = dbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            System.out.println("connection = " + connection);
            boolean isOrderSaved = OrderRepo.SaveOrder(po.getOrder());
            System.out.println("isOrderSaved = " + isOrderSaved);
            if (isOrderSaved) {


                boolean isUpdatedFishQty = false;
                System.out.println("isUpdatedFishQty = " + isUpdatedFishQty);

                if (isUpdatedFishQty) {
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }

        } catch (Exception e) {
            connection.rollback();
            return connection!=null;
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }
}