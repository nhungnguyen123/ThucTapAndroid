package com.uiapp.thuctap.mvp.main.garden.gardendetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.editgarden.adapter.EditGardenHolder;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.adapter.AdapterGardenClient;
import com.uiapp.thuctap.mvp.main.garden.vegetable.view.VegetableFragment;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by hongnhung on 8/13/16.
 */
public class AdapterGardenDetail extends RecyclerView.Adapter {

    List<Vegetable> mListVegetable;
    Context mContext;

    Onclick mOnclick;

    public interface Onclick {
        void OnClickDetailVegetable(int position);

    }

    public  void SetOnClick(Onclick mOnClick)
    {
        this.mOnclick = mOnClick;
    }

    public AdapterGardenDetail(List<Vegetable> mListVegetable, Context mContext) {
        this.mListVegetable = mListVegetable;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vegetableDetail = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vegetable_garden, parent, false);
        return new AdapterGardenHolder(vegetableDetail);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Vegetable vegetable = mListVegetable.get(position);
        LogUtils.logE("description", vegetable.getDescription() + "");
        AdapterGardenHolder adapterGardenHolder = (AdapterGardenHolder) holder;
        adapterGardenHolder.mTvNameVegetable.setText(vegetable.getName());
        adapterGardenHolder.mTvDescription.setText(vegetable.getDescription());
        if (position % 2 == 0) {
            adapterGardenHolder.mLnBack.setBackgroundColor(mContext.getResources().getColor(R.color.color_orange));
        } else {
            adapterGardenHolder.mLnBack.setBackgroundColor(mContext.getResources().getColor(R.color.green_garden));

        }

        adapterGardenHolder.mLnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.logE("detail", position + "");
                mOnclick.OnClickDetailVegetable(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mListVegetable.size();
    }
}
