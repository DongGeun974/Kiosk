package com.example.myapplication.ui.MenuBuy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.data.orderMenuData.OrderMenu;
import com.example.myapplication.ui.MenuMain.MenuActivity;
import com.example.myapplication.ui.MenuQuantity.MenuQuantityFragment;

import static com.example.myapplication.ui.InitActivity.getDbMenuList;

public class MenuBuyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_buy, container, false);
//        int selMenuId = getArguments().getInt("selMenu");

        FrameLayout c = (FrameLayout) view.findViewById(R.id.frag_menu_buy_background);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button backBtn =  (Button) view.findViewById(R.id.btn_frag_menubuy_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().remove(MenuBuyFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        Button buyBtn = (Button) view.findViewById(R.id.btn_frag_menubuy_buy);
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().remove(MenuBuyFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }
}
