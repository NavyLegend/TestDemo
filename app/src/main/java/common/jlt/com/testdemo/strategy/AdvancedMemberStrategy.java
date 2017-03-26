package common.jlt.com.testdemo.strategy;

/**
 * Created by Administrator on 2017/2/24.
 */

public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于高级会员的折扣为20%");
        return booksPrice*0.8;
    }
}
