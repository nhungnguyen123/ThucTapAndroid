package com.uiapp.thuctap.mvp.main.garden.gardenclient.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.uiapp.thuctap.MainActivity;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.mvp.main.garden.editgarden.adapter.EditGardenAdapter;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.adapter.AdapterGardenClient;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.injections.DaggerGardenClientComponent;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.injections.GardenClientComponent;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.injections.GardenClientModule;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.presenter.GardenClientPresenter;
import com.uiapp.thuctap.mvp.welcome.login.WelcomeListener;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by hongnhung on 7/31/16.
 */
public class GardenClientFragment extends BaseFragment implements IGardenClientView {
    /**
     * Thực hiện show tất cả các vườn có loại rau đó .
     * thực hiện chọn thông tin chính xác của loại rau đó.
     */
    @Bind(R.id.rv_garden_client)
    RecyclerView mRvGarden;

    @Bind(R.id.edt_enter_name_garden)
    EditText mEdtNameGarden;

    EditGardenAdapter mAdapterGardenClient;

    public String idBoss;

    @Inject
    GardenClientPresenter mPresenter;

    MainListener mParentListener;


    public static GardenClientFragment newInstance() {


        return new GardenClientFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GardenClientComponent component = DaggerGardenClientComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .gardenClientModule(new GardenClientModule())
                .build();

        Bundle bundle = getArguments();
        if (bundle != null) {
            idBoss = bundle.getString("idBoss");
            LogUtils.logE("idBoss", idBoss);
        }
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
            mParentListener = (MainActivity) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement " + WelcomeListener.class.getSimpleName());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mParentListener = (MainActivity) activity;
        } catch (Exception e) {
            throw new ClassCastException(activity.toString() + " must implement " + WelcomeListener.class.getSimpleName());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_show_garden_client;
    }

    @Override
    protected void initData() {
        mPresenter.getAllGardenClient(idBoss);
        mAdapterGardenClient = new EditGardenAdapter(mPresenter.listGarden, getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mRvGarden.setHasFixedSize(true);
        mRvGarden.setLayoutManager(layoutManager);
        mRvGarden.setAdapter(mAdapterGardenClient);

        mEdtNameGarden.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

//                String text = mEdtNameGarden.getText().toString().toLowerCase(Locale.getDefault());
//                mAdapterGardenClient.filter(text);
//                mAdapterGardenClient.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getAllGardenSuccess() {
        mAdapterGardenClient = new EditGardenAdapter(mPresenter.listGarden, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRvGarden.setHasFixedSize(true);
        mRvGarden.setLayoutManager(layoutManager);
        mRvGarden.setAdapter(mAdapterGardenClient);
        mAdapterGardenClient.notifyDataSetChanged();
    }

    @Override
    public void getAllGardenError(String message) {

    }
}
