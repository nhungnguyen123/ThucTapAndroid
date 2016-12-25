package com.uiapp.thuctap.mvp.client.allgarden.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uiapp.thuctap.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongnhung on 8/21/16.
 */
public class GardenHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tv_name_garden)
    TextView mTvName;


    @Bind(R.id.tv_total_vegetable)
    TextView mTvTotalVegetable;

    @Bind(R.id.tv_description)
    TextView mTvDescription;

    @Bind(R.id.imv_garden)
    ImageView mImgGarden;

    @Bind(R.id.ln_detail_information)
    LinearLayout mLnDetail;

    @Bind(R.id.ln_edit_garden)
    LinearLayout mLnEditGarden;

    @Bind(R.id.img_delete)
    ImageView mImgDelete;

    @Bind(R.id.view_line)
    View mviewLine;

    @Bind(R.id.img_edit)
    ImageView mImgEdit;

    public GardenHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}