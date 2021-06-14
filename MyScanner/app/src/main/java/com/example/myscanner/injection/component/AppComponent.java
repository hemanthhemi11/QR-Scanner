package com.example.myscanner.injection.component;

import android.app.Application;
import android.content.Context;

import com.example.myscanner.AndroidApplication;
import com.example.myscanner.data.PreferenceManager;
import com.example.myscanner.injection.module.AppModule;
import com.example.myscanner.services.ApiService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {

    void inject(AndroidApplication app);

    Context context();

    Application application();

    PreferenceManager getPreferenceManager();

    ApiService apiService();

}
