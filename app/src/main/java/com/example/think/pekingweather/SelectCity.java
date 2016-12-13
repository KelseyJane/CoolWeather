package com.example.think.pekingweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.chensi.app.MyApplication;
import cn.edu.pku.chensi.bean.City;


/**
 * Created by think on 2016/10/18.
 */
public class SelectCity extends Activity implements View.OnClickListener{
    private ImageView mBackBtn;
    private ListView mlistView;
    private MyApplication App;
    private List<City> data;

    private String SelectedId;
    private TextView cityName;
    //private TextView selectcity;

    ArrayList<String> city = new ArrayList<String>();
    ArrayList<String> cityId = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_city);

        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        mlistView = (ListView)findViewById(R.id.lv_listView);

        App = (MyApplication)getApplication();
        data = App.getCityList();
        int i = 0;
        while(i < data.size()){
            city.add(data.get(i).getCity().toString());
            cityId.add(data.get(i++).getNumber().toString());
            i++;
        }
        //setContentView(R.layout.select_city);

        //获取listView控件
        //ListView listView = (ListView)findViewById(R.id.lv_listView);
        //创建一个功能数组
        //String[] str = {"北京","上海","成都"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        mlistView.setAdapter(adapter);

        cityName = (TextView)findViewById(R.id.title_city_name);
        //selectcity = (TextView)findViewById(R.id.select_city_name);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(SelectCity.this,"你单击了："+city.get(position),Toast.LENGTH_SHORT).show();
                SelectedId = cityId.get(position);
            }
        });
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                Intent i = new Intent();
                i.putExtra("cityCode","101160101");
                setResult(RESULT_OK,i);
                finish();
                break;
            case R.id.lv_listView:
                Intent j = new Intent();
                j.putExtra("cityCode",SelectedId);
                setResult(RESULT_OK,j);
                finish();
                break;
            default:
                break;
        }
    }
}
