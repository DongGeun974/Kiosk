//package com.example.myapplication.ui.MenuMain;
//
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.example.myapplication.R;
//import com.example.myapplication.data.orderMenuData.OrderMenu;
//import com.example.myapplication.data.orderMenuData.OrderMenuList;
//import com.example.myapplication.data.menuData.Menu;
//import com.example.myapplication.data.menuData.MenuList;
//import com.example.myapplication.ui.MenuBuy.MenuBuyFragment;
//import com.example.myapplication.ui.MenuCategoryBar.MenuCategoryBarFragment;
//import com.example.myapplication.ui.InitActivity;
//import com.example.myapplication.ui.MenuQuantity.MenuQuantityFragment;
//
//public class MenuActivity extends InitActivity {
//    private String[] categoryNameList = {"chicken", "burger&box&set", "side", "beverage"};
//    private int categoryState =0;
//    private int menuPage=0;
//    private int menuUISize = 6;
//    private MenuList categoryMenuList = new MenuList();//
//    private OrderMenuList cart = new OrderMenuList();//장바구니 친구
//
//
//    /**
//     * 처음 액티비티 생성에서 실행
//     * 메뉴화면의 왼쪽, 오른쪽 화살표 버튼에 대한 이벤트 설정
//     * 메뉴화면의 구매 버튼 이벤트 설정
//     *
//     * BottomBarClose 프래그먼트, MenuCategory 프래그먼트 이용
//     *
//     * @param savedInstanceState
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//
//        displayCategoryBar();
//        displayBottomBar();
//
//        Button back = (Button) findViewById(R.id.btn_actMenu_back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        categoryMenuList.setMenuListWithCategory(getDbMenuList(), categoryNameList[categoryState].split(("&")));
//
//        displayMenuList();
//
//        ImageButton btnBefore = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuBefore);
//        btnBefore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try{
//                    menuPage--;
//                    displayMenuList();
//                }catch(Exception e){
//                    menuPage++;
//                    displayMenuList();
//                }
//
//                Log.d("menuPage:", String.valueOf(menuPage));
//            }
//        });
//
//        ImageButton btnNext = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuNext);
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                menuPage++;
//
//                if(menuPage > categoryMenuList.getMenuList().size() / 6)
//                    menuPage--;
//
//                displayMenuList();
//
//                Log.d("menuPage:", String.valueOf(menuPage));
//
//            }
//        });
//
//        Button btnBuy = (Button) findViewById(R.id.btn_actMenu_Buy);
//        btnBuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                MenuBuyFragment menuBuyFragment = new MenuBuyFragment();
//                fragmentTransaction.add(R.id.lay_actMenu_buy, menuBuyFragment);
//
//                fragmentTransaction.commit();
//            }
//        });
//
//
//    }
//
//    //변수 하나(역할: 입력되면 gilde 이미지 가져와서 출력)
//
//    /**
//     * 메뉴를 보여주는 6개 버튼을 지정
//     * 각 버튼의 이벤트 설정
//     */
//    public void displayMenuList() {
//        String tagName = "lay_actMenu_menuImg";
//        LinearLayout ll = findViewById(R.id.lay_actMenu_eachMenuList);
//
//        for(int i=0; i<menuUISize; i++){
//            int categoryIndex = menuPage*6+i;
//            Log.d("display", String.valueOf(categoryIndex));
//
//            LinearLayout ll2 = ll.findViewWithTag(tagName+i);
//
//            try {
//                Menu selMenu = categoryMenuList.getMenuList().get(menuPage * 6 + i);
//
//                //표시되는 메뉴 이름
//                TextView t = (TextView) ll2.getChildAt(1);
//                t.setText(selMenu.getName());
//
//                TextView t2 = (TextView) ll2.getChildAt(2);
//                t2.setText(String.valueOf(selMenu.getPrice())+"원") ;
//
//                //표시되는 메뉴 이미지를 url에서 가져와서 출력
//                String imageUrl = selMenu.getUrl();
//                ImageButton iv = (ImageButton) ll2.getChildAt(0);
//                Glide.with(this).load(imageUrl).into(iv);
//
//                int finalI = i;
//                iv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        FragmentManager fragmentManager = getFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                        MenuQuantityFragment menuQuantityFragment = new MenuQuantityFragment();
//                        fragmentTransaction.add(R.id.lay_actMenu_quantity, menuQuantityFragment);
//
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("selMenu", selMenu.getId()); // Key, Value
//                        Log.d("In selMenu", String.valueOf(selMenu.getId()));
//                        menuQuantityFragment.setArguments(bundle);
//
//                        fragmentTransaction.commit();
//                    }
//                });
//            }catch (Exception e){
//                //표시되는 메뉴 이름
//                TextView t = (TextView) ll2.getChildAt(1);
//                t.setText(null);
//
//                //표시되는 메뉴 이미지를 url에서 가져와서 출력
//                ImageButton iv = (ImageButton) ll2.getChildAt(0);
//                iv.setBackgroundResource(R.drawable.frame_actbuy_menu_ic_unselected);
//            }
//        }
//        Log.d("for", "end");
//    }
//
//
//    public void displayCategoryBar(){
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        MenuCategoryBarFragment buyMenu = new MenuCategoryBarFragment();
//        fragmentTransaction.add(R.id.lay_actMain_menuBar, buyMenu);
//
//        fragmentTransaction.commit();
//    }
//
//    public void setCategoryState(int categoryState) {
//        this.categoryState = categoryState;
//    }
//
//    public void setCart(OrderMenuList cart) {
//        this.cart = cart;
//    }
//
//    public void setMenuPage(int menuPage) {
//        this.menuPage = menuPage;
//    }
//
//    public OrderMenuList getCart() {
//        return cart;
//    }
//
//    public MenuList getCategoryMenuList() {
//        return categoryMenuList;
//    }
//
//    public int getMenuPage() {
//        return menuPage;
//    }
//
//    public void changeOrderItemListState(){
//        int sum = 0;
//        int quantity = 0;
//
//        cart.deleteZeroQuantityItem();
//
//        for(OrderMenu orderMenu: cart.getOrderMenuList()){
//            quantity += orderMenu.getQuantity();
//            sum += orderMenu.getQuantity() * orderMenu.getMenu().getPrice();
//        }
//
//        ((TextView)findViewById(R.id.text_actMenu_quantity)).setText(String.valueOf(quantity));
//        ((TextView)findViewById(R.id.text_actMenu_sum)).setText(String.valueOf(sum));
//        Log.d("aaaaaaaaaaaaaaaaaaaaaa", String.valueOf(sum));
//
//
///////////////////////////////리스트뷰 내용(임시로 둠)
//        ListView lv = (ListView) findViewById(R.id.lv);
//        final MyAdapter myAdapter = new MyAdapter(this, cart);
//
//        lv.setAdapter(myAdapter);
//////////////////////////////
//
//    }
//
//    @Override
//    protected void displayBottomBar() {
//        super.displayBottomBar();
//
//
//        //변했는 걸 어떻게 알까?
//        //1. 하단바 클릭해서 돌아오면 무조건 확인(하단바 프래그먼트 들어가는 XML의 뷰에 클릭 이벤트 설정해서)
//        //2. 하단바 상태를 가져와서 바뀌었으면 확인
//
//
//        //변했을 떄 메서드 실행
//    }
//}

