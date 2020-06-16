package com.example.myapplication.ui.menubuy;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.addfunc.AddFunction;
import com.example.myapplication.data.orderData.Order;
import com.example.myapplication.ui.menumain.MenuActivity;

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
public class MenuBuyFragment extends Fragment implements AddFunction {
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
     * @param inflater .
     * @param container .
     * @param savedInstanceState .
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
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

        ToggleButton toGoBtn = (ToggleButton) view.findViewById(R.id.toggleBtn_fragMenuBuy_toGo);
        ToggleButton toHereBtn = (ToggleButton) view.findViewById(R.id.toggleBtn_fragMenuBuy_toHere);

        toGoBtn.setChecked(true);
        toGoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                toGoBtn.setChecked(true);
                toHereBtn.setChecked(false);
            }
        });
        toHereBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                toGoBtn.setChecked(false);
                toHereBtn.setChecked(true);
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

            }
        });

        Button buyBtn = (Button) view.findViewById(R.id.btn_fragMenuBuy_buy);
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().remove(MenuBuyFragment.this).commit();

                getActivity().finish();
                Log.d("asasasas", String.valueOf(getActivity()));

                if (getFragmentManager().getBackStackEntryCount() > 0){
                    getFragmentManager().popBackStack();
                }
            }
        });

        TextView t = (TextView) view.findViewById(R.id.text_fragMenuBuy_totalPrice);

        for(Order order : MenuActivity.getCart().getOrderList())
            totalPrice += order.getQuantity() * order.getMenu().getPrice();

        t.setText(String.valueOf(totalPrice)+"원");

        ////////////////////////////////
        // 리사이클러 뷰
        mRecyclerView = view.findViewById(R.id.recycler_fragMenuBuy);

        if(!wheel.isOn()) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mRecyclerView.getLayoutParams();
            layoutParams.weight = 3.5F;
            mRecyclerView.setLayoutParams(layoutParams);
        }

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
