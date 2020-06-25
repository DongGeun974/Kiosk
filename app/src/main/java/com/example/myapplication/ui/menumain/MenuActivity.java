package com.example.myapplication.ui.menumain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.orderData.Order;
import com.example.myapplication.data.orderData.OrderList;
import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.data.menuData.MenuList;
import com.example.myapplication.ui.menubuy.MenuBuyFragment;
import com.example.myapplication.ui.menucategorybar.MenuCategoryBarFragment;
import com.example.myapplication.ui.InitActivity;
import com.example.myapplication.ui.menuquantity.MenuQuantityFragment;
import com.example.myapplication.ui.bottombar.InitBottomBar;

/**
 * <p>
 * {@link InitActivity}에서 상속받은 메뉴 보여주는 액티비티
 * </p>
 * <p>
 * 인스턴스 변수:
 * {@link MenuActivity#categoryNameList}, {@link MenuActivity#categoryState},
 * {@link MenuActivity#menuPage}, {@link MenuActivity#menuUISize},
 * {@link MenuActivity#categoryMenuList}, {@link MenuActivity#cart},
 * </p>
 * <p>
 * 메소드(타이머부분 제외):
 * {@link MenuActivity#onCreate(Bundle)}. {@link MenuActivity#setEvent()},
 * {@link MenuActivity#displayMenuList()}, {@link MenuActivity#displayCategoryBar()},
 * {@link MenuActivity#changeCartState()}, {@link MenuActivity#checkFunctionState()}
 * </p>
 */
