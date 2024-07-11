package lk.ijse.BlueDewMangementSystem.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTm {
    private String EmployeeId;
    private String EmployeeName;
    private int Age;
    private double Salary;
    private java.sql.Date Date;
    private String Attendence;
    private int Contact;
    private String Description;



}

