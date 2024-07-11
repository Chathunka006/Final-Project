package lk.ijse.BlueDewMangementSystem.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class OrderTm {
    private String OrderId;
    private java.util.Date Date;
    private String CustomerId;
    private double Price;
    private int Qty;

}
