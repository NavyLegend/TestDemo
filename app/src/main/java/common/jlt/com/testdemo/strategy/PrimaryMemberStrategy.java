package common.jlt.com.testdemo.strategy;

/**
 * 初级会员折扣类
 * Created by Administrator on 2017/2/24.
 */

public class PrimaryMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于初级会员的没有折扣");
        return booksPrice;
    }
}
