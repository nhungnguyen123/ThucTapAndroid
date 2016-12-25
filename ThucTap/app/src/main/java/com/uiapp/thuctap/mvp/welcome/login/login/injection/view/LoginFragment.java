package com.uiapp.thuctap.mvp.welcome.login.login.injection.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.uiapp.thuctap.MainActivity;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.interactor.api.response.user.LoginResponse;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.injection.DaggerLoginComponent;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.injection.LoginComponent;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.injection.LoginModule;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.presenter.LoginPresenter;
import com.uiapp.thuctap.utils.CommonUtils;
import com.uiapp.thuctap.utils.LogUtils;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by hongnhung on 7/19/16.
 */
public class LoginFragment extends BaseFragment implements ILoginView {

    @Bind(R.id.btn_login)
    Button mBtnLogin;

    @Bind(R.id.tv_email)
    EditText mEdtEmail;

    @Bind(R.id.tv_pass)
    EditText mEdtPass;


    @Bind(R.id.btn_cancel)
    Button mLnCancel;
    @Inject
    LoginPresenter mLoginPresenter;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginComponent component = DaggerLoginComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .loginModule(new LoginModule(this))
                .build();
        component.inject(this);
        mLoginPresenter.attachView(this);
        mProgressDialog = KProgressHUD.create(getContext())
                .setLabel(getResources().getString(R.string.please_wait))
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
//                if (CommonUtils.isNetworkConnect()) {
                LogUtils.logE("email", mEdtEmail.getText().toString());
                LogUtils.logE("pass", mEdtPass.getText().toString());

                mLoginPresenter.login(mEdtEmail.getText().toString().trim(), mEdtPass.getText().toString().trim());

//                } else {
//                    CommonUtils.showToast(getContext(), "Please check your connection !");
//                }

            }
        });
        mLnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void loginError(String message) {
        CommonUtils.showSnackBar(getView(), message, 2000);
        mProgressDialog.dismiss();
    }

    @Override
    public void loginSuccess(LoginResponse results) {
//        mLoginPresenter.setKeyUserToken(results.results.getToken());
//        mLoginPresenter.setNameDisPlay(results.results.getDisplayName());
//        mLoginPresenter.setRoleUser(results.results.getRoles());
        mProgressDialog.dismiss();
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }


}
