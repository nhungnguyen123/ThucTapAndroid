package com.uiapp.thuctap.mvp.main.garden.menucontainer.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.interactor.api.response.garden.AllGardenResponse;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.adapter.GardenAdapter;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.injection.DaggerMenuContainerComponent;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.injection.MenuContainerComponent;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.injection.MenuContainerModule;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.presenter.MenuContainerPresenter;
import com.uiapp.thuctap.utils.LogUtils;

import javax.inject.Inject;

import butterknife.Bind;


public class MenuContainerFragment extends BaseFragment implements IMenuContainerView {
    @Bind(R.id.ln_all_garden)
    LinearLayout mLnAllGarden;

    @Bind(R.id.ln_create_garden)
    LinearLayout mLnCreateGarden;

    @Bind(R.id.ln_all_vegetable)
    LinearLayout mLnAllVegetable;

    @Bind(R.id.ln_about_us)
    LinearLayout mLnAboutus;

    @Bind(R.id.ln_create_vegetable)
    LinearLayout mLnCreateVegetable;


    @Bind(R.id.tv_name_people)
    TextView mTvNameUser;

    @Bind(R.id.tv_log_out)
    TextView mTvLogout;

    @Bind(R.id.ln_create_garden_user)
    LinearLayout mTvAddGarden;

    public String role;

    MainListener mParentListener;

    GardenAdapter mGardenAdapter;

    @Inject
    MenuContainerPresenter mPresenter;

    public static MenuContainerFragment newInstance() {

        Bundle args = new Bundle();

        MenuContainerFragment fragment = new MenuContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MenuContainerComponent component = DaggerMenuContainerComponent.builder()
                .menuContainerModule(new MenuContainerModule())
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .build();
        component.inject(this);
        mPresenter.attachView(this);
        mProgressDialog = KProgressHUD.create(getContext())
                .setLabel(getResources().getString(R.string.please_wait))
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu_container;
    }

    @Override
    protected void initData() {

        role = mPresenter.getRoleUser();

        /**
         * role == null. get all Vegetable
         */

        if (role == null) {
            mLnCreateGarden.setVisibility(View.GONE);
        } else {
            if (role.equalsIgnoreCase("admin")) {
                mLnCreateGarden.setVisibility(View.GONE);

            } else {

                mLnCreateVegetable.setVisibility(View.GONE);
                mLnAllGarden.setVisibility(View.GONE);
            }
            mTvNameUser.setText(mPresenter.getNameDisplay());
            mPresenter.getAllGardens();

            mGardenAdapter = new GardenAdapter(getContext(), mPresenter.getListGarden());
        }

//        mSpnKiosks.setAdapter(mGardenAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mParentListener = (MainListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " + MainListener.class.getSimpleName());

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mParentListener = (MainListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement " + MainListener.class.getSimpleName());

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mParentListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected void initListener() {

        mLnCreateVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goCreateVegetable();
            }
        });

        mLnAllGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getAllUser();

            }
        });
        mLnCreateGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.createGarden();
            }
        });
        mTvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.doLogout();
            }
        });

        mLnAllVegetable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPresenter.allVegetable();
            }
        });
//        mTvEditGarden.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.goEditGarden();
//            }
//        });


        mTvAddGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goCreateVegetable();
            }
        });
    }

    @Override
    public void getAllGardensSuccess() {
        LogUtils.logE("Success", "ok");
        mGardenAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAllGardenError(String message) {
        LogUtils.logE("error", "ok");
    }

    @Override
    public void doLogOut() {
        mParentListener.doLogout();
    }

    @Override
    public void goToEditGarden() {
        mParentListener.goEditGarden();
    }

    @Override
    public void goCreateGarden() {
        mParentListener.createGarden();
    }

    @Override
    public void goAllVegetable() {
        mParentListener.allVegetable();
    }

    @Override
    public void goAllUser() {
        mParentListener.getAllUser();
    }

    @Override
    public void goToCreateVegetable(String vegetableDetail) {
        mParentListener.goCreateVegetable(vegetableDetail);
    }
}
