package com.tanqiu.ui.main1;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.tanqiu.entity.FormEntity;
import com.tanqiu.subscribe.LoginSubscribe;
import com.tanqiu.ui.form.FormFragment;
import com.tanqiu.ui.network.NetWorkFragment;
import com.tanqiu.ui.rv_multi.MultiRecycleViewFragment;
import com.tanqiu.ui.main.activity.MainActivity;
import com.tanqiu.ui.viewpager.activity.ViewPagerActivity;
import com.tanqiu.ui.vp_frg.ViewPagerGroupFragment;
import com.tanqiu.utils.http.HttpsUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * Created by LiangHuan on 2019/3/20
 */

public class DemoViewModel extends BaseViewModel {
    //使用Observable
    public SingleLiveEvent<Boolean> requestCameraPermissions = new SingleLiveEvent();
    //使用LiveData
    public MutableLiveData<String> loadUrl = new MutableLiveData();

    public DemoViewModel(@NonNull Application application) {
        super(application);
    }

    //网络访问点击事件
    public BindingCommand netWorkClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startContainerActivity(NetWorkFragment.class.getCanonicalName());
        }
    });
    //RecycleView多布局
    public BindingCommand rvMultiClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startContainerActivity(MultiRecycleViewFragment.class.getCanonicalName());
        }
    });
    //进入TabBarActivity
    public BindingCommand startTabBarClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MainActivity.class);
        }
    });
    //ViewPager绑定
    public BindingCommand viewPagerBindingClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ViewPagerActivity.class);
        }
    });
    //ViewPager+Fragment
    public BindingCommand viewPagerGroupBindingClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startContainerActivity(ViewPagerGroupFragment.class.getCanonicalName());
        }
    });
    //表单提交点击事件
    public BindingCommand formSbmClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startContainerActivity(FormFragment.class.getCanonicalName());
        }
    });

    //上传文件
    public BindingCommand ScClick =new BindingCommand(new BindingAction() {
        @Override
        public void call() {
           File file =new File("/mnt/shared/Image/ios.png");
           HttpsUtils.PostFile("http://192.168.0.251:5555/file/multipart/upload",file);
        }
    });

    //Sms
    public BindingCommand SmsClick =new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Map<String,String> map =new HashMap<>();
            map.put("version","v1");
            map.put("city","长沙");
            LoginSubscribe.getSmsDataForMap(map);
       }
    });




    //表单修改点击事件
    public BindingCommand formModifyClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //模拟一个修改的实体数据
            FormEntity entity = new FormEntity();
            entity.setId("12345678");
            entity.setName("goldze");
            entity.setSex("1");
            entity.setBir("xxxx年xx月xx日");
            entity.setMarry(true);
            //传入实体数据
            Bundle mBundle = new Bundle();
            mBundle.putParcelable("entity", entity);
            startContainerActivity(FormFragment.class.getCanonicalName(), mBundle);
        }
    });
    //权限申请
    public BindingCommand permissionsClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestCameraPermissions.call();
        }
    });

    //全局异常捕获
    public BindingCommand exceptionClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //伪造一个异常
            Integer.parseInt("goldze");
        }
    });
    //文件下载
    public BindingCommand fileDownLoadClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            loadUrl.setValue("http://gdown.baidu.com/data/wisegame/a2cd8828b227b9f9/neihanduanzi_692.apk");
        }
    });





    }


