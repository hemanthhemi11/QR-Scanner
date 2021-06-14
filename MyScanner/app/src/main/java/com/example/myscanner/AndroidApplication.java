package com.example.myscanner;

import android.app.Application;
import android.content.Context;

import com.example.myscanner.injection.component.AppComponent;
import com.example.myscanner.injection.component.DaggerAppComponent;
import com.example.myscanner.injection.module.AppModule;


public class AndroidApplication extends Application {

    private AppComponent appComponent;

    public static AndroidApplication get(Context context) {
        return (AndroidApplication) context.getApplicationContext();
    }

    public AppComponent getComponent() {
        if (appComponent == null) {

         appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
