package com.example.app.digitalizebillscustomer.Models;

import java.util.Date;

/**
 * Created by vikkycorner on 25/05/16.
 */
public class Bill {
    private int id;
    private String vendorName;
    private String vendorAddress;
    private String billDate;
    private int  amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int  amount) {
        this.amount = amount;
    }
}
