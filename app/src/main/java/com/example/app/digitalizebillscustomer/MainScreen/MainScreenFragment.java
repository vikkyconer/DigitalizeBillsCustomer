package com.example.app.digitalizebillscustomer.MainScreen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.digitalizebillscustomer.Models.Bill;
import com.example.app.digitalizebillscustomer.R;

import java.util.LinkedList;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by vikkycorner on 27/05/16.
 */
public class MainScreenFragment extends Fragment implements MainScreenView{

    Dialog mProgress;
    private final BehaviorSubject<Boolean> initialized = BehaviorSubject.create();

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
        initialized.onNext(true);
    }

    private void defaultConfiguration() {

    }

    private void setEventsForViews() {

    }

    @Override
    public Observable<Boolean> Initialized() {
        return initialized.asObservable();
    }

    @Override
    public void showBills(LinkedList<Bill> bills) {

    }
}
