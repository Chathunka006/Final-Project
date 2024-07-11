package lk.ijse.BlueDewMangementSystem.repository;

import javafx.scene.control.Alert;
import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.Fish;
import lk.ijse.BlueDewMangementSystem.model.FishOrderInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FishRepo {
    public static boolean DELETE(String id) throws SQLException {
        String sql = "DELETE FROM Fish WHERE Fish_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Fish> getAll() throws SQLException {
        String sql = "SELECT * FROM Fish";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Fish> fishList = new ArrayList<>();

        while (resultSet.next()) {
            String FishId = resultSet.getString(1);
            String FishName = resultSet.getString(2);
            int Count = Integer.parseInt(resultSet.getString(3));
            double Price = resultSet.getDouble(4);
            String TankId = resultSet.getString(5);
            int Qty = Integer.parseInt(resultSet.getString(6));
            String SupplierId = resultSet.getString(7);




            Fish fish = new Fish(FishId, FishName,Count,Price,TankId, Qty,SupplierId);
            fishList.add(fish);
        }
        return fishList;
    }
    public static boolean UPDATE(Fish fish) throws SQLException {
        String sql = "UPDATE Fish SET fishname = ?, fishCount = ?, price = ? , tank_id = ?, qyt = ?, supplier_id=? WHERE fish_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, fish.getFishName());
        pstm.setObject(2, fish.getCount());
        pstm.setObject(3, fish.getPrice());
        pstm.setObject(4, fish.getTankId());
        pstm.setObject(5, fish.getQty());
        pstm.setObject(6, fish.getSupplierId());
        pstm.setObject(7, fish.getFishId());


        return pstm.executeUpdate() > 0;
    }

    public static Fish SEARCH(String id) throws SQLException {
        String sql = "SELECT * FROM Fish WHERE Fish_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String FishId = resultSet.getString(1);
            String FishName = resultSet.getString(2);
            int Count = Integer.parseInt(resultSet.getString(3));
            double Price = resultSet.getDouble(4);
            String TankId = resultSet.getString(5);
            int Qty = Integer.parseInt(resultSet.getString(6));
            String SupplierId = resultSet.getString(7);



            Fish fish = new Fish(FishId, FishName,Count,Price,TankId, Qty,SupplierId);

            return fish;
        }

        return null;
    }

    public static void SAVE(String FishId, String FishName, int Count, double Price,String TankId,int Qty,String SupplierId) throws SQLException {
        String sql = "INSERT INTO Fish VALUES(?, ?, ?, ?,?,?,?)";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, FishId);
        pstm.setObject(2, FishName);
        pstm.setObject(3, Count);
        pstm.setObject(4, Price);
        pstm.setObject(5, TankId);
        pstm.setObject(6, Qty);
        pstm.setObject(7, SupplierId);

        int effectedRows = pstm.executeUpdate();
        if (effectedRows > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Fish save successfully!!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't save this Fish").show();
        }
    }

//    public static boolean UPDATEFish(FishOrderInfo fish) throws SQLException {
//        String sql = "UPDATE Fish SET fishname = ?, fishCount = ?, price = ? , tank_id = ?, qyt = ? WHERE fish_id = ?";
//
//        Connection connection = dbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, fish.getFishName());
//        pstm.setObject(2, fish.getCount());
//        pstm.setObject(3, fish.getPrice());
//        pstm.setObject(4, fish.getTankId());
//        pstm.setObject(5, fish.getQty());
//        pstm.setObject(6, fish.getFishId());
//
//        return pstm.executeUpdate() > 0;
//    }



    public static List<String> getId() throws SQLException {
        String sql = "SELECT fish_id FROM Fish";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> fishList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            fishList.add(id);
        }
        return fishList;
    }


    // Fish Transaction
    public static boolean UpdateQty(FishOrderInfo fish) throws SQLException {
        String sql = "Update fish set qyt = qyt - ? where fish_id = ?";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, fish.getQty());
        pstm.setString(2, fish.getFish_id());

        return pstm.executeUpdate() > 0;

    }

    // Insert new values into the FishOrderInfo Table
    public static boolean SaveFish(FishOrderInfo fish) throws SQLException {
        String sql = "INSERT INTO fishorderinfo VALUES(?, ?, ?,?)";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, fish.getOrder_id());
        pstm.setObject(2, fish.getFish_id());
        pstm.setObject(3, fish.getQty());
        pstm.setObject(4, fish.getPrice());

        return pstm.executeUpdate() > 0;
    }


    public static String getName(String value) throws SQLException {
        String sql = "SELECT fishname FROM Fish WHERE fish_id = ?";
        PreparedStatement pstm = dbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,value);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("fishname");
        }
        return null;
    }

    public static String getprice(String value) throws SQLException {
        String sql = "SELECT price FROM Fish WHERE fish_id = ?";
        PreparedStatement preparedStatement = dbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1,value);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("price");
        }
        return null;
    }
}
