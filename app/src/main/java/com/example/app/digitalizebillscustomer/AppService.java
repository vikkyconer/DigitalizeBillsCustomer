package com.example.app.digitalizebillscustomer;

import com.example.app.digitalizebillscustomer.Models.User;
import com.example.app.digitalizebillscustomer.Models.Vendor;

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
    Observable<User> customerSignIn(@FieldMap Map<String, String> loginMap);

    @FormUrlEncoded
    @POST("/user/sign_up")
    Observable<User> customerSignUp(@FieldMap Map<String, String> signUpMap);

    @FormUrlEncoded
    @POST("/user/sign_in")
    Observable<Vendor> vendorSignIn(@FieldMap Map<String, String> loginMap);
}
