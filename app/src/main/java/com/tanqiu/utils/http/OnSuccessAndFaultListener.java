package com.tanqiu.utils.http;

/**
 * Created by LiangHuan on 2019/4/9.
 */
public interface OnSuccessAndFaultListener {
    void onSuccess(String result);

    void onFault(String errorMsg);
}
