package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name ="name")
    private String name;

    @Column(name="category")
    private String category;


    @Column(name = "expiry")
    private boolean expiry;
 


    public Items() {
    }

    public Items(long id, String name, String category, boolean expiry) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.expiry = expiry;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean getExpiry() {
        return expiry;
    }


    public void setExpiry(boolean date){
        this.expiry=date;

    }

    public void setCategory(String category){
        this.category=category;
    }

   

//    public boolean isExpired(){
//        Date current = new Date();
//        Date expiry = this.expiry;
//
//        int result = current.compareTo(expiry);
//
//        if(result>0){
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }


    @Override
    public String toString(){
        return "Item [id = "+ id +" , name = "+name+" category = "+category+"Expiry =  "+expiry+"]";

    }

    
}
