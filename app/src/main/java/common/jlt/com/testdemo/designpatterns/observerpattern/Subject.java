package common.jlt.com.testdemo.designpatterns.observerpattern;

/**抽象被观察者（Subject）
 *
 * 抽象主题，提供了attach、detach、notify三个方法：
 * Created by Administrator on 2017/9/13.
 */

public interface Subject {
    /**
     * 增加订阅者
     */
    public void attach(Observer observer);
    /**
     * 删除订阅者
     */
    public void detach(Observer observer);
    /**
     * 通知订阅者更新消息
     */
    public void notify(String message);
}
