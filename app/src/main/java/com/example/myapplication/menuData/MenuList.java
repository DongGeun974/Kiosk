package com.example.myapplication.menuData;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuList {
    private GitHubService gitHubService;
    private List<Menu> menu;
    private Retrofit retrofit;

    public void setMenuList(Call<List<Menu>> call) {
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://rldnd2637.dothome.co.kr")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        gitHubService = retrofit.create(GitHubService.class);


    }

    public List<Menu> getMenuList(){
        return menu;
    }
}
