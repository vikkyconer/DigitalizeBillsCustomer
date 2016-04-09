package com.example.app.digitalizebillscustomer.SignInScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.app.digitalizebillscustomer.Navigator;
import com.example.app.digitalizebillscustomer.R;
import com.example.app.digitalizebillscustomer.User;

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
    }

    @Override
    public Observable<Map<String, String>> signIn() {
        return signIn.asObservable();
    }

    @Override
    public void userAuthenticated(User signInResponse) {
        if (signInResponse.getStatus() != false) {
            Navigator.toMainScreen(getActivity());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_up) {
            Navigator.toSingUpScreen(getActivity());
        }
    }
}
