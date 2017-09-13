package common.jlt.com.testdemo.designpatterns.strategy;

/**
 * 价格类
 * Created by Administrator on 2017/2/24.
 */

public class Price {
    private MemberStrategy strategy;

    /**
     * 构造函数，传入一个具体的策略对象
     * @param strategy
     */
    public Price(MemberStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 通过传入的不同策略，来计算不同的价格
     * @param booksPrice
     * @return
     */
    public double calculate(double booksPrice){
        return this.strategy.calcPrice(booksPrice);
    }
}
