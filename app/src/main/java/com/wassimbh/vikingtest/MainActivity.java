package com.wassimbh.vikingtest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import com.wassimbh.vikingtest.Fragments.RecyclerFragment;

public class MainActivity extends AppCompatActivity {

    public FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        Fragment recyclerFragment = new RecyclerFragment();
        changeFragment(recyclerFragment);
    }


    public void changeFragment(Fragment fragment){

        FragmentTransaction transaction =fm.beginTransaction();
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
