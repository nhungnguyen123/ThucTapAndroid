package com.uiapp.thuctap.mvp.main.garden.addvegetable.adapter;

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
public class AllVegetableHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.tv_name_vegetable)
    TextView mTvNameVegetable;

    @Bind(R.id.tv_description_vegetable)
    TextView mTvDescription;

    @Bind(R.id.ln_back)
    LinearLayout mLnBack;


    public AllVegetableHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
