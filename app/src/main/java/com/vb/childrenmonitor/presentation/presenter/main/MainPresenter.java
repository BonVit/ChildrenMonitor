package com.vb.childrenmonitor.presentation.presenter.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.vb.childrenmonitor.App;
import com.vb.childrenmonitor.presentation.view.main.MainView;

import javax.inject.Inject;

/**
 * Created by bonar on 6/11/2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private static final String TAG = "MainPresenter";

    @Inject
    FirebaseAuth mAuth;

    AuthStateListener mAuthStateListener;

    public MainPresenter() {
        Log.d(TAG, "MainPresenter");

        App.getAppComponent().inject(this);

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser firebaseUser = mAuth.getCurrentUser();
            if (firebaseUser != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + firebaseUser.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");

                getViewState().startLoginActivity();
            }
        };
    }

    public void signOut() {
        mAuth.signOut();
    }

    public void registerAuthListener() {
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    public void unregisterAuthListener() {
        mAuth.removeAuthStateListener(mAuthStateListener);
    }


}
