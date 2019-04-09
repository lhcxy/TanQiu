package com.tanqiu.apiservice;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 存放所有的Api
 */

public interface HttpApiService {
    //获取短信接口
    @POST("api")
    @FormUrlEncoded
    Observable<ResponseBody> Sms(@FieldMap Map<String, String> map);

    //登陆
    @POST("api/user/login")
    @FormUrlEncoded
    Observable<ResponseBody> Login(@FieldMap Map<String, String> map);
}
