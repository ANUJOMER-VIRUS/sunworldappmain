package com.monstertechno.loginsignupui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.fragment.BookingFragment;
import com.monstertechno.loginsignupui.fragment.ChatFragment;
import com.monstertechno.loginsignupui.fragment.HomeFragment;
import com.monstertechno.loginsignupui.fragment.LogoutFragment;
import com.monstertechno.loginsignupui.fragment.NewBookingFragment;
import com.monstertechno.loginsignupui.fragment.PrivacyPolicyFragment;
import com.monstertechno.loginsignupui.fragment.ProfileFragment;
import com.monstertechno.loginsignupui.fragment.RedeemFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                Fragment fragment=null;
                switch (id)
                {
                    case R.id.home:
                        fragment=new HomeFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.redeem:
                        fragment=new RedeemFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.newbooking:
                        fragment=new NewBookingFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.booking:
                        fragment=new BookingFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.profile:
                        fragment=new ProfileFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.termsandconditions:
                        fragment=new TermsandConditionsFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.privacy:
                        fragment=new PrivacyPolicyFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.chat:
                        fragment=new ChatFragment();
                        loadFragment(fragment);
                        break;

                    case R.id.logout:
                        fragment=new LogoutFragment();
                        loadFragment(fragment);
                        break;
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }**/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, SiginActivity.class));
                finish();
            }
        },2000);

    }
}