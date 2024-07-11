package lk.ijse.BlueDewMangementSystem.model;

import lk.ijse.BlueDewMangementSystem.model.tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlaceOrder {
    private Order order;

    private List<CartTm> FishList;


}