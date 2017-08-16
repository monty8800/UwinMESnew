package com.rlzz.uwinmes.mvp.login;

import com.rlzz.uwinmes.common.base.viewinterface.ILoadingDialog;

/**
 * 注释
 * Created by monty on 2017/8/16.
 */

public interface ILoginView extends ILoadingDialog{
    void onLoginSuccess();
    void onLoginFailure();
}
