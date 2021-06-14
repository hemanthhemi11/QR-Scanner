package com.example.myscanner.ui.activity.home;

import com.example.myscanner.base.BasePresenter;
import com.example.myscanner.injection.ConfigPersistent;

import javax.inject.Inject;

@ConfigPersistent
public class HomeActivityPresenter extends BasePresenter<HomeActivityView> {

    @Inject
    public HomeActivityPresenter(){

    }

    @Override
    public void attachView(HomeActivityView mvpView) {
        super.attachView(mvpView);
        if(getView()!=null){
            getView().setUp();
        }
    }
}
