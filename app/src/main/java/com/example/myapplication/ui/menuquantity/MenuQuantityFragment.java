package com.example.myapplication.ui.menuquantity;

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
import com.example.myapplication.addfunc.AddFunction;
import com.example.myapplication.data.orderData.Order;
import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.ui.menumain.MenuActivity;
import com.example.myapplication.ui.bottombar.InitBottomBar;

import static com.example.myapplication.ui.InitActivity.getDbMenuList;

/**
 * <p>
 * 메뉴 수량 확인하는 창 띄우는 프래그먼트
 * </p>
 * <p>
 * 메소드:
 * {@link MenuQuantityFragment#displaySelMenu(View, Menu)}
 * </p>
 */
public class MenuQuantityFragment extends Fragment implements AddFunction {
    /**
     *
     * @param inflater 어디를 기준으로 확장할 것인지(자세히 모름)
     * @param container 뷰그룹 관련(자세히 모름)
     * @param savedInstanceState 데이터 전달하는 {@link Bundle} 객체
     * @return 크게 필요 x
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 뷰 생성
        View view = inflater.inflate(R.layout.fragment_menu_quantity, container, false);

        // 선택한 메뉴의 id를 가져옴
        int selMenuId = getArguments().getInt("selMenu");
        Menu selMenu = getDbMenuList().getMenuWithId(selMenuId);

        // 선택한 메뉴의 수량을 가져옴
        TextView q = (TextView) view.findViewById(R.id.text_fragMenuQuantity_quantity);
        final int[] quantity = {Integer.parseInt((String) q.getText())};

        // 메소드 통해 선택한 메뉴 id 바탕으로 화면 출력
        displaySelMenu(view, selMenu);

        // 수량 뺴기 버튼
        Button subBtn = (Button) view.findViewById(R.id.btn_fragMenuQuantity_sub);
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(--quantity[0] < 0)
                    quantity[0]=0;
                q.setText(String.valueOf(quantity[0]));
            }
        });

        // 수량 더하기 버튼
        Button addBtn = (Button) view.findViewById(R.id.btn_fragMenuQuantity_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++quantity[0];
                q.setText(String.valueOf(quantity[0]));
            }
        });

        // 프래그먼트 배경 부분에 대해 클릭 이벤트가 없도록 변경
        FrameLayout c = (FrameLayout) view.findViewById(R.id.frame_fragMenuQuantity_background);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 뒤로가기 버튼, 현재 프래그먼트 종료
        Button backBtn =  (Button) view.findViewById(R.id.btn_fragMenuQuantity_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().remove(MenuQuantityFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        // 추가하기 버튼, MenuActivity의 cart에 추가하고 현재 프래그먼트 종료
        Button cartAddBtn = (Button) view.findViewById(R.id.btn_fragMenuQuantity_cartAdd);
        cartAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order(selMenu, quantity[0]);
                ((MenuActivity)getActivity()).getCart().addOrderAtList(order);
                Log.d("At Quantity", String.valueOf(order));
                Log.d("At Quantity", String.valueOf(((MenuActivity)getActivity()).getCart()));
                ((MenuActivity)getActivity()).changeCartState();

                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction().remove(MenuQuantityFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }

    /**
     * 선택한 메뉴의 이름, 가격, 사진을 가져와 출력함
     * @param view 현재 뷰
     * @param selMenu 선택한 Menu 객체
     */
    private void displaySelMenu(View view, Menu selMenu){
        String url = selMenu.getUrl();

        url = colorBlind.changeURL(url);

        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_loading)
                .into((ImageView)view.findViewById(R.id.img_fragMenuQuantity_selectMenu));
        TextView a = (TextView)view.findViewById(R.id.text_fragMenuQuantity_selectMenuName);
        a.setText(selMenu.getName());
    }
}
