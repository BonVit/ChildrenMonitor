package com.vb.childrenmonitor.presentation.view.login;

import com.arellomobile.mvp.MvpView;

/**
 * Created by bonar on 6/10/2017.
 */

public interface LoginView extends MvpView {
    void showMessage(String message);
    void showBadData();
    void startMainActivity();
}
