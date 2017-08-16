package com.rlzz.uwinmes.mvp.login;

import com.rlzz.uwinmes.common.API;
import com.rlzz.uwinmes.common.Constants;
import com.rlzz.uwinmes.common.base.presenter.Presenter;
import com.rlzz.uwinmes.net.ResponseModel;
import com.rlzz.uwinmes.net.RetrofitHelper;
import com.rlzz.uwinmes.utils.LogUtil;
import com.rlzz.uwinmes.utils.PreferencesManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 注释
 * Created by monty on 2017/8/16.
 */

public class LoginPresenter extends Presenter<ILoginView> {
    public LoginPresenter(ILoginView loginView) {
        attachView(loginView);
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @param keepPassword 是否记住密码
     */
    public void login(final String account, final String password, final boolean keepPassword) {
        PreferencesManager.putBoolean(Constants.KEY_KEEPPASSWORD, keepPassword);
        mvpView.showLoadingDialog("正在登录");
//        RetrofitHelper.getInstance().getService(API.class).login(account, password)
        RetrofitHelper.getInstance().getService(API.class).getContentFromDay("2017/08/02")
                .map(new Function<ResponseModel<String>, Boolean>() {
                    @Override
                    public Boolean apply(ResponseModel<String> stringResponseModel) throws Exception {
                        LogUtil.d(stringResponseModel.data);
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.d("onSubscribe");
                    }

                    @Override
                    public void onNext(Boolean value) {
                        if (value) LogUtil.d("登录成功");
                        else LogUtil.d("登录失败");
                        if (keepPassword) {
                            PreferencesManager.putString(Constants.KEY_ACCOUNT, account);
                            PreferencesManager.putString(Constants.KEY_PASSWORD, password);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("onError -> " + e);
                        mvpView.closeLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d("onComplete");
                        mvpView.closeLoadingDialog();
                    }
                });
    }
}
