package nl.brickx.brickxwms2020;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;
import dagger.android.DaggerApplication;
import nl.brickx.data.dagger.DataContext;

@Module(includes = {AppModule.Bindings.class})
class AppModule {

    Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    Gson provideGson(){
        return new Gson();
    }

    @Provides
    @DataContext
    Context provideDataContext(BaseApplication application){
        return application;
    }

    @Module
    interface Bindings{

        @Binds
        Application bindApplication(BaseApplication baseApplication);

    }
}
