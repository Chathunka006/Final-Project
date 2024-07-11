package lk.ijse.BlueDewMangementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor@NoArgsConstructor@Data
public class Supplier {
    private String SupplierId;
    private String SupplierName;
    private String Description;
    private int Contact;
    private Date Date;

}
