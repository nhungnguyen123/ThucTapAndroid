package com.uiapp.thuctap.mvp.main.garden.client.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.interactor.api.response.BaseResponse;
import com.uiapp.thuctap.mvp.main.garden.client.adapter.ClientAdapter;
import com.uiapp.thuctap.mvp.main.garden.client.injection.ClientComponent;
import com.uiapp.thuctap.mvp.main.garden.client.injection.ClientModule;
import com.uiapp.thuctap.mvp.main.garden.client.injection.DaggerClientComponent;
import com.uiapp.thuctap.mvp.main.garden.client.presenter.ClientPresenter;
import com.uiapp.thuctap.mvp.main.garden.garden.injection.GardenModule;
import com.uiapp.thuctap.mvp.main.garden.garden.presenter.GardenPresenter;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.view.GardenClientFragment;
import com.uiapp.thuctap.mvp.welcome.login.WelcomeListener;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by hongnhung on 7/29/16.
 */
public class ClientFragment extends BaseFragment implements IClientView, ClientAdapter.OnClickBoss {

    @Bind(R.id.recycler_view_user)
    ListView mLvBoss;

//    @Bind(R.id.edt_enter_name)
//    EditText mEdtSearchName;

    ClientAdapter mClientAdapter;

    MainListener mParentListener;

    @Inject
    ClientPresenter mPresenter;


    public static ClientFragment newInstance() {

        Bundle args = new Bundle();

        ClientFragment fragment = new ClientFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClientComponent component = DaggerClientComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .clientModule(new ClientModule(this))
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
            mParentListener = (MainListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement " + MainListener.class.getSimpleName());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mParentListener = (MainListener) activity;
        } catch (Exception e) {
            throw new ClassCastException(activity.toString() + " must implement " + MainListener.class.getSimpleName());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_client_show;
    }

    @Override
    protected void initData() {
        mPresenter.getAllUser();
        mClientAdapter = new ClientAdapter(getContext(), mPresenter.listUser);
        mClientAdapter.setOnclickBoss(new ClientAdapter.OnClickBoss() {
            @Override
            public void setBossChoice(String idBoss) {

            }
        });



        mLvBoss.setAdapter(mClientAdapter);
//        mLvBoss.setAdapter(mClientAdapter);
//        mEdtSearchName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String text = mEdtSearchName.getText().toString().toLowerCase(Locale.getDefault());
//                LogUtils.logE("text", text);
//                mClientAdapter.filter(text);
//                mClientAdapter.notifyDataSetChanged();
//            }
//        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getAllUserSuccess() {

        mClientAdapter.setOnclickBoss(this);
        mClientAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAllUserError(String message) {

    }

    @Override
    public void getAllVegetableSuccess() {
        LogUtils.logE(TAG, "Success");
    }

    @Override
    public void getAlVegetableError(String message) {
        LogUtils.logE(TAG, message);
    }

    /**
     * Change Fragment
     *
     * @param idBoss
     */
    @Override
    public void setBossChoice(String idBoss) {

        GardenClientFragment fragment = new GardenClientFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idBoss", idBoss);
        fragment.setArguments(bundle);
        mParentListener.goAddOtherMenu(fragment);
//        fragment.setAddOtherMenu(new GardenClientFragment.OnAddOtherMenu(){
//
//        });

    }
}
