package com.uiapp.thuctap.mvp.client.allgarden.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.interactor.api.request.UpdateGardenRequest;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.model.VegetableBody;
import com.uiapp.thuctap.mvp.client.vegetableclient.adapter.OnClickDetail;
import com.uiapp.thuctap.mvp.main.garden.editgarden.adapter.EditGardenHolder;
import com.uiapp.thuctap.mvp.main.garden.editgarden.interfacecallback.OnClickAction;
import com.uiapp.thuctap.mvp.main.garden.garden.adapter.CreateGardenAdapter;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongnhung on 8/21/16.
 */
public class AdapterGarden extends RecyclerView.Adapter {


    List<Garden> list;
    Context mContext;

    OnClickDetailVegetable mOnClickDetailVegetable;

    public void setOnClickVetableDetail(OnClickDetailVegetable mOnClickDetailVegetable) {
        this.mOnClickDetailVegetable = mOnClickDetailVegetable;
    }

    public AdapterGarden(List<Garden> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View editGardenHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_garden, parent, false);
        return new GardenHolder(editGardenHolder);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final GardenHolder gardenHolder = (GardenHolder) holder;
        final Garden garden = list.get(position);
        gardenHolder.mTvName.setText(garden.getName());
        gardenHolder.mTvDescription.setText(garden.getDescription());
        gardenHolder.mTvTotalVegetable.setText(garden.getVegetableList().size() + "");
        gardenHolder.mImgDelete.setVisibility(View.GONE);
        gardenHolder.mImgEdit.setVisibility(View.GONE);
        gardenHolder.mviewLine.setVisibility(View.GONE);
        gardenHolder.mLnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String garden = new Gson().toJson(list.get(position));

                mOnClickDetailVegetable.OnClickDetail(garden);
                LogUtils.logE("ok", "click");
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
