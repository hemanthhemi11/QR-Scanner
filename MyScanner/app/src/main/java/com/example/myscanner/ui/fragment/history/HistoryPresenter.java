package com.example.myscanner.ui.fragment.history;

import com.example.myscanner.base.BasePresenter;
import com.example.myscanner.injection.ConfigPersistent;
import javax.inject.Inject;

@ConfigPersistent
public class HistoryPresenter extends BasePresenter<HistoryView> {

    @Inject
    public HistoryPresenter(){

    }

    @Override
    public void attachView(HistoryView mvpView) {
        super.attachView(mvpView);
        if (getView() != null)
            getView().setUp();
    }
}
