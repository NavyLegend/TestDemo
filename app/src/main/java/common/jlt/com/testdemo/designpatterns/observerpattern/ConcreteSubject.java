package common.jlt.com.testdemo.designpatterns.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体被观察者（ConcreteSubject）
 * <p>
 * 微信公众号是具体主题（具体被观察者），里面存储了订阅该公众号的微信用户，并实现了抽象主题中的方法：
 * Created by Administrator on 2017/9/13.
 */

public class ConcreteSubject implements Subject {

    private List<Observer> weixinUserList = new ArrayList<>();
    private List<Observer> weixinUserList2 = new ArrayList<>();
    //订阅
    @Override
    public void attach(Observer observer) {
        weixinUserList.add(observer);
    }
    //取消订阅
    @Override
    public void detach(Observer observer) {
        weixinUserList2.clear();
        for (Observer observer1 : weixinUserList) {
            if (!((WeixinUser) observer).getName().equals(((WeixinUser) observer1).getName())){
                weixinUserList2.add(observer1);
            }
        }
        weixinUserList.clear();
        weixinUserList.addAll(weixinUserList2);

    }
    //更新
    @Override
    public void notify(String message) {
        for (Observer observer : weixinUserList) {
            observer.update(message);
        }
    }
}
