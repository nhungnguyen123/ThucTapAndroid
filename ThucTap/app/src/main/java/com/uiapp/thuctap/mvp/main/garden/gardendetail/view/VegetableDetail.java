package com.uiapp.thuctap.mvp.main.garden.gardendetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.utils.LogUtils;

import butterknife.Bind;

/**
 * Created by hongnhung on 8/17/16.
 */
public class VegetableDetail extends BaseFragment {


    @Bind(R.id.edi_name_vegetable)
    TextView mEdtName;


    @Bind(R.id.edt_pd)
    TextView mEdtPh;

    @Bind(R.id.tv_day_from)
    TextView mTvFrom;
    //
    @Bind(R.id.tv_day_to)
    TextView mTvTo;


    @Bind(R.id.edt_pesticides)
    TextView mEdtPesticides;

    @Bind(R.id.edt_description)
    TextView mEdtDescription;

    public String informaion;

    public static VegetableDetail newInstance() {

        Bundle args = new Bundle();

        VegetableDetail fragment = new VegetableDetail();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        informaion = bundle.getString("VEGETABLE");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vegetable_detail;
    }

    @Override
    protected void initData() {

        LogUtils.logE(TAG, "information" + informaion);
        Vegetable vegetable = new Gson().fromJson(informaion, Vegetable.class);
        mEdtName.setText(vegetable.getName());
        mEdtPesticides.setText(vegetable.getPesticides());
        mEdtPh.setText(vegetable.getPh());
        mEdtDescription.setText(vegetable.getDescription());
        mTvTo.setText(vegetable.getFinishAt());
        mTvFrom.setText(vegetable.getCreatedAt());

    }

    @Override
    protected void initListener() {

    }
}
