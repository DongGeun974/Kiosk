package com.example.myapplication.ui.MenuMain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.menuData.Menu;
import com.example.myapplication.menuData.MenuList;
import com.example.myapplication.ui.MenuMain.MenuBar.MenuBarFragment;
import com.example.myapplication.ui.InitActivity;
import com.example.myapplication.ui.MenuQuantity.MenuQuantity;

import java.util.ArrayList;

public class MenuActivity extends InitActivity {
    private String[] categoryList = {"chichen", "burger", "set", "beverage", "side"};
    private int menuState=0;
    private int menuPage=0;
    private int menuUISize = 6;
    private MenuList ct = new MenuList();

    RecyclerView mRecyclerView = null ;
    RecyclerImageTextAdapter mAdapter = null ;
    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        MenuBarFragment menu = new MenuBarFragment();

        makeBottomBar();

        Button back = (Button) findViewById(R.id.back_btn_buy);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ct.setCategoryMenuList(getDbMenuList(), categoryList[menuState]);

        displayMenuList();

        ImageButton btnBefore = (ImageButton) findViewById(R.id.menu_before);
        btnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    menuPage++;
                    displayMenuList();
                }catch(Exception e){
                    menuPage--;
                    displayMenuList();
                }

                Log.d("menuPage:", String.valueOf(menuPage));
            }
        });

        ImageButton btnNext = (ImageButton) findViewById(R.id.menu_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    menuPage--;
                    displayMenuList();
                }catch(Exception e){
                    menuPage++;
                    displayMenuList();
                }
                Log.d("menuPage:", String.valueOf(menuPage));

            }
        });

//        Button.OnClickListener onClickListener = new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                MenuQuantity bottomBarCloseFragment = new MenuQuantity();
//                fragmentTransaction.add(R.id.frame_bottom_bar, bottomBarCloseFragment);
//
//                Bundle bundle = new Bundle(); bundle.putInt("bottomBarState", functionState); // Key, Value
//                bottomBarCloseFragment.setArguments(bundle);
//
//                fragmentTransaction.commit();
//            }
//        } ;


        ////////////////////////////////
//        mRecyclerView = findViewById(R.id.ReLayout) ;
//
//        mAdapter = new RecyclerImageTextAdapter(mList) ;
//        mRecyclerView.setAdapter(mAdapter) ;
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
//
        //////////////////////////////////////



        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MenuBarFragment buyMenu = new MenuBarFragment();
        fragmentTransaction.add(R.id.menu_bar, buyMenu);

        fragmentTransaction.commit();


    }

    public void displayMenuList() {
        Log.d("call", "call");
        String tagName = "menu_img_lay";
        LinearLayout ll = findViewById(R.id.each_menu_list);

        for(int i=0; i<menuUISize; i++){
            LinearLayout ll2 = ll.findViewWithTag(tagName+i);

            TextView t = (TextView) ll2.getChildAt(1);
            t.setText(ct.getMenuList().get(menuPage*6+i).getName());

            String imageUrl = ct.getMenuList().get(menuPage*6+i).getUrl();
            ImageButton iv = (ImageButton) ll2.getChildAt(0);
            Glide.with(this).load(imageUrl).into(iv);
        }
    }

    //https://black-jin0427.tistory.com/100 참고
//    public void addItem(Drawable menuImg, String menuName) {
//        RecyclerItem item = new RecyclerItem();
//
//        item.setIcon(menuImg);
//        item.setTitle(menuName);
//
//        mList.add(item);
//    }


    public void setMenuState(int menuState) {
        this.menuState = menuState;
    }

    public MenuList getCt() {
        return ct;
    }
}
