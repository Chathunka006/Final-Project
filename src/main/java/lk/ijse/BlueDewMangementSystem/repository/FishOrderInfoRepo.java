package lk.ijse.BlueDewMangementSystem.repository;

import lk.ijse.BlueDewMangementSystem.db.dbConnection;
import lk.ijse.BlueDewMangementSystem.model.FishOrderInfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FishOrderInfoRepo {

    public static boolean save(List<FishOrderInfo> FishList) throws SQLException {
        for (FishOrderInfo od : FishList){
            boolean isSaved = Save(od);
            if(!isSaved){
                return false;
            }
        }
        return true;
    }

    private static boolean Save(FishOrderInfo Fish) throws SQLException {
        String sql = "INSERT INTO Order_Item VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = dbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, Fish.getOrder_id());
        pstm.setString(2, Fish.getFish_id());
//        pstm.setInt(3, od.getQuantity());
//        pstm.setDouble(4, od.getPrice());

        return pstm.executeUpdate() > 0;
    }

}
