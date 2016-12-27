package com.example.think.pekingweather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jane on 2016/12/6.
 */

public class Guide extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPagerAdapter vpAdapter;
    private ViewPager vp;
    private List<View> views;

    private ImageView[] dots;
    private int[] ids = {R.id.iv1,R.id.iv2,R.id.iv3};

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);

        SharedPreferences preferences = getSharedPreferences("count",MODE_PRIVATE);
        int count = preferences.getInt("count", 0);
        //如果不是第一次运行，直接跳转到MainActivity页面
        if (count==1) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),MainActivity.class);

            startActivity(intent);
            this.finish();
        } else {
            //判断程序与第几次运行，如果是第一次运行则显示引导页面
            SharedPreferences.Editor editor = preferences.edit();
            //存入数据
            editor.putInt("count", 1);
            //提交修改
            editor.commit();

        }
        initViews();
        init();
        initDots();



//        initViews();
//        initDots();
//        btn = (Button) views.get(2).findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent i = new Intent(Guide.this,MainActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
    }

    void init(){
        btn = (Button) views.get(2).findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Guide.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    void initDots(){
        dots = new ImageView[views.size()];
        for(int i = 0; i<views.size();i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    private void initViews(){
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.page1,null));
        views.add(inflater.inflate(R.layout.page2,null));
        views.add(inflater.inflate(R.layout.page3,null));
        vpAdapter = new ViewPagerAdapter(views,this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int i) {
        for(int a = 0; a<ids.length;a++){
            if(a == i){
                dots[a].setImageResource(R.drawable.yuandian1);
            }
            else{
                dots[a].setImageResource(R.drawable.unfocused);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
