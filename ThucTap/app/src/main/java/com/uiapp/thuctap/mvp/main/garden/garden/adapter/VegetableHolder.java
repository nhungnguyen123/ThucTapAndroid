package com.uiapp.thuctap.mvp.main.garden.garden.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uiapp.thuctap.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongnhung on 8/15/16.
 */
public class VegetableHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.tv_name_vegetable)
    TextView mTvNameVegetable;

    @Bind(R.id.tv_description_vegetable)
    TextView mTvDescription;

    @Bind(R.id.ln_back)
    LinearLayout mLnBack;


    public VegetableHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
