package com.vb.childrenmonitor.dagger.component;

import com.vb.childrenmonitor.dagger.module.FirebaseModule;
import com.vb.childrenmonitor.presentation.presenter.login.LoginPresenter;
import com.vb.childrenmonitor.presentation.presenter.main.MainPresenter;
import com.vb.childrenmonitor.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bonar on 6/10/2017.
 */

@Component(modules = {FirebaseModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginPresenter loginPresenter);
    void inject(MainPresenter mainPresenter);
}
