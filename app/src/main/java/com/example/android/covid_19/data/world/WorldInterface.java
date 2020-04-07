package com.example.android.covid_19.data.world;

import com.example.android.covid_19.model.WorldModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WorldInterface {
    @GET("countries")
    public Call<List<WorldModel>> getAll(@Query("sort") String sort);
}
