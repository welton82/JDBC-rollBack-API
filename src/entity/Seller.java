package entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Seller {
    private Integer id;
    private String name;
    private String email;
    private java.sql.Date birthdate;
    private Double baseSalary;
    private Integer departmentId;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Seller() {
    }

    public Seller(String name, String email, String birth, Double baseSalary, Integer departmentId) {

        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.baseSalary = baseSalary;
        try {
            java.util.Date dt = sdf.parse(birth);
            this.birthdate = (java.sql.Date) new java.sql.Date(dt.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.birthdate = birthdate;
        this.baseSalary = baseSalary;
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.sql.Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birth) {
        try {
            java.util.Date dt = sdf.parse(birth);
            this.birthdate = (java.sql.Date) new java.sql.Date(dt.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.birthdate = birthdate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
