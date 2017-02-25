package com.example.parktaejun.linker.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by parktaejun on 2017. 2. 25..
 */

public interface JSONService {

    @FormUrlEncoded
    @POST("/login")
    Call<User> login(@Field("user_id") String user_id, @Field("user_password") String user_pw);

    @FormUrlEncoded
    @POST("/register")
    Call<User> register(@Field("user_id") String user_id, @Field("user_password") String user_pw, @Field("user_name") String user_name);

    @FormUrlEncoded
    @POST("/loadlist")
    Call<List<User>> loadlist();

    @FormUrlEncoded
    @POST("/savechat")
    Call<Chat> save(@Field("roomnumber") String room, @Field("who") String who, @Field("msg") String msg);

    @FormUrlEncoded
    @POST("/loadlist")
    Call<List<Chat>> loadchat();
}