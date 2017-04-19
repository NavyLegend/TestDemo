package common.jlt.com.testdemo.pupowindow;

/**
 * Created by Administrator on 2017/4/19.
 */

public class Good {
    private String name;
    private double price;
    private double single_price;
    private int num;

    public Good(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Good(String name, float price,float single_price, int num) {
        this.single_price=single_price;
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public double getSingle_price() {
        return single_price;
    }

    public void setSingle_price(double single_price) {
        this.single_price = single_price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
}
