package com.tanqiu.ui.login.vm;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.tanqiu.subscribe.LoginSubscribe;
import com.tanqiu.ui.main1.DemoActivity;
import com.tanqiu.ui.main.activity.MainActivity;
import com.tanqiu.utils.GsonUtils;
import com.tanqiu.utils.http.OnSuccessAndFaultListener;
import com.tanqiu.utils.http.OnSuccessAndFaultSub;

import org.w3c.dom.Entity;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by LiangHuan 2019/3/11
 */

public class LoginViewModel extends BaseViewModel {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //用户名清除按钮的显示隐藏绑定
    public ObservableInt clearBtnVisibility = new ObservableInt();
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            userName.set("");
        }
    });
    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    public BindingCommand passwordShowSwitchOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uc.pSwitchEvent.setValue(uc.pSwitchEvent.getValue() == null ? true : !uc.pSwitchEvent.getValue());
        }
    });

    public BindingCommand wjpasswordOnClickCommand=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
                startActivity(DemoActivity.class);
        }
    });
    //用户名输入框焦点改变的回调事件
    public BindingCommand<Boolean> onFocusChangeCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if (hasFocus) {
                clearBtnVisibility.set(View.VISIBLE);
            } else {
                clearBtnVisibility.set(View.INVISIBLE);
            }
        }
    });
    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    /**
     * 网络模拟一个登陆操作
     **/
    private void login() {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }
        //RaJava模拟一个延迟操作
//        Observable.just("")
//                .delay(3, TimeUnit.SECONDS) //延迟3秒
//                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))//界面关闭自动取消
//                .compose(RxUtils.schedulersTransformer()) //线程调度
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        showDialog();
//                    }
//                })
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        dismissDialog();
//                        //进入DemoActivity页面
//                        startActivity(MainActivity.class);
//                        //关闭页面
//                        finish();
//                    }
//                });


        Map<String,String> map =new HashMap<>();
        map.put("loginType","mobile");
        map.put("password","youtu708");
        map.put("username","root");
        LoginSubscribe.Login(map,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GsonUtils.fromJson(result, Entity.class);
                startActivity(MainActivity.class);
            }

            @Override
            public void onFault(String errorMsg) {
              ToastUtils.showShortSafe(errorMsg);
            }
        }));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
