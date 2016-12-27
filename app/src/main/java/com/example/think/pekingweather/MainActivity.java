package com.example.think.pekingweather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.chensi.bean.TodayWeather;
import cn.edu.pku.chensi.util.NetUtil;

public class MainActivity extends Activity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private static final int UPDATE_TODAY_WEATHER = 1;

    private ImageView mUpdateBtn;

    private ImageView mCitySelect;

    private TextView cityTv, timeTv, humidityTv, weekTv, pmDataTv,
            pmQualityTv,
            temperatureTv, climateTv, windTv, city_name_Tv;
    private ImageView weatherImg, pmImg;
    private ViewPager vp;
    private ViewPager vp2;
    private List<View> views;
    private List<View> views2;
    private ViewPagerAdapter vpAdapter2;

    private ImageView[] dots2;
    private int[] ids2 = {R.id.iv4,R.id.iv5};
    //private ViewGroup dotsGroup = null ;


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case UPDATE_TODAY_WEATHER:
                    updateTodayWeather((TodayWeather) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    void initView(){
        city_name_Tv = (TextView) findViewById(R.id.title_city_name);
        cityTv = (TextView) findViewById(R.id.city);
        timeTv = (TextView) findViewById(R.id.time);
        humidityTv = (TextView) findViewById(R.id.humidity);
        weekTv = (TextView) findViewById(R.id.week_today);
        pmDataTv = (TextView) findViewById(R.id.pm_data);
        pmQualityTv = (TextView) findViewById(R.id.pm2_5_quality
        );
        pmImg = (ImageView) findViewById(R.id.pm2_5_img);
        temperatureTv = (TextView) findViewById(R.id.temperature
        );
        climateTv = (TextView) findViewById(R.id.climate);
        windTv = (TextView) findViewById(R.id.wind);
        weatherImg = (ImageView) findViewById(R.id.weather_img);
        city_name_Tv.setText("N/A");
        cityTv.setText("N/A");
        timeTv.setText("N/A");
        humidityTv.setText("N/A");
        pmDataTv.setText("N/A");
        pmQualityTv.setText("N/A");
        weekTv.setText("N/A");
        temperatureTv.setText("N/A");
        climateTv.setText("N/A");
        windTv.setText("N/A");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info);

        mUpdateBtn = (ImageView) findViewById(R.id.title_update_btn);
        mUpdateBtn.setOnClickListener(this);

        if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
            Log.d("myWeather", "网络OK");
            Toast.makeText(MainActivity.this,"网络OK！", Toast.LENGTH_LONG).show();
        }else
        {
            Log.d("myWeather", "网络挂了");
            Toast.makeText(MainActivity.this,"网络挂了！", Toast.LENGTH_LONG).show();
        }

        mCitySelect = (ImageView) findViewById(R.id.title_city_manager);
        mCitySelect.setOnClickListener(this);

        initView();
        //initView2();

        initViews();
        initDots();

        vp = (ViewPager) findViewById(R.id.viewfuturepager);
        vp2 = (ViewPager) findViewById(R.id.futureDian);
        LayoutInflater inflater = getLayoutInflater();
        View figure1 = inflater.inflate(R.layout.figure1, null);
        View figure2= inflater.inflate(R.layout.figure2, null);
        views=new ArrayList<>();
        views.add(figure1);
        views.add(figure2);

        //实现小圆点
//        dotsGroup = (ViewGroup) findViewById ( R.id.dotsGroup ) ;
//        init_smallDots (0, ) ;


        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }
        };

        if(vp==null){
            Log.i("mmmm","null");
        } else {
            vp.setAdapter(pagerAdapter);
            vp.setOnPageChangeListener(this);
        }


    }


    void initDots(){
        dots2 = new ImageView[2];
        for(int i = 0; i<2;i++){
            dots2[i] = (ImageView) findViewById(ids2[i]);
        }
    }

    private void initViews(){
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        vpAdapter2 = new ViewPagerAdapter(views,this);
        //vp2 = (ViewPager) findViewById(R.id.futureDian);
        //vp2.setAdapter(vpAdapter2);
        //vp2.setOnPageChangeListener(this);
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

//    private void init_smallDots(int index, int size){
//        dotsGroup.removeAllViews();
//
//        //add some dots
//        for(int i=0;i<size;i++){
//            ImageView dot_image = new ImageView(getBaseContext());
//            dot_image.setLayoutParams(new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) ) ;
//            dot_image.setPadding(5,0,5,0);
//            if(i == index){
//                dot_image.setImageResource(R.drawable.yuandian1);
//            }
//            else{
//                dot_image.setImageResource(R.drawable.unfocused);
//            }
//            dotsGroup.addView(dot_image);
//        }
//    }

//    void initDots(){
//        dots2 = new ImageView[views.size()];
//        for(int i = 0; i<views.size();i++){
//            dots2[i] = (ImageView) findViewById(ids2[i]);
//        }
//    }

    /*View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    txt222.setVisibility(View.VISIBLE);
                    break;

                case R.id.btn2:
                    txt222.setVisibility(View.INVISIBLE);
                    break;

                case R.id.btn3:
                    txt222.setVisibility(View.GONE);
                    break;
            }
        }
    };*/



    @Override
    public void onClick(View view){


        if(view.getId() == R.id.title_city_manager){
            Intent i = new Intent(this, SelectCity.class);
            //startActivity(i);
            startActivityForResult(i,1);
        }
        if(view.getId() == R.id.title_update_btn){
            SharedPreferences sharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
            String cityCode = sharedPreferences.getString("main_city_code","101010100");
            Log.d("myWeather",cityCode);


            if(NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE){
                Log.d("myWeather","网络OK");
                queryWeatherCode(cityCode);
            }
            else{
                Log.d("myWeather","网络挂了");
                Toast.makeText(MainActivity.this,"网络挂了！",Toast.LENGTH_LONG).show();
            }
        }
    }
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK){
            String newCityCode = data.getStringExtra("cityCode");
            Log.d("myWeather","选择的城市代码为"+newCityCode);

            if(NetUtil.getNetworkState(this)!=NetUtil.NETWORN_NONE){
                Log.d("myWeather","网络OK");
                queryWeatherCode(newCityCode);
            }
            else{
                Log.d("myWeater","网络挂了");
                Toast.makeText(MainActivity.this,"网络挂了！",Toast.LENGTH_LONG).show();
            }
        }
    }

    private TodayWeather parseXML(String xmldata) {
        TodayWeather todayWeather = null;
        int fengxiangCount = 0;
        int fengliCount = 0;
        int dateCount = 0;
        int highCount = 0;
        int lowCount = 0;
        int typeCount = 0;
        try {
            XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = fac.newPullParser();
            xmlPullParser.setInput(new StringReader(xmldata));
            int eventType = xmlPullParser.getEventType();
            Log.d("myWeather", "parseXML");
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // 判断当前事件是否为文档开始事件
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    // 判断当前事件是否为标签元素开始事件
                    case XmlPullParser.START_TAG:
                        if (xmlPullParser.getName().equals("resp")) {
                            //eventType = xmlPullParser.next();
                            todayWeather = new TodayWeather();
                            //Log.d("myWeather", "city: "+xmlPullParser.getText());
                        }
                        if (todayWeather != null) {
                            if (xmlPullParser.getName().equals("city")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setCity(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("updatetime")) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "updatetime: " +xmlPullParser.getText());
                                todayWeather.setUpdatetime(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("shidu")) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "shidu: "+xmlPullParser.getText());
                                todayWeather.setShidu(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("wendu")) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "wendu: "+xmlPullParser.getText());
                                todayWeather.setWendu(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("pm25")) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "pm25: "+xmlPullParser.getText());
                                todayWeather.setPm25(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("quality")) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "quality: "+xmlPullParser.getText());
                                todayWeather.setQuality(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("fengxiang") && fengxiangCount == 0) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "fengxiang: "+ xmlPullParser.getText());
                                todayWeather.setFengxiang(xmlPullParser.getText());
                                fengxiangCount++;
                            } else if (xmlPullParser.getName().equals("fengli") && fengliCount == 0) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "fengli: "+xmlPullParser.getText());
                                todayWeather.setFengli(xmlPullParser.getText());
                                fengliCount++;
                            } else if (xmlPullParser.getName().equals("date") && dateCount == 0) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "date: "+xmlPullParser.getText());
                                todayWeather.setDate(xmlPullParser.getText());
                                dateCount++;
                            } else if (xmlPullParser.getName().equals("high") && highCount == 0) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "high: "+xmlPullParser.getText());
                                todayWeather.setHigh(xmlPullParser.getText().substring(2).trim());
                                highCount++;
                            } else if (xmlPullParser.getName().equals("low") && lowCount == 0) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "low: "+xmlPullParser.getText());
                                todayWeather.setLow(xmlPullParser.getText().substring(2).trim());
                                lowCount++;
                            } else if (xmlPullParser.getName().equals("type") && typeCount == 0) {
                                eventType = xmlPullParser.next();
                                //Log.d("myWeather", "type: "+xmlPullParser.getText());
                                todayWeather.setType(xmlPullParser.getText());
                                typeCount++;
                            }
                        }
                        break;
                    // 判断当前事件是否为标签元素结束事件
                    case XmlPullParser.END_TAG:
                        break;
                }
                // 进入下一个元素并触发相应事件
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todayWeather;
    }

    void updateTodayWeather(TodayWeather todayWeather){
        city_name_Tv.setText(todayWeather.getCity()+"天气");
        cityTv.setText(todayWeather.getCity());
        timeTv.setText(todayWeather.getUpdatetime()+ "发布");
        humidityTv.setText("湿度："+todayWeather.getShidu());
        pmDataTv.setText(todayWeather.getPm25());
        pmQualityTv.setText(todayWeather.getQuality());
        weekTv.setText(todayWeather.getDate());
        temperatureTv.setText(todayWeather.getHigh()+"~"+todayWeather.getLow());
        climateTv.setText(todayWeather.getType());
        windTv.setText("风力:"+todayWeather.getFengli());
        Toast.makeText(MainActivity.this,"更新成功！",Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param cityCode
     */
    private  void  queryWeatherCode(String cityCode){
        final String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey=" + cityCode;
        Log.d("myWeather",address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                TodayWeather todayWeather = null;
                try{
                    URL url = new URL(address);
                    con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    InputStream in = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String str;
                    while((str=reader.readLine()) !=null){
                        response.append(str);
                        Log.d("myWeather",str);
                    }
                    String responseStr = response.toString();
                    Log.d("myWeather",responseStr);
                    todayWeather = parseXML(responseStr);
                    if(todayWeather != null){
                        Log.d("myWeather", todayWeather.toString());

                        Message msg =new Message();
                        msg.what = UPDATE_TODAY_WEATHER;
                        msg.obj=todayWeather;
                        mHandler.sendMessage(msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(con != null){
                        con.disconnect();
                    }
                }
            }
        }).start();
    }


}
