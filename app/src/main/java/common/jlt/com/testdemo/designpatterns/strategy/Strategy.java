package common.jlt.com.testdemo.designpatterns.strategy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import common.jlt.com.testdemo.R;

/**
 * 策略者模式案例
 */
public class Strategy extends AppCompatActivity {

    private EditText price_edit;
    private RadioGroup radioGroup;
    private MemberStrategy strategy;
    private Price price;
    private double quote;
    private TextView realPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);
        price_edit = ((EditText) findViewById(R.id.price));
        realPrice = ((TextView) findViewById(R.id.realPrice));
        radioGroup = ((RadioGroup) findViewById(R.id.radioGroup));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    //选择并创建需要使用的策略对象
                    case R.id.radioButton1:
                        strategy = new PrimaryMemberStrategy();
                        //创建环境
                        price = new Price(strategy);
                        quote = price.calculate(Double.parseDouble(price_edit.getText().toString()));
                        realPrice.setText(String.valueOf(quote));
                        break;
                    case R.id.radioButton2:
                        strategy = new IntermediateMemberStrategy();
                        price = new Price(strategy);
                        quote = price.calculate(Double.parseDouble(price_edit.getText().toString()));
                        realPrice.setText(String.valueOf(quote));
                        break;
                    case R.id.radioButton3:
                        strategy = new AdvancedMemberStrategy();
                        price = new Price(strategy);
                        quote = price.calculate(Double.parseDouble(price_edit.getText().toString()));
                        realPrice.setText(String.valueOf(quote));
                        break;
                }
            }
        });
    }
}
