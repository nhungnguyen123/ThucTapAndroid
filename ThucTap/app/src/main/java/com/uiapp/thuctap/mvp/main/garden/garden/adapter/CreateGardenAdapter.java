package com.uiapp.thuctap.mvp.main.garden.garden.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.adapter.BaseRecyclerAdapter;
import com.uiapp.thuctap.model.Vegetable;

import java.util.List;

/**
 * Created by hongnhung on 8/10/16.
 */
public class CreateGardenAdapter extends RecyclerView.Adapter {

    public Context mContext;

    List<Vegetable> mListVegetables;

    public CreateGardenAdapter(List<Vegetable> mListVegetables) {
        this.mListVegetables = mListVegetables;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View vegetable = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vegetable, null);
        return new VegetableHolder(vegetable);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final VegetableHolder vegetableHolder = (VegetableHolder) holder;

        final Vegetable vegetable = mListVegetables.get(position);
        vegetableHolder.mTvNameVegetable.setText(mListVegetables.get(position).getName());
        if (mListVegetables.get(position).isChoose()) {
            vegetableHolder.mTvNameVegetable.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            vegetableHolder.mTvNameVegetable.setBackgroundColor(mContext.getResources().getColor(R.color.colorBackgroundGardenItem));
        } else {
            vegetableHolder.mTvNameVegetable.setBackgroundResource(R.drawable.custom_bg_edit_black_border);
            vegetableHolder.mTvNameVegetable.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        }

        vegetableHolder.mTvNameVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean choose = vegetable.isChoose();
                vegetable.setChoose(!choose);

                if (!choose) {
                    vegetableHolder.mTvNameVegetable.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                    vegetableHolder.mTvNameVegetable.setBackgroundColor(mContext.getResources().getColor(R.color.colorBackgroundGardenItem));
                } else {
                    vegetableHolder.mTvNameVegetable.setBackgroundResource(R.drawable.custom_bg_edit_black_border);
                    vegetableHolder.mTvNameVegetable.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
                }


            }
        });
    }


    @Override
    public int getItemCount() {
        return mListVegetables.size();
    }
}
