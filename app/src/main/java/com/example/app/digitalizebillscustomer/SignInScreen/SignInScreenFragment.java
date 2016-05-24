package com.example.app.digitalizebillscustomer.SignInScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.digitalizebillscustomer.Navigator;
import com.example.app.digitalizebillscustomer.R;
import com.example.app.digitalizebillscustomer.Models.User;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.BehaviorSubject;


/**
 * Created by vikkycorner on 09/04/16.
 */
public class SignInScreenFragment extends Fragment implements SignInScreenView, View.OnClickListener {

    private BehaviorSubject<Map<String, String>> signIn = BehaviorSubject.create();
    private TextView signUp;
    Button signInButton;
    EditText email, password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.login_fragment, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeViews(view);
        setEventsForViews();
    }

    private void setEventsForViews() {
        signUp.setOnClickListener(this);
        signInButton.setOnClickListener(this);
    }

    private void initializeViews(View view) {
        signUp = (TextView) view.findViewById(R.id.sign_up);
        signInButton = (Button) view.findViewById(R.id.sign_in);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
    }

    @Override
    public Observable<Map<String, String>> signIn() {
        return signIn.asObservable();
    }

    @Override
    public void userAuthenticated(User signInResponse) {
        if (signInResponse.getStatus() != false) {
            Navigator.toMainScreen(getActivity());
        } else {
            Toast.makeText(getActivity(), "Username or password wrong", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_up) {
            Navigator.toSingUpScreen(getActivity());
        } else {
            Map<String, String> signIpRequestMap = new LinkedHashMap<>();
            signIpRequestMap.put("email", email.getText().toString());
            signIpRequestMap.put("password", password.getText().toString());
            signIn.onNext(signIpRequestMap);
        }
    }
}
