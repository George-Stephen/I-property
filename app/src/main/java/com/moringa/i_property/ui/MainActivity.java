package com.moringa.i_property.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moringa.i_property.R;
import com.moringa.i_property.fragments.ContactFragment;
import com.moringa.i_property.fragments.DashboardFragment;
import com.moringa.i_property.fragments.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation) BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadFragment(new DashboardFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }


    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.dashboard:
                fragment = new DashboardFragment();
                break;
            case R.id.search:
                fragment = new SearchFragment();
                break;
            case R.id.contact:
                fragment = new ContactFragment();
                break;
        }

        return loadFragment(fragment);

    }
}