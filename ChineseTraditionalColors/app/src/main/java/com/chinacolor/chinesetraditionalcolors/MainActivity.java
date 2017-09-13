package com.chinacolor.chinesetraditionalcolors;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * 已实现：变换背景色功能
 *
 * Todo:
 * 实现Text淡入淡出
 * rgb解析
 */
public class MainActivity extends AppCompatActivity {
    private View bg;
    private GridView gView;
    private TextView title;
    private Typeface tf;
    private TextView color_r;
    private TextView color_g;
    private TextView color_b;



    public int currentColorpos = 0;
    public static final int[] colorValue = {0xff973444, 0xffcc3536, 0xffc43739,0xffdd3b44,0xffdc143c,
    0xffeacdd1, 0xffbb1c33, 0xff89303f, 0xffa54358, 0xff674950,
    0xff643441, 0xff79485a, 0xff793d56, 0xff9c6680, 0xff3e3c3d,
    0xffa71368, 0xffeea5d1, 0xffa22076, 0xffc3a6cb, 0xffab96c5};
    public String[] colorname = {"玫瑰红", "艳红", "猩红", "银朱","洋红"
    ,"浅血牙","月季红", "枣红", "紫粉","茄皮紫",
    "深烟红", "雪紫", "玫瑰灰", "洋葱紫", "元青",
    "品红", "紫薇花", "牵牛紫", "紫水晶", "浅石英紫"};
    public List<Color> colorList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //initialize
        bg = findViewById(R.id.background);
        gView = (GridView)findViewById(R.id.gview);
        colorList = new ArrayList<Color>();
        colorList_init();
        title = (TextView)findViewById(R.id.color_title);
        title.setText(colorname[currentColorpos]);
        color_r = (TextView)findViewById(R.id.color_R);
        color_g = (TextView) findViewById(R.id.color_G);
        color_b = (TextView) findViewById(R.id.color_B);
        int[] argb = parseRGB(colorValue[currentColorpos]);
        color_r.setText(Integer.toString(argb[1]));
        color_g.setText(Integer.toString(argb[2]));
        color_b.setText(Integer.toString(argb[3]));
        bg.setBackgroundColor(colorValue[currentColorpos]);

        //设置typeface
        tf = Typeface.createFromAsset(getAssets(),"fonts/hanyiquantangshijian.ttf");
        title.setTypeface(tf);

        //设置gView适配器
        ColorAdapter adapter = new ColorAdapter(MainActivity.this, R.layout.color_item, colorList);
        gView.setAdapter(adapter);

        //为每节一个item设置监听事件
        gView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                crossfade(colorValue[currentColorpos], colorValue[i],colorname[i]);
                currentColorpos = i;

            }
        });

    }

    /*
    背景色渐变和文字渐变
     */
    public void crossfade(int startColor, int endColor, String newColorname){
        ValueAnimator colorAnim = ObjectAnimator.ofInt(bg,"backgroundColor", startColor, endColor);
        colorAnim.setDuration(500);
        colorAnim.setEvaluator(new ArgbEvaluator());

        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(title, "alpha", 1f, 0f);
        fadeAnim.setDuration(500);

        AnimatorSet crossFador = new AnimatorSet();
        crossFador.play(colorAnim).with(fadeAnim);
        crossFador.start();

        title.setText(newColorname);
        //十进制变换rgb
        int[] argb = parseRGB(endColor);
        color_r.setText(Integer.toString(argb[1]));
        color_g.setText(Integer.toString(argb[2]));
        color_b.setText(Integer.toString(argb[3]));


        ValueAnimator fadeinAnim = ObjectAnimator.ofFloat(title, "alpha", 0f, 1f);
        fadeinAnim.setDuration(500);
        fadeinAnim.start();

    }

    /**
     * 初始化颜色数据
     */
    public void colorList_init(){
        Color color;
        for (int i = 0; i<colorValue.length; i++){
            color = new Color(colorname[i], colorValue[i]);
            colorList.add(color);
        }
    }

    /**
     * 解析int类型RGB
     * @param colorValue
     * @return int[](a, r, g, b)
     */
    public int[] parseRGB(int colorValue){
        int[] rgb = {0,0,0,0};
        rgb[0] = (colorValue >> 24) & 0xff; //a
        rgb[1] = (colorValue >> 16) & 0xff; //r
        rgb[2] = (colorValue >> 8) & 0xff;//g
        rgb[3] = (colorValue) & 0xff; //b
        return rgb;
    }

}


