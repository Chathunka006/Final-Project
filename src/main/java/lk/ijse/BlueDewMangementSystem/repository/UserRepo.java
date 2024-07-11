package lk.ijse.BlueDewMangementSystem.repository;

import lk.ijse.BlueDewMangementSystem.db.dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT user_id FROM user";

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
