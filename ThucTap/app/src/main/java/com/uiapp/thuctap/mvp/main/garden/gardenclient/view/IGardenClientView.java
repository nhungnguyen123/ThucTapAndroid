package com.uiapp.thuctap.mvp.main.garden.gardenclient.view;

import com.uiapp.thuctap.base.presenter.IView;

/**
 * Created by hongnhung on 7/31/16.
 */
public interface IGardenClientView extends IView {

    void getAllGardenSuccess();

    void getAllGardenError(String message);
}
