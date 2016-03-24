package com.ad.jspiner.admmspost.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ad.jspiner.admmspost.Models.MenuModel;
import com.ad.jspiner.admmspost.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 12kd1004 on 2016. 3. 23..
 */
public class MenuAdapter extends BaseAdapter {
    private ArrayList<MenuModel> m_list;
    public Context mContext;

    public MenuAdapter(Context mContext){
        this.mContext = mContext;
        m_list = new ArrayList<MenuModel>();
    }

    public int Additem(MenuModel item){
        m_list.add(item);
        return 1;
    }

    @Override
    public int getCount() {
        return m_list.size();
    }

    @Override
    public Object getItem(int position) {
        return m_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listview_userinfo, parent, false);

        TextView phone = (TextView)convertView.findViewById(R.id.listview_user_phone);
        TextView number = (TextView)convertView.findViewById(R.id.listview_user_number);
        TextView name = (TextView)convertView.findViewById(R.id.listview_user_name);
        TextView date = (TextView)convertView.findViewById(R.id.listview_user_date);
        int is_active = Integer.parseInt( m_list.get(position).is_active);
        if( is_active == 1){
            name.setTextColor(Color.parseColor("#f06292"));
        }

        number.setText(m_list.get(position).number);
        phone.setText(m_list.get(position).phone);
        name.setText(m_list.get(position).name);
        date.setText(m_list.get(position).date);

        return convertView;
    }
}
