package com.vb.childrenmonitor.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vb.childrenmonitor.R;
import com.vb.childrenmonitor.presentation.presenter.main.MainPresenter;
import com.vb.childrenmonitor.presentation.view.main.MainView;
import com.vb.childrenmonitor.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bonar on 6/11/2017.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView {
    private static final String TAG = "MainActivity";

    /*MVP binding*/
    @InjectPresenter
    MainPresenter mMainPresenter;

    /*View binding*/
    @BindView(R.id.button2)
    Button buttonSignOut;

    Unbinder mUnbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainPresenter.signOut();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null)
            mUnbinder.unbind();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainPresenter.registerAuthListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMainPresenter.unregisterAuthListener();
    }

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return i;
    }

    @Override
    public void startLoginActivity() {
        startActivity(LoginActivity.newIntent(this));
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
