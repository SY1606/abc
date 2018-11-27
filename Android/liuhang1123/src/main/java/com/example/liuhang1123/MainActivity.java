package com.example.liuhang1123;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.liuhang1123.frag.Frag01;
import com.example.liuhang1123.frag.Frag02;
import com.example.liuhang1123.frag.Frag03;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private ListView listView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        drawerLayout = findViewById(R.id.drawer);
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        final Frag01 frag01 = new Frag01();
        final Frag02 frag02 = new Frag02();
        final Frag03 frag03 = new Frag03();
        transaction.add(R.id.fragment,frag01).show(frag01);
        transaction.add(R.id.fragment,frag02).hide(frag02);
        transaction.add(R.id.fragment,frag03).hide(frag03);

        transaction.commit();

        rg = findViewById(R.id.rg);
        rg.check(rg.getChildAt(0).getId());
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case 1:
                        transaction1.show(frag01).hide(frag02).hide(frag03);
                        break;
                    case 2:
                        transaction1.show(frag02).hide(frag01).hide(frag03);
                        break;
                    case 3:
                        transaction1.show(frag03).hide(frag02).hide(frag01);
                        break;
                }
                transaction1.commit();
            }
        });

       //创建集合
        ArrayList<String> list = new ArrayList<>();
        //循环n个条目
        for (int i=0;i<3;i++){
            switch (i){
                case 0:
                    list.add("我的收藏");
                    break;
                case 1:
                    list.add("我的关注");
                    break;
                case 2:
                    list.add("我的帖子");
                    break;
            }
        }
        //适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction2 = manager.beginTransaction();
                switch (position){
                    case 0:
                        transaction2.show(frag01).hide(frag02).hide(frag03);
                        rg.check(rg.getChildAt(position).getId());
                        break;
                    case 1:
                        transaction2.show(frag02).hide(frag01).hide(frag03);
                        rg.check(rg.getChildAt(position).getId());
                        break;
                    case 2:
                        transaction2.show(frag03).hide(frag02).hide(frag01);
                        rg.check(rg.getChildAt(position).getId());
                        break;
                }
                transaction2.commit();
                drawerLayout.closeDrawers();
            }
        });

    }
}
