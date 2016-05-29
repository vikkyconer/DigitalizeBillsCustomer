package com.example.app.digitalizebillscustomer;

import android.app.Service;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;

import java.util.List;

/**
 * Created by vikkycorner on 29/05/16.
 */

public class BillDetailsRVAdapter extends RecyclerView.Adapter<BillDetailsRVAdapter.PersonViewHolder> {

    private List<Bill> billList;
    private List<Product> productList;
    private Context context;
    DatabaseHelper db;

    public BillDetailsRVAdapter(List<Bill> billList, List<Product> productList, Context context) {
        this.billList = billList;
        this.productList = productList;
        this.context = context;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        db = new DatabaseHelper(context);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        initialize(holder, position);

    }


    private void initialize(PersonViewHolder holder, int i) {
        holder.vendorName.setText(billList.get(i).getVendorName());
        holder.date.setText(String.valueOf(billList.get(i).getBillDate()));
        showBills(holder, i);

    }

    private void showBills(PersonViewHolder holder, int position) {
        holder.itemsContainer.removeAllViews();
        String name,price,quantity,total;
        for (int i = 0; i < productList.size(); i++) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.product_purchased, null);
            name = productList.get(i).getName();
            price = String.valueOf(productList.get(i).getPrice());
            quantity = String.valueOf(productList.get(i).getQuantity());
            total = String.valueOf((productList.get(i).getPrice() * productList.get(i).getQuantity()));
            ((TextView) view.findViewById(R.id.item_name)).setText(name);
            ((TextView) view.findViewById(R.id.price)).setText(price);
            ((TextView) view.findViewById(R.id.quantity)).setText(quantity);
            ((TextView) view.findViewById(R.id.total)).setText(total);
            holder.itemsContainer.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        CardView cv;
        TextView vendorName;
        TextView date;
        LinearLayout itemsContainer;


        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            vendorName = (TextView) itemView.findViewById(R.id.vendor_name);
            date = (TextView) itemView.findViewById(R.id.date);
            itemsContainer = (LinearLayout) itemView.findViewById(R.id.item_container);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public boolean onLongClick(View v) {
//            Place place = new Place();
//            place.setPlaceId(v.getId());
//            Toast.makeText(context, String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
            return true;
        }


    }
}