package com.example.myapplication.ui.MenuMain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.orderMenuData.OrderMenu;
import com.example.myapplication.data.orderMenuData.OrderMenuList;
import com.example.myapplication.data.menuData.Menu;
import com.example.myapplication.data.menuData.MenuList;
import com.example.myapplication.ui.MenuBuy.MenuBuyFragment;
import com.example.myapplication.ui.MenuCategoryBar.MenuCategoryBarFragment;
import com.example.myapplication.ui.InitActivity;
import com.example.myapplication.ui.MenuQuantity.MenuQuantityFragment;
import com.example.myapplication.ui.bottomBar.InitBottomBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
    private OrderMenuList cart = new OrderMenuList();
    /**
     *  수정 필요   일반 >> 휠체어 시 일반 액티비티 종료용
     */
    public static MenuActivity menuActivity;
    /**
     *  수정 필요   휠체어 >> 일반 시 휠체어 액티비티 종료용
     */
    MenuWheelActivity menuWheelActivity = (MenuWheelActivity)MenuWheelActivity.menuWheelActivity;

    /////////////////////////////////////////////////////////////////////
    /**
     * 여기부터 타이머
     */
    int i = 0; // timer
    int check = 0;
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            //timer handling
            if (i == 20) {
                Log.i(this.getClass().getName(), "system re-boot");
                restart();
            } else if (i == 10) {
                Log.i(this.getClass().getName(), "voice start");
                AlarmNoInputSound();
            }
            Log.i(this.getClass().getName(), Integer.toString(i) + ", check :" + Integer.toString(check));
            i++;
        }
    };

    public void tempTask() {
        i = 0;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (i == 20) {
                    Log.i(this.getClass().getName(), "system re-boot");
                } else if (i == 10) {
                    Log.i(this.getClass().getName(), "voice start");
                }
                Log.i(this.getClass().getName(), Integer.toString(i) + ", check :" + Integer.toString(check));
                i++;
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 1000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        timer.cancel();
        i = 0;
        tempTask();
        return super.onTouchEvent(event);
    }

    public void restart() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
    /**
     * 여기까지 타이머
     */
    ////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    /**
     * 여기부터 음성안내
     */
    MediaPlayer Start;
    MediaPlayer NoInput;

    protected void AlarmStartSound() {
        try {
            Start = new MediaPlayer();
            Start.setDataSource("http://zxcasd3004.dothome.co.kr/project/menu_cart.mp3");
            Start.prepare();
            Start.start();

            //Toast.makeText(this, "재생 시작됨.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void AlarmNoInputSound() {
        try {
            NoInput = new MediaPlayer();
            NoInput.setDataSource("http://zxcasd3004.dothome.co.kr/project/no_input.mp3");
            NoInput.prepare();
            NoInput.start();

            //Toast.makeText(this, "재생 시작됨.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 여기까지 음성안내
     */
    ////////////////////////////////////////////////////////////////////////

    /**
     * 이벤트 설정({@link MenuActivity#setEvent()}), 카테고리 프래그먼트 실행({@link MenuActivity#displayCategoryBar()},
     * 카테고리의 메뉴들 화면으로 출력({@link MenuActivity#displayMenuList()}
     * <p>
     * BottomBarClose 프래그먼트, MenuCategory 프래그먼트 이용
     * </p>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //////////////////////////////////////////////////
        //타이머 시작
        timer.schedule(timerTask, 1000, 1000);

        //시작 음성 안내 시작
        AlarmStartSound();
        ///////////////////////////////////////////////////

        displayCategoryBar();
        displayBottomBar();
        setEvent();
        categoryMenuList.setMenuListWithCategory(getDbMenuList(), categoryNameList[categoryState].split(("&")));

        displayMenuList();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    /**
     * 각종 이벤트를 따로 빼둠
     */
    public void setEvent(){

        Button back = (Button) findViewById(R.id.btn_actMenu_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //이전 액티비티 종료
                    menuWheelActivity.finish();
                }catch (Exception ex){}
                finish();
            }
        });

        ImageButton btnBefore = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuBefore);
            btnBefore.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v){
                    try {
                        menuPage--;
                        displayMenuList();
                    } catch (Exception e) {
                        menuPage++;
                        displayMenuList();
                    }

                    Log.d("menuPage:", String.valueOf(menuPage));
                }
        });

        ImageButton btnNext = (ImageButton) findViewById(R.id.imgBtn_actMenu_menuNext);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v){
                    menuPage++;

                    if (menuPage > categoryMenuList.getMenuList().size() / 6)
                        menuPage--;

                    displayMenuList();

                    Log.d("menuPage:", String.valueOf(menuPage));

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
            Log.d("display", String.valueOf(categoryIndex));

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
                Glide.with(this).load(imageUrl).into(iv);

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

    public void setCategoryState(int categoryState) {
        this.categoryState = categoryState;
    }

    public void setCart(OrderMenuList cart) {
        this.cart = cart;
    }

    public void setMenuPage(int menuPage) {
        this.menuPage = menuPage;
    }

    public OrderMenuList getCart() {
        return cart;
    }

    public MenuList getCategoryMenuList() {
        return categoryMenuList;
    }

    public int getMenuPage() {
        return menuPage;
    }

    /**
     * 현재 {@link MenuActivity#cart} 에 따라 화면에 보여지는 총수량, 총합 수정
     */
    public void changeCartState(){
        int sum = 0;
        int quantity = 0;

        cart.deleteZeroQuantityItem();

        //cart에 담겨진 주문들의 총수량, 총합 계산
        for(OrderMenu orderMenu: cart.getOrderMenuList()){
            quantity += orderMenu.getQuantity();
            sum += orderMenu.getQuantity() * orderMenu.getMenu().getPrice();
        }

        //화면에 보여지는 총수량, 총합 수정 부분
        ((TextView)findViewById(R.id.text_actMenu_quantity)).setText(String.valueOf(quantity));
        ((TextView)findViewById(R.id.text_actMenu_sum)).setText(String.valueOf(sum));
        Log.d("메뉴화면에서", String.valueOf(sum));


/////////////////////////////리스트뷰 내용(임시로 둠)
        ListView lv = (ListView) findViewById(R.id.lv);
        final MyAdapter myAdapter = new MyAdapter(this, cart);

        lv.setAdapter(myAdapter);
////////////////////////////

    }

    /**
     * {@link InitActivity#checkFunctionState()} 재정의
     * <p>
     * 휠체어: {@link MenuWheelActivity} 로 전환
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
    @Override
    public void checkFunctionState() {
        int functionState =  getFunctionState();

        Log.d("메뉴화면에서", String.valueOf(functionState));

        if((functionState & InitBottomBar.WHEEL) != 0) {
            Log.d("메뉴화면에서", "휠체어로 변환");

            try {
                //이전 액티비티 종료
                menuWheelActivity.finish();

                //전환된 액티비티에서 이전 액티비티 종료용
                menuActivity = this;
            }catch (Exception ex){}

            Intent intent=new Intent(MenuActivity.this, MenuWheelActivity.class);

            // 출처: https://wingsnote.com/128 [날개의 노트 (Wing's Note)]
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);

            startActivity(intent);
        }else{
            Log.d("메뉴화면에서", "휠체어에서 일반으로 변환");
        }

        if((functionState & InitBottomBar.BIGGER) != 0){
            Log.d("메뉴화면에서", "돋보기 기능");
        }else{
            Log.d("메뉴화면에서", "돋보기 해제");
        }

        if((functionState & InitBottomBar.COLORBLIND) != 0){
            Log.d("메뉴화면에서", "색맹으로 전환");
        }else{
            Log.d("메뉴화면에서", "색맹 해제");
        }
    }
}