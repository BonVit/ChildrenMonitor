package com.vb.childrenmonitor.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vb.childrenmonitor.R;
import com.vb.childrenmonitor.presentation.presenter.LoginPresenter;
import com.vb.childrenmonitor.presentation.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {
    private static final String TAG = "LoginActivity";

    /*MVP DI*/
    @InjectPresenter
    LoginPresenter mLoginPresenter;

    /*View Binding*/
    @BindView(R.id.email)
    TextView mEmailInput;
    @BindView(R.id.password)
    EditText mPasswordInput;
    @BindView(R.id.email_sign_in_button)
    Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @Override
    public void showMessage(String message) {
        Log.d(TAG, "showMessage: " + message);
    }
}

