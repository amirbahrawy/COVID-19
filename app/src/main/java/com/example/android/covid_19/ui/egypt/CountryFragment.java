package com.example.android.covid_19.ui.egypt;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.example.android.covid_19.R;
import com.example.android.covid_19.databinding.FragmentCountryBinding;
import com.example.android.covid_19.model.EgyptModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryFragment extends Fragment {

    FragmentCountryBinding fragmentCountryBinding;
    EgyptViewModel egyptViewModel;
    public CountryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         fragmentCountryBinding= DataBindingUtil
                .inflate(inflater,R.layout.fragment_country,container,false);




        return fragmentCountryBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        egyptViewModel= ViewModelProviders.of(getActivity()).get(EgyptViewModel.class);
        fragmentCountryBinding.setEgyptViewModel(egyptViewModel);
        fragmentCountryBinding.setLifecycleOwner(getActivity());
        egyptViewModel.getData();
        egyptViewModel.egyptmutableLiveData.observe(getActivity(), new Observer<EgyptModel>() {
            @Override
            public void onChanged(EgyptModel egyptModel) {
                fragmentCountryBinding.progress.setVisibility(View.INVISIBLE);
                fragmentCountryBinding.totalConfirmed.setText(egyptModel.getCases()+"");
                fragmentCountryBinding.todayConfirmed.setText(egyptModel.getTodayCases()+"");
                fragmentCountryBinding.totalDeaths.setText(egyptModel.getDeaths()+"");
                fragmentCountryBinding.todayDeaths.setText(egyptModel.getTodayDeaths()+"");
                fragmentCountryBinding.countryRecovered.setText(egyptModel.getRecovered()+"");

            }
        });
    }

}
