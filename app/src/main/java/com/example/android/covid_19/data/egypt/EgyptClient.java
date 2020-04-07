package com.example.android.covid_19.data.egypt;

import com.example.android.covid_19.model.EgyptModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EgyptClient {
    private final static String COUNTRY="egypt";
    private final static String BASE_URL="https://corona.lmao.ninja/";
    private EgyptInterface egyptInterface;
    private static EgyptClient egyptClient;
    private EgyptClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        egyptInterface=retrofit.create(EgyptInterface.class);
    }
    public static EgyptClient getInstance(){
        if (egyptClient==null)
            egyptClient= new EgyptClient();

        return egyptClient;
    }
    public Call<EgyptModel> getData(){
        return egyptInterface.getData(COUNTRY);
    }
}
