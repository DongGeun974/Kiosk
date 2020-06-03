package com.example.myapplication.ui.MenuBuy;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
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

/**
 * 구매 확인하는 창 띄우는 프래그먼트
 * <p>
 * 인스턴스 변수:
 * {@link MenuBuyFragment#mRecyclerView}, {@link MenuBuyFragment#mAdapter},
 * </p>
 * <p>
 * 메소드:
 * {@link MenuBuyFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
 * </p>
 */
public class MenuBuyFragment extends Fragment {
    /**
     * 리싸이클러 뷰를 저장하는 객체
     */
    RecyclerView mRecyclerView = null ;
    /**
     * 리싸이클러 뷰 어댑터를 저장하는 객체
     */
    RecyclerImageTextAdapter mAdapter = null ;

    /**
     * 화면의 각종 이벤트 설정 및 리싸이클러뷰 적용
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_buy, container, false);
        int totalPrice = 0;

        FrameLayout c = (FrameLayout) view.findViewById(R.id.frag_menuBuy_background);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button backBtn =  (Button) view.findViewById(R.id.btn_fragMenuBuy_back);
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

        Button buyBtn = (Button) view.findViewById(R.id.btn_fragMenuBuy_buy);
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
        ////////////////////////////////

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MenuActivity)getActivity()).changeCartState();
    }
}
