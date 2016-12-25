package com.uiapp.thuctap.base.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.uiapp.thuctap.utils.LogUtils;

import butterknife.ButterKnife;

/**
 * Created by hongnhung on 7/6/16.
 */
abstract public class BaseFragment extends Fragment {
    public static final String TAG = LogUtils.makeLogTag(BaseFragment.class);

    //TODO thucjw hieenj cho dialog KProgressHUD vào
    public interface BackPressListener {
        boolean onBackPress();
    }

    protected KProgressHUD mProgressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    //TODO thực hiện cho thêm ButterKnife.bind(this, view);
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initData();
        initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initListener();
}
