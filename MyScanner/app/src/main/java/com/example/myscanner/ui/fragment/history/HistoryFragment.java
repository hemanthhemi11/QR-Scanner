package com.example.myscanner.ui.fragment.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myscanner.R;
import com.example.myscanner.adapters.ListAdapter;
import com.example.myscanner.base.BaseFragment;
import com.example.myscanner.db.AppDatabase;
import com.example.myscanner.db.Data;
import com.example.myscanner.injection.component.FragmentComponent;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import com.example.myscanner.databinding.FragmentHostoryBinding;

public class HistoryFragment extends BaseFragment implements HistoryView {

    @Inject HistoryPresenter historyPresenter;
    ListAdapter listAdapter;
    FragmentHostoryBinding fragmentHostoryBinding;

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container, boolean b) {
        fragmentHostoryBinding = FragmentHostoryBinding.inflate(inflater,container,false);
        return fragmentHostoryBinding.getRoot();
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        historyPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        historyPresenter.detachView();
    }

    @Override
    public void setUp() {
        initRecycler();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentHostoryBinding = null;
    }

    private void initRecycler() {
        AppDatabase appDatabase = AppDatabase.getInstance(getContext());
        List<Data> dataList = appDatabase.dataDao().DATA_LIST();

        if (dataList.size() == 0) {
            fragmentHostoryBinding.noData.setVisibility(View.VISIBLE);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.VERTICAL, false);
            fragmentHostoryBinding.recyclerview.setLayoutManager(linearLayoutManager);
            fragmentHostoryBinding.recyclerview.setHasFixedSize(true);
            listAdapter = new ListAdapter(getContext(), dataList);
            fragmentHostoryBinding.recyclerview.setAdapter(listAdapter);
            fragmentHostoryBinding.noData.setVisibility(View.GONE);
        }
    }
}
