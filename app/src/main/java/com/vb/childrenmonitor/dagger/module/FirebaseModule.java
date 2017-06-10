package com.vb.childrenmonitor.dagger.module;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bonar on 6/10/2017.
 */

@Module
public class FirebaseModule {
    @Provides
    @NonNull
    @Singleton
    public FirebaseAuth provideFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }
}
