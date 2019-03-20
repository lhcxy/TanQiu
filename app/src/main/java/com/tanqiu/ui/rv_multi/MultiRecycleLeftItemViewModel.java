package com.tanqiu.ui.rv_multi;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import me.goldze.mvvmhabit.base.MultiItemViewModel;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by LiangHuan
 * Create Date：2019/03/20
 * Description：
 */

public class MultiRecycleLeftItemViewModel extends MultiItemViewModel<MultiRecycleViewModel> {
    public ObservableField<String> text = new ObservableField<>("");

    public MultiRecycleLeftItemViewModel(@NonNull MultiRecycleViewModel viewModel, String text) {
        super(viewModel);
        this.text.set(text);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //拿到position
            int position = viewModel.observableList.indexOf(MultiRecycleLeftItemViewModel.this);
            ToastUtils.showShort("position：" + position);
        }
    });
}
