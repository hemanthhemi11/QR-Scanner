package com.example.myscanner.ui.fragment.history;

import android.view.View;
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

public class HistoryFragment extends BaseFragment implements HistoryView {

    @Inject HistoryPresenter historyPresenter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.no_data)
    TextView noData;
    ListAdapter listAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_hostory;
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

    private void initRecycler() {
        AppDatabase appDatabase = AppDatabase.getInstance(getContext());
        List<Data> dataList = appDatabase.dataDao().DATA_LIST();

        if (dataList.size() == 0) {
            noData.setVisibility(View.VISIBLE);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            listAdapter = new ListAdapter(getContext(), dataList);
            recyclerView.setAdapter(listAdapter);
            noData.setVisibility(View.GONE);
        }
    }
}
