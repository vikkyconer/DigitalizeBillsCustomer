package com.example.app.digitalizebillscustomer;

import java.util.Map;

import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by vikkycorner on 09/04/16.
 */
public interface AppService {

    @FormUrlEncoded
    @POST("/user/sign_in")
    Observable<User> signIn(@FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("/user/sign_up")
    Observable<User> signUp(@FieldMap Map<String, String> signUpMap);}
