package com.ld.collegetask;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by heliang on 2017/9/1.
 */

public interface RequestSerives {
    @POST("/app/login.jspx")
    @FormUrlEncoded
    Call<String> login(@Field("CallBack") String callback,
                       @Field("username") String username,
                       @Field("password") String password,
                       @Field("version") String version);//version=1,md5加密

//    @POST("app/qmgb-user-login.jspx")
//    @FormUrlEncoded
//    Call<User> login2(@Field("username") String userCode,
//                            @Field("password") String password);

    //首页获取学时、学习档案
    @POST("/app/user-archives.jspx")
    @FormUrlEncoded
    Call<String> get_user_archives(@Field("CallBack") String callback,
                                   @Field("userId") String userId);


}
