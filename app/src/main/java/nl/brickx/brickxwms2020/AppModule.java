package nl.brickx.brickxwms2020;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import nl.brickx.data.Authentication.LocalAuthenticationMenuCategoriesRepository;
import nl.brickx.data.Authentication.LocalAuthenticationRepository;
import nl.brickx.data.Authentication.LocalAuthenticationRepositoryService;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.data.User.LocalUserRepository;
import nl.brickx.data.User.LocalUserRepositoryService;
import nl.brickx.domain.Users.Data.AuthenticationRepository;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module(includes = {AppModule.Bindings.class})
class AppModule {

    Application application;
    Retrofit retrofit;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    Retrofit provideRetrofit(){
        if(retrofit == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .cache(null)
                    .retryOnConnectionFailure(false)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api-ae.brickx.software/api/apiservice.svc/")
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(provideGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Provides
    LocalAuthenticationRepositoryService localAuthenticationRepositoryService(){
        return retrofit.create(LocalAuthenticationRepositoryService.class);
    }

    @Provides
    LocalUserRepositoryService localUserRepositoryService(){
        return retrofit.create(LocalUserRepositoryService.class);
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
        AuthenticationRepository.Categories bindAuthenticationMenuCategoriesRepository(LocalAuthenticationMenuCategoriesRepository localAuthenticationMenuCategoriesRepository);

        @Binds
        AuthenticationRepository.Api bindAuthenticationRepository(LocalAuthenticationRepository localAuthenticationRepository);

        @Binds
        AuthenticationRepository.GetUserData bindAuthenticationGetUserDataRepository(LocalUserRepository localUserRepository);

    }
}
