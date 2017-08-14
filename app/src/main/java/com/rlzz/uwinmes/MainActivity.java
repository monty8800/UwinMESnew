package com.rlzz.uwinmes;

import android.os.Bundle;

import com.rlzz.uwinmes.common.base.BaseActivity;
import com.rlzz.uwinmes.utils.ToolBarUtil;

public class MainActivity extends BaseActivity {

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initBeforeView() {
        // TODO: 2017/8/11 init presenter
    }

    @Override
    public void initView() {
        ToolBarUtil.hideToolbarNavigation(getToolbar());
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
