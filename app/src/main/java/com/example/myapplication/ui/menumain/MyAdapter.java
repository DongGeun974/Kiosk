package com.example.myapplication.ui.menumain;

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
import com.example.myapplication.addfunc.AddFunction;
import com.example.myapplication.data.orderData.Order;
import com.example.myapplication.data.orderData.OrderList;
import com.example.myapplication.ui.bottombar.InitBottomBar;


/**
 * 하단에 보이는 노란 배경 장바구니 리스트뷰 어댑터
 */
public class MyAdapter extends BaseAdapter implements AddFunction {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    OrderList sample;

    public MyAdapter(Context context, OrderList data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.getOrderList().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Order getItem(int position) {
        return sample.getOrderList().get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.sample_list_view, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.listmenuimg);
        TextView name = (TextView)view.findViewById(R.id.listmenuname);
        TextView count = (TextView)view.findViewById(R.id.listmenucount);

        String url = sample.getOrderList().get(position).getMenu().getUrl();

        if((InitBottomBar.getState() & InitBottomBar.COLORBLIND) != 0)
            url = url.replace("original", "colorblind");

        url = colorBlind.changeURL(url);

        Glide.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.ic_loading)
                .into(imageView);
        Log.d("마이어뎁터에서", sample.getOrderList().get(position).getMenu().getUrl());

        name.setText(String.valueOf(sample.getOrderList().get(position).getMenu().getName()));
        count.setText(String.valueOf(sample.getOrderList().get(position).getQuantity()));

        return view;
    }

}
