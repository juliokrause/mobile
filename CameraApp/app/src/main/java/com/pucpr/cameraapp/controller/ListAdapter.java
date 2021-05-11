package com.pucpr.cameraapp.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.cameraapp.model.DataModel;
import com.pucpr.cameraapp.model.List;
import com.pucpr.cameraapp.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private static ClickListener clickListener;

    public void setClickListener(ClickListener clickListener){
        ListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position,View view);
        boolean onItemLongClick(int position,View view);
    }



    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_list_recyclerview,
                        parent,false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        List c = DataModel.getInstance().getLists().get(position);
        holder.textViewList.setText(c.getName());
        holder.textViewPreco.setText(String.valueOf(c.getvalorm()));

    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getLists().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewList;
        TextView textViewPreco;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewList = itemView.findViewById(R.id.textViewList);
            textViewPreco = itemView.findViewById(R.id.textViewPreco);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener == null)
                        return;
                    clickListener.onItemClick(getAdapterPosition(),view);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(clickListener == null)
                        return false;

                    return clickListener.onItemLongClick(getAdapterPosition(),view);
                }
            });

        }
    }
}
