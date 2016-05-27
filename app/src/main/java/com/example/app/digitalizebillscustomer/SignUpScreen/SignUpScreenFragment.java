package com.example.app.digitalizebillscustomer.SignUpScreen;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.digitalizebillscustomer.R;
import com.example.app.digitalizebillscustomer.Models.User;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by vikkycorner on 09/04/16.
 */
public class SignUpScreenFragment extends Fragment implements SignUpScreenView {

    EditText firstName, lastName, email, password;
    Button signUp;
    private BehaviorSubject<Map<String, String>> createAccount = BehaviorSubject.create();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.sign_up_fragment, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        defaultConfiguration();
        setEventsForViews();

    }

    private void initializeViews(View view) {
        firstName = (EditText) view.findViewById(R.id.first_name);
        lastName = (EditText) view.findViewById(R.id.last_name);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);

        signUp = (Button) view.findViewById(R.id.sign_up);
    }

    private void defaultConfiguration() {

    }

    private void setEventsForViews() {
//        signUp.setOnClickListener(v -> authenticate());
    }

    private void authenticate() {
        if (!isValid())
            return;

        Map<String, String> signUpRequestMap = new LinkedHashMap<>();
        signUpRequestMap.put("first_name", firstName.getText().toString());
        signUpRequestMap.put("last_name", lastName.getText().toString());
        signUpRequestMap.put("email", email.getText().toString());
        signUpRequestMap.put("password", password.getText().toString());
//        signUpRequestMap.put("device_id", Utility.uniqueDeviceID(getActivity()));
//        signUpRequestMap.put("device_token", AppSettings.getValue(getActivity(), AppSettings.PREF_GCM_REGISTRATION_ID, ""));
//        signUpRequestMap.put("device_type", "android");
//
//        AppSettings.setValue(getActivity(), AppSettings.PREF_USER_FIRST_NAME, firstName.getText().toString());
//        AppSettings.setValue(getActivity(), AppSettings.PREF_USER_LAST_NAME, lastName.getText().toString());
        createAccount.onNext(signUpRequestMap);
    }

    private boolean isValid() {
        String validationMessage = "";
        if (firstName.getText().toString().equalsIgnoreCase(""))
            validationMessage = "Please enter first name";
        else if (lastName.getText().toString().equalsIgnoreCase(""))
            validationMessage = "Please enter last name";
        else if (email.getText().toString().equalsIgnoreCase(""))
            validationMessage = "Please enter email name";
        else if (password.getText().toString().equalsIgnoreCase(""))
            validationMessage = "Please enter password";

        if (!validationMessage.equalsIgnoreCase(""))
            Toast.makeText(getActivity(), validationMessage, Toast.LENGTH_LONG).show();

        return validationMessage.length() == 0;
    }


    @Override
    public Observable<Map<String, String>> signUp() {
        return createAccount.asObservable();
    }

    @Override
    public void userCreated(User signUpResponse) {
        if(signUpResponse.getUserId() != null) {
            Toast.makeText(getActivity(),"User Created",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getActivity(),"User NOT Created",Toast.LENGTH_LONG).show();
        }
    }
}
