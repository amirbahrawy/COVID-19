package com.example.android.covid_19.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.android.covid_19.R;
import com.example.android.covid_19.databinding.ActivityMainBinding;
import com.example.android.covid_19.ui.egypt.CountryFragment;
import com.example.android.covid_19.ui.world.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        if(checkInternet()){
        fragmentManager=getSupportFragmentManager();
        loadFragment(new HomeFragment());
        setBottomNavigation();}
        else createDialog();


    }
    void setBottomNavigation(){
        binding.navigation
                .setOnNavigationItemSelectedListener(new BottomNavigationView
                        .OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.home:
                                loadFragment(new HomeFragment());

                                return true;
                            case R.id.advices:
                                loadFragment(new AdviceFragment());

                                return true;
                            case R.id.egypt:
                                loadFragment(new CountryFragment());

                                return true;

                            default:return true;
                        }
                    }
                });
    }
    //replae fragments
    void loadFragment(Fragment fragment){

        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();


    }

    private void createDialog(){
        //intialize Dialog
        Dialog dialog=new Dialog(this);
        //connect dialog with layout
        dialog.setContentView(R.layout.alert_dialog);
        //lma 7ad edos 3la dialog me5rgsh
        dialog.setCanceledOnTouchOutside(false);
        //set height and width
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        //5alfea 4fafa
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // set animation
        dialog.getWindow().getAttributes().windowAnimations=android.R.style.Animation_Dialog;
        Button button=dialog.findViewById(R.id.try_again);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reload current fragment
                recreate();

            }


        });
        dialog.show();
    }
    private boolean checkInternet(){
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        // if there is no internet
        if(networkInfo==null||!networkInfo.isConnected()||!networkInfo.isAvailable()){
            return false;
        }
        else
            return true;
    }
}
