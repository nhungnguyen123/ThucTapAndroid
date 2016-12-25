package com.uiapp.thuctap.mvp.main.garden.editgarden.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.uiapp.thuctap.MainActivity;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.User;
import com.uiapp.thuctap.mvp.main.garden.editgarden.adapter.EditGardenAdapter;
import com.uiapp.thuctap.interactor.api.request.UpdateGardenRequest;
import com.uiapp.thuctap.mvp.main.garden.editgarden.injection.DaggerEditGardenComponent;
import com.uiapp.thuctap.mvp.main.garden.editgarden.injection.EditGardenComponent;
import com.uiapp.thuctap.mvp.main.garden.editgarden.injection.EditGardenModule;
import com.uiapp.thuctap.mvp.main.garden.editgarden.interfacecallback.OnClickAction;
import com.uiapp.thuctap.mvp.main.garden.editgarden.presenter.EditGardenPresenter;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;


public class EditGardenFragment extends BaseFragment implements IEditGardenView {
    //
    @Bind(R.id.rv_edit_garden)
    RecyclerView mRvEditGarden;


    EditGardenAdapter editGardenAdapter;

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    String role = "";


    @Inject
    EditGardenPresenter mPresenter;

    MainListener mMainListener;


    public static EditGardenFragment newInstance() {

        Bundle args = new Bundle();
        EditGardenFragment fragment = new EditGardenFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditGardenComponent component = DaggerEditGardenComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .editGardenModule(new EditGardenModule())
                .build();
        component.inject(this);
        mPresenter.attachView(this);
        mProgressDialog = KProgressHUD.create(getContext())
                .setLabel(getResources().getString(R.string.please_wait))
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolBar.setBackgroundColor(getContext().getResources().getColor(R.color.green_garden));
        ((MainActivity) getActivity()).setSupportActionBar(mToolBar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_add_garden);
        ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_garden_user;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.search, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                LogUtils.logE(TAG, "delete");
                return true;

            case R.id.edit:
                LogUtils.logE(TAG, "edit");
                return true;
        }
        return false;
    }

    @Override
    protected void initData() {

        role = mPresenter.getRoles();
        if (role.equalsIgnoreCase("admin")) {
            mPresenter.getAllUser();
            LogUtils.logE(TAG, "roleEdit" + role);
            mPresenter.getAllGarden();
            mProgressDialog.dismiss();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            mRvEditGarden.setLayoutManager(mLayoutManager);
            mRvEditGarden.setHasFixedSize(true);
            editGardenAdapter = new EditGardenAdapter(mPresenter.mLisetAllGardenUser, getActivity());

            mRvEditGarden.setAdapter(editGardenAdapter);
        } else {
            LogUtils.logE(TAG, "roleEdit" + role);
            mPresenter.getAllGarden();
            mProgressDialog.dismiss();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            mRvEditGarden.setLayoutManager(mLayoutManager);
            mRvEditGarden.setHasFixedSize(true);
            LogUtils.logE(TAG, "list" + mPresenter.listGarden.size() + "");
            editGardenAdapter = new EditGardenAdapter(mPresenter.listGarden, getActivity());

            mRvEditGarden.setAdapter(editGardenAdapter);
        }

    }

    @Override
    protected void initListener() {

    }


    @Override
    public void getAllGardenSuccess() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRvEditGarden.setLayoutManager(mLayoutManager);
        mRvEditGarden.setHasFixedSize(true);
        editGardenAdapter = new EditGardenAdapter(mPresenter.listGarden, getActivity());
        LogUtils.logE("sizeGarden", mPresenter.listGarden.size() + "");
        mRvEditGarden.setAdapter(editGardenAdapter);
        editGardenAdapter.notifyDataSetChanged();
        editGardenAdapter.setOnClickAction(new OnClickAction() {
            @Override
            public void OnClickDelete(String idGarden) {
                mProgressDialog.show();
                mPresenter.deleteGarden(idGarden);
                LogUtils.logE(TAG, idGarden + "");

            }

            @Override
            public void OnClickChange(String idGarden, UpdateGardenRequest gardenBodyRequest) {
                mPresenter.updateGarden(idGarden, gardenBodyRequest);
                String jsonGaren = new Gson().toJson(gardenBodyRequest);
                LogUtils.logE(TAG, "json" + jsonGaren);
                LogUtils.logE(TAG, "Id" + idGarden);
                mProgressDialog.show();
            }

            @Override
            public void OnClickDetail(String garden) {
                mMainListener.goGardenDetail(garden);
            }
        });

    }

    @Override
    public void getAllGardenError(String error) {
        mProgressDialog.dismiss();
    }

    @Override
    public void updateGardenSuccess() {
//        editGardenAdapter.notifyDataSetChanged();
//        mProgressDialog.dismiss();


    }

    @Override
    public void updateGardenError(String message) {
        mProgressDialog.dismiss();
        LogUtils.logE("MessageError", message);

    }

    @Override
    public void deleteGardenSuccess() {
        editGardenAdapter.notifyDataSetChanged();
        mProgressDialog.dismiss();
        LogUtils.logE("deleteSuccess", "ok");


    }

    @Override
    public void deleteGardenError(String message) {
        LogUtils.logE("ok", message);
        mProgressDialog.dismiss();

    }

    @Override
    public void getAllUserSuccess(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            LogUtils.logE(TAG, "Id+ " + users.get(i).getId());
            mPresenter.getGarden(users.get(i).getId());
        }
        LogUtils.logE(TAG, "Success GetAll User");
    }

    @Override
    public void getAllUserError(String message) {

    }

    @Override
    public void getGardenSuccess() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRvEditGarden.setLayoutManager(mLayoutManager);
        mRvEditGarden.setHasFixedSize(true);
        editGardenAdapter = new EditGardenAdapter(mPresenter.mLisetAllGardenUser, getActivity());
        LogUtils.logE("sizeGarden", mPresenter.listGarden.size() + "");
        mRvEditGarden.setAdapter(editGardenAdapter);
        editGardenAdapter.notifyDataSetChanged();
        editGardenAdapter.setOnClickAction(new OnClickAction() {
            @Override
            public void OnClickDelete(String idGarden) {
                mProgressDialog.show();
                mPresenter.deleteGarden(idGarden);
                LogUtils.logE(TAG, idGarden + "");

            }

            @Override
            public void OnClickChange(String idGarden, UpdateGardenRequest gardenBodyRequest) {
                mPresenter.updateGarden(idGarden, gardenBodyRequest);
                String jsonGaren = new Gson().toJson(gardenBodyRequest);
                LogUtils.logE(TAG, "json" + jsonGaren);
                LogUtils.logE(TAG, "Id" + idGarden);
                mProgressDialog.show();
            }

            @Override
            public void OnClickDetail(String garden) {
                mMainListener.goGardenDetail(garden);
            }
        });
    }

    @Override
    public void getGardenError(String error) {

    }
}
