package com.example.think.pekingweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
<<<<<<< HEAD
import android.widget.EditText;
=======
>>>>>>> 5515b3ef3795743678c7541c0cf0cfa377ec546a
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.StringTokenizer;
=======
>>>>>>> 5515b3ef3795743678c7541c0cf0cfa377ec546a

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
<<<<<<< HEAD
    //baby
    private EditText mEditText;
    private List<City> mCityList;
    private City citySelected;
=======
>>>>>>> 5515b3ef3795743678c7541c0cf0cfa377ec546a

    ArrayList<String> city = new ArrayList<String>();
    ArrayList<String> cityId = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //baby
        mCityList = MyApplication.getInstance().getCityList();
        for(int i = 0; i < mCityList.size(); ++i) {
            city.add(mCityList.get(i).getNumber().toString());
        }

        setContentView(R.layout.select_city);
        //baby
        mlistView = (ListView)findViewById(R.id.lv_listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,city);

        //baby
        mlistView.setAdapter(adapter);
        cityName = (TextView)findViewById(R.id.title_name);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                cityName.setText("选择城市:" + city.get(i));
                SelectedId = cityId.get(i);
            }
        });

        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        mlistView = (ListView)findViewById(R.id.lv_listView);

<<<<<<< HEAD
        //baby
        mEditText = (EditText)findViewById(R.id.search_edit);
        mEditText.addTextChangedListener(mTextWatcher);

        //mlistView = (ListView)findViewById(R.id.lv_listView);

        //babyDelect
//        App = (MyApplication)getApplication();
//        data = App.getCityList();
//        int i = 0;
//        while(i < data.size()){
//            city.add(data.get(i).getCity().toString());
//            cityId.add(data.get(i++).getNumber().toString());
//            i++;
//        }
=======
        App = (MyApplication)getApplication();
        data = App.getCityList();
        int i = 0;
        while(i < data.size()){
            city.add(data.get(i).getCity().toString());
            cityId.add(data.get(i++).getNumber().toString());
            i++;
        }
>>>>>>> 5515b3ef3795743678c7541c0cf0cfa377ec546a
        //setContentView(R.layout.select_city);

        //获取listView控件
        //ListView listView = (ListView)findViewById(R.id.lv_listView);
        //创建一个功能数组
        //String[] str = {"北京","上海","成都"};

<<<<<<< HEAD
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        //mlistView.setAdapter(adapter);

        cityName = (TextView)findViewById(R.id.title_city_name);


        //selectcity = (TextView)findViewById(R.id.select_city_name);
        //babyMayDelect
//        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(SelectCity.this,"你单击了："+city.get(position),Toast.LENGTH_SHORT).show();
//                SelectedId = cityId.get(position);
//            }
//        });

        //baby
        mEditText = (EditText)findViewById(R.id.search_edit);
        mEditText.addTextChangedListener(mTextWatcher);
        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);

=======
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
>>>>>>> 5515b3ef3795743678c7541c0cf0cfa377ec546a
    }
    //baby
    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            temp = charSequence;
            Log.d("SelectCity","beforeTextChanged: " + temp);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //final List<City> mUpdateList = new ArrayList<City>();
            final List<String> mUpdateList = new ArrayList<String>();
            for(City c : mCityList){
                //若匹配，则加入到更新列表中
                if(c.getCity().contains(charSequence)){
                    mUpdateList.add(c.getCity());
                }
            }

            //ListView mListView = (ListView)findViewById(R.id.lv_listView);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mUpdateList);
            //mListView.setAdapter(adapter);
            mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id){
                    //citySelected = mUpdateList.get(i);
                    SelectedId = mUpdateList.get(i);
                    cityName.setText("选择城市：" + citySelected.getCity());
                }
            });
        }

        @Override
        public void afterTextChanged(Editable editable) {
            editStart = mEditText.getSelectionStart();
            editEnd = mEditText.getSelectionEnd();
            if(editable.length() > 10){
                Toast.makeText(SelectCity.this,"输入字数超过限制！",Toast.LENGTH_SHORT).show();
                editable.delete(editStart-1,editEnd);
                int tempSelection = editStart;
                mEditText.setText(editable);
                mEditText.setSelection(tempSelection);
            }

        }
    };

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                Intent i = new Intent();
                //babyChange
                //i.putExtra("cityCode","101160101");
                i.putExtra("CityCode",SelectedId);

                setResult(RESULT_OK,i);
                finish(); //退出该Activity
                break;
<<<<<<< HEAD

            //babyDelect
//            case R.id.lv_listView:
//                Intent j = new Intent();
//                j.putExtra("cityCode",SelectedId);
//                setResult(RESULT_OK,j);
//                finish();
//                break;

=======
            case R.id.lv_listView:
                Intent j = new Intent();
                j.putExtra("cityCode",SelectedId);
                setResult(RESULT_OK,j);
                finish();
                break;
>>>>>>> 5515b3ef3795743678c7541c0cf0cfa377ec546a
            default:
                break;
        }
    }
}
