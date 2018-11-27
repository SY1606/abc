package com.example.x1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int a[] = {R.drawable.q1,R.drawable.q2,R.drawable.q3};

        pager = findViewById(R.id.pager);
        final ArrayList<View> views = new ArrayList<View>();
        for (int i=0;i<a.length-1;i++){
            View viewa = View.inflate(MainActivity.this,R.layout.ye1,null);

            viewa.setBackgroundResource(a[i]);
            views.add(viewa);
        }
        View viewb = View.inflate(MainActivity.this,R.layout.ye2,null);
        viewb.setBackgroundResource(a[2]);
        views.add(viewb);

        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view==o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view1 = views.get(position);
                container.addView(view1);
                return view1;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View)object);
            }
        });

        rg = findViewById(R.id.radioGroup1);
        for (int i=0;i<views.size();i++){
            RadioButton radioButton = new RadioButton(MainActivity.this);
            rg.addView(radioButton);
        }
        rg.check(rg.getChildAt(0).getId());
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                rg.check(rg.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewb.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TwoActivity.class));
                finish();
            }
        });
    }



}
