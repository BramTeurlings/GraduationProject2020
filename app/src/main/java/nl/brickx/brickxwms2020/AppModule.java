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
import nl.brickx.data.LocationInfo.LocalLocationInfoRepository;
import nl.brickx.data.LocationInfo.LocalLocationRepositoryService;
import nl.brickx.data.OrderPick.PickSlip.LocalOrderPickPickSlipRepository;
import nl.brickx.data.OrderPick.PickSlip.LocalOrderPickPickSlipService;
import nl.brickx.data.OrderPick.ProductImage.LocalOrderPickProductImageRepository;
import nl.brickx.data.OrderPick.ProductImage.LocalOrderPickProductImageService;
import nl.brickx.data.OrderPickLanding.LocalOrderPickLandingRepository;
import nl.brickx.data.OrderPickLanding.LocalOrderPickLandingService;
import nl.brickx.data.ProductInfo.LocalProductInfoRepository;
import nl.brickx.data.ProductInfo.LocalProductRepositoryService;
import nl.brickx.data.User.LocalUserRepository;
import nl.brickx.data.User.LocalUserRepositoryService;
import nl.brickx.domain.Location.Info.Data.LocationRepository;
import nl.brickx.domain.OrderPick.Landing.Data.OrderPickLandingRepository;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;
import nl.brickx.domain.Product.Info.Data.ProductRepository;
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
                .disableHtmlEscaping()
                .setDateFormat("dd-MM-yyyy HH:mm:ss")
                .serializeNulls()
                .create();
    }

    @Provides
    Retrofit provideRetrofit(){
        if(retrofit == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
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
    LocalProductRepositoryService localProductRepositoryService(){
        return retrofit.create(LocalProductRepositoryService.class);
    }

    @Provides
    LocalLocationRepositoryService locationRepositoryService(){
        return retrofit.create(LocalLocationRepositoryService.class);
    }

    @Provides
    LocalOrderPickLandingService orderPickLandingRepositoryService(){
        return retrofit.create(LocalOrderPickLandingService.class);
    }

    @Provides
    LocalOrderPickPickSlipService orderPickPickSlipService(){
        return retrofit.create(LocalOrderPickPickSlipService.class);
    }

    @Provides
    LocalOrderPickProductImageService orderPickProductImageService(){
        return retrofit.create(LocalOrderPickProductImageService.class);
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
        AuthenticationRepository.GetUserDataFromApi bindAuthenticationGetUserDataRepository(LocalUserRepository localUserRepository);

        @Binds
        AuthenticationRepository.GetUserDataSharedPref bindAuthenticationGetUserDataSharedPrefRepository(LocalUserRepository localUserRepository);

        @Binds
        AuthenticationRepository.SaveUserDataSharedPref bindAuthenticationSaveUserDataSharedPrefRepository(LocalUserRepository localUserRepository);

        @Binds
        ProductRepository.ProductInfo bindProductInfoRepository(LocalProductInfoRepository localProductInfoRepository);

        @Binds
        LocationRepository.LocationInfo bindLocationInfoRepository(LocalLocationInfoRepository localLocationInfoRepository);

        @Binds
        OrderPickLandingRepository.OpenOrders bindOrderPickLandingRepository(LocalOrderPickLandingRepository localOrderPickLandingRepository);

        @Binds
        OrderPickRepository.PickSlips bindOrderPickPickSlipRepository(LocalOrderPickPickSlipRepository localOrderPickPickSlipRepository);

        @Binds
        OrderPickRepository.ProductImage bindOrderPickProductImageRepository(LocalOrderPickProductImageRepository localOrderPickProductImageRepository);
    }
}
