package com.uiapp.thuctap.mvp.main.garden.gardendetail.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.injections.DaggerGardenClientComponent;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.injections.GardenClientComponent;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.injections.GardenClientModule;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.view.IGardenClientView;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.adapter.AdapterGardenDetail;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.injections.DaggerGardenDetailComponent;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.injections.GardenDetailComponent;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.injections.GardenDetailModule;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.presenter.GardenDetailPresenter;
import com.uiapp.thuctap.mvp.main.garden.vegetable.view.VegetableFragment;
import com.uiapp.thuctap.utils.LogUtils;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by hongnhung on 8/13/16.
 */
public class GardenDetailFragment extends BaseFragment implements IGardenDetailView {

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


//    @Bind(R.id.appbar)
//    AppBarLayout appBarLayout;

    public Garden gardenDetailObject;

    public String gardemDetail;

    AdapterGardenDetail mAdapterDetail;


    MainListener mMainListener;

    @Inject
    GardenDetailPresenter mPresenter;

    public static GardenDetailFragment newInstance(String garden) {

        Bundle args = new Bundle();
        GardenDetailFragment fragment = new GardenDetailFragment();
        args.putString("POSITION_DETAIL", garden);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GardenDetailComponent component = DaggerGardenDetailComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .gardenDetailModule(new GardenDetailModule())
                .build();
        component.inject(this);
        mPresenter.attachView(this);

        gardemDetail = getArguments().getString("POSITION_DETAIL");

        gardenDetailObject = new Gson().fromJson(gardemDetail, Garden.class);
        mPresenter.setGardenDetail(gardenDetailObject);

        LogUtils.logE("positionHave", gardemDetail + "");

    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_garden_detail;
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
    protected void initData() {
        mBtnAdd.setVisibility(View.VISIBLE);
        mRvVegetable.setHasFixedSize(true);
        mRvVegetable.setLayoutManager(new GridLayoutManager(getContext(), 2));
        LogUtils.logE("sizeList", gardenDetailObject.getVegetableList().size() + "");
        mAdapterDetail = new AdapterGardenDetail(gardenDetailObject.getVegetableList(), getContext());

        mRvVegetable.setAdapter(mAdapterDetail);

        mAdapterDetail.SetOnClick(new AdapterGardenDetail.Onclick() {
            @Override
            public void OnClickDetailVegetable(int position) {

                LogUtils.logE(TAG, "posotion" + position);

                mPresenter.setVegetableDetail(position);

            }
        });
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    collapsingToolbar.setTitle(getString(R.string.app_name));
//                    isShow = true;
//                } else if (isShow) {
//                    collapsingToolbar.setTitle(" ");
//                    isShow = false;
//                }
//            }
//        });
        mTvAddress.setText(gardenDetailObject.getAddress());
        mTvNamegarden.setText(gardenDetailObject.getName());


    }

    @Override
    protected void initListener() {

    }


    @Override
    public void setVegetableDetail(Vegetable vegetableDetail) {

        String vegetableDetailString = new Gson().toJson(vegetableDetail);
        mMainListener.goVegetableDetail(vegetableDetailString);

    }
}
