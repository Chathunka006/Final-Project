package lk.ijse.BlueDewMangementSystem.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor@Data@AllArgsConstructor
public class FishTm {
    private String FishId;
    private String FishName;
    private int Count;
    private double Price;
    private  String TankId;
    private int Qty;
    private String SupplierId;


}
