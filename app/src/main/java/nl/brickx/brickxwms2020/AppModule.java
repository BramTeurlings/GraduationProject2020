package nl.brickx.brickxwms2020;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;
import nl.brickx.data.dagger.DataContext;

@Module(includes = {AppModule.Bindings.class})
public class AppModule {

    @Provides
    Gson provideGson(){
        return new Gson();
    }

    @Provides
    @DataContext
    Context provideDataContext(Application application){
        return application.getApplicationContext();
    }

    @Module
    interface Bindings{

        @Binds
        Application bindApplication(BaseApplication baseApplication);

    }
}
