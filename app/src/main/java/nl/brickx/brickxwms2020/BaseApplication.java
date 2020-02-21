package nl.brickx.brickxwms2020;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public AndroidInjector<DaggerApplication> applicationInjector(){
        return DaggerAppComponent.builder().build();
    }
}
