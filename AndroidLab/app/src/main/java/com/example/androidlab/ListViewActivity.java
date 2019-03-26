package com.example.androidlab;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {
    private String[] names = new String[]{"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private int[] imageIds = new int[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        //创建List集合，集合元素是Map
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for (int i=0; i<names.length;i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("header",imageIds[i]);
            listItem.put("name",names[i]);
            listItems.add(listItem);
        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
                R.layout.simple_item,
                new String[] {"header","name"},
                new int[] {R.id.header,R.id.name});
        ListView list = findViewById(R.id.myListView);
        // 为ListView设置Adapter
        list.setAdapter(simpleAdapter);

        // 为ListView的列表项的单击事件绑定事件监听器
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // 第position项被单击时激发该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {   // Toast是Android中的一种简易消息提示框
                Toast.makeText(getApplicationContext(), names[position], Toast.LENGTH_LONG).show();
            }
        });
    }

}
