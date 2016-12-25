package com.uiapp.thuctap.mvp.welcome.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.uiapp.thuctap.MainActivity;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.activity.BaseActivity;
import com.uiapp.thuctap.mvp.main.garden.client.view.ClientFragment;
import com.uiapp.thuctap.mvp.main.garden.garden.view.GardenFragment;
import com.uiapp.thuctap.mvp.main.garden.vegetable.view.VegetableFragment;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.view.LoginFragment;
import com.uiapp.thuctap.mvp.welcome.login.signup.view.SignUpFragment;

public class WelcomeActivity extends BaseActivity implements WelcomeListener {

    @Override
    protected Fragment onCreateFragment(Bundle bundle) {

        return SignUpFragment.newInstance();
    }

    @Override
    protected int getContainerId() {
        return R.id.frm_container_welcome;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcom;
    }

    @Override
    protected void setSupportToolBar(Toolbar toolBar) {

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void goMainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void goAddOtherMenu(Fragment fragment) {
        showFragment(fragment);
    }
}
