/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Both;

import coursework.Cheese;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Hassan
 */
public class Parcel implements Serializable{
    
    ArrayList<String> columnName;
    ArrayList<Cheese> data;

    public Parcel(ArrayList<Cheese> data, ArrayList<String> columnName) {

        this.data = data;
        this.columnName = columnName;

    }

    public Parcel() {
        
    }

    public ArrayList<String> getColumnNames() {

        return columnName;

    }
    
    public ArrayList<Cheese> getData(){
        
        return data;
    }


    
}
