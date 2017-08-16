package com.rlzz.uwinmes.common.base.presenter;

/**
 * Created by monty on 2017/8/11.
 */

public abstract class Presenter<V> {
    public V mvpView;
    public void attachView(V view){
        this.mvpView = view;
    }

    public void detachView(){
        this.mvpView = null;
    }
}
