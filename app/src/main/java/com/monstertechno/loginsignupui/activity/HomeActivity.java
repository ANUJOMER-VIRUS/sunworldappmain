package com.monstertechno.loginsignupui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import com.monstertechno.loginsignupui.fragment.BookingFragment;
import com.monstertechno.loginsignupui.fragment.HomeFragment;
import com.monstertechno.loginsignupui.fragment.NewBookFragment;
import com.monstertechno.loginsignupui.fragment.ProfileFragment;
import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.fragment.RedeemFragment;

public class HomeActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    ViewPager viewPager;

SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences =this.getSharedPreferences("login",MODE_PRIVATE);
        editor=sharedPreferences.edit();
//Toast.makeText(getApplicationContext(), sharedPreferences.getString("userid","not_found"),Toast.LENGTH_SHORT).show();
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_attach_money_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_add_circle_outline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_book_online_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_baseline_person_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        fragment = new HomeFragment();
                        break;

                    case 2:
                        fragment = new RedeemFragment();
                        break;

                    case 3:
                        fragment = new NewBookFragment();
                        break;

                    case 4:
                        fragment = new BookingFragment();
                        break;

                    case 5:
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });


        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
             
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

}
