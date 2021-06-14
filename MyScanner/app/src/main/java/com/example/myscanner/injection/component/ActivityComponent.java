package com.example.myscanner.injection.component;

import com.example.myscanner.injection.PerActivity;
import com.example.myscanner.injection.module.ActivityModule;
import com.example.myscanner.ui.activity.home.HomeActivity;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
     void inject(HomeActivity homeActivity);
}
