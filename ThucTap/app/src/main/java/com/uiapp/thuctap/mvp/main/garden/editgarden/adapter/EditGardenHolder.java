package com.uiapp.thuctap.mvp.main.garden.editgarden.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongnhung on 8/2/16.
 */
public class EditGardenHolder extends RecyclerView.ViewHolder {

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

    @Bind(R.id.img_edit)
    ImageView mImgEdit;

    public EditGardenHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
