package Models;

import java.time.LocalDateTime;

public class Currency {

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
    private LocalDateTime LastUpdate;
    private String trying; //skal ikke bruges


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

    public String getBase_Currency() {
        return base_Currency;
    }

    public void setBase_Currency(String base_Currency) {
        this.base_Currency = base_Currency;
    }

    public LocalDateTime getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        LastUpdate = lastUpdate;
    }

    public Currency(double currencyDkk) {
        this.DKKToDKK = DKKToDKK;
    }



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

    public double getUSDToDKK() {
        return USDToDKK;
    }

    public void setUSDToDKK(double USDToDKK) {
        this.USDToDKK = USDToDKK;
    }

    public double getSEKToDKK() {
        return SEKToDKK;
    }

    public void setSEKToDKK(double SEKToDKK) {
        this.SEKToDKK = SEKToDKK;
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
}
