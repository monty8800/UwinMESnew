package com.rlzz.uwinmes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.common.base.BaseActivity;
import com.rlzz.uwinmes.utils.ToolBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_testTask)
    Button btnTestTask;
    @BindView(R.id.textView)
    TextView textView;

    public static void GoToActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

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

    @OnClick(R.id.btn_testTask)
    public void onViewClicked() {
        TestTaskActivity.GoToActivity(this);
    }
}
