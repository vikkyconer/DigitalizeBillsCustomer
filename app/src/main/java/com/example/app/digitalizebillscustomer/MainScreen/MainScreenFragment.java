package com.example.app.digitalizebillscustomer.MainScreen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.Models.Product;
import com.example.app.digitalizebillscustomer.R;
import com.example.app.digitalizebillscustomer.RVAdapter;

import java.util.ArrayList;
import java.util.LinkedList;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by vikkycorner on 27/05/16.
 */
public class MainScreenFragment extends Fragment {

    Dialog mProgress;
    //    private final BehaviorSubject<Boolean> initialized = BehaviorSubject.create();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RVAdapter billAdapter;
    private ArrayList<Bill> billList;
    private ArrayList<Product> productList;
    TextView check;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_screen_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        defaultConfiguration();
        setEventsForViews();


    }

    private void initializeViews(View view) {
//        mProgress = ProgressHUD.show(getActivity(),"",true,false,this,true);
//        initialized.onNext(true);
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
//        mLayoutManager = new LinearLayoutManager(getActivity());
        billList = new ArrayList<>();
        productList = new ArrayList<>();
        check = (TextView) view.findViewById(R.id.check_it);
        check.setText(((MainActivity) getActivity()).getType());
//        billAdapter = new RVAdapter(billList, productList, getActivity());
    }

    private void defaultConfiguration() {
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void setEventsForViews() {

    }

}
