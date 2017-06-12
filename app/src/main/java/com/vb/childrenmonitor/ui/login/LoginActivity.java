package com.vb.childrenmonitor.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.vb.childrenmonitor.R;
import com.vb.childrenmonitor.presentation.presenter.login.LoginPresenter;
import com.vb.childrenmonitor.presentation.view.login.LoginView;
import com.vb.childrenmonitor.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

public class LoginActivity extends MvpAppCompatActivity implements LoginView,
        View.OnClickListener {
    private static final String TAG = "LoginActivity";

    /*MVP DI*/
    @InjectPresenter
    LoginPresenter mLoginPresenter;

    /*View Binding*/
    @BindView(R.id.email)
    TextView mEmailInput;
    @BindView(R.id.password)
    EditText mPasswordInput;
    @BindView(R.id.email_sign_up_button)
    Button mSignUpButton;
    @BindView(R.id.email_sign_in_button)
    Button mSignInButton;

    private Unbinder mUnbinder;

    private DisposableSubscriber<Boolean> mDisposableObserver;
    private Flowable<CharSequence> mEmailChangeObservable;
    private Flowable<CharSequence> mPasswordChangeObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_login);
        mUnbinder = ButterKnife.bind(this);

        mSignInButton.setEnabled(false);
        mSignInButton.setOnClickListener(this);
        mSignUpButton.setEnabled(false);
        mSignUpButton.setOnClickListener(this);


        initFormObservables();
        combineLatestEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
        if (mDisposableObserver != null)
            mDisposableObserver.dispose();
    }

    private void initFormObservables() {
        mEmailChangeObservable =
                RxTextView.textChanges(mEmailInput).skip(1).toFlowable(BackpressureStrategy.LATEST);
        mPasswordChangeObservable =
                RxTextView.textChanges(mPasswordInput).skip(1).toFlowable(BackpressureStrategy.LATEST);
        mDisposableObserver = null;
    }


    private void combineLatestEvents() {
        mDisposableObserver =
                new DisposableSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean formValid) {
                        if (formValid) {
                            mSignInButton.setBackgroundColor(
                                    ContextCompat.getColor(LoginActivity.this, R.color.colorPrimary));
                            mSignInButton.setEnabled(true);
                            mSignUpButton.setBackgroundColor(
                                    ContextCompat.getColor(LoginActivity.this, R.color.colorPrimary));
                            mSignUpButton.setEnabled(true);
                        } else {
                            mSignInButton.setBackgroundColor(
                                    ContextCompat.getColor(LoginActivity.this, R.color.colorAccent));
                            mSignInButton.setEnabled(false);
                            mSignUpButton.setBackgroundColor(
                                    ContextCompat.getColor(LoginActivity.this, R.color.colorAccent));
                            mSignUpButton.setEnabled(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "mDisposableObserver: onComplete");
                    }
                };

        Flowable.combineLatest(
                mEmailChangeObservable,
                mPasswordChangeObservable,
                (newEmail, newPassword) -> mLoginPresenter.isEmailOkay(newEmail.toString())
                        && mLoginPresenter.isPasswordOkay(newPassword.toString()))
                .subscribe(mDisposableObserver);
    }


    @Override
    public void showMessage(String message) {
        Log.d(TAG, "showMessage: " + message);
    }

    @Override
    public void showBadData() {
        Log.d(TAG, "showBadData");
    }

    @Override
    public void startMainActivity() {
        startActivity(MainActivity.newIntent(this));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_sign_up_button:
                mLoginPresenter.signUp(mEmailInput.getText().toString(), mPasswordInput.getText().toString());
                break;
            case R.id.email_sign_in_button:
                mLoginPresenter.signIn(mEmailInput.getText().toString(), mPasswordInput.getText().toString());
                break;
        }
    }

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return i;
    }

    @Override
    public void onBackPressed() {
    }
}

