package com.example.android.covid_19.data.egypt;

import com.example.android.covid_19.model.EgyptModel;
import com.example.android.covid_19.model.WorldModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EgyptInterface {
    @GET("countries/{country}")
    public Call<EgyptModel> getData(@Path("country") String country);
}
