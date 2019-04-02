package com.tanqiu.subscribe;

import com.tanqiu.apiservice.HttpApiService;
import com.tanqiu.utils.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginSubscribe {

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
//                Log.i("str",str);
            }
        });




//        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().Sms(map);
//        //RetrofitFactory.getInstance().changeBaseUrl("");
//        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }






}
