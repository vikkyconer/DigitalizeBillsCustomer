package com.example.app.digitalizebillscustomer.SlidingDrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.digitalizebillscustomer.AppSettings;
import com.example.app.digitalizebillscustomer.DatabaseHelper;
import com.example.app.digitalizebillscustomer.MainScreen.MainActivity;
import com.example.app.digitalizebillscustomer.Navigator;
import com.example.app.digitalizebillscustomer.R;


/**
 * Created by vikkycorner on 27/05/16.
 */
public class SlidingDrawerFragment extends Fragment implements SlidingDrawerView, View.OnClickListener {

    LinearLayout buttonGroceries, userInfo, logout, profile;
    TextView userName;
    DatabaseHelper db;
//    TextView checking;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drawer_items, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Notes", "nav drawer fragment called");
        intializeViews(view);
        defaultConfiguration();
        setEventsForViews();
    }

    private void intializeViews(View view) {
        buttonGroceries = (LinearLayout) view.findViewById(R.id.button_groceries);
        userName = (TextView) view.findViewById(R.id.set_user_name);
        userInfo = (LinearLayout) view.findViewById(R.id.user_info);
        logout = (LinearLayout) view.findViewById(R.id.button_log_out);
//        checking = (TextView) getActivity().findViewById(R.id.check);
        profile = (LinearLayout) view.findViewById(R.id.user_info);
        db = new DatabaseHelper(getActivity());
    }

    private void defaultConfiguration() {
        userName.setText(AppSettings.getValue(getActivity(),
                AppSettings.PREF_USER_FIRST_NAME, "") + " " + AppSettings.getValue(getActivity(), AppSettings.PREF_USER_LAST_NAME, ""));
    }

    private void setEventsForViews() {
        buttonGroceries.setOnClickListener(this);
        userInfo.setOnClickListener(this);
        logout.setOnClickListener(this);
        profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_groceries:
                getGroceriesBills();
                break;
            case R.id.button_food_n_resto:
                getFoodNRestoBills();
                break;
        }
    }

    private void getFoodNRestoBills() {
//        checking.setText("food and resto");
    }

    private void getGroceriesBills() {
//        db.getBillByType("groceries");
//        checking.setText("groceries");
        ((MainActivity) getActivity()).setType("groceries");
        Log.i("Notes","selected groceries");
        Navigator.toMainScreen(getActivity());
    }
}
