package me.ry4nn00b.hortifruti.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "product")
public class ProductModel {

    //Product Attribute's
    @Id
    private String id;
    private String name;
    private String categoryID;
    private String validity;
    private double price;
    private int amount;

    public ProductModel(){}

    public ProductModel(String id, String name, String categoryID, String validity, double price, int amount){

        this.id = id;
        this.name = name;
        this.categoryID = categoryID;
        this.validity = validity;
        this.price = price;
        this.amount = amount;

    }

    //Getter's and Setter's
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getCategoryID(){
        return categoryID;
    }
    public void setCategoryID(String categoryID){
        this.categoryID = categoryID;
    }

    public String getValidity(){
        return id;
    }
    public void setValidity(String validity){
        this.validity = validity;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }

}
