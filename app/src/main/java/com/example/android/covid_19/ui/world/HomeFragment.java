package com.example.android.covid_19.ui.world;


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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.android.covid_19.R;
import com.example.android.covid_19.model.WorldModel;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    View v;
    SearchView searchView;
    WorldViewModel worldViewModel;
    RecyclerView recyclerView;
    WorldAdapter worldAdapter;
    ProgressBar progressBar;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_home, container, false);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            progressBar=v.findViewById(R.id.progress);
            progressBar.setVisibility(View.VISIBLE);
            searchView=v.findViewById(R.id.search_view);
            worldViewModel= ViewModelProviders.of(getActivity()).get(WorldViewModel.class);
            worldViewModel.getAll();
            worldAdapter=new WorldAdapter();
            recyclerView=v.findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(worldAdapter);
            worldViewModel.mutableLiveData.observe(getActivity(), new Observer<List<WorldModel>>() {
                @Override
                public void onChanged(List<WorldModel> worldModels) {
                    worldAdapter.setWorldList(worldModels);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
            search();
        }

   private void search(){
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
              worldAdapter.getFilter().filter(newText);
               return false;
           }
       });
   }

}
