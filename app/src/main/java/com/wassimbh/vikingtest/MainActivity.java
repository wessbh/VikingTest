package com.wassimbh.vikingtest;


import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wassimbh.vikingtest.Fragments.RecyclerFragment;

public class MainActivity extends AppCompatActivity {

    public FragmentManager fm;
    private ActionBar abar;
    private TextView textviewTitle;
    boolean doubleBackToExitPressedOnce;
    int bsEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doubleBackToExitPressedOnce = false;
        abar = getSupportActionBar();
        View viewActionBar = getLayoutInflater().inflate(R.layout.custom_toolbar, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setText(R.string.app_name);
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(false);
        abar.setHomeButtonEnabled(false);

        fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                bsEntry = fm.getBackStackEntryCount() ;
                Log.d("mriGel", "Bs Entry: "+bsEntry);
                if (bsEntry  > 1) {
                    abar.setDisplayHomeAsUpEnabled(true);

                } else {
                    textviewTitle.setText(R.string.app_name);
                    abar.setDisplayHomeAsUpEnabled(false);
                }
                if(bsEntry == 0){
                    finish();
                }
            }
        });

        Fragment recyclerFragment = new RecyclerFragment();
        changeFragment(recyclerFragment);
    }


    public void changeFragment(Fragment fragment){
        FragmentTransaction transaction =fm.beginTransaction();
        transaction.setCustomAnimations(
                R.animator.enter_from_left,
                R.animator.exit_to_right,
                R.animator.enter_from_right,
                R.animator.exit_to_left);
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public ActionBar getAbar() {
        return abar;
    }

    public TextView getTextviewTitle() {
        return textviewTitle;
    }

    public FragmentManager getFm() {
        return fm;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        if(bsEntry == 1){
            Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();
            this.doubleBackToExitPressedOnce = true;
        }
        else
            super.onBackPressed();
    }
}
