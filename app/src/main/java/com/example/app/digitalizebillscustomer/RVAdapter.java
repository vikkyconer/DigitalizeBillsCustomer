package com.example.app.digitalizebillscustomer;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;

import java.util.List;

/**
 * Created by vikkycorner on 28/05/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    List<Bill> billList;
    List<Product> productList;
    Context context;
    DatabaseHelper db;


    public RVAdapter(List<Bill> billList, List<Product> productList, Context context) {
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
    public void onBindViewHolder(PersonViewHolder holder, int i) {

        initialize(holder, i);


    }

    private void initialize(PersonViewHolder holder, int i) {
        holder.date.setText(billList.get(i).getBillDate());
        holder.shopName.setText(billList.get(i).getVendorName());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.i("Notes", String.valueOf(billList.size()));
        return billList.size();
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        CardView cv;
        TextView date;
        TextView shopName;
        LinearLayout itemContainer;
        ArrayAdapter adapter;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            date = (TextView) itemView.findViewById(R.id.date);
            shopName = (TextView) itemView.findViewById(R.id.shop_name);
            itemContainer = (LinearLayout) itemView.findViewById(R.id.item_container);
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
