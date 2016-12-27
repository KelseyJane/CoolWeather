package com.example.think.pekingweather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by jane on 2016/12/26.
 */

public class Guide2 extends Activity implements ViewPager.OnPageChangeListener{

    private ViewPagerAdapater2 vpAdapter2;
    private ViewPager vp2;
    private List<View> views2;

    private ImageView[] dots2;
    private int[] ids2 = {R.id.iv4,R.id.iv5};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_figure);
    }

    void initDots2(){
        dots2 = new ImageView[views2.size()];
        for(int i = 0; i < views2.size(); i++){
            dots2[i] = (ImageView)findViewById(ids2[i]);
        }
    }

    private void initViews2(){
        LayoutInflater inflater2 = LayoutInflater.from(this);
        views2 = new ArrayList<View>();
        views2.add(inflater2.inflate(R.layout.figure1,null));
        views2.add(inflater2.inflate(R.layout.figure2,null));

        vpAdapter2 = new ViewPagerAdapater2(views2,this);
        vp2 = (ViewPager)findViewById(R.id.viewfuturepager);
        vp2.setAdapter(vpAdapter2);
        vp2.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int i) {
        for(int a=0; a<ids2.length; a++){
            if(a == i){
                dots2[a].setImageResource(R.drawable.yuandian1);
            }
            else{
                dots2[a].setImageResource(R.drawable.unfocused);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
