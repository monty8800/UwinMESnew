package com.rlzz.uwinmes.common.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.common.base.viewinterface.IUI;
import com.rlzz.uwinmes.utils.LogHelper;
import com.rlzz.uwinmes.utils.ToolBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by monty on 2017/8/11.
 */

public abstract class BaseActivity extends AppCompatActivity implements IUI {

    public String TAG = this.getClass().getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    FrameLayout container;

    public abstract int getContentLayoutId();

    @Override
    public void initBeforeView() {
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.d("onCreate");
        initBeforeView();
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setupView();
        initView();
        initData();
    }

    private void setupView() {
        LogHelper.d("setupView");
        setupToolbar();
        int contentLayoutId = getContentLayoutId();
        View view = LayoutInflater.from(this).inflate(contentLayoutId, null, false);
        container.addView(view);
    }

    /**
     * 初始化Toolbar
     */
    private void setupToolbar() {
        ToolBarUtil.setToolBarTitle(toolbar,"标题");
        ToolBarUtil.setToolbarNavigation(toolbar, R.mipmap.ic_launcher, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ToolBarUtil.hideToolbarNavigation(toolbar);
        LogHelper.d("setupToolbar");
        setSupportActionBar(toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onDestroy();
    }
}
