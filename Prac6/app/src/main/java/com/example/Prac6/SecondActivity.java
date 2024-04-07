package com.example.Prac6;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Настройка ActionBar
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        viewPager.setAdapter(new ViewPagerFragmentAdapter(this));

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.menu_home) {
                            viewPager.setCurrentItem(0);
                            return true;
                        } else if (itemId == R.id.menu_profile) {
                            viewPager.setCurrentItem(1);
                            return true;
                        } else if (itemId == R.id.menu_notifications) {
                            viewPager.setCurrentItem(2);
                            return true;
                        }
                        return false;
                    }
                });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.menu_home);
                        getSupportActionBar().setTitle("Home");
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu_profile);
                        getSupportActionBar().setTitle("Profile");
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.menu_notifications);
                        getSupportActionBar().setTitle("Notifications");
                        break;
                }
            }
        });
    }

    public void backtoMenu(View view){ finish();}

    private static class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        ViewPagerFragmentAdapter(SecondActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new ProfileFragment();
                case 2:
                    return new NotificationFragment();
            }
            return new HomeFragment();
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}


