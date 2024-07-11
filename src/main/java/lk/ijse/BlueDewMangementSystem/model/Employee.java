package lk.ijse.BlueDewMangementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Employee {
    private String EmployeeId;
    private String EmployeeName;
    private int Age;
    private double Salary;
    private java.sql.Date Date;
    private String Attendence;
    private int Contact;
    private String Description;
}




