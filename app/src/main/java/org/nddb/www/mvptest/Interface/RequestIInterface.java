package org.nddb.www.mvptest.Interface;

import org.nddb.www.mvptest.Model.Activitys;
import org.nddb.www.mvptest.Model.Android;
import org.nddb.www.mvptest.Model.User;
import org.nddb.www.mvptest.Model.Users;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by admin on 09/28/2017.
 */

public interface RequestIInterface {

    @FormUrlEncoded
    @POST("/api/users")
    Observable<Response<Users>> getUser(@FieldMap HashMap<String, String> hashMap);

    @GET("android/jsonarray/")
    Observable<List<Android>> getData();

    @GET("api/users")
    Observable<Response<User>> getUSERS(@Query("page") Integer i);

    @GET("/api/unknown")
    Observable<Response<User>> getUsersUnknown();

    @GET("api/users")
    Observable<Response<User>> getDelayResponse(@Query("delay") int abc);

    @GET("api/Activities")
    Observable<Response<List<Activitys>>> getActivitys();











}
