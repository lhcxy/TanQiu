package com.tanqiu.ui.vp_frg;

import android.support.v4.app.Fragment;


import com.tanqiu.ui.base.fragment.BasePagerFragment;
import com.tanqiu.ui.main.fragment.HomeFragment;
import com.tanqiu.ui.main.fragment.MatchFragment;
import com.tanqiu.ui.main.fragment.GuessFragment;
import com.tanqiu.ui.main.fragment.ChatFragment;

import java.util.ArrayList;
import java.util.List;

/**
   Created by LiangHuan 2019/3/11
 * Description：ViewPager+Fragment的实现
 */

public class ViewPagerGroupFragment extends BasePagerFragment {
    @Override
    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new MatchFragment());
        list.add(new GuessFragment());
        list.add(new ChatFragment());
        return list;
    }

    @Override
    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("page1");
        list.add("page2");
        list.add("page3");
        list.add("page4");
        return list;
    }
}
