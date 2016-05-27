package com.example.app.digitalizebillscustomer.MainScreen;

import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.app.digitalizebillscustomer.AppService;
import com.example.app.digitalizebillscustomer.Constants;
import com.example.app.digitalizebillscustomer.DatabaseHelper;
import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;
import com.example.app.digitalizebillscustomer.R;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerFragment;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerModel;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerModelImpl;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerPresenter;
import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerView;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStoredData();
        initializeViews();
        //       new MainScreenPresenter(mainScreenModel(), mainScreenView());
        //       new SlidingDrawerPresenter(slidingDrawerModel(), slidingDrawerView());
    }

    private void initializeViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = ThirdFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private void getStoredData() {
        File sdcard = Environment.getExternalStorageDirectory();

        File file = new File(sdcard, "test.txt");

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

//            Log.i("Notes",br.readLine());
            while ((line = br.readLine()) != null) {
                text.append(line);
                Log.i("Notes", line);
                text.append('\n');
            }
            br.close();
//            boolean deleted = file.delete();
//            Log.i("Notes", String.valueOf(deleted));
        } catch (IOException e) {
            //You'll need to add proper error handling here
            Log.i("Notes", e.toString());
        }

        Log.i("Notes", "reached");

    }

//    private MainScreenView mainScreenView() {
//        return (MainScreenFragment) getSupportFragmentManager().findFragmentById(R.id.main_screen_fragment);
//    }

    private MainScreenModel mainScreenModel() {
        return new MainScreenModelImpl(service());

    }

//    private SlidingDrawerView slidingDrawerView() {
//        return (SlidingDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.left_drawer);
//    }

    private SlidingDrawerModel slidingDrawerModel() {
        return new SlidingDrawerModelImpl(service());
    }

    private AppService service() {
        AppService appService = restAdapterBuilder().setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL).build().create(AppService.class);
        return appService;
    }

    private RestAdapter.Builder restAdapterBuilder() {
        return new RestAdapter.Builder().setConverter(new GsonConverter(new GsonBuilder().create()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
