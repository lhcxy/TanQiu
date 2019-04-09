package com.tanqiu.ui.main.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import com.tanqiu.BR;
import com.tanqiu.R;
import com.tanqiu.databinding.ActivityTabBarBinding;
import com.tanqiu.ui.main.fragment.HomeFragment;
import com.tanqiu.ui.main.fragment.MatchFragment;
import com.tanqiu.ui.main.fragment.GuessFragment;
import com.tanqiu.ui.main.fragment.ChatFragment;
import com.tanqiu.ui.main.fragment.MyFragment;
import com.tanqiu.ui.main.vm.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

/**
 * 底部tab按钮的例子
 *
 * Created by LiangHuan on 2019/3/20
 */

public class MainActivity extends BaseActivity<ActivityTabBarBinding, HomeViewModel> {
    private List<Fragment> mFragments;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_tab_bar;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        //初始化Fragment
        initFragment();
        //初始化底部Button
        initBottomTab();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new MatchFragment());
        mFragments.add(new GuessFragment());
        mFragments.add(new ChatFragment());
        mFragments.add(new MyFragment());
        //默认选中第一个
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, mFragments.get(0));
        transaction.commitAllowingStateLoss();
    }

    private void initBottomTab() {
        NavigationController navigationController = binding.pagerBottomTab.material()
                .addItem(R.mipmap.yingyong, "首页")
                .addItem(R.mipmap.huanzhe, "赛事")
                .addItem(R.mipmap.xiaoxi_select, "竞猜")
                .addItem(R.mipmap.yingyong,"聊天")
                .addItem(R.mipmap.yingyong,"我的")
                .setDefaultColor(ContextCompat.getColor(this, R.color.textColorVice))
                .build();
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, mFragments.get(index));
                transaction.commitAllowingStateLoss();
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }
}
