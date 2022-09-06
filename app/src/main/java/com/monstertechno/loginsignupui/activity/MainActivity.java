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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, SiginActivity.class));
                finish();
            }
        },2000);

    }
}