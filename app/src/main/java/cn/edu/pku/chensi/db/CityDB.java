package cn.edu.pku.chensi.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.think.pekingweather.R;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.chensi.bean.City;

/**
 * Created by think on 2016/11/1.
 */
public class CityDB {

    public static final String CITY_DB_NAME = "city.db";
    private static final String CITY_TABLE_NAME = "city";
    private SQLiteDatabase db;
    public CityDB(Context context, String path) {
        db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
    }
    public List<City> getAllCity() {
        List<City> list = new ArrayList<City>();
        Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME, null);
        while (c.moveToNext()) {
            String province = c.getString(c.getColumnIndex("province"));
                    String city = c.getString(c.getColumnIndex("city"));
            String number = c.getString(c.getColumnIndex("number"));
                    String allPY = c.getString(c.getColumnIndex("allpy"));
            String allFirstPY = c.getString(c.getColumnIndex("allfirstpy"));
                    String firstPY = c.getString(c.getColumnIndex("firstpy"));
                            City item = new City(province, city, number, firstPY, allPY,allFirstPY);
            list.add(item);
        }
        //ListView ll = (ListView)findViewById(R.id.lv_listView);
        /*LinearLayout ll=(LinearLayout)findViewById(R.id.lv_listView);
        //把数据显示到屏幕
        for(Person p:list)
        {
            //1.集合中每有一条数据，就new一个TextView
            TextView tv=new TextView(this);
            //2.把人物的信息设置为文本的内容
            tv.setText(p.toString());
            tv.setTextSize(18);
            //3.把TextView设置成线性布局的子节点
            ll.addView(tv);
        }*/

        return list;
    }

}
