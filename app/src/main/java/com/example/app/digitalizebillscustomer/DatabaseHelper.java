package com.example.app.digitalizebillscustomer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;

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
    private static final String KEY_VENDOR_NAME = "vendor_name";
    private static final String KEY_VENDOR_ADDRESS = "vendor_address";
    private static final String KEY_BILL_DATE = "bill_date";
    private static final String KEY_AMOUNT = "amount";

    //PRODUCT Table Column Names
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String  KEY_ITEM_PRICE = "price";

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
            KEY_ITEM_PRICE + " INTEGER" + ")";

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
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, bill.getId());
        values.put(KEY_VENDOR_NAME, bill.getVendorName());
        values.put(KEY_VENDOR_ADDRESS, bill.getVendorAddress());
        values.put(KEY_BILL_DATE, bill.getBillDate());
        values.put(KEY_AMOUNT, bill.getAmount());

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
}