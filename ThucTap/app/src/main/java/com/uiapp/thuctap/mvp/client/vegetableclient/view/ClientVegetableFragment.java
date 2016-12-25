package com.uiapp.thuctap.mvp.client.vegetableclient.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.model.User;
import com.uiapp.thuctap.mvp.client.vegetableclient.adapter.AdapterClientVegetable;
import com.uiapp.thuctap.mvp.client.vegetableclient.adapter.OnClickDetail;
import com.uiapp.thuctap.mvp.client.vegetableclient.injections.ClientVegetableComponent;
import com.uiapp.thuctap.mvp.client.vegetableclient.injections.ClientVegetableModule;
import com.uiapp.thuctap.mvp.client.vegetableclient.injections.DaggerClientVegetableComponent;
import com.uiapp.thuctap.mvp.client.vegetableclient.presenter.ClientVegetablePresenter;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.adapter.AdapterAllVegetable;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.injections.DaggerAllVegetableComponent;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.presenter.AllVegetablePresenter;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by hongnhung on 8/21/16.
 */
public class ClientVegetableFragment extends BaseFragment implements IClientVegetableView {


    @Bind(R.id.recycler_view)
    RecyclerView mRvVegetable;

    MainListener mMainListener;
    AdapterClientVegetable adapterClientVegetable;

    @Inject
    ClientVegetablePresenter mPresenter;

    public static ClientVegetableFragment newInstance() {

        Bundle args = new Bundle();

        ClientVegetableFragment fragment = new ClientVegetableFragment();
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
        ClientVegetableComponent component = DaggerClientVegetableComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .clientVegetableModule(new ClientVegetableModule())
                .build();
        component.inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_vegetable_client;
    }

    @Override
    protected void initData() {
        mPresenter.getAllUser();
        mPresenter.getAllVegetable();
        mRvVegetable.setHasFixedSize(true);
        mRvVegetable.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterClientVegetable = new AdapterClientVegetable(mPresenter.mListGardenClient);
        mRvVegetable.setAdapter(adapterClientVegetable);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getAllUserSuccess(List<User> mListUser) {

        for (int i = 0; i < mListUser.size(); i++) {
            LogUtils.logE(TAG, "Id+ " + mListUser.get(i).getId());
            mPresenter.getGarden(mListUser.get(i).getId());
        }
        LogUtils.logE(TAG, "Success GetAll User");
    }

    @Override
    public void getAllUserError(String message) {
        LogUtils.logE(TAG, "getAllUserError" + message);
    }

    @Override
    public void getAllVegetableSuccess() {

    }

    @Override
    public void getAlVegetableError(String message) {

    }

    @Override
    public void getGardenSuccess() {
        LogUtils.logE(TAG, "getGardenSuccess");
    }

    @Override
    public void getGardenError(String error) {
        LogUtils.logE(TAG, "getGardenErr" + error);
    }

    @Override
    public void sumSuccess() {
        LogUtils.logE("toal", mPresenter.mListGardenClient.size() + "");
        LogUtils.logE("totalsuccess", new Gson().toJson(mPresenter.mListGardenClient));
        for (int i = 0; i < mPresenter.mListGardenClient.size(); i++) {

            if (mPresenter.mListGardenClient.get(i).getmListGarden() == null) {

            } else {
                LogUtils.logE(TAG + "gardensuccess :" + i + ": ", new Gson().toJson(mPresenter.mListGardenClient.get(i)));
            }

        }
        adapterClientVegetable.notifyDataSetChanged();

        adapterClientVegetable.setOnClickAction(new OnClickDetail() {
            @Override
            public void OnClickDetailVeget(String json) {
                LogUtils.logE("clickId", json + "");
//                mPresenter.clickDetail(position);
                mMainListener.allVegetableGardenDetail(json);
            }
        });
    }
}
