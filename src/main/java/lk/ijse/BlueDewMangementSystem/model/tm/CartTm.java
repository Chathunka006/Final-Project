package lk.ijse.BlueDewMangementSystem.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartTm {

    private String FishId;
    private String FishName;
    private int Qty;
    private double Price;
    private double Total;
//    private JFXButton btnRemove;


}