public class MenuActivity extends InitActivity {
    /**
     * 카테고리 이름 저장한 리스트, 4개 항목 있음
     */
    private String[] categoryNameList = {"chicken", "burger&box&set", "side", "beverage"};
    /**
     * 현재 카테고리
     */
    private int categoryState = 0;
    /**
     * 현재 메뉴 페이지
     */
    private int menuPage = 0;
    /**
     * xml상 보여지는 메뉴 객체의 수
     */
    private int menuUISize = 6;
    /**
     * 카테고리에 해당하는 메뉴들을 담은 객체
     */
    private MenuList categoryMenuList = new MenuList();
    /**
     * 현재 구매 버튼을 누른 메뉴들을 저장한 객체
     */
    private static OrderList cart = new OrderList();

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        int action = event.getAction();
        switch(action){
            case(MotionEvent.ACTION_DOWN):
                Log.i("dispatch touch","count reset");
                alarm.restart();

                break;
            case(MotionEvent.ACTION_MOVE):
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 이벤트 설정({@link MenuActivity#setEvent()}), 카테고리 프래그먼트 실행({@link MenuActivity#displayCategoryBar()},
     * 카테고리의 메뉴들 화면으로 출력({@link MenuActivity#displayMenuList()}
     * <p>
     * BottomBarClose 프래그먼트, MenuCategory 프래그먼트 이용
     * </p>
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if((InitBottomBar.getState() & InitBottomBar.WHEEL) == 0)
            setContentView(R.layout.activity_menu);
        else
            setContentView(R.layout.activity_menu_wheel);

        alarm.setActivity(this);
        alarm.start();

        displayCategoryBar();
        displayBottomBar();
        setEvent();
        categoryMenuList.setMenuListWithCategory(getDbMenuList(), categoryNameList[categoryState].split(("&")));
        changeCartState();

        displayMenuList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("메뉴에서", "알람종료");

        alarm.cancel();
    }

    /**
     * 각종 이벤트를 따로 빼둠
     */
    public void setEvent(){

        Button back = (Button) findViewById(R.id.btn_actMenu_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton btnBefore = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuBefore);
            btnBefore.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v){
                    menuPage--;

                    if(menuPage < 0)
                        menuPage = 0;

                    displayMenuList();
                }
        });

        ImageButton btnNext = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuNext);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v){
                    menuPage++;

                    if (menuPage*menuUISize >= categoryMenuList.getMenuList().size()) {
                        menuPage--;
                        return;
                    }

                    displayMenuList();
                }
        });

        Button btnBuy = (Button) findViewById(R.id.btn_actMenu_Buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                MenuBuyFragment menuBuyFragment = new MenuBuyFragment();
                fragmentTransaction.add(R.id.frame_actMenu_buy, menuBuyFragment);

                fragmentTransaction.commit();
            }
        });
    }

    /**
     * 해당 카테고리의 메뉴를 보여주는 6개 버튼을 지정하고 각 버튼의 이벤트 설정
     */
    public void displayMenuList() {
        // 6개 버튼의 태그명
        String tagName = "lay_actMenu_menuImg";
        LinearLayout ll = findViewById(R.id.lay_actMenu_eachMenuList);

        // 6개 버튼 각각에 대해
        for(int i=0; i<menuUISize; i++){
            int categoryIndex = menuPage*6+i;

            LinearLayout ll2 = ll.findViewWithTag(tagName+i);

            // catrgoryMenuList에서 해당하는 인덱스의 Menu를 가져와 보여준다.
            try {
                Menu selMenu = categoryMenuList.getMenuList().get(menuPage * 6 + i);


                //표시되는 메뉴 이름
                TextView t = (TextView) ll2.getChildAt(1);
                t.setText(selMenu.getName());

                TextView t2 = (TextView) ll2.getChildAt(2);
                t2.setText(String.valueOf(selMenu.getPrice())+"원") ;

                //표시되는 메뉴 이미지를 url에서 가져와서 출력
                String imageUrl = selMenu.getUrl();
                ImageButton iv = (ImageButton) ll2.getChildAt(0);

//                if((InitBottomBar.getState() & InitBottomBar.COLORBLIND) != 0)
//                    imageUrl = imageUrl.replace("original", "colorblind");

                imageUrl = colorBlind.changeURL(imageUrl);

                Glide.with(this)
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_loading)
                        .into(iv);

                //각 버튼을 클릭했을 때 수량확인 프래그먼트 실행
                int finalI = i;
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        MenuQuantityFragment menuQuantityFragment = new MenuQuantityFragment();
                        fragmentTransaction.add(R.id.frame_actMenu_quantity, menuQuantityFragment);

                        Bundle bundle = new Bundle();
                        bundle.putInt("selMenu", selMenu.getId()); // Key, Value

                        menuQuantityFragment.setArguments(bundle);

                        fragmentTransaction.commit();
                    }
                });
            }catch (IndexOutOfBoundsException e){
                //표시되는 메뉴 이름
                TextView t = (TextView) ll2.getChildAt(1);
                t.setText(null);

                TextView t2 = (TextView) ll2.getChildAt(2);
                t2.setText(null);

                //기존 이미지에 덮어쓰기
                ImageButton iv = (ImageButton) ll2.getChildAt(0);
                Glide.with(this).load(R.drawable.frame_fragbottombar_icunselected).into(iv);

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }catch (Exception e){
                Menu selMenu = categoryMenuList.getMenuList().get(menuPage * 6 + i);

                //표시되는 메뉴 이름
                TextView t = (TextView) ll2.getChildAt(1);
                t.setText(selMenu.getName());

                TextView t2 = (TextView) ll2.getChildAt(2);
                t2.setText(String.valueOf(selMenu.getPrice())+"원") ;

                //기존 이미지에 덮어쓰기
                ImageButton iv = (ImageButton) ll2.getChildAt(0);

                Glide.with(this)
                        .load(R.drawable.ic_loading)
                        .into(iv);

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        MenuQuantityFragment menuQuantityFragment = new MenuQuantityFragment();
                        fragmentTransaction.add(R.id.frame_actMenu_quantity, menuQuantityFragment);

                        Bundle bundle = new Bundle();
                        bundle.putInt("selMenu", selMenu.getId()); // Key, Value

                        menuQuantityFragment.setArguments(bundle);

                        fragmentTransaction.commit();
                    }
                });
            }
        }
    }

    /**
     * {@link MenuCategoryBarFragment} 실행
     */
    public void displayCategoryBar(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MenuCategoryBarFragment buyMenu = new MenuCategoryBarFragment();
        fragmentTransaction.add(R.id.lay_actMain_menuBar, buyMenu);

        fragmentTransaction.commit();
    }

    /**
     * 현재 {@link MenuActivity#cart} 에 따라 화면에 보여지는 총수량, 총합 수정
     */
    public void changeCartState(){
        int sum = 0;
        int quantity = 0;

        cart.deleteZeroQuantityOrder();

        //cart에 담겨진 주문들의 총수량, 총합 계산
        for(Order order : cart.getOrderList()){
            quantity += order.getQuantity();
            sum += order.getQuantity() * order.getMenu().getPrice();
        }

        //화면에 보여지는 총수량, 총합 수정 부분
        ((TextView)findViewById(R.id.text_actMenu_quantity)).setText(String.valueOf(quantity));
        ((TextView)findViewById(R.id.text_actMenu_sum)).setText(String.valueOf(sum));

        ListView lv = (ListView) findViewById(R.id.lv);
        final MyAdapter myAdapter = new MyAdapter(this, cart);

        lv.setAdapter(myAdapter);
    }

    /**
     * {@link InitActivity#checkFunctionState()} 재정의
     * <p>
     * 휠체어:
     *
     * </p>
     * <p>
     * 돋보기: 각자 적기
     *
     * </p>
     * <p>
     * 색맹: 각자 적기
     *
     * </p>
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void checkFunctionState() {
        int functionState = InitBottomBar.getState();

        if((functionState & InitBottomBar.BIGGER) != 0){
            Log.d("메뉴화면에서", "돋보기 기능");

            magnify.setOn(true);
            magnify.setMagnifyOnView(findViewById(R.id.lay_actMenu_all));
        }else{
            Log.d("메뉴화면에서", "돋보기 해제");

            magnify.setOn(false);
            magnify.setMagnifyOnView(findViewById(R.id.lay_actMenu_all));
        }

        if((functionState & InitBottomBar.COLORBLIND) != 0){
            Log.d("메뉴화면에서", "색맹으로 전환");
            colorBlind.setOn(true);
        }else{
            Log.d("메뉴화면에서", "색맹 해제");
            colorBlind.setOn(false);
        }

        if((functionState & InitBottomBar.WHEEL) != 0) {
            Log.d("메뉴화면에서", "휠체어로 변환");

            alarm.cancel();

            if (wheel.isOnChange(true)){
                finish();
                startActivity(getIntent());
            }

        }else{
            Log.d("메뉴화면에서", "휠체어에서 일반으로 변환");

            alarm.cancel();

            if (wheel.isOnChange(false)){
                finish();
                startActivity(getIntent());
            }
        }

        displayMenuList();
        changeCartState();
    }

    public String[] getCategoryNameList() {
        return categoryNameList;
    }

    public void setCategoryNameList(String[] categoryNameList) {
        this.categoryNameList = categoryNameList;
    }

    public int getCategoryState() {
        return categoryState;
    }

    public void setCategoryState(int categoryState) {
        this.categoryState = categoryState;
    }

    public int getMenuPage() {
        return menuPage;
    }

    public void setMenuPage(int menuPage) {
        this.menuPage = menuPage;
    }

    public int getMenuUISize() {
        return menuUISize;
    }

    public void setMenuUISize(int menuUISize) {
        this.menuUISize = menuUISize;
    }

    public MenuList getCategoryMenuList() {
        return categoryMenuList;
    }

    public void setCategoryMenuList(MenuList categoryMenuList) {
        this.categoryMenuList = categoryMenuList;
    }

    public static OrderList getCart() {
        return cart;
    }

    public static void setCart(OrderList cart) {
        MenuActivity.cart = cart;
    }
}