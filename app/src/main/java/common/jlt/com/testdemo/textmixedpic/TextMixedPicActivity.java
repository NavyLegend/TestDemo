package common.jlt.com.testdemo.textmixedpic;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.jlt.com.testdemo.R;

public class TextMixedPicActivity extends AppCompatActivity {

    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_mixed_pic);
        textview = ((TextView) findViewById(R.id.textview));
         CharSequence text="如图所示FACE，dsfdsfdddd，如果fdsfs";
        SpannableStringBuilder builder=new SpannableStringBuilder(text);
        //需要替换的字符
        String rexgString="FACE";
        Pattern pattern=Pattern.compile(rexgString);
        Matcher matcher=pattern.matcher(text);
        String rexgString2="如果";
        Pattern pattern2=Pattern.compile(rexgString2);
        Matcher matcher2=pattern2.matcher(text);
        String rexgString3="如图";
        Pattern pattern3=Pattern.compile(rexgString3);
        Matcher matcher3=pattern3.matcher(text);
        while (matcher.find()){
            //找到指定的字符后 setSpan的参数分别为（指定的图片，字符的开始位置，字符的结束位置）
            builder.setSpan(new ImageSpan(TextMixedPicActivity.this,R.mipmap.jiantou,ImageSpan.ALIGN_BASELINE),matcher.start(),matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        while (matcher2.find()){
            //找到指定的字符后 setSpan的参数分别为（指定的图片，字符的开始位置，字符的结束位置）
            builder.setSpan(new ImageSpan(TextMixedPicActivity.this,R.mipmap.jiantl),matcher2.start(),matcher2.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        while (matcher3.find()){
            builder.setSpan(new ForegroundColorSpan(Color.RED),matcher3.start(),matcher3.end(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        textview.setText(builder);

    }
}
