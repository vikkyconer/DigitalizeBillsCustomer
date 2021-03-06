package com.example.app.digitalizebillscustomer.MainScreen;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app.digitalizebillscustomer.AppService;
import com.example.app.digitalizebillscustomer.Constants;
import com.example.app.digitalizebillscustomer.DatabaseHelper;
import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;
import com.example.app.digitalizebillscustomer.Navigator;
import com.example.app.digitalizebillscustomer.R;
import com.example.app.digitalizebillscustomer.RVAdapter;
import com.example.app.digitalizebillscustomer.RecyclerItemClickListener;
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
import java.util.ArrayList;
import java.util.LinkedList;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private String type;
    private DatabaseHelper db;
    private RecyclerView mRecyclerView;
    //    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RVAdapter billAdapter;
    private ArrayList<Bill> billList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        defaultConfiguration();
        getStoredData();


        //       new MainScreenPresenter(mainScreenModel(), mainScreenView());
        //       new SlidingDrawerPresenter(slidingDrawerModel(), slidingDrawerView());
    }

    private void defaultConfiguration() {
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        Log.i("Notes Position", String.valueOf(position));              //change position to place id
                        Navigator.toBillDetailsActivity(context, billList.get(position).getId());
                    }
                })
        );

        context = this;
    }

    private void initializeViews() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        db = new DatabaseHelper(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mLayoutManager = new LinearLayoutManager(this);
        billList = new ArrayList<>();
    }


    private void getStoredData() {
        File sdcard = Environment.getExternalStorageDirectory();

        File file = new File(sdcard, "test.txt");

        StringBuilder text = new StringBuilder();
        Product product = new Product();
        Bill bill = new Bill();


//        billAdapter.notifyDataSetChanged();

//        billList =  db.getBills();
//        Log.i("billListSize", String.valueOf(billList.size()));
//        billAdapter.notifyDataSetChanged();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            long billId = 0, productId;
            int i;
            line = br.readLine();

            for (i = 0; line != null; i++) {
                Log.i("lines", line);
                if (i == 0) {
                    bill = new Bill();
                    bill.setId(Integer.parseInt(line));
                } else if (i == 1)
                    bill.setVendorName(line);
                else if (i == 2)
                    bill.setBillDate(line);
                else if (i == 3) {
                    bill.setAmount(Integer.parseInt(line));
                    Log.i("amount",bill.getAmount()+"");
                    bill.setVendorAddress("MacD");
                    billId = db.createBill(bill);
//                    billList.add(bill);
//                    Log.i("notes", billList.get(0).getVendorName());
//                    billAdapter.notifyDataSetChanged();
                    Log.i("billId", String.valueOf(billId));
                } else if (i > 3) {
                    if ((i - 4) % 3 == 0) {
                        product = new Product();
                        product.setId(i);
                        product.setName(line);
                    }
                    if ((i - 4) % 3 == 1)
                        product.setPrice(Integer.parseInt(line));
                    else if ((i - 4) % 3 == 2) {
                        product.setQuantity(Integer.parseInt(line));
                        productId = db.createProduct(product);
                        db.createBillProduct(billId, productId);
                    }
                }
                line = br.readLine();
            }
            br.close();
            boolean deleted = file.delete();
            Log.i("Notes", String.valueOf(deleted));
        } catch (IOException e) {
            Log.i("Notes", e.toString());
        }

        billList = db.getBills();
        billAdapter = new RVAdapter(billList, this);
        mRecyclerView.setAdapter(billAdapter);
        for (int i = 0; i < billList.size(); i++) {
            Log.i("bill-1", billList.get(i).getVendorName());
        }
//        billAdapter.notifyDataSetChanged();
        Log.i("Notes", "reached");

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.button_groceries: billList.clear();
                billList = db.getBillByType("groceries");
                type = "groceries";
                Log.i("Notes", "groceris clicked");
                break;
            case R.id.button_clothing:
                type = "clothing";
                break;
            case R.id.button_food_n_resto:
                type = "food_n_resto";
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
