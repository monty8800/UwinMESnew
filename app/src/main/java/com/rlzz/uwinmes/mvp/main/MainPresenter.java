package com.rlzz.uwinmes.mvp.main;

import com.rlzz.uwinmes.common.base.presenter.Presenter;

/**
 * 注释
 *
 * @e-mail mwu@szrlzz.com
 * Created by monty on 2017/8/17.
 */

public class MainPresenter extends Presenter<IMainView> {
    public MainPresenter(IMainView mainView) {
        attachView(mainView);
    }
}
