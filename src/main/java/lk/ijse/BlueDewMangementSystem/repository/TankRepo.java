package lk.ijse.BlueDewMangementSystem.repository;

import javafx.scene.control.Alert;
import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.Tank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TankRepo {
    public static boolean DELETE(String id) throws SQLException {
        String sql = "DELETE FROM tank WHERE tank_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Tank> getAll() throws SQLException {
        String sql = "SELECT * FROM tank";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Tank> supList = new ArrayList<>();

        while (resultSet.next()) {
            String TankId = resultSet.getString(1);
            String Size = resultSet.getString(2);
            String Desing = resultSet.getString(3);
            String Description = resultSet.getString(4);
            String UserId = resultSet.getString(5);


            Tank tank = new Tank(TankId,Size,Desing,Description,UserId);
            supList.add(tank);
        }
        return supList;
    }

    public static boolean UPDATE(Tank tank) throws SQLException {
        String sql = "UPDATE tank SET size = ?, design = ?, description = ?  ,user_id = ? WHERE tank_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, tank.getSize());
        pstm.setObject(2, tank.getDesing());
        pstm.setObject(3, tank.getDescription());
        pstm.setObject(4, tank.getUserId());
        pstm.setObject(5, tank.getTankId());
        return pstm.executeUpdate() > 0;
    }

    public static Tank SEARCH(String id) throws SQLException {
        String sql = "SELECT * FROM tank WHERE tank_id = ?";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String TankId = resultSet.getString(1);
            String Size = resultSet.getString(2);
            String Desing = resultSet.getString(3);
            String Description = resultSet.getString(4);
            String UserId = resultSet.getString(5);



            Tank tank = new Tank(TankId,Size,Desing,Description,UserId);

            return tank;
        }

        return null;
    }
    public static void SAVE(String TankId, String Size, String Desing, String Description, String UserId) throws SQLException {
        String sql = "INSERT INTO tank VALUES(?, ?,?,?,?)";

        Connection connection = dbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, TankId);
        pstm.setObject(2, Size);
        pstm.setObject(3, Desing);
        pstm.setObject(4, Description);
        pstm.setObject(5, UserId);

        int effectedRows = pstm.executeUpdate();
        if (effectedRows > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Tank save successfully!!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't save this Tank").show();
        }
    }

    public static List<String> getTankIds() throws SQLException {
        String sql = "SELECT tank_id FROM tank";

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

