package com.example.myapplication.ui.MenuChoice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.menuData.GitHubService;
import com.example.myapplication.menuData.Menu;
import com.example.myapplication.menuData.MenuList;
import com.example.myapplication.ui.InitActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuChoiceActivity extends InitActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choice_opt);

        GitHubService gitHubService;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rldnd2637.dothome.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubService = retrofit.create(GitHubService.class);
        Call<List<Menu>> call = gitHubService.getRepos();

        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if (response.isSuccessful()) {
                    List<Menu> menu = response.body();
                    TextView b = (TextView) findViewById(R.id.abc);
                    b.setText(menu.get(0).getName()+menu.get(0).getPrice()+menu.get(0).getUrl());
                }
            }
            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.e("Not Response", call + " " + t.getLocalizedMessage());
            }
        });

        makeBottomBar();

        Button backBtn = (Button) findViewById(R.id.back_btn_menu_choice);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
