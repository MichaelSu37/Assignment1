package com.example.ysu3_sizebook;

/**
 * Created by ysu3 on 1/25/17.
 */
public class Record {
    private String name;
    private String date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;
    private String finalString;

    public Record(String name){
        this.name = name;
        this.date = null;
        this.neck = null;
        this.bust = null;
        this.chest = null;
        this.waist = null;
        this.hip = null;
        this.inseam = null;
        this.comment = null;

    }

    public Record(){
        this.name = null;
        this.date = null;
        this.neck = null;
        this.bust = null;
        this.chest = null;
        this.waist = null;
        this.hip = null;
        this.inseam = null;
        this.comment = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getInseam() {
        return inseam;
    }

    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        finalString = null;
        finalString = "Name: " + name + "\n" +
                "bust: " + bust + "\n" +
                "chest: " + chest + "\n" +
                "waist: " + waist + "\n" +
                "inseam: "+ inseam;
        return finalString;
    }
}
