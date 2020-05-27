package com.example.myapplication.ui.MenuMain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.data.orderMenuData.OrderMenu;
import com.example.myapplication.data.orderMenuData.OrderMenuList;
import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.data.menuData.MenuList;
import com.example.myapplication.ui.MenuBuy.MenuBuyFragment;
import com.example.myapplication.ui.MenuCategoryBar.MenuCategoryBarFragment;
import com.example.myapplication.ui.InitActivity;
import com.example.myapplication.ui.MenuQuantity.MenuQuantityFragment;

import java.util.ArrayList;

public class MenuActivity extends InitActivity {
    private String[] categoryNameList = {"chicken", "burger&box&set", "side", "beverage"};
    private int categoryState =0;
    private int menuPage=0;
    private int menuUISize = 6;
    private MenuList categoryMenuList = new MenuList();//
    private OrderMenuList orderMenuList = new OrderMenuList();//장바구니 친구

//    RecyclerView mRecyclerView = null ;
//    RecyclerImageTextAdapter mAdapter = null ;
//    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        displayCategoryBar();
        displayBottomBar();

        Button back = (Button) findViewById(R.id.btn_actMenu_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        categoryMenuList.setMenuListWithCategory(getDbMenuList(), categoryNameList[categoryState].split(("&")));
        Log.d("aa", String.valueOf(categoryNameList[categoryState].split(("&"))));

        displayMenuList();

        ImageButton btnBefore = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuBefore);
        btnBefore.setOnClickListener(new View.OnClickListener() {
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

        ImageButton btnNext = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuPage++;

                if(menuPage > categoryMenuList.getMenuList().size() / 6)
                    menuPage--;

                displayMenuList();

                Log.d("menuPage:", String.valueOf(menuPage));

            }
        });

        Button q = (Button) findViewById(R.id.btn_actMenu_Buy);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                MenuBuyFragment menuBuyFragment = new MenuBuyFragment();
                fragmentTransaction.add(R.id.lay_actMenu_buy, menuBuyFragment);

//                Bundle bundle = new Bundle();
//                bundle.putInt("selMenu", selMenu.getId()); // Key, Value
//                Log.d("In selMenu", String.valueOf(selMenu.getId()));
//                menuQuantityFragment.setArguments(bundle);

                fragmentTransaction.commit();
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

    //변수 하나(역할: 입력되면 gilde 이미지 가져와서 출력)

    public void displayMenuList() {
        String tagName = "lay_actMenu_menuImg";
        LinearLayout ll = findViewById(R.id.lay_actMenu_eachMenuList);

        for(int i=0; i<menuUISize; i++){
            int categoryIndex = menuPage*6+i;

            LinearLayout ll2 = ll.findViewWithTag(tagName+i);

            try {
                Menu selMenu = categoryMenuList.getMenuList().get(menuPage * 6 + i);

                //표시되는 메뉴 이름
                TextView t = (TextView) ll2.getChildAt(1);
                t.setText(selMenu.getName());

                //표시되는 메뉴 이미지를 url에서 가져와서 출력
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
                        fragmentTransaction.add(R.id.lay_actMenu_quantity, menuQuantityFragment);

                        Bundle bundle = new Bundle();
                        bundle.putInt("selMenu", selMenu.getId()); // Key, Value
                        Log.d("In selMenu", String.valueOf(selMenu.getId()));
                        menuQuantityFragment.setArguments(bundle);

                        fragmentTransaction.commit();
                    }
                });
            }catch (Exception e){
                //표시되는 메뉴 이름
                TextView t = (TextView) ll2.getChildAt(1);
                t.setText(null);

                //표시되는 메뉴 이미지를 url에서 가져와서 출력
                ImageButton iv = (ImageButton) ll2.getChildAt(0);
                iv.setBackgroundResource(R.drawable.frame_actbuy_menu_ic_unselected);
            }
        }
        Log.d("for", "end");
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
        fragmentTransaction.add(R.id.lay_actMain_menuBar, buyMenu);

        fragmentTransaction.commit();
    }

    public void setCategoryState(int categoryState) {
        this.categoryState = categoryState;
    }

    public void setOrderMenuList(OrderMenuList orderMenuList) {
        this.orderMenuList = orderMenuList;
    }

    public void setMenuPage(int menuPage) {
        this.menuPage = menuPage;
    }

    public OrderMenuList getOrderMenuList() {
        return orderMenuList;
    }

    public MenuList getCategoryMenuList() {
        return categoryMenuList;
    }

    public int getMenuPage() {
        return menuPage;
    }

    public void a(){
        int sum = 0;
        int quantity = 0;
        for(OrderMenu orderMenu: orderMenuList.getOrderMenuList()){
            quantity += orderMenu.getQuantity();
            sum += orderMenu.getQuantity() * orderMenu.getMenu().getPrice();
        }

        ((TextView)findViewById(R.id.text_actMenu_quantity)).setText(String.valueOf(quantity));
        ((TextView)findViewById(R.id.text_actMenu_sum)).setText(String.valueOf(sum));
        Log.d("aaaaaaaaaaaaaaaaaaaaaa", String.valueOf(sum));


        ListView lv = (ListView) findViewById(R.id.lv);
        final MyAdapter myAdapter = new MyAdapter(this, orderMenuList);

        lv.setAdapter(myAdapter);


    }

    @Override
    protected void displayBottomBar() {
        super.displayBottomBar();


        //변했는 걸 어떻게 알까?
        //1. 하단바 클릭해서 돌아오면 무조건 확인(하단바 프래그먼트 들어가는 XML의 뷰에 클릭 이벤트 설정해서)
        //2. 하단바 상태를 가져와서 바뀌었으면 확인


        //변했을 떄 메서드 실행
    }
}
