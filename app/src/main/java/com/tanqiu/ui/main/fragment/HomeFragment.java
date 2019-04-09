package com.tanqiu.ui.main.fragment;

import android.databinding.adapters.FrameLayoutBindingAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tanqiu.BR;
import com.tanqiu.R;
import com.tanqiu.databinding.FragmentHomeBinding;
import com.tanqiu.ui.main.vm.HomeViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * Created by LiangHuan on 2019/3/20
 */

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

}
