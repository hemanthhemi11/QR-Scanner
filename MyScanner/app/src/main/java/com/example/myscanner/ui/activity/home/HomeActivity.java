package com.example.myscanner.ui.activity.home;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.myscanner.R;
import com.example.myscanner.base.BaseActivity;
import com.example.myscanner.databinding.ActivityMainBinding;
import com.example.myscanner.injection.component.ActivityComponent;
import com.example.myscanner.ui.fragment.history.HistoryFragment;
import com.example.myscanner.ui.fragment.qr.QRFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import javax.inject.Inject;
import butterknife.BindView;


public class HomeActivity extends BaseActivity implements HomeActivityView,
        BottomNavigationView.OnNavigationItemSelectedListener  {

    boolean exit = false;
    @Inject
    HomeActivityPresenter homeActivityPresenter;
    ActivityMainBinding activityMainBinding;


    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]
            {"android.permission.CAMERA"};

    @Override
    protected View getLayout() {
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        return view;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        homeActivityPresenter.attachView(this);
    }

    @Override
    public void setUp() {
        //loading the default fragment
        if(allPermissionsGranted()){
            loadFragment(new QRFragment());
            activityMainBinding.navigation.setOnNavigationItemSelectedListener(this);
        } else{
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    @Override
    protected void detachPresenter() {
        homeActivityPresenter.detachView();
    }

    private boolean allPermissionsGranted(){
        for(String permission : REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                loadFragment(new QRFragment());
                activityMainBinding.navigation.setOnNavigationItemSelectedListener(this);
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(getApplicationContext(),R.string.confirm_exit,Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);// 3 sec
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.qr_scanner:
                fragment = new QRFragment();
                break;

            case R.id.history:
                fragment = new HistoryFragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
