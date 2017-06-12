package com.vb.childrenmonitor;

import android.app.Application;
import android.util.Log;

import com.vb.childrenmonitor.dagger.component.AppComponent;
import com.vb.childrenmonitor.dagger.component.DaggerAppComponent;
import com.vb.childrenmonitor.dagger.module.FirebaseModule;

/**
 * Created by bonar on 6/10/2017.
 */

public class App extends Application {
    private static final String TAG = "App";

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        mAppComponent = buildComponent();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    private AppComponent buildComponent() {
        Log.d(TAG, "buildComponent");

        return DaggerAppComponent.builder()
                .firebaseModule(new FirebaseModule())
                .build();
    }
}
