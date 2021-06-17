package com.example.myscanner.ui.fragment.qr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.myscanner.R;
import com.example.myscanner.base.BaseFragment;
import com.example.myscanner.databinding.FragmentQrBinding;
import com.example.myscanner.db.AppDatabase;
import com.example.myscanner.db.Data;
import com.example.myscanner.injection.component.FragmentComponent;
import com.google.zxing.Result;
import javax.inject.Inject;
import butterknife.BindView;

public class QRFragment extends BaseFragment implements QRView {

    @Inject QRPresenter qrPresenter;
    CodeScanner mCodeScanner;
    FragmentQrBinding fragmentQrBinding;

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        fragmentQrBinding = FragmentQrBinding.inflate(inflater,container,false);
        return fragmentQrBinding.getRoot();
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        qrPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        qrPresenter.detachView();
    }

    @Override
    public void setUp() {
        startScan();
    }

    private void startScan() {
        mCodeScanner = new CodeScanner(getActivity(), fragmentQrBinding.scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), result.getText(), Toast.LENGTH_SHORT).show();
                        AppDatabase appDatabase = AppDatabase.getInstance(getContext());
                        Data data = new Data();
                        data.description = result.getText().toString();
                        appDatabase.dataDao().insertData(data);
                    }
                });
            }
        });
        fragmentQrBinding.scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentQrBinding = null;
    }
}
