package com.uiapp.thuctap.base.presenter;

/**
 * Created by hongnhung on 7/6/16.
 */
public interface IPresenter<V extends IView> {
    void attachView(V mvpView);

    void detachView();
}
