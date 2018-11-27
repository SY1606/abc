package com.example.liuhang1123.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuhang1123.R;
import com.example.liuhang1123.bean.User;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User.DataBean> list;

    public MyAdapter(Context context, ArrayList<User.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User.DataBean bean = list.get(position);
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case 0:
                ViewHolder1 holder1 = null;
                if (convertView==null){
                    convertView = View.inflate(context, R.layout.item_a,null);

                    holder1 = new ViewHolder1();

                    holder1.text1 = convertView.findViewById(R.id.text1);
                    holder1.textView2 = convertView.findViewById(R.id.textview2);
                    holder1.text3 = convertView.findViewById(R.id.text3);
                    holder1.text4 = convertView.findViewById(R.id.text4);
                    holder1.image_a = convertView.findViewById(R.id.image_a);

                    convertView.setTag(holder1);
                }else{
                    holder1 = (ViewHolder1) convertView.getTag();
                }
                holder1.text1.setText(bean.getTitle());
                holder1.textView2.setText(bean.getDate());
                holder1.text3.setText(bean.getCategory());
                holder1.text4.setText(bean.getAuthor_name());
                ImageLoader.getInstance().displayImage(bean.getThumbnail_pic_s02(),holder1.image_a);

                break;
            case 1:
                ViewHolder2 holder2 = null;
                if (convertView==null){
                    convertView =View.inflate(context,R.layout.item_b,null);
                    holder2 = new ViewHolder2();

                    holder2.texta = convertView.findViewById(R.id.texta);
                    holder2.textb = convertView.findViewById(R.id.textb);
                    holder2.textc = convertView.findViewById(R.id.textc);
                    holder2.textd = convertView.findViewById(R.id.textd);

                    convertView.setTag(holder2);
                }else{
                    holder2 = (ViewHolder2) convertView.getTag();
                }
                holder2.texta.setText(bean.getTitle());
                holder2.textb.setText(bean.getDate());
                holder2.textc.setText(bean.getCategory());
                holder2.textd.setText(bean.getAuthor_name());
                break;
        }

        return convertView;
    }
    class ViewHolder1{
        TextView text1;
        TextView textView2;
        TextView text3;
        TextView text4;
        ImageView image_a;
    }
    class ViewHolder2{
        TextView texta;
        TextView textb;
        TextView textc;
        TextView textd;

    }
}
