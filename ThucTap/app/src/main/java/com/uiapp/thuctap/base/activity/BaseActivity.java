package com.uiapp.thuctap.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.StackView;

import com.uiapp.thuctap.R;
import com.uiapp.thuctap.base.fragment.BaseFragment;

import java.util.Stack;

import butterknife.ButterKnife;

/**
 * Created by hongnhung on 7/6/16.
 */
abstract public class BaseActivity extends AppCompatActivity {
    protected final Stack<String> mFragmentTagStack = new Stack<>();

    abstract protected Fragment onCreateFragment(Bundle bundle);

    abstract protected int getContainerId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
        //TODO thực hiện cho thêm ButterKnife vào

    }

    protected abstract int getLayoutId();
    protected abstract void setSupportToolBar(Toolbar toolBar);


    protected void initData() {
        showFragment(onCreateFragment(null));
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = null;

        String tag = mFragmentTagStack.peek();
        if (mFragmentTagStack.size() > 1) {
            f = fm.findFragmentByTag(tag);
        }
        if (f != null && f instanceof BaseFragment.BackPressListener) {
            if (((BaseFragment.BackPressListener) f).onBackPress()) {
                return;
            }
        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction ft = fm.beginTransaction();
            if (fm.findFragmentByTag(tag) != null) {
                mFragmentTagStack.pop();
                ft.remove(f);
            }
            ft.commit();
            return;
        }
        super.onBackPressed();
    }

    public void showFragment(Fragment f) {
        showFragment(getContainerId(), f);
    }


    public void showFragment(int containerId, Fragment f) {
        if (f == null) {
            return;
        }
        String tag = f.getClass() + ":" + getNextPositionOfFragment(f.getClass().getName());
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.screen_enter, R.anim.screen_exit, R.anim.screen_pop_enter, R.anim.screen_pop_exit);
        if (mFragmentTagStack.size() > 0) {
            final BaseFragment fr = (BaseFragment) fm.findFragmentByTag(mFragmentTagStack.peek());
            ft.hide(fr);
        }
        ft.add(containerId, f, tag);
        ft.show(f);
        ft.addToBackStack(tag);
        ft.commit();
        mFragmentTagStack.add(tag);
    }

    private int getNextPositionOfFragment(String tag) {
        int pos = 0;
        if (mFragmentTagStack.size() > 0) {
            for (String stackTag : mFragmentTagStack) {
                if (stackTag.contains(tag)) {
                    pos++;
                }
            }
        }
        return pos;
    }

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
