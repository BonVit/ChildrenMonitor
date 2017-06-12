package com.vb.childrenmonitor.presentation.presenter.login;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.vb.childrenmonitor.App;
import com.vb.childrenmonitor.Utils.StringUtils;
import com.vb.childrenmonitor.presentation.view.login.LoginView;

import javax.inject.Inject;

/**
 * Created by bonar on 6/10/2017.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView>{
    private static final String TAG = "LoginPresenter";

    @Inject
    FirebaseAuth mAuth;

    public LoginPresenter() {
        Log.d(TAG, "Constructor");

        App.getAppComponent().inject(this);

    }

    public void signIn(String email, String password) {
        Log.d(TAG, "signUp");
        if(!(isEmailOkay(email) && isPasswordOkay(password)))
        {
            getViewState().showBadData();
            return;
        }

        signInWithEmail(email, password);

    }

    public void signUp(String email, String password) {
        Log.d(TAG, "signUp");
        if(!(isEmailOkay(email) && isPasswordOkay(password)))
        {
            getViewState().showBadData();
            return;
        }

        signUpWithEmail(email, password);

    }

    public boolean isEmailOkay(String email) {
        return StringUtils.isMatching(email, StringUtils.EMAIL_PATTERN);
    }

    public boolean isPasswordOkay(String password) {
        return StringUtils.isMatching(password, StringUtils.PASSWORD_PATTERN);
    }

    private void signInWithEmail(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        getViewState().showMessage("SignIn failed!");
                    } else {
                        getViewState().showMessage("SignIn success!");
                        getViewState().startMainActivity();
                    }
                });
    }

    private void signUpWithEmail(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        getViewState().showMessage("SignUp failed!");
                    } else {
                        getViewState().showMessage("SignUp success!");
                        getViewState().startMainActivity();
                    }
                });
    }
}
