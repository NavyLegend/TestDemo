package common.jlt.com.testdemo.strategy;

/**
 * 抽象折扣类
 * Created by Administrator on 2017/2/24.
 */

public interface MemberStrategy {
    /**
     * 计算图书价格的抽象接口
     */
    public double calcPrice(double booksPrice);
}
