package Homework3;

public class MilkTea {
    private final StringBuffer size;
    private final StringBuffer base;
    private final StringBuffer charge;
    private int price;
    public MilkTea() {
        this.size = new StringBuffer();
        this.base = new StringBuffer();
        this.charge = new StringBuffer();
        this.price = 0;
    }

    public int getPrice() {
        return price;
    }

    public void addPrice(int price) {
        this.price += price;
    }

    public String getSize() {
        return size.toString();
    }

    public void setSize(String size) {
        this.size.append(size);
    }

    public String getBase() {
        return base.toString();
    }

    public void setBase(String base) {
        this.base.append(base);
    }

    public String getCharge() {
        return charge.toString();
    }

    public void setCharge(String charge) {
        this.charge.append(charge).append(" ");
    }
}
