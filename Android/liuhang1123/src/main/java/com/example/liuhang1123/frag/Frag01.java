package com.example.liuhang1123.frag;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.liuhang1123.R;
import com.example.liuhang1123.adapter.MyAdapter;
import com.example.liuhang1123.bean.User;
import com.example.liuhang1123.sql.Dao;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import java.util.ArrayList;
import java.util.List;

public class Frag01 extends Fragment {
    String urlString ="http://www.xieast.com/api/news/news.php?page";
    ArrayList<User.DataBean> list = new ArrayList<>();
    int page;
    private PullToRefreshListView plv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag01,container,false);

        plv = view.findViewById(R.id.plv);
        plv.setMode(PullToRefreshListView.Mode.BOTH);
        init(page);
        //适配器
        MyAdapter adapter = new MyAdapter(getActivity(),list);
        plv.setAdapter(adapter);
        //上下拉
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            //上拉加载
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                list.clear();
                init(0);
            }
            //下拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page++;
                init(page);
            }
        });
        //刷新适配器
        adapter.notifyDataSetChanged();
        return view;
    }

    //判断网络
    private void init(int page) {
        String string = urlString+page;
        //如果有的话就执行异步
        if (NetStateUtil.isConn(getActivity())){
            new MySyncTask().execute(string);
            //没网就去设置网络
        }else{
            NetStateUtil.showNoNetWorkDlg(getActivity());
            Toast.makeText(getActivity(),"没网",Toast.LENGTH_SHORT).show();
            Dao dao = new Dao(getActivity());
            Cursor cursor = dao.query("abc",null,null,null,null,null,null);
            ArrayList<User.DataBean> lista = new ArrayList<>();
            if (cursor.moveToFirst()){
                do {
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    User.DataBean bean = new User.DataBean(title);
                    lista.add(bean);
                }
                while (cursor.moveToNext());

            }
            list.addAll(lista);
        }
    }

    //执行异步
    private class MySyncTask extends AsyncTask<String,Void,String> {

        private Long insert;

        @Override
        protected String doInBackground(String... strings) {
            String string = Net.getJson(strings[0]);
            return string;
        }


        //解析Gson
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            User data = gson.fromJson(s,User.class);
            List<User.DataBean> bean = data.getData();

            list.addAll(bean);
            //取消刷新头和刷新尾
            plv.onRefreshComplete();

            Dao dao = new Dao(getActivity());

            //添加数据库
            for (int i=0;i<list.size();i++){
                User.DataBean bean1 = list.get(i);
                ContentValues values = new ContentValues();
                values.put("title",bean1.getTitle());
                values.put("date",bean1.getDate());
                values.put("cetagory",bean1.getCategory());
                values.put("author_name",bean1.getAuthor_name());

                insert = dao.insert("abc",null,values);

            }
            Toast.makeText(getActivity(),"添加到数据库的条数为:"+insert,Toast.LENGTH_SHORT).show();
        }
    }
}
