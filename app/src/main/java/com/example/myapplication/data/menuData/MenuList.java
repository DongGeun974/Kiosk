package com.example.myapplication.data.menuData;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 메뉴들을 저장하는 클래스
 * <p>
 * 인스턴스 변수:
 * {@link MenuList#menuList}
 * </p>
 * <p>
 * 메소드:
 * {@link MenuList#setMenuListWithCategory(MenuList, String...)},
 * {@link MenuList#addMenuList(Menu)}, {@link MenuList#getMenuWithId(int)}
 * </p>
 */
public class MenuList {
    /**
     * {@link Menu}로 이루어진 ArrayList
     */
    private ArrayList<Menu> menuList = new ArrayList<>();

    public void setMenuList(List<Menu> input) {
        menuList = (ArrayList<Menu>) input;
    }

    public List<Menu> getMenuList(){
        return menuList;
    }

    /**
     * {@link MenuList#menuList}에 메뉴 추가
     * @param menu 추가하고 싶은 메뉴
     */
    public void addMenuList(Menu menu){
        getMenuList().add(menu);
    }

    /**
     * {@link MenuList#menuList}를 카테고리를 통해 설정
     * @param mainMenuList 해당 카테고리만 찾고 싶은 menuList
     * @param categorys 찾고자하는 카테고리
     */
    public void setMenuListWithCategory(MenuList mainMenuList, String... categorys){
        setMenuList(new ArrayList<>());
        Log.d("setMenuListWithCategory", "category is "+ categorys);

        for(Menu item : mainMenuList.getMenuList())
            for(String category : categorys)
                if(item.getCategory().equals(category))
                    getMenuList().add(item);

    }

    /**
     * {@link MenuList#menuList}에서 해당하는 id의 메뉴 반환
     * @param id 찾고자 하는 메뉴의 id
     * @return 해당 id의 메뉴
     */
    public Menu getMenuWithId(int id){
        for(Menu menu: getMenuList())
            if (menu.getId() == id)
                return menu;

        return null;
    }
}
