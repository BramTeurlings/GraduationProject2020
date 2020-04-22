package nl.brickx.data.Authentication;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.data.R;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.Gson.TestAuthentication;
import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.MenuItemIdentifier;
import nl.brickx.domain.Models.Permission;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LocalAuthenticationRepository implements nl.brickx.domain.Users.Data.AuthenticationRepository.Api {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalAuthenticationRepositoryService localAuthenticationRepositoryService;

    Context context;

    @Inject
    LocalAuthenticationRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Observable<Authentication> authenticateUser(String apiKey) {
        //Todo: Save APIkey in sharedpreferences and add it to the end of every request
        Observable<Authentication> authenticationSingle = localAuthenticationRepositoryService.validateApiKey(apiKey, apiKey);

        return authenticationSingle;
    }
}
