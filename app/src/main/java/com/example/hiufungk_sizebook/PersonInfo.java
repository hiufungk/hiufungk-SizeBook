package com.example.hiufungk_sizebook;

import android.util.Log;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by Kevin on 26-Jan-17.
 */

public class PersonInfo {
    private String name;
    private String date; //yyyy-mm-dd
    private Double neck;
    private Double bust;
    private Double chest;
    private Double waist;
    private Double hip;
    private Double inseam;
    private String comment;

    //constructor
    public PersonInfo(String name) {

        this.name = name;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Double getNeck() {
        return neck;
    }

    public Double getBust() {
        return bust;
    }

    public Double getChest() {
        return chest;
    }

    public Double getWaist() {
        return waist;
    }

    public Double getHip() {
        return hip;
    }

    public Double getInseam() {
        return inseam;
    }

    public String getComment() {
        return comment;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) { this.date = date; }

    public void setNeck(String neckStr) throws InputNumberException {
        try {
            if (neckStr.length() != 0) {

                Double neckNum = Double.parseDouble(neckStr);
                //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                ////2017-01-28 16:42
                this.neck = (double) Math.round(neckNum * 10d) / 10d;
            }
        }catch (NumberFormatException e){
            throw new InputNumberException();
        }

    }

    public void setBust(String bustStr) throws InputNumberException {
        try {
            if (bustStr.length() != 0) {

                Double bustNum = Double.parseDouble(bustStr);
                //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                ////2017-01-28 16:42
                this.bust = (double) Math.round(bustNum * 10d) / 10d;
            }
        }catch (NumberFormatException e){
            throw new InputNumberException();
        }
    }


    public void setChest(String  chestStr) throws InputNumberException {
        try {
            if (chestStr.length() != 0) {

                Double chestNum = Double.parseDouble(chestStr);
                //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                ////2017-01-28 16:42
                this.chest = (double) Math.round(chestNum * 10d) / 10d;
            }
        }catch (NumberFormatException e){
            throw new InputNumberException();
        }
    }

    public void setWaist(String waistStr) throws InputNumberException {
        try {
            if (waistStr.length() != 0) {

                Double waistNum = Double.parseDouble(waistStr);
                //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                ////2017-01-28 16:42
                this.bust = (double) Math.round(waistNum * 10d) / 10d;
            }
        }catch (NumberFormatException e){
            throw new InputNumberException();
        }
    }

    public void setHip(String hipStr) throws InputNumberException {
        try {
            if (hipStr.length() != 0) {

                Double hipNum = Double.parseDouble(hipStr);
                //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                ////2017-01-28 16:42
                this.bust = (double) Math.round(hipNum * 10d) / 10d;
            }
        }catch (NumberFormatException e){
            throw new InputNumberException();
        }
    }

    public void setInseam(String inseamStr) throws InputNumberException {
        try {
            if (inseamStr.length() != 0) {

                Double inseamNum = Double.parseDouble(inseamStr);
                //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                ////2017-01-28 16:42
                this.bust = (double) Math.round(inseamNum * 10d) / 10d;
            }
        }catch (NumberFormatException e){
            throw new InputNumberException();
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
