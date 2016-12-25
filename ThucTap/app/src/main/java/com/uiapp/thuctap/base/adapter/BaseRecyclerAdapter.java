package com.uiapp.thuctap.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hongnhung on 7/6/16.
 */
public class BaseRecyclerAdapter extends RecyclerView.Adapter {

    protected ArrayList<IRecyclerItem> mItems;

    protected OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener= listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.size()>0){
            return mItems.get(position).getItemViewType();
        }
        return -1;
    }
}
