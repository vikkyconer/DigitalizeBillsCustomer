package com.example.app.digitalizebillscustomer;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;

import java.util.ArrayList;

public class BillDetailsActivity extends AppCompatActivity {

    private int billId,totalAmount;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private BillDetailsRVAdapter billAdapter;
    private ArrayList<Product> productList;
    private DatabaseHelper db;
    private TextView itemName,itemPrice,itemQuantity,itemTotal,billTotal,billDate,vendorName;
    private LinearLayout itemContainer;
    private Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);

        Intent i = getIntent();
        setBillId(i.getIntExtra("billId", 0));

        Log.i("billId",getBillId()+"");
        initializeView();
        defaultConfiguration();

        getBillProducts();

        setBillDetails();
    }

    private void setBillDetails() {
        billDate.setText(bill.getBillDate());
        vendorName.setText(bill.getVendorName());

        showProducts();
        billTotal.setText(String.valueOf(totalAmount));
    }

    private void showProducts() {
        itemContainer.removeAllViews();
        String name;
        float price,amount;
        int quantity;

        for(int i = 0; i < productList.size(); i++ ) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.product_purchased, null);

            name = productList.get(i).getName();
            price = productList.get(i).getPrice();
            quantity = productList.get(i).getQuantity();

            amount = price * quantity;
            totalAmount += amount;

            ((TextView) view.findViewById(R.id.item_name)).setText(name);
            ((TextView) view.findViewById(R.id.item_price)).setText(String .valueOf(price));
            ((TextView) view.findViewById(R.id.item_quantity)).setText(String .valueOf(quantity));
            ((TextView) view.findViewById(R.id.item_total)).setText(String.valueOf(amount));

            itemContainer.addView(view);
        }
    }

    private void getBillProducts() {
        productList = db.getBillProducts(String.valueOf(billId));
        bill = db.getBill(billId);
//        billAdapter = new BillDetailsRVAdapter(bill, productList, this);
//        mRecyclerView.setAdapter(billAdapter);
    }

    private void defaultConfiguration() {
//        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initializeView() {
        productList = new ArrayList<>();
        itemName = (TextView) findViewById(R.id.item_name);
        itemPrice = (TextView) findViewById(R.id.item_price);
        itemQuantity = (TextView) findViewById(R.id.item_quantity);
        itemTotal = (TextView) findViewById(R.id.item_total);
        billTotal = (TextView) findViewById(R.id.bill_total);
        itemContainer = (LinearLayout) findViewById(R.id.item_container1);
        billDate = (TextView) findViewById(R.id.bill_date);
        vendorName = (TextView) findViewById(R.id.vendor_name);
        totalAmount = 0;
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView = (RecyclerView) findViewById(R.id.bill_details_recycleView);
        db = new DatabaseHelper(this);
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }
}
