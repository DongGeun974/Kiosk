package com.example.myapplication.ui.MenuMain;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.orderMenuData.OrderMenu;
import com.example.myapplication.data.orderMenuData.OrderMenuList;
import com.example.myapplication.ui.bottomBar.InitBottomBar;

import static com.example.myapplication.ui.InitActivity.getFunctionState;

/**
 * 하단에 보이는 노란 배경 장바구니 리스트뷰 어댑터
 */
public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    OrderMenuList sample;

    public MyAdapter(Context context, OrderMenuList data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.getOrderMenuList().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public OrderMenu getItem(int position) {
        return sample.getOrderMenuList().get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.sample_list_view, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.listmenuimg);
        TextView name = (TextView)view.findViewById(R.id.listmenuname);
        TextView count = (TextView)view.findViewById(R.id.listmenucount);

        String url = sample.getOrderMenuList().get(position).getMenu().getUrl();

        if((getFunctionState() & InitBottomBar.COLORBLIND) != 0)
            url = url.replace("original", "colorblind");

        Glide.with(view.getContext()).load(url).into(imageView);
        Log.d("마이어뎁터에서", sample.getOrderMenuList().get(position).getMenu().getUrl());

        name.setText(String.valueOf(sample.getOrderMenuList().get(position).getMenu().getName()));
        count.setText(String.valueOf(sample.getOrderMenuList().get(position).getQuantity()));

        return view;
    }

}
