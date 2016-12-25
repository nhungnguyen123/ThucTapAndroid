package com.uiapp.thuctap.mvp.main.garden.gardendetail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uiapp.thuctap.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongnhung on 8/13/16.
 */
public class AdapterGardenHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.tv_name_vegetable)
    TextView mTvNameVegetable;

    @Bind(R.id.tv_description_vegetable)
    TextView mTvDescription;

    @Bind(R.id.ln_back)
    LinearLayout mLnBack;


    public AdapterGardenHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
