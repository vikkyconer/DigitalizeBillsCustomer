package com.example.app.digitalizebillscustomer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Bill bill;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setData();
        createTable();
    }

    private void createTable() {
        long billId = db.createBill(bill);
        long productId = db.createProduct(product);

        long id = db.createBillProduct(billId,productId);
    }

    private void setData() {
        bill.setId(123);
        bill.setVendorName("MacDonald");
        bill.setVendorAddress("Pheonix");
        bill.setBillDate("20/5/16");
        bill.setAmount(160);

        product.setId(456);
        product.setName("MacAallo");
        product.setPrice(25);
    }

    private void initialize() {
        db = new DatabaseHelper(this);
        bill = new Bill();
        product = new Product();

    }

}
