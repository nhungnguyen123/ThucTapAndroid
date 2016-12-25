package com.uiapp.thuctap;

import android.support.v4.app.Fragment;

import com.uiapp.thuctap.base.IFragmentInteraction;
import com.uiapp.thuctap.model.Garden;

/**
 * Created by hongnhung on 7/27/16.
 */
public interface MainListener extends IFragmentInteraction {

    void openMenuContainer();

    void goEditAccount();

    void createGarden();

    void allVegetable();

    void getAllUser();

    void createTree();

    void doLogout();

    void goEditGarden();

    void goVegetableDetail(String vegetable);

    void goCreateVegetable(String vegetable);

    void goGardenDetail(String garden);

    void goAddOtherMenu(Fragment fragment);

    void allVegetableGardenDetail(String allGarden);
}
