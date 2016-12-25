package com.uiapp.thuctap.mvp.client;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.uiapp.thuctap.MainListener;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.activity.BaseActivity;
import com.uiapp.thuctap.mvp.client.allgarden.view.AllGardenFragment;
import com.uiapp.thuctap.mvp.client.vegetableclient.view.ClientVegetableFragment;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.view.AllVegetableFragement;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.view.VegetableDetail;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.view.MenuContainerFragment;

import butterknife.Bind;

public class AllVegetableClient extends BaseActivity implements MainListener {


    @Bind(R.id.drw_container_main)
    DrawerLayout mDlFrmContainerMain;

    @Bind(R.id.fl_container_main)
    FrameLayout mFlContainerMain;

    @Bind(R.id.fl_menu_container)
    FrameLayout mFlMenuContainer;

    ClientVegetableFragment clientVegetableFragment;

    @Override
    protected void initData() {
        super.initData();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_menu_container, MenuContainerFragment.newInstance()).commit();
    }

    @Override
    protected Fragment onCreateFragment(Bundle bundle) {
        clientVegetableFragment = new ClientVegetableFragment().newInstance();
        return clientVegetableFragment;
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
    public void openMenuContainer() {

    }

    @Override
    public void goEditAccount() {

    }

    @Override
    public void createGarden() {

    }

    @Override
    public void allVegetable() {

    }

    @Override
    public void getAllUser() {

    }

    @Override
    public void createTree() {

    }

    @Override
    public void doLogout() {

    }

    @Override
    public void goEditGarden() {

    }

    @Override
    public void goVegetableDetail(String vegetable) {
        hideContainerMenu();
        Bundle bundle = new Bundle();
        bundle.putString("VEGETABLE", vegetable);
        VegetableDetail vegetableDetail = new VegetableDetail().newInstance();
        vegetableDetail.setArguments(bundle);
        showFragment(R.id.fl_container_main, vegetableDetail);
    }

    @Override
    public void goCreateVegetable(String vegetable) {

    }

    @Override
    public void goGardenDetail(String garden) {

    }

    @Override
    public void goAddOtherMenu(Fragment fragment) {

    }

    /**
     * show detail .
     *
     * @param allGarden
     */
    @Override
    public void allVegetableGardenDetail(String allGarden) {
        AllGardenFragment allGardenFragment = AllGardenFragment.newInstance(allGarden);
        showFragment(R.id.fl_container_main, allGardenFragment);
    }
}
