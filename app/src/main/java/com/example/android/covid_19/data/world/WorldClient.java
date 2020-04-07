package com.example.android.covid_19.data.world;

import com.example.android.covid_19.model.WorldModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorldClient {
    private final static String sort="cases";
    private final static String BASE_URL="https://corona.lmao.ninja/";
    private WorldInterface worldInterface;
    private static WorldClient worldClient;
    private WorldClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        worldInterface=retrofit.create(WorldInterface.class);
    }
    public static WorldClient getInstance(){
        if (worldClient==null)
            worldClient= new WorldClient();

        return worldClient;
    }
    public Call<List<WorldModel>> getAll(){
        return worldInterface.getAll(sort);
    }

}
