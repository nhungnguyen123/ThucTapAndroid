package com.uiapp.thuctap.mvp.main.garden.garden.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.interactor.api.request.GardenBodyRequest;
import com.uiapp.thuctap.interactor.api.response.garden.CreateGardenResponse;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.view.AllVegetableFragement;
import com.uiapp.thuctap.mvp.main.garden.garden.adapter.CreateGardenAdapter;
import com.uiapp.thuctap.mvp.main.garden.garden.injection.DaggerGardenComponent;
import com.uiapp.thuctap.mvp.main.garden.garden.injection.GardenComponent;
import com.uiapp.thuctap.mvp.main.garden.garden.injection.GardenModule;
import com.uiapp.thuctap.mvp.main.garden.garden.presenter.GardenPresenter;
import com.uiapp.thuctap.utils.CommonUtils;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;


public class GardenFragment extends BaseFragment implements IGardenView {

    /**
     * Need to use id_vegetable
     */

    @Bind(R.id.edi_name_garden)
    EditText mEdtNameGarden;
    @Bind(R.id.tv_address)
    EditText mEdtAddress;
    //
    @Bind(R.id.edt_ares_garden)
    EditText mEdtArea;
    //
    @Bind(R.id.btn_create_garden)
    TextView mBtnCreate;

    @Bind(R.id.edt_description_garden)
    EditText mEdtDescription;

    @Bind(R.id.edt_vegetable_garden)
    Button mEdtChooseGarden;

    MainListener mMainListener;

    public ArrayList<String> listVegetableId = new ArrayList<>();
    public ArrayList<String> listVegetableName = new ArrayList<>();


    @Inject
    GardenPresenter mPresenter;

    MainListener mPrarentListener;

    public static GardenFragment newInstance() {

        Bundle args = new Bundle();

        GardenFragment fragment = new GardenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GardenComponent gardenComponent = DaggerGardenComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .gardenModule(new GardenModule(this))
                .build();
        gardenComponent.inject(this);
        mPresenter.attachView(this);
        mProgressDialog = KProgressHUD.create(getContext())
                .setLabel(getResources().getString(R.string.please_wait))
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_garden;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {

        /**
         * thực hiện gọi chọn các loại rau để đưa vào vườn.
         */
        mEdtChooseGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<String> list = new ArrayList<String>();
                Bundle bundle = new Bundle();
                AllVegetableFragement fragement = new AllVegetableFragement().newInstance(AppConstants.VEGETABLE_CHOOSE, list);
                fragement.setArguments(bundle);
                fragement.setOnAddVegetable(new AllVegetableFragement.OnAddVegetable() {
                    @Override
                    public void addVegetable(ArrayList<Vegetable> listVegetable) {
                        for (int i = 0; i < listVegetable.size(); i++) {
                            listVegetableId.add(listVegetable.get(i).getId());
                            listVegetableName.add(listVegetable.get(i).getName());
                            listVegetableName.add(",");
                        }
                        String name = "";
                        for (int i = 0; i < listVegetableName.size(); i++) {
                            name = name + listVegetableName.get(i);
                        }
                        mEdtChooseGarden.setText(name);


                        LogUtils.logE("listVegetableID", listVegetable.get(0).toString() + "");
                    }
                });


                mPrarentListener.goAddOtherMenu(fragement);
                LogUtils.logE("choose vegetable", TAG);


            }
        });


        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean nameOk = checkName();
                boolean addressOk = checkAddress();
                boolean areaOk = checkArea();
                boolean descriptionOk = checkDescription();


                if (nameOk && addressOk && areaOk && descriptionOk) {
                    mProgressDialog.show();
                    GardenBodyRequest gardenBodyRequest = new GardenBodyRequest();
                    String nameGarden = mEdtNameGarden.getText().toString().trim();
                    String address = mEdtAddress.getText().toString().trim();
                    String area = mEdtArea.getText().toString().trim();
                    gardenBodyRequest.setName(nameGarden);
                    gardenBodyRequest.setAddress(address);
                    gardenBodyRequest.setArea(area);
                    gardenBodyRequest.setUser("user");
                    gardenBodyRequest.setVegetableList(listVegetableId);

                    String request = new Gson().toJson(gardenBodyRequest);
                    LogUtils.logE(TAG, "request" + request.toString());
                    mPresenter.createGarden(gardenBodyRequest);
                } else
                    CommonUtils.showSnackBar(getView(), "Check info again, please!", Snackbar.LENGTH_LONG);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPrarentListener = (MainListener) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mPrarentListener = (MainListener) activity;
    }

    @Override
    public void createGardenSuccess(CreateGardenResponse signUpResponse) {
        mProgressDialog.dismiss();
        String log = new Gson().toJson(signUpResponse);
        LogUtils.logE("respose", log);
        mEdtDescription.setText("");
        mEdtAddress.setText("");
        mEdtArea.setText("");
        mEdtNameGarden.setText("");
        mEdtChooseGarden.setText("Choose vegetable");
        CommonUtils.showSnackBar(getView(), "Create Garden success !", 1000);
        mPrarentListener.onBackScreen();
    }

    @Override
    public void createGardenFailer(String message) {
        LogUtils.logE("message", message);
        mProgressDialog.dismiss();
    }

    @Override
    public void getAllVegetableSuccess(String message) {
        LogUtils.logE(TAG, message);
//        mCreateAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAllVegetableError(String message) {
        LogUtils.logE(TAG, message);
    }

    private boolean checkName() {
        String name = mEdtNameGarden.getText().toString().trim();
        if (name.isEmpty() || name.equals("")) {
            mEdtNameGarden.setError(" Name isn't empty");
            return false;
        }
        return true;
    }

    private boolean checkAddress() {
        String address = mEdtAddress.getText().toString().trim();
        if (address.isEmpty() || address.equals("")) {
            mEdtAddress.setError("Address isn't empty");
            return false;
        }
        return true;
    }


    private boolean checkArea() {
        String area = mEdtArea.getText().toString().trim();
        if (area.isEmpty() || area.equals("")) {
            mEdtArea.setError("Area isn't empty");
            return false;
        }
        return true;
    }

    private boolean checkDescription() {
        String description = mEdtDescription.getText().toString().trim();
        if (description.isEmpty() || description.equals("")) {
            mEdtDescription.setError("Description isn't empty");
            return false;
        }
        return true;
    }
}
