package com.uiapp.thuctap.mvp.client.allgarden.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.GardenClient;
import com.uiapp.thuctap.mvp.client.allgarden.view.adapter.AdapterGarden;
import com.uiapp.thuctap.mvp.client.allgarden.view.adapter.OnClickDetailVegetable;
import com.uiapp.thuctap.mvp.main.garden.editgarden.adapter.EditGardenAdapter;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by hongnhung on 8/21/16.
 */
public class AllGardenFragment extends BaseFragment {


    @Bind(R.id.tv_name_garden_detail)
    TextView mTvnameVegetable;

    @Bind(R.id.tv_address_garden_detail)
    TextView mTvDes;


    @Bind(R.id.recycler_view)
    RecyclerView mRvEditGarden;

    GardenClient gardenClient;


    public String allGardenDetal;

    AdapterGarden editGardenAdapter;

    MainListener mMainListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mMainListener = (MainListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement " + MainListener.class.getSimpleName());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mMainListener = (MainListener) activity;
        } catch (Exception e) {
            throw new ClassCastException(activity.toString() + " must implement " + MainListener.class.getSimpleName());
        }
    }


    public static AllGardenFragment newInstance(String message) {

        Bundle args = new Bundle();

        AllGardenFragment fragment = new AllGardenFragment();
        args.putString("VEGETABLE_DETAIL_GARDEN", message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allGardenDetal = getArguments().getString("VEGETABLE_DETAIL_GARDEN");
        gardenClient = new Gson().fromJson(allGardenDetal, GardenClient.class);
        LogUtils.logE(TAG, "VEGETABLE_DETAIL_GARDEN" + allGardenDetal + "");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_vegetable_client;
    }

    @Override
    protected void initData() {
        mTvnameVegetable.setText(gardenClient.getNameVegetable());
        mTvDes.setText(gardenClient.getDescription());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRvEditGarden.setLayoutManager(mLayoutManager);
        mRvEditGarden.setHasFixedSize(true);
        editGardenAdapter = new AdapterGarden(gardenClient.getmListGarden(), getActivity());
        mRvEditGarden.setAdapter(editGardenAdapter);

        editGardenAdapter.setOnClickVetableDetail(new OnClickDetailVegetable() {
            @Override
            public void OnClickDetail(String idGarden) {
                Garden gardendetail = new Gson().fromJson(idGarden, Garden.class);
                LogUtils.logE("gardenClientId", gardenClient.getId());

                for (int i = 0; i < gardendetail.getVegetableList().size(); i++) {
                    LogUtils.logE("vegetId", gardendetail.getVegetableList().get(i).getId());
                    if (gardenClient.getId().equalsIgnoreCase(gardendetail.getVegetableList().get(i).getId()))
                    {
                        String vegetDetail = new Gson().toJson(gardendetail.getVegetableList().get(i));
                        mMainListener.goVegetableDetail(vegetDetail);
//                        LogUtils.logE(TAG, "vegetDetail :" + vegetDetail);
                    }
                }


            }
        });
    }

    @Override
    protected void initListener() {

    }
}
