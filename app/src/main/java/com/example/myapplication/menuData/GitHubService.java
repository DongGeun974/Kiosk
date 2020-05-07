package com.example.myapplication.menuData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {
    @GET("/try.php")
    Call<List<Menu>> getRepos();
}

