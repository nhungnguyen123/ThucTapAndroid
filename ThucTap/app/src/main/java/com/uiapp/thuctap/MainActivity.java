package com.uiapp.thuctap;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.uiapp.thuctap.base.activity.BaseActivity;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.view.AllVegetableFragement;
import com.uiapp.thuctap.mvp.main.garden.client.view.ClientFragment;
import com.uiapp.thuctap.mvp.main.garden.editgarden.view.EditGardenFragment;
import com.uiapp.thuctap.mvp.main.garden.garden.view.GardenFragment;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.view.GardenDetailFragment;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.view.VegetableDetail;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.view.MenuContainerFragment;

import com.uiapp.thuctap.mvp.main.garden.vegetable.view.VegetableFragment;
import com.uiapp.thuctap.mvp.welcome.login.WelcomeActivity;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainListener {

    @Bind(R.id.drw_container_main)
    DrawerLayout mDlFrmContainerMain;

    @Bind(R.id.fl_container_main)
    FrameLayout mFlContainerMain;

    @Bind(R.id.fl_menu_container)
    FrameLayout mFlMenuContainer;

    EditGardenFragment gardenFragment;

    @Override
    protected Fragment onCreateFragment(Bundle bundle) {
        gardenFragment = EditGardenFragment.newInstance();
        return gardenFragment;
    }

    @Override
    protected void initData() {
        super.initData();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_menu_container, MenuContainerFragment.newInstance()).commit();
    }

    private long exitTimer = Long.MIN_VALUE;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        mDlFrmContainerMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (mFragmentTagStack.size() == 1) {
                if ((exitTimer + AppConstants.EXIT_INTERVAL) < System.currentTimeMillis()) {
                    Toast.makeText(this, getString(R.string.confirm_exit), Toast.LENGTH_SHORT).show();
                    exitTimer = System.currentTimeMillis();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    protected int getContainerId() {
        return R.id.fl_container_main;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setSupportToolBar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
    }


    @Override
    public void openMenuContainer() {
        mDlFrmContainerMain.openDrawer(Gravity.LEFT);
        Log.e("TAG", "OPEN");
    }

    @Override
    public void goEditAccount() {

    }

    @Override
    public void createGarden() {
        hideContainerMenu();
        GardenFragment fragment = new GardenFragment().newInstance();
        showFragment(R.id.fl_container_main, fragment);
    }

    @Override
    public void allVegetable() {
        ArrayList<String> list = new ArrayList<>();
        hideContainerMenu();
        AllVegetableFragement fragement = new AllVegetableFragement().newInstance(AppConstants.VEGETABLE_CHOOSE, list);
        showFragment(R.id.fl_container_main, fragement);
    }

    @Override
    public void getAllUser() {
        hideContainerMenu();
        ClientFragment fragment = new ClientFragment().newInstance();
        showFragment(R.id.fl_container_main, fragment);
    }

    @Override
    public void createTree() {

    }

    @Override
    public void doLogout() {
        finish();
        startActivity(new Intent(this, WelcomeActivity.class));
    }

    /**
     * Edit garden
     */
    @Override
    public void goEditGarden() {
        hideContainerMenu();
        Log.e("TAG", "CLOSE");
        EditGardenFragment fragment = EditGardenFragment.newInstance();
        showFragment(R.id.fl_container_main, fragment);

    }

    @Override
    public void goVegetableDetail(String vegetable) {
//        mDlFrmContainerMain.closeDrawer(Gravity.LEFT);
        hideContainerMenu();
        Bundle bundle = new Bundle();
        bundle.putString("VEGETABLE", vegetable);
        VegetableDetail vegetableDetail = new VegetableDetail().newInstance();


        vegetableDetail.setArguments(bundle);
        showFragment(R.id.fl_container_main, vegetableDetail);
    }


    /**
     * Go to Fragment Create Vegetable
     */
    @Override
    public void goCreateVegetable(String vegetableDetail) {

        mDlFrmContainerMain.closeDrawer(Gravity.LEFT);
        VegetableFragment vegetableFragment = VegetableFragment.newInstance();
        showFragment(R.id.fl_container_main, vegetableFragment);

    }

    @Override
    public void goGardenDetail(String garden) {
        hideContainerMenu();
        Bundle bundle = new Bundle();
        GardenDetailFragment gardenDetailFragment = GardenDetailFragment.newInstance(garden);
        showFragment(R.id.fl_container_main, gardenDetailFragment);
    }

    @Override
    public void goAddOtherMenu(Fragment fragment) {
        showFragment(fragment);
    }


    /**
     * show add detail
     * @param allGarden
     */
    @Override
    public void allVegetableGardenDetail(String allGarden) {
        hideContainerMenu();

    }

    @Override
    public void onBackScreen() {
        mDlFrmContainerMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        onBackPressed();

    }


    private void hideContainerMenu() {
        Log.e("TAG", "Close Container");
        mDlFrmContainerMain.closeDrawer(Gravity.LEFT);
        mDlFrmContainerMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText && !v.getClass().getName().startsWith("android.webkit.")) {
            if (ev.getRawX() < v.getLeft() || ev.getRawX() > v.getRight() || ev.getRawY() < v.getTop() || ev.getRawY() > v.getBottom()) {
                hideKeyboard();
            }
        }
        return super.dispatchTouchEvent(ev);
    }


}
