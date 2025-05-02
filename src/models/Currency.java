package models;

import java.time.LocalDate;

public class Currency {

    //TODO

    private double DKKToDKK;
    private double EURToDKK;
    private double USDToDKK;
    private double SEKToDKK;
    private double NOKToDKK;
    private double GBPToDKK;
    private double JPYToDKK;
    private double CHFToDKK;
    private double AUDToDKK;
    private double CADToDKK;
    private String base_Currency;
    private LocalDate LastUpdate;

    public Currency(double DKKToDKK, double EURToDKK, double USDToDKK, double SEKToDKK, double NOKToDKK, double GBPToDKK, double CHFToDKK, double JPYToDKK, double AUDToDKK, double CADToDKK, String base_Currency, LocalDate lastUpdate) {
        this.DKKToDKK = DKKToDKK;
        this.EURToDKK = EURToDKK;
        this.USDToDKK = USDToDKK;
        this.SEKToDKK = SEKToDKK;
        this.NOKToDKK = NOKToDKK;
        this.GBPToDKK = GBPToDKK;
        this.CHFToDKK = CHFToDKK;
        this.JPYToDKK = JPYToDKK;
        this.AUDToDKK = AUDToDKK;
        this.CADToDKK = CADToDKK;
        this.base_Currency = base_Currency;
        LastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "DKKToDKK=" + DKKToDKK +
                ", EURToDKK=" + EURToDKK +
                ", USDToDKK=" + USDToDKK +
                ", SEKToDKK=" + SEKToDKK +
                ", NOKToDKK=" + NOKToDKK +
                ", GBPToDKK=" + GBPToDKK +
                ", JPYToDKK=" + JPYToDKK +
                ", CHFToDKK=" + CHFToDKK +
                ", AUDToDKK=" + AUDToDKK +
                ", CADToDKK=" + CADToDKK +
                ", base_Currency='" + base_Currency + '\'' +
                ", LastUpdate=" + LastUpdate +
                '}';
    }

    /// Attributes


    public double getDKKToDKK() {
        return DKKToDKK;
    }

    public void setDKKToDKK(double DKKToDKK) {
        this.DKKToDKK = DKKToDKK;
    }

    public double getEURToDKK() {
        return EURToDKK;
    }

    public void setEURToDKK(double EURToDKK) {
        this.EURToDKK = EURToDKK;
    }

    public double getSEKToDKK() {
        return SEKToDKK;
    }

    public void setSEKToDKK(double SEKToDKK) {
        this.SEKToDKK = SEKToDKK;
    }

    public double getUSDToDKK() {
        return USDToDKK;
    }

    public void setUSDToDKK(double USDToDKK) {
        this.USDToDKK = USDToDKK;
    }

    public double getNOKToDKK() {
        return NOKToDKK;
    }

    public void setNOKToDKK(double NOKToDKK) {
        this.NOKToDKK = NOKToDKK;
    }

    public double getGBPToDKK() {
        return GBPToDKK;
    }

    public void setGBPToDKK(double GBPToDKK) {
        this.GBPToDKK = GBPToDKK;
    }

    public double getJPYToDKK() {
        return JPYToDKK;
    }

    public void setJPYToDKK(double JPYToDKK) {
        this.JPYToDKK = JPYToDKK;
    }

    public double getCHFToDKK() {
        return CHFToDKK;
    }

    public void setCHFToDKK(double CHFToDKK) {
        this.CHFToDKK = CHFToDKK;
    }

    public double getAUDToDKK() {
        return AUDToDKK;
    }

    public void setAUDToDKK(double AUDToDKK) {
        this.AUDToDKK = AUDToDKK;
    }

    public double getCADToDKK() {
        return CADToDKK;
    }

    public void setCADToDKK(double CADToDKK) {
        this.CADToDKK = CADToDKK;
    }

    public String getBase_Currency() {
        return base_Currency;
    }

    public void setBase_Currency(String base_Currency) {
        this.base_Currency = base_Currency;
    }

    public LocalDate getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        LastUpdate = lastUpdate;
    }
/// Constructor


    /// Getters





    /// toString

}
