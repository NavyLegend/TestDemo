package common.jlt.com.testdemo.designpatterns.strategy;

/**
 * 中级会员折扣类
 * Created by Administrator on 2017/2/24.
 */

public class IntermediateMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于中级会员的折扣为10%");
        return booksPrice*0.9;
    }
}
