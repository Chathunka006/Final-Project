package lk.ijse.BlueDewMangementSystem.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data@AllArgsConstructor@NoArgsConstructor
public class SupplierTm {
    private String SupplierId;
    private String SupplierName;
    private String Description;
    private int Contact;
    private Date Date;
}
