package com.rlzz.uwinmes.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rlzz.uwinmes.common.base.presenter.Presenter;

/**
 * MVPBaseActivity
 * Created by monty on 2017/8/16.
 */

public abstract class MVPActivity<P extends Presenter> extends BaseActivity {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }
}
