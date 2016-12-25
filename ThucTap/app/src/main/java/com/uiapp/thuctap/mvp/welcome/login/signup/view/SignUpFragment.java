package com.uiapp.thuctap.mvp.welcome.login.signup.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.uiapp.thuctap.MainActivity;
import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.interactor.api.response.user.LoginResponse;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;
import com.uiapp.thuctap.mvp.client.AllVegetableClient;
import com.uiapp.thuctap.mvp.welcome.login.signup.injection.DaggerSignUpComponent;
import com.uiapp.thuctap.mvp.welcome.login.signup.injection.SignUpComponent;
import com.uiapp.thuctap.mvp.welcome.login.signup.injection.SignUpModule;
import com.uiapp.thuctap.mvp.welcome.login.signup.presenter.SignUpPresenter;
import com.uiapp.thuctap.utils.CommonUtils;
import com.uiapp.thuctap.utils.LogUtils;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.Bind;


public class SignUpFragment extends BaseFragment implements ISignUpView {

    View clientLogin;
    View clientNewAccount;

    @Bind(R.id.btn_register)
    TextView mBtnSignUp;

    @Bind(R.id.btn_register_login)
    TextView mBtnLogin;


    @Bind(R.id.layout_clientLogin)
    View mViewLogin;

    @Bind(R.id.layout_client_create)
    View mViewRegister;

    @Bind(R.id.tv_for_guest)
    TextView mTvGuest;

    public EditText mEdtPassLogin, mEdtEmailLogin;
    public EditText mEdtPassRegister, mEdtEmailRegister, mEdtUseRegeister;
    public TextView mBtnCancelRegister, mBtnRegisterOk;
    public LinearLayout mBtnLoginOk;

    @Inject
    SignUpPresenter mPresenter;

    public static SignUpFragment newInstance() {
        Bundle args = new Bundle();
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SignUpComponent signUpComponent = DaggerSignUpComponent.builder()
                .applicationComponent(MainApplication.getApplicationComponent(getActivity()))
                .signUpModule(new SignUpModule(this))
                .build();
        signUpComponent.inject(this);
        mPresenter.attachView(this);
        mProgressDialog = KProgressHUD.create(getContext())
                .setLabel(getResources().getString(R.string.please_wait))
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign_up;
    }

    @Override
    protected void initData() {
        mViewRegister.setVisibility(View.INVISIBLE);
        mViewLogin.setVisibility(View.VISIBLE);
        mEdtPassLogin = (EditText) mViewLogin.findViewById(R.id.tv_pass_login);
        mEdtEmailLogin = (EditText) mViewLogin.findViewById(R.id.tv_email_login);
        mEdtEmailRegister = (EditText) mViewRegister.findViewById(R.id.tv_email_register);
        mEdtUseRegeister = (EditText) mViewRegister.findViewById(R.id.tv_user_register);
        mEdtPassRegister = (EditText) mViewRegister.findViewById(R.id.tv_pass_register);
        mBtnCancelRegister = (TextView) mViewRegister.findViewById(R.id.btn_cancel_register);
        mBtnRegisterOk = (TextView) mViewRegister.findViewById(R.id.btn_register_ok);
        mBtnLoginOk = (LinearLayout) mViewLogin.findViewById(R.id.ln_login_ok);


        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewRegister.setVisibility(View.VISIBLE);
                mViewLogin.setVisibility(View.INVISIBLE);
                mBtnSignUp.setBackgroundColor(getResources().getColor(R.color.color_orange));
                mBtnLogin.setBackgroundColor(getResources().getColor(R.color.green_garden));

            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewRegister.setVisibility(View.INVISIBLE);
                mViewLogin.setVisibility(View.VISIBLE);
                mBtnSignUp.setBackgroundColor(getResources().getColor(R.color.color_orange));
                mBtnLogin.setBackgroundColor(getResources().getColor(R.color.green_garden));
            }
        });

/**
 * Login
 */
        mBtnLoginOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
                mPresenter.login(mEdtEmailLogin.getText().toString(), mEdtPassLogin.getText().toString());

            }
        });


        /**
         * register Cancel
         */

        mBtnCancelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
                String userName = mEdtUseRegeister.getText().toString();
                String email = mEdtEmailRegister.getText().toString();
                String pass = mEdtPassRegister.getText().toString();
                mPresenter.signUp("", "", "", email, pass, "user", userName);

            }
        });

        /**
         * register Ok
         */

        mBtnRegisterOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mTvGuest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(new Intent(getActivity(), AllVegetableClient.class));
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void SignUpError(String message) {
        LogUtils.logE("error", message);
    }

    @Override
    public void SignUpSuccess(SignUpResponse results) {
        mPresenter.setKeyUserToken(results.results.getToken());

        mPresenter.getKeyUserToken();
        mPresenter.setNameDisPlay(results.results.getUsername());
        mPresenter.setRoleUser(results.results.getRoles());
        mProgressDialog.dismiss();
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
        LogUtils.logE("success", "");
    }

    @Override
    public void loginError(String message) {
        CommonUtils.showSnackBar(getView(), message, 2000);
        mProgressDialog.dismiss();
    }

    @Override
    public void loginSuccess(LoginResponse results) {
//        mPresenter.setKeyUserToken(results.results.getToken());
//        mPresenter.setNameDisPlay(results.results.getUsername());
//        mPresenter.setRoleUser(results.results.getRoles());
        mProgressDialog.dismiss();
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}
