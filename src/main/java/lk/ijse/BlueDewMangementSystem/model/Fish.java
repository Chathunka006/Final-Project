package lk.ijse.BlueDewMangementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class Fish {
    private String FishId;
    private String FishName;
    private int Count;
    private double Price;
    private  String TankId;
    private int Qty;
    private String SupplierId;




}
