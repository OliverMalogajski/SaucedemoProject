package models;

public class Products {
    private String name;
    private double price;
    private String dsc;

    public Products( String name, double price, String dsc) {
        this.name = name;
        this.price = price;
        this.dsc = dsc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }
}
