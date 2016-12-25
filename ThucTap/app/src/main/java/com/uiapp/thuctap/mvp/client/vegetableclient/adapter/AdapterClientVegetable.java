package com.uiapp.thuctap.mvp.client.vegetableclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.model.GardenClient;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.adapter.AllVegetableHolder;
import com.uiapp.thuctap.mvp.main.garden.editgarden.interfacecallback.OnClickAction;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.List;

/**
 * Created by hongnhung on 8/21/16.
 */
public class AdapterClientVegetable extends RecyclerView.Adapter {

    public OnClickDetail mOnClickDetail;

    public void setOnClickAction(OnClickDetail mOnClickDetail) {
        this.mOnClickDetail = mOnClickDetail;
    }

    List<GardenClient> mListVegetables;

    public AdapterClientVegetable(List<GardenClient> mListVegetables) {
        this.mListVegetables = mListVegetables;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vegetable = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vegetable_client, null);
        return new ClientVegetableHolder(vegetable);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ClientVegetableHolder vegetableHolder = (ClientVegetableHolder) holder;

        final GardenClient vegetable = mListVegetables.get(position);
        vegetableHolder.mTvName.setText(vegetable.getNameVegetable());
        vegetableHolder.mTvDes.setText(vegetable.getDescription());
        vegetableHolder.mTvTotal.setText(vegetable.getTotal() + "");
        vegetableHolder.mLnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.logE("click", "Ok");
                String jsonSend = new Gson().toJson(vegetable);
                mOnClickDetail.OnClickDetailVeget(jsonSend);

            }
        });

    }


    @Override
    public int getItemCount() {
        return mListVegetables.size();
    }
}
