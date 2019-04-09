package com.tanqiu.subscribe;

import android.util.Log;

import com.tanqiu.apiservice.HttpApiService;
import com.tanqiu.utils.GsonUtils;
import com.tanqiu.utils.http.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginSubscribe {

    private static final String Tag="LoginSubscribe";

    //获取短信接口 DisposableObserver<ResponseBody> subscriber
    public static void getSmsDataForMap(Map<String,String> map){

        RetrofitClient.getInstance().create(HttpApiService.class).Sms(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int i;
               while ((i=responseBody.byteStream().read())!=-1){
                   baos.write(i);
               }
                String str = baos.toString();
                Log.i("str",str);
            }
        });





//        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().Sms(map);
//        //RetrofitFactory.getInstance().changeBaseUrl("");
//        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }


//    public static void Login(Map<String,String> map) {
//
//        RetrofitClient.getInstance().create(HttpApiService.class).Login(map).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
//            @Override
//            public void accept(ResponseBody responseBody) throws Exception {
//               //  BaseEntity baseEntity =getData(responseBody,BaseEntity.class);
//                // Log.i(Tag,baseEntity.getStatus()+"");
//                getData(responseBody,BaseEntity.class);
//            }
//        });
//
//
//    }

    public static void Login(Map<String,String> map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitClient.getInstance().create(HttpApiService.class).Login(map);

        RetrofitClient.getInstance().toSubscribe(observable,subscriber);


    }

    public static <T>T getData(ResponseBody responseBody,
                            final Class<T> entity
                               ) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = responseBody.byteStream().read()) != -1) {
            baos.write(i);
        }
        String str = baos.toString();
        Log.i(Tag,str);
       // GsonUtils.fromJson(str, BaseEntity.class).getData();
        return  GsonUtils.fromJson(str,entity);
    }

}
