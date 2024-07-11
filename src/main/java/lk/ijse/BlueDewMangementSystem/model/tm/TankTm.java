package lk.ijse.BlueDewMangementSystem.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class TankTm {
    private String TankId;
    private String Size;
    private String Desing;
    private String Description;
    private String UserId;
}
