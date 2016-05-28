package com.example.app.digitalizebillscustomer.MainScreen;

import android.content.res.Configuration;
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
import android.view.Menu;
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
            Log.i("Notes", e.toString());
        }

        Log.i("Notes", "reached");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /*
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
    */
}
