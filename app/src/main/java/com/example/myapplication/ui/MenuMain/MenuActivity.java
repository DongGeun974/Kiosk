package com.example.myapplication.ui.MenuMain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.orderMenuData.OrderMenuList;
import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.data.menuData.MenuList;
import com.example.myapplication.ui.MenuCategoryBar.MenuCategoryBarFragment;
import com.example.myapplication.ui.InitActivity;
import com.example.myapplication.ui.MenuQuantity.MenuQuantityFragment;

public class MenuActivity extends InitActivity {
    private String[] categoryNameList = {"chichen", "burger", "set", "beverage", "side"};
    private int categoryState =0;
    private int menuPage=0;
    private int menuUISize = 6;
    private MenuList categoryMenuList = new MenuList();
    private OrderMenuList orderMenuList = new OrderMenuList();

//    RecyclerView mRecyclerView = null ;
//    RecyclerImageTextAdapter mAdapter = null ;
//    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        displayCategoryBar();
        displayBottomBar();

        Button back = (Button) findViewById(R.id.back_btn_buy);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        categoryMenuList.setMenuListWithCategory(getDbMenuList(), categoryNameList[categoryState]);

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


        ////////////////////////////////
//        mRecyclerView = findViewById(R.id.ReLayout) ;
//
//        mAdapter = new RecyclerImageTextAdapter(mList) ;
//        mRecyclerView.setAdapter(mAdapter) ;
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
//
        //////////////////////////////////////
    }

    public void displayMenuList() {
        String tagName = "menu_img_lay";
        LinearLayout ll = findViewById(R.id.each_menu_list);

        for(int i=0; i<menuUISize; i++){
            LinearLayout ll2 = ll.findViewWithTag(tagName+i);
            Menu selMenu = categoryMenuList.getMenuList().get(menuPage*6+i);

            TextView t = (TextView) ll2.getChildAt(1);
            t.setText(selMenu.getName());

            String imageUrl = selMenu.getUrl();
            ImageButton iv = (ImageButton) ll2.getChildAt(0);
            Glide.with(this).load(imageUrl).into(iv);

            int finalI = i;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    MenuQuantityFragment menuQuantityFragment = new MenuQuantityFragment();
                    fragmentTransaction.add(R.id.frame_quantity, menuQuantityFragment);

                    Bundle bundle = new Bundle();
                    bundle.putInt("selMenu", selMenu.getId()); // Key, Value
                    Log.d("In selMenu", String.valueOf(selMenu.getId()));
                    menuQuantityFragment.setArguments(bundle);

                    fragmentTransaction.commit();
                }
            });
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
    public void displayCategoryBar(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MenuCategoryBarFragment buyMenu = new MenuCategoryBarFragment();
        fragmentTransaction.add(R.id.menu_bar, buyMenu);

        fragmentTransaction.commit();
    }

    public void setCategoryState(int categoryState) {
        this.categoryState = categoryState;
    }

    public void setOrderMenuList(OrderMenuList orderMenuList) {
        this.orderMenuList = orderMenuList;
    }

    public OrderMenuList getOrderMenuList() {
        return orderMenuList;
    }

    public MenuList getCategoryMenuList() {
        return categoryMenuList;
    }

}
