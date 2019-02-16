package coursework;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author Hassan
 */
public class Cheese implements Serializable {

    

    ArrayList<String> columnName;
    ArrayList<Cheese> data;

    Statement st;
    ResultSet rs;

    private String id;
    private String cheeseName;
    private String cheeseType;
    private String style;
    private String origin;
    private String age;
    private String milk;
    private String approval;
    private String strength;
    private String quantity;
    private String cost;
//    Cheese myCheese = new Cheese();

    public Cheese() {
        
    }

    public void getColumns(int row, String value) {
        
        switch(row){
        
            case 0:
                this.id = value;
            case 1:
                this.cheeseName = value;
            case 2: 
                this.cheeseType = value;
            case 3:
                this.style = value;
            case 4:
                this.origin = value;
            case 5:
                this.age = value;
            case 6:
                this.milk = value;
            case 7:
                this.approval = value;
            case 8:
                this.strength = value;
            case 9:
                this.quantity = value;
            case 10:
                this.cost = value;
                
                
        }
        

    }
    
    public String getRows(int row) {
        
            switch(row){
            case 0:
                return id;
            case 1:
                return cheeseName;
            case 2: 
                return cheeseType;
            case 3:
                return style;
            case 4:
                return origin;
            case 5:
                return age;
            case 6:
                return milk;
            case 7:
                return approval;
            case 8:
                return strength;
            case 9:
                return quantity;
            case 10:
                return cost;
                
            }
            
            return "Error!!";
    }

    public Cheese(String ID, String CheeseName, String CheeseType, String Style,
            String Origin, String Age, String Milk, String Approval,
            String Strength, String Quantity, String Cost) {

        this.id = ID;
        this.cheeseName = CheeseName;
        this.cheeseType = CheeseType;
        this.style = Style;
        this.origin = Origin;
        this.age = Age;
        this.milk = Milk;
        this.approval = Approval;
        this.strength = Strength;
        this.quantity = Quantity;
        this.cost = Cost;

    }

    /**
     * the code below is used to make methods for each column
     *
     * @return
     */
    public String getid() {
        return id;
    }

    public String getnameCheese() {
        return cheeseName;
    }

    public String getcheeseType() {
        return cheeseType;
    }

    public String getstyle() {
        return style;
    }

    public String getorigin() {
        return origin;
    }

    public String getage() {
        return age;
    }

    public String getmilk() {
        return milk;
    }

    public String getapproval() {
        return approval;
    }

    public String getstrength() {
        return strength;
    }

    public String getquantity() {
        return quantity;
    }

    public String getcost() {
        return cost;
    }

}
