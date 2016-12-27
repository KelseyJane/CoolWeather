package com.example.think.pekingweather;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jane on 2016/12/26.
 */

public class ViewPagerAdapater2 extends PagerAdapter{
    private List<View> views2;
    private Context context2;

    public ViewPagerAdapater2(List<View> views2, Context context2){
        this.views2 = views2;
        this.context2 = context2;
    }
    @Override
    public int getCount(){
        return views2.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views2.get(position));
        return views2.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView(views2.get(position));
    }

    @Override
    public boolean isViewFromObject(View view2, Object o){
        return (view2 == o);
    }
}
