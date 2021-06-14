package com.example.myscanner.base;

public interface Presenter <V extends BaseView> {

    void attachView(V mvpView);

    void detachView();
}
