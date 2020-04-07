package com.example.android.covid_19.ui.world;

import android.widget.Toast;

import com.example.android.covid_19.data.world.WorldClient;
import com.example.android.covid_19.model.WorldModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorldViewModel extends ViewModel {
    MutableLiveData<List<WorldModel>> mutableLiveData=new MutableLiveData<>();
    public void getAll(){
        WorldClient.getInstance().getAll().enqueue(new Callback<List<WorldModel>>() {
            @Override
            public void onResponse(Call<List<WorldModel>> call, Response<List<WorldModel>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<WorldModel>> call, Throwable t) {
                Toast.makeText(new HomeFragment().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
