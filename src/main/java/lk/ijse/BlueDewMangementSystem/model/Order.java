package lk.ijse.BlueDewMangementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data@AllArgsConstructor@NoArgsConstructor
public class Order {
    private String OrderId;
    private Date Date;
    private String CustomerId;
    private double Price;
    private int Qty;


}
