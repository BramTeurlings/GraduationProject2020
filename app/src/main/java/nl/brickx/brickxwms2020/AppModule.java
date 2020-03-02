package nl.brickx.brickxwms2020;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import nl.brickx.data.Authentication.LocalAuthenticationRepository;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Users.Data.AuthenticationRepository;

@Module(includes = {AppModule.Bindings.class})
class AppModule {

    Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
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

        @Binds
        AuthenticationRepository bindAuthenticationRepository(LocalAuthenticationRepository localAuthenticationRepository);

    }
}
