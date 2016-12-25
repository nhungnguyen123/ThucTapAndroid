package com.uiapp.thuctap.mvp.main.garden.addvegetable.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uiapp.thuctap.R;
import com.uiapp.thuctap.model.Vegetable;

import java.util.List;

/**
 * Created by hongnhung on 8/15/16.
 */
public class AdapterAllVegetable extends RecyclerView.Adapter {

    List<Vegetable> mListVegetables;

    public AdapterAllVegetable(List<Vegetable> mListVegetables) {
        this.mListVegetables = mListVegetables;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vegetable = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vegetable, null);
        return new AllVegetableHolder(vegetable);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final AllVegetableHolder vegetableHolder = (AllVegetableHolder) holder;
        vegetableHolder.mTvNameVegetable.setText(mListVegetables.get(position).getName());
        vegetableHolder.mTvDescription.setText(mListVegetables.get(position).getDescription());
        vegetableHolder.mLnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean choose = mListVegetables.get(position).isChoose();
                mListVegetables.get(position).setChoose(!choose);
                if (!choose)
                {
                    vegetableHolder.mLnBack.setBackgroundResource(R.color.green_garden);


                }else {
                    vegetableHolder.mLnBack.setBackgroundResource(R.color.color_orange);
                }
            }
        });


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mListVegetables.size();
    }


}
