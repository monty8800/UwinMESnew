package com.rlzz.uwinmes.mvp.login;

import com.rlzz.uwinmes.common.API;
import com.rlzz.uwinmes.common.Constants;
import com.rlzz.uwinmes.common.base.presenter.Presenter;
import com.rlzz.uwinmes.entity.Test;
import com.rlzz.uwinmes.net.ResponseModel2;
import com.rlzz.uwinmes.net.RetrofitHelper;
import com.rlzz.uwinmes.utils.LogUtil;
import com.rlzz.uwinmes.utils.PreferencesManager;

import java.util.List;

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
        PreferencesManager.getInstanceDefault().putBoolean(Constants.KEY_KEEPPASSWORD, keepPassword);

        mvpView.showLoadingDialog("正在登录...");
//        RetrofitHelper.getInstance().getService(API.class).login(account, password)
        RetrofitHelper.getInstance().getService(API.class).getAndroidData()
                .map(new Function<ResponseModel2<List<Test>>, Boolean>() {
                    @Override
                    public Boolean apply(ResponseModel2<List<Test>> stringResponseModel) throws Exception {
                        LogUtil.d(stringResponseModel.results.toString());
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
                    public void onNext(Boolean loginSuccess) {
                        if (loginSuccess) {
                            keepPassword(account, password);
                        } else {
                            clearPassword();
                        }
                        mvpView.onLoginSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("onError -> " + e);
                        mvpView.onLoginFailure("登录失败，请稍后再试!");
                        mvpView.closeLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d("onComplete");
                        mvpView.closeLoadingDialog();
                    }
                });
    }

    /**
     * 记住账号密码
     *
     * @param account
     * @param password
     */
    private void keepPassword(String account, String password) {
        PreferencesManager.getInstanceUser().putString(Constants.KEY_ACCOUNT, account);
        PreferencesManager.getInstanceUser().putString(Constants.KEY_PASSWORD, password);
    }

    /**
     * 清除密码
     */
    private void clearPassword() {
//        PreferencesManager.getInstanceUser().putString(Constants.KEY_ACCOUNT, "");
        PreferencesManager.getInstanceUser().putString(Constants.KEY_PASSWORD, "");
    }
}
