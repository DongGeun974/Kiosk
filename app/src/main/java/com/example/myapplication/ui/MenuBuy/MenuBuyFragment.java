package com.example.myapplication.ui.MenuBuy;

import android.annotation.SuppressLint;
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

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.orderMenuData.OrderMenu;
import com.example.myapplication.ui.MenuMain.MenuActivity;

import java.util.ArrayList;

public class MenuBuyFragment extends Fragment {
    RecyclerView mRecyclerView = null ;
    RecyclerImageTextAdapter mAdapter = null ;
    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_buy, container, false);
        int totalPrice = 0;

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

                if (getFragmentManager().getBackStackEntryCount() > 0)
                    getFragmentManager().popBackStack();
//                fragmentManager.popBackStack();
            }
        });

        Button buyBtn = (Button) view.findViewById(R.id.btn_frag_menubuy_buy);
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().remove(MenuBuyFragment.this).commit();

                if (getFragmentManager().getBackStackEntryCount() > 0)
                    getFragmentManager().popBackStack();
//                fragmentManager.popBackStack();
            }
        });

        TextView t = (TextView) view.findViewById(R.id.text_fragMenuBuy_totalPrice);

        for(OrderMenu orderMenu: ((MenuActivity)getActivity()).getCart().getOrderMenuList())
            totalPrice += orderMenu.getQuantity() * orderMenu.getMenu().getPrice();

        t.setText(String.valueOf(totalPrice)+"원");

        ////////////////////////////////
        // 리사이클러 뷰
        mRecyclerView = view.findViewById(R.id.recycler_fragMenuBuy);

        mAdapter = new RecyclerImageTextAdapter(((MenuActivity)getActivity()).getCart());
        mRecyclerView.setAdapter(mAdapter) ;

        mRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext())) ;

//////////////////////////////////////
        //수량 변경 버튼 이벤트
//        mAdapter.setOnItemClickListener(new RecyclerImageTextAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                // TODO : 아이템 클릭 이벤트를 MainActivity에서 처리.
//
//                Button plus =  v.findViewById(R.id.plus);
//                TextView menuQuantity = v.findViewById(R.id.text_fragMenuBuy_quantity);
//                plus.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        int pos = getAdapterPosition() ;
//                        int afterQuantity = Integer.parseInt((String) menuQuantity.getText())+1;
//                        menuQuantity.setText(String.valueOf(afterQuantity));
//                        ((MenuActivity) getActivity()).getCart().getOrderMenuList().get(position).setQuantity(afterQuantity);
//                    }
//                });
//
//                Log.d("메뉴바이프레그먼트에서", String.valueOf(position));
//            }
//        }) ;


//        Button plus =  view.findViewById(R.id.plus);
//        TextView menuQuantity = view.findViewById(R.id.text_fragMenuBuy_quantity);
//        plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                        int pos = getAdapterPosition() ;
//                int afterQuantity = Integer.parseInt((String) menuQuantity.getText())+1;
//                menuQuantity.setText(String.valueOf(afterQuantity));
//                ((MenuActivity) getActivity()).getCart().getOrderMenuList().get(position).setQuantity(afterQuantity);
//            }
//        });
        /////////////////////////////////////

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MenuActivity)getActivity()).changeOrderItemListState();
    }
}
