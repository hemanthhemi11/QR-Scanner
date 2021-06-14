package com.example.myscanner.ui.fragment.qr;

import com.example.myscanner.base.BasePresenter;
import com.example.myscanner.injection.ConfigPersistent;
import javax.inject.Inject;

@ConfigPersistent
public class QRPresenter extends BasePresenter<QRView> {

    @Inject
    public QRPresenter(){

    }

    @Override
    public void attachView(QRView mvpView) {
        super.attachView(mvpView);
        if (getView() != null)
            getView().setUp();
    }
}
