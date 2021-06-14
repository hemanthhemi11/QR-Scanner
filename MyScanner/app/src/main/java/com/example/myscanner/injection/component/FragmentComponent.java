package com.example.myscanner.injection.component;



import com.example.myscanner.injection.PerFragment;
import com.example.myscanner.injection.module.FragmentModule;
import com.example.myscanner.ui.fragment.history.HistoryFragment;
import com.example.myscanner.ui.fragment.qr.QRFragment;
import dagger.Subcomponent;

/**
 * This component inject dependencies to all Fragments across the application
 */

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(QRFragment qrFragment);
    void inject(HistoryFragment historyFragment);
}
