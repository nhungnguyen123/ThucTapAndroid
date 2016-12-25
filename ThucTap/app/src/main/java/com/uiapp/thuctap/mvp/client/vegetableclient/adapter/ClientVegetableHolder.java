package com.uiapp.thuctap.mvp.client.vegetableclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uiapp.thuctap.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientVegetableHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.ln_back_client)
    LinearLayout mLnBack;
    @Bind(R.id.tv_name_vegetable_client)
    TextView mTvName;

    @Bind(R.id.tv_description_vegetable_client)
    TextView mTvDes;


    @Bind(R.id.tv_total_garden)
    TextView mTvTotal;

    public ClientVegetableHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
