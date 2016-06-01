package com.example.app.digitalizebillscustomer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikkycorner on 24/05/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "hisab";

    //Table Names
    private static final String TABLE_BILL = "bills";
    private static final String TABLE_PRODUCT = "products";
    private static final String TABLE_BILL_PRODUCT = "bill_products";

    //Commong Column Names
    private static final String KEY_ID = "id";

    //BILL Table Column Names
    private static final String KEY_BILL_ID_SERVER = "bill_id";
    private static final String KEY_VENDOR_BILL_ID = "vendor_bill_id";
    private static final String KEY_VENDOR_NAME = "vendor_name";
    private static final String KEY_VENDOR_ADDRESS = "vendor_address";
    private static final String KEY_BILL_DATE = "bill_date";
    private static final String KEY_AMOUNT = "amount";

    //PRODUCT Table Column Names
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String KEY_ITEM_PRICE = "price";
    private static final String KEY_QUANTITY = "quantity";

    //BILL_PRODUCT Table Column Names
    private static final String KEY_BILL_ID = "bill_id";
    private static final String KEY_PRODUCT_ID = "product_id";

    //Table Create Statements
    //Bill Table create statement
    private static final String CREATE_TABLE_BILL = "CREATE TABLE " + TABLE_BILL +
            "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_VENDOR_NAME + " TEXT," +
            KEY_VENDOR_ADDRESS + " TEXT," + KEY_BILL_DATE + " INTEGER," + KEY_AMOUNT +
            " INTEGER" + ")";

    //Product Table create statement
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT +
            "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PRODUCT_NAME + " TEXT," +
            KEY_ITEM_PRICE + " INTEGER," + KEY_QUANTITY + " INTEGER" + ")";

    //Bill_Product Table create statement
    private static final String CREATE_TABLE_BILL_PRODUCT = "CREATE TABLE " + TABLE_BILL_PRODUCT +
            "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BILL_ID + " INTEGER," +
            KEY_PRODUCT_ID + " INTEGER" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BILL);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_BILL_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_BILL);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_BILL_PRODUCT);

        onCreate(db);
    }

    /*
* Creating a bill
*/
    public long createBill(Bill bill) {
        Log.i("database", "started");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Log.i("database", "start saving");
        values.put(KEY_ID, bill.getId());
        values.put(KEY_VENDOR_NAME, bill.getVendorName());
        values.put(KEY_VENDOR_ADDRESS, bill.getVendorAddress());
        values.put(KEY_BILL_DATE, bill.getBillDate());
        Log.i("database", "bill date saved");
        values.put(KEY_AMOUNT, bill.getAmount());

        Log.i("database", "inserting");
        // insert row
        long bill_id = db.insert(TABLE_BILL, null, values);

        return bill_id;
    }

    /*
* Creating a product
*/
    public long createProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, product.getId());
        values.put(KEY_PRODUCT_NAME, product.getName());
        values.put(KEY_ITEM_PRICE, product.getPrice());
        values.put(KEY_QUANTITY, product.getQuantity());

        // insert row
        long product_id = db.insert(TABLE_PRODUCT, null, values);

        return product_id;
    }

    public long createBillProduct(long bill_id, long product_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BILL_ID, bill_id);
        values.put(KEY_PRODUCT_ID, product_id);

        // insert row
        long id = db.insert(TABLE_BILL_PRODUCT, null, values);

        return id;
    }

    public ArrayList<Bill> getBillByType(String type) {
        ArrayList<Bill> billList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_BILL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Bill bill = new Bill();
                bill.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                bill.setBillDate(c.getString(c.getColumnIndex(KEY_BILL_DATE)));
                bill.setVendorName(c.getString(c.getColumnIndex(KEY_VENDOR_NAME)));
//                place.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to place list
                billList.add(bill);
            } while (c.moveToNext());
        }

        return billList;
    }

    public ArrayList<Bill> getBills() {
        ArrayList<Bill> billList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_BILL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Bill bill = new Bill();
                bill.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                bill.setBillDate(c.getString(c.getColumnIndex(KEY_BILL_DATE)));
                bill.setVendorName(c.getString(c.getColumnIndex(KEY_VENDOR_NAME)));
                bill.setAmount(Integer.parseInt(c.getString(c.getColumnIndex(KEY_AMOUNT))));
//                place.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to place list
                billList.add(bill);
            } while (c.moveToNext());
        }

        return billList;
    }

    public ArrayList<Product> getBillProducts(String bill_id) {
        ArrayList<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT + " product, "
                + TABLE_BILL + " bill, " + TABLE_BILL_PRODUCT + " bill_product WHERE bill."
                + KEY_ID + " = '" + bill_id + "'" + " AND product." + KEY_ID
                + " = " + "bill_product." + KEY_PRODUCT_ID + " AND bill." + KEY_ID + " = "
                + "bill_product." + KEY_BILL_ID;

        Log.e("query", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                product.setName((c.getString(c.getColumnIndex(KEY_PRODUCT_NAME))));
                product.setQuantity(Integer.parseInt(c.getString(c.getColumnIndex(KEY_QUANTITY))));
//                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to friend list
                productList.add(product);
            } while (c.moveToNext());
        }

        return productList;
    }

    public Bill getBill(long bill_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_BILL + " WHERE "
                + KEY_ID + " = " + bill_id;

        Log.e("query", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Bill bill = new Bill();
        bill.setId(c.getColumnIndex(KEY_ID));
        bill.setBillDate((c.getString(c.getColumnIndex(KEY_BILL_DATE))));
        bill.setVendorName(c.getString(c.getColumnIndex(KEY_VENDOR_NAME)));
//        place.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return bill;
    }
}
