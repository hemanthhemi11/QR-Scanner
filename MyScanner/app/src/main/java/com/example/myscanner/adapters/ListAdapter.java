package com.example.myscanner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myscanner.R;
import com.example.myscanner.db.Data;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Data> getAllLists;
    private Context context;

    public ListAdapter(Context context, List<Data> allList) {
        this.getAllLists = allList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int position) {
        holder.desc.setText(getAllLists.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return getAllLists.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView desc;
        MyViewHolder(View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.description);

        }
    }
}
