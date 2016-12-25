package com.uiapp.thuctap.mvp.main.garden.addvegetable.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.adapter.AdapterAllVegetable;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.injections.AllVegetableComponent;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.injections.AllVegetableModule;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.injections.DaggerAllVegetableComponent;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.presenter.AllVegetablePresenter;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by hongnhung on 8/15/16.
 */
public class AllVegetableFragement extends BaseFragment implements IAllVegetableView {


//    @Bind(R.id.choice)
//    TextView mBtnchoice;


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recycler_view)
    RecyclerView mRvVegetable;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Bind(R.id.tv_name_garden_detail)
    TextView mTvNamegarden;

    @Bind(R.id.tv_address_garden_detail)
    TextView mTvAddress;

    @Bind(R.id.btn_add)
    Button mBtnAdd;
    private int form;


    MainListener mMainListener;


    public interface OnAddVegetable {
        void addVegetable(ArrayList<Vegetable> listVegetable);
    }

    public OnAddVegetable mSetVegetable;

    public void setOnAddVegetable(OnAddVegetable mSetVegetable) {
        this.mSetVegetable = mSetVegetable;

    }

    AdapterAllVegetable adapterAllVegetable;

    @Inject
    AllVegetablePresenter mPresenter;


    public static AllVegetableFragement newAll() {
        AllVegetableFragement allVegetableFragement = new AllVegetableFragement();
        Bundle args = new Bundle();
        allVegetableFragement.setArguments(args);
        return allVegetableFragement;

    }

    public static AllVegetableFragement newInstance(int type, ArrayList<String> inforGarden) {

        Bundle args = new Bundle();

        AllVegetableFragement fragment = new AllVegetableFragement();
        switch (type) {
            case AppConstants.VEGETABLE_CHOOSE:
                args.putInt(AppConstants.VEGETABLE_TYPE, AppConstants.VEGETABLE_CHOOSE);
                break;
        }
        args.putStringArrayList("INFO_GARDEN", inforGarden);
        fragment.setArguments(args);
        return fragment;
    }

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AllVegetableComponent component = DaggerAllVegetableComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .allVegetableModule(new AllVegetableModule())
                .build();

        form = getArguments().getInt(AppConstants.VEGETABLE_TYPE);

        component.inject(this);
        mPresenter.attachView(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_garden_detail;
    }

    @Override
    protected void initData() {

        LogUtils.logE(TAG, form + "form");
        if (form == 1) {
            mBtnAdd.setVisibility(View.INVISIBLE);
        } else {
            mBtnAdd.setVisibility(View.VISIBLE);
        }
        mTvNamegarden.setText("ALL VEGETABLE");
        mTvAddress.setText("vegetable good for heather");
        mTvNamegarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.logE(TAG, "click");
            }
        });


        mPresenter.getAllVegetable();
        mRvVegetable.setHasFixedSize(true);
        mRvVegetable.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterAllVegetable = new AdapterAllVegetable(mPresenter.listVegetables);
        mRvVegetable.setAdapter(adapterAllVegetable);
    }

    @Override
    protected void initListener() {

        /**
         * Add vegettable
         */

    }

    @Override
    public void getAllVegetableSuccess() {
        adapterAllVegetable.notifyDataSetChanged();


/**
 * Can't return Fragment
 */
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Vegetable> listVegetable = new ArrayList<Vegetable>();
                for (int i = 0; i < mPresenter.listVegetables.size(); i++) {
                    if (mPresenter.listVegetables.get(i).isChoose()) {
                        Vegetable vegetable = new Vegetable();
                        vegetable.setId(mPresenter.listVegetables.get(i).getId());
                        vegetable.setName(mPresenter.listVegetables.get(i).getName());

                        listVegetable.add(vegetable);
                    }
                }
                LogUtils.logE(TAG, "list" + listVegetable.size() + "");
                if (listVegetable.size() == 0) {
                } else {
                    mSetVegetable.addVegetable(listVegetable);
                    mMainListener.onBackScreen();
                }


            }
        });
    }

    @Override
    public void getAllVegetanleError(String error) {
        LogUtils.logE(TAG, error);
    }
}
