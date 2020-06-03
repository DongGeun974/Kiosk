package com.example.myapplication.ui.MenuCategoryBar;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.myapplication.R;
import com.example.myapplication.ui.MenuMain.MenuActivity;

import static com.example.myapplication.ui.InitActivity.getDbMenuList;

/**
 * {@link MenuActivity}의 카테고리 바 프래그먼트
 * <p>
 * 인스턴스 변수:
 * {@link MenuCategoryBarFragment#categoryList}, {@link MenuCategoryBarFragment#categoryState}
 * </p>
 * <p>
 * 메소드:
 * {@link MenuCategoryBarFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}, {@link MenuCategoryBarFragment#setCategoryButtonEvent(View)}
 * </p>
 */
public class MenuCategoryBarFragment extends Fragment {
    /**
     * 카테고리들의 이름
     */
    private String[] categoryList = {"chicken", "burger&set&box", "side", "beverage"};
    /**
     * 현재 선택된 카테고리
     */
    private int categoryState;

    /**
     * 해당하는 프래그먼트 보여줌. {@link MenuCategoryBarFragment#setCategoryButtonEvent(View)} 통해 이벤트 설정
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_menubar, container, false);

        setCategoryButtonEvent(view);

        return view;
    }

    /**
     * 프래그먼트 xml의 이벤트 설정. tag를 이용해 이벤트 설정하고 상태에 따라 다른 버튼 모양 출력
     * @param v 현재 뷰
     */
    public void setCategoryButtonEvent(View v) {
        final int numOfCategoryBtn = 4;
        final ToggleButton[] categoryBtnList = new ToggleButton[numOfCategoryBtn];

        //xml의 카테고리 버튼들을 가져옴
        for (int i = 0; i < numOfCategoryBtn; i++)
            categoryBtnList[i] = v.findViewWithTag("menu" + i);

        //처음 실행 시 버튼들 중 맨 앞에 있는 버튼이 선택되도록 설정
        categoryBtnList[0].setTextColor(getResources().getColor(R.color.white));
        categoryBtnList[0].setChecked(true);

        //카테고리 버튼들의 클릭 이벤트
        for (final ToggleButton menuBtn : categoryBtnList) {
            menuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //현재 카테고리와 이전 카테고리를 가져옴
                    int nowBtnState = Integer.parseInt(menuBtn.getTag().toString().split("menu")[1]);
                    ToggleButton beforeOnBtn = categoryBtnList[categoryState];

                    //버튼 클릭되서 켜진 경우 현재 카테고리의 버튼 On, 이전 카테고리의 버튼 Off
                    if (menuBtn.isChecked()) {
                        beforeOnBtn.setTextColor(getResources().getColor(R.color.dark_grey));
                        beforeOnBtn.setChecked(false);

                        menuBtn.setTextColor(getResources().getColor(R.color.white));
                        menuBtn.setChecked(true);

                        categoryState = nowBtnState;

                        //MenuActivity의 메소드들을 통해 현재 카테고리의 메뉴들을 보여주도록 변경
                        ((MenuActivity)getActivity()).setMenuPage(0);
                        ((MenuActivity)getActivity()).setCategoryState(categoryState);
                        ((MenuActivity)getActivity()).getCategoryMenuList().setMenuListWithCategory(getDbMenuList(), categoryList[categoryState].split("&"));
                        ((MenuActivity)getActivity()).displayMenuList();

                        Log.d("Menu_bar", "버튼 On & "+categoryList[categoryState]);

                    }else if(menuBtn == beforeOnBtn){
                        menuBtn.setTextColor(getResources().getColor(R.color.white));
                        menuBtn.setChecked(true);
                    }
                }
            });
        }
    }
}
