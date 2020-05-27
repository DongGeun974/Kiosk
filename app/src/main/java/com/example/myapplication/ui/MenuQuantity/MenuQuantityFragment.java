package com.example.myapplication.ui.MenuQuantity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.orderMenuData.OrderMenu;
import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.ui.MenuMain.MenuActivity;

import static com.example.myapplication.ui.InitActivity.getDbMenuList;

public class MenuQuantityFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_quantity, container, false);
        int selMenuId = getArguments().getInt("selMenu");
        Menu selMenu = getDbMenuList().getMenuWithId(selMenuId);
        TextView q = (TextView) view.findViewById(R.id.frag_menu_quantity_menu_quantity);
        final int[] quantity = {Integer.parseInt((String) q.getText())};

        displaySelMenu(view, selMenu);

        Button subBtn = (Button) view.findViewById(R.id.frag_menu_quantity_sub);
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(--quantity[0] < 0)
                    quantity[0]=0;
                q.setText(String.valueOf(quantity[0]));
            }
        });

        Button addBtn = (Button) view.findViewById(R.id.frag_menu_quantity_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++quantity[0];
                q.setText(String.valueOf(quantity[0]));
            }
        });

        FrameLayout c = (FrameLayout) view.findViewById(R.id.frag_menu_quantity_background);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button backBtn =  (Button) view.findViewById(R.id.back_btn_menu_quantity);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().remove(MenuQuantityFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        Button cartAddBtn = (Button) view.findViewById(R.id.frag_menu_quantity_btn_cart_add);
        cartAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderMenu orderMenu = new OrderMenu(selMenu, quantity[0]);
                ((MenuActivity)getActivity()).getOrderMenuList().addOrderMenuList(orderMenu);
                Log.d("At Quantity", String.valueOf(orderMenu));
                Log.d("At Quantity", String.valueOf(((MenuActivity)getActivity()).getOrderMenuList()));
                ((MenuActivity)getActivity()).a();

                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().remove(MenuQuantityFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }

    private void displaySelMenu(View view, Menu selMenu){
        Glide.with(this).load(selMenu.getUrl()).into((ImageView)view.findViewById(R.id.select_menu_img));
        TextView a = (TextView)view.findViewById(R.id.select_menu_name);
        a.setText(selMenu.getName());
    }
}
