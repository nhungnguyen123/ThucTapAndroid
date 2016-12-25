package com.uiapp.thuctap.mvp.main.garden.menucontainer.presenter;

/**
 * Created by hongnhung on 7/27/16.
 */
public interface IMenuContainerPresenter {


    void getAllGardens();

    String getUserToken();

    String getNameDisplay();

    String getRoleUser();

    void getAllUser();

    void loadKiosk(int position);

    void doLogout();

    void goEditGarden();

    void getGarden();

    void createGarden();

    void allVegetable();

    void goCreateVegetable();

}
