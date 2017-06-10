package com.vb.childrenmonitor.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vb.childrenmonitor.presentation.view.LoginView;

/**
 * Created by bonar on 6/10/2017.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView>{
    private static final String TAG = "LoginPresenter";

    public LoginPresenter() {
        Log.d(TAG, "Constructor");
        getViewState().showMessage("LoginPresenter was created!");
    }
}
