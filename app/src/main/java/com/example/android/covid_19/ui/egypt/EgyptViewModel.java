package com.example.android.covid_19.ui.egypt;

import android.widget.Toast;

import com.example.android.covid_19.data.egypt.EgyptClient;
import com.example.android.covid_19.model.EgyptModel;
import com.example.android.covid_19.model.WorldModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EgyptViewModel extends ViewModel {
    public MutableLiveData<EgyptModel> egyptmutableLiveData=new MutableLiveData<>();
    EgyptModel s;
    public void getData() {
        EgyptClient.getInstance().getData().enqueue(new Callback<EgyptModel>() {
            @Override
            public void onResponse(Call<EgyptModel> call, Response<EgyptModel> response) {
                s=response.body();
                 egyptmutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<EgyptModel> call, Throwable t) {

            }
        });
    }
    }
