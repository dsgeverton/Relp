package br.edu.iff.pooa.relp.util;

import android.app.Application;
import android.content.res.Configuration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SessionApplication extends Application {

    private static SessionApplication singleton;
    private String userLogged;
    private int userId;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());

        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.name("relp.realm");
        builder.schemaVersion(0);
        builder.deleteRealmIfMigrationNeeded();
        RealmConfiguration realmConfiguration = builder.build();
        Realm.setDefaultConfiguration(realmConfiguration);

        singleton = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public SessionApplication getInstance(){
        return singleton;
    }

    public void setUserLogged(String userLogged){
        this.userLogged = userLogged;
    }

    public String getUserLogged(){
        return this.userLogged;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

