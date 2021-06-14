package com.example.myscanner.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.LongSparseArray;
import com.example.myscanner.AndroidApplication;
import com.example.myscanner.data.PreferenceManager;
import com.example.myscanner.injection.component.ActivityComponent;
import com.example.myscanner.injection.component.ConfigPersistentComponent;
import com.example.myscanner.injection.component.DaggerConfigPersistentComponent;
import com.example.myscanner.injection.module.ActivityModule;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final LongSparseArray<ConfigPersistentComponent> componentsArray = new LongSparseArray<>();
    private Activity mContext;
    private long activityId;
    private PreferenceManager mPref;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(getLayout());
        } catch (Exception e){
            Log.e("NO LAYOUT","layout");
        }
        ButterKnife.bind(this);
        mContext = this;
        mPref = PreferenceManager.instance(mContext);

        ConfigPersistentComponent configPersistentComponent;

        if (componentsArray.get(activityId) == null) {

            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .appComponent(AndroidApplication.get(this).getComponent())
                    .build();
            componentsArray.put(activityId, configPersistentComponent);
        } else {

            configPersistentComponent = componentsArray.get(activityId);
        }
        ActivityComponent activityComponent =
                configPersistentComponent.activityComponent(new ActivityModule(this));
        inject(activityComponent);
        attachView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected abstract int getLayout();

    protected abstract void inject(ActivityComponent activityComponent);

    protected abstract void attachView();

    protected abstract void detachPresenter();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            componentsArray.remove(activityId);
        }
        detachPresenter();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContext = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

