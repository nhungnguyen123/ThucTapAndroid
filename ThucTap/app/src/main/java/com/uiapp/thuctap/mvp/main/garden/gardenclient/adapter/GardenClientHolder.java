package com.uiapp.thuctap.mvp.main.garden.gardenclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uiapp.thuctap.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongnhung on 8/1/16.
 */
public class GardenClientHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tv_name_garden_client)
    TextView mTvNameGardenClient;


    @Bind(R.id.tv_address_garden_client)
    TextView mTvAddressGardenClient;

    public GardenClientHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
