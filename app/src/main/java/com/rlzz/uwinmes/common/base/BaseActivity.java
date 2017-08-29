package com.rlzz.uwinmes.common.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.common.base.viewinterface.IUI;
import com.rlzz.uwinmes.utils.LogUtil;
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
        LogUtil.d("onCreate");
        initBeforeView();
        setContentView(R.layout.activity_base);
        setupView((ViewGroup) findViewById(R.id.container));
        ButterKnife.bind(this);
        setupToolbar();
        initView();
        initData();
    }

    private void setupView(ViewGroup containerView) {
        LogUtil.d("setupView");

        int contentLayoutId = getContentLayoutId();
        View view = LayoutInflater.from(this).inflate(contentLayoutId, null, false);
        containerView.addView(view);
    }

    /**
     * 初始化Toolbar
     */
    private void setupToolbar() {
        ToolBarUtil.setToolBarTitle(toolbar, "标题");
        ToolBarUtil.setToolbarNavigation(toolbar, R.mipmap.ic_launcher, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LogUtil.d("setupToolbar");
        setSupportActionBar(toolbar);
    }

    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中");
        progressDialog.show();
        return progressDialog;
    }

    protected ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.mvpView.WindowLeaked
            progressDialog.dismiss();
        }
    }

}
