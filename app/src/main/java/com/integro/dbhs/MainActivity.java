package com.integro.dbhs;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.github.demono.AutoScrollViewPager;
import com.google.android.material.tabs.TabLayout;
import com.integro.dbhs.adapters.CoverPhotosAdapter;
import com.integro.dbhs.adapters.SlideAdapter;
import com.integro.dbhs.apis.ApiClients;
import com.integro.dbhs.apis.ApiServices;
import com.integro.dbhs.model.CoverPhotos;
import com.integro.dbhs.model.CoverPhotosList;
import com.integro.dbhs.model.PrincipalMessage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new SlideAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.news2);
        tabLayout.getTabAt(2).setIcon(R.drawable.notifications);
        tabLayout.getTabAt(3).setIcon(R.drawable.web2);
    }

    public void getE_Learning(View view) {
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", "http://e-learning.dbhspanjim.com/class_KG.php");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void getUpcomingEvents(View view) {
        Intent intent = new Intent(getApplicationContext(), UpcomingEventsActivity.class);
        startActivity(intent);
    }

    public void getPrincipalMessage(View view) {
        Intent intent = new Intent(getApplicationContext(), PrincipalMessageActivity.class);
        startActivity(intent);
    }

    public void getAnnouncements(View view) {
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", "http://www.dbhspanjim.com/announcement.php");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void getYearPlanner(View view) {
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", "www.dbhspanjim.com/year_planner.php");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void getRhapsody(View view) {
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", "http://www.dbhspanjim.com/rhapsody.php");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void getKindergarten(View view) {
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", "http://www.dbhspanjim.com/kindergarten.php");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void getAlumni(View view) {
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", "http://www.dbhspanjim.com/alumni.php");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void address(View view) {
        Toast.makeText(getApplicationContext(), "coming soon", Toast.LENGTH_SHORT).show();
    }

    public void getSupportUs(View view) {
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", "http://www.dbhspanjim.com/support.php");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

