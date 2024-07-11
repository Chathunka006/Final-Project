package lk.ijse.BlueDewMangementSystem.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor


public class FishOrderInfo {

    private String order_id;
    private String fish_id;
    private int qty;
    private double price;
}
