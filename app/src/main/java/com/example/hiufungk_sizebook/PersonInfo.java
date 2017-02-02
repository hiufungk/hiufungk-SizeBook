package com.example.hiufungk_sizebook;

import android.util.Log;
import android.widget.EditText;

import java.util.Date;

import static java.sql.Types.NULL;

/**
 * Created by Kevin on 26-Jan-17.
 */

public class PersonInfo {
    private String name;
    private String date; //yyyy-mm-dd
    private Double neck = 0.0;
    private Double bust = 0.0;
    private Double chest = 0.0;
    private Double waist = 0.0;
    private Double hip = 0.0;
    private Double inseam = 0.0;
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

    public void setDate(String date) throws InputDateException {

        try {
            if (date.length() != 0) {
                String[] parts;
                if (date.contains("-")) {
                    parts = date.split("-");
                }else{
                    throw new InputDateException();
                }
                //yyyy-mm-dd
                Integer year = Integer.parseInt(parts[0]);
                Log.d("myTag", "year is: " + year);
                Integer month = Integer.parseInt(parts[1]);
                Integer day = Integer.parseInt(parts[2]);
                if ((year < 1900) || (year > 9999)) {
                    throw new InputDateException();
                }
                if ((month < 1) || (month > 12)) {
                    throw new InputDateException();
                }

                if (month == 2) {//feb
                    if ((day < 1) || (day > 29)) {
                        throw new InputDateException();
                    }
                } else if (month % 2 == 0) { //even
                    if ((day < 1) || (day > 30)) {
                        throw new InputDateException();
                    }
                } else if (month % 2 == 1) { //odd
                    if ((day < 1) || (day > 31)) {
                        throw new InputDateException();
                    }
                }

                this.date = date;
            }

        }catch (NullPointerException e){
            throw new InputDateException();
        }catch (NumberFormatException e){
            throw new InputDateException();
        }
    }

    public void setNeck(String neckStr) throws InputNumberException {
        try {
            if (neckStr.length() != 0) {

                Double neckNum = Double.parseDouble(neckStr);
                if (neckNum < 0){
                    throw new InputNumberException();
                }
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
                if (bustNum < 0){
                    throw new InputNumberException();
                }
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
                if (chestNum < 0){
                    throw new InputNumberException();
                }
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
                if (waistNum < 0){
                    throw new InputNumberException();
                }
                //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                ////2017-01-28 16:42
                this.waist = (double) Math.round(waistNum * 10d) / 10d;
            }
        }catch (NumberFormatException e){
            throw new InputNumberException();
        }
    }

    public void setHip(String hipStr) throws InputNumberException {
        try {
            if (hipStr.length() != 0) {

                Double hipNum = Double.parseDouble(hipStr);
                if (hipNum < 0){
                    throw new InputNumberException();
                }
                //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                ////2017-01-28 16:42
                this.hip = (double) Math.round(hipNum * 10d) / 10d;
            }
        }catch (NumberFormatException e){
            throw new InputNumberException();
        }
    }

    public void setInseam(String inseamStr) throws InputNumberException {
        try {
            if (inseamStr.length() != 0) {

                Double inseamNum = Double.parseDouble(inseamStr);
                if (inseamNum < 0){
                    throw new InputNumberException();
                }
                //taken from http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
                ////2017-01-28 16:42
                this.inseam = (double) Math.round(inseamNum * 10d) / 10d;
            }
        }catch (NumberFormatException e){
            throw new InputNumberException();
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString(){
        String outStr = "Name: "+this.name;
        if (this.neck > 0){
            outStr += "\nNeck: "+this.neck;
        }
        if (this.bust > 0){
            outStr += "\nBust: "+this.bust;
        }
        if (this.chest > 0){
            outStr += "\nChest: "+this.chest;
        }
        if (this.waist > 0){
            outStr += "\nWaist: "+this.waist;
        }
        if (this.inseam > 0){
            outStr += "\nInseam: "+this.inseam;
        }
        if (this.hip > 0){
            outStr += "\nHip: "+this.hip;
        }

        return outStr;
    }
}
