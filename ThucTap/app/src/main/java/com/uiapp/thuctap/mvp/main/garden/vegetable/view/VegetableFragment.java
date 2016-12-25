package com.uiapp.thuctap.mvp.main.garden.vegetable.view;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.interactor.api.request.VegetableBodyRequest;
import com.uiapp.thuctap.mvp.main.garden.vegetable.injection.DaggerVegetableComponent;
import com.uiapp.thuctap.mvp.main.garden.vegetable.injection.VegetableComponent;
import com.uiapp.thuctap.mvp.main.garden.vegetable.injection.VegetableModule;
import com.uiapp.thuctap.mvp.main.garden.vegetable.presenter.VegetablePresenter;
import com.uiapp.thuctap.utils.CommonUtils;
import com.uiapp.thuctap.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;


public class VegetableFragment extends BaseFragment implements IVegetableView {

    public final static int REQUEST_CODE_DIALOG = 200;
    @Bind(R.id.edi_name_vegetable)
    EditText mEdtName;


//    @Bind(R.id.edt_pd)
//    EditText mEdtPh;

    @Bind(R.id.pick_day_from)
    TextView fromDate;
    //
    @Bind(R.id.pick_day_to)
    TextView toDate;


    @Bind(R.id.edt_pesticides)
    EditText mEdtPesticides;

    @Bind(R.id.ln_create_vegetable)
    LinearLayout mBTnCancelVegetable;

    @Bind(R.id.edt_description)
    EditText mEdtDescription;

    @Bind(R.id.tv_click)
    TextView mTvClick;

    @Bind(R.id.volume_seekbar)
    SeekBar mSeekBar;

    @Bind(R.id.tv_value_ph)
    TextView mTvValuePh;

    private int mSeekColor = 0;


    public String name, fromDay, toDay, pes, ph, des;
    VegetableBodyRequest vegetableBodyRequest;

    MainListener mMainListener;

    @Inject
    VegetablePresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VegetableComponent component = DaggerVegetableComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .vegetableModule(new VegetableModule())
                .build();
        component.inject(this);
        mPresenter.attachView(this);
        mProgressDialog = KProgressHUD.create(getContext())
                .setLabel(getResources().getString(R.string.please_wait))
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
    }

    public static VegetableFragment newInstance() {

        Bundle args = new Bundle();

        VegetableFragment fragment = new VegetableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vegetable;
    }

    @Override
    protected void initData() {

        mSeekBar.setProgress(14);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSeekColor = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mTvValuePh.setText(mSeekColor + "");
            }
        });
        mPresenter.getAllVegetable();
        String token = mPresenter.getUserToken();
        LogUtils.logE(TAG, "token" + token);

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
    protected void initListener() {

        Date date = new Date(System.currentTimeMillis());
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        mBTnCancelVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean nameOk = checkName();
                boolean phOk = checkPh();
                boolean pesOk = phOk;
                boolean checkPesticidesOk = checkPesticides();
                boolean checkDescriptionOk = checkDescription();
                if (nameOk && phOk && pesOk && checkPesticidesOk && checkDescriptionOk) {


                    name = mEdtName.getText().toString();
                    ph = mTvValuePh.getText().toString();

                    int phInt = Integer.parseInt(ph);

                    if (phInt > 14 || phInt < 0) {
                        Toast.makeText(getActivity(), "Ph must be <=14 and >=0", Toast.LENGTH_SHORT).show();
                    } else {
                        pes = mEdtPesticides.getText().toString();
                        des = mEdtDescription.getText().toString();
                        mPresenter.checkVegetable(name);
                        vegetableBodyRequest = new VegetableBodyRequest(name, toDay, fromDay, pes, ph, des);
                        String send = new Gson().toJson(vegetableBodyRequest);
                        mPresenter.createVegetable(vegetableBodyRequest);
                        LogUtils.logE(TAG, "information " + send);
                    }

                } else
                    CommonUtils.showSnackBar(getView(), "Check info again, please!", Snackbar.LENGTH_LONG);


            }


        });

    }

    @Override
    public void createVegetableSuccess() {
        LogUtils.logE(TAG, "success vegetable");
        mEdtDescription.setText("");
        mEdtPesticides.setText("");
        mEdtName.setError("");
//        mEdtPh.setText("");
        mEdtName.setText("");
        mMainListener.onBackScreen();
        CommonUtils.showSnackBar(getView(), "Create Vegetable Success !", 600);
    }

    @Override
    public void createVegetableError(String error) {
        LogUtils.logE(TAG, error + "createrror");
    }

    @Override
    public void getAllVegetableSuccess() {
        LogUtils.logE(TAG, "success" + "");
    }

    @Override
    public void getAllVegetableError(String message) {
        LogUtils.logE(TAG, "error" + message);
    }

    @Override
    public void checkVegetableSuccess() {
        mPresenter.createVegetable(vegetableBodyRequest);
        LogUtils.logE(TAG, "can insert Name  vegetable");

    }

    @Override
    public void checkVegetableError(String message) {
        LogUtils.logE(TAG, message);

    }

    private boolean checkName() {
        String name = mEdtName.getText().toString().trim();
        if (name.isEmpty() || name.equals("")) {
            mEdtName.setError(" Name isn't empty");
            return false;
        }
        return true;
    }

    private boolean checkPh() {
//        String ph = mEdtPh.getText().toString().trim();
//        if (ph.isEmpty() || ph.equals("")) {
//            mEdtPh.setError("Ph isn't empty");
//            return false;
//        }
        return true;
    }

    private boolean checkPesticides() {
        String pes = mEdtPesticides.getText().toString().trim();
        if (pes.isEmpty() || pes.equals("")) {
            mEdtPesticides.setError("Pesticides isn't empty");
            return false;
        }
        return true;
    }

    private boolean checkDescription() {
        String des = mEdtDescription.getText().toString().trim();
        if (des.isEmpty() || des.equals("")) {
            mEdtDescription.setError("Description isn't empty");
            return false;
        }
        return true;
    }

    @OnClick(R.id.tv_click)
    public void Click() {
        DialogFragment newFragment = DatePickerFragment.newInstance();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        newFragment.setTargetFragment(this, REQUEST_CODE_DIALOG);
        newFragment.show(fm, "datePicker");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_DIALOG) {

            int mode = data.getIntExtra("Mode", 0);
            fromDay = data.getStringExtra("FromDay");
            toDay = data.getStringExtra("ToDay");

            fromDate.setText(fromDay.toString());
            toDate.setText(toDay.toString());

//            LogUtils.logE("TAG", "FromDate: " + fromDay + " ToDay: " + toDay);
//
//            mCurrentDate = (Date) data.getSerializableExtra("CurrentDay");
//            mPresenter.getReport(fromDay, toDay,
//                    AppConstants.REPORT_GET_ALL,
//                    AppConstants.REPORT_GET_ALL,
//                    AppConstants.REPORT_GET_ALL);
//            setUpSpinnerPosition();
//            if (mode == 0)
//                mTvDate.setText(fromDay);
//            else
//                mTvDate.setText(fromDay + " -> " + toDay);
        }
    }
}
