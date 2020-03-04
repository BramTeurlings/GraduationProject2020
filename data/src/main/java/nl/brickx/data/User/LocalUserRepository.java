package nl.brickx.data.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Flowable;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.UserInfo;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.Data.AuthenticationRepository;
import retrofit2.Retrofit;

public class LocalUserRepository implements AuthenticationRepository.GetUserDataFromApi, AuthenticationRepository.GetUserDataSharedPref, AuthenticationRepository.SaveUserDataSharedPref {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalUserRepositoryService localUserRepositoryService;

    final static String storageName = "USER_STORAGE";
    final static String TAG = "SharedPreferences: ";

    Context context;

    private SharedPreferences sharedPreferences;

    @Inject
    LocalUserRepository(@DataContext Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(storageName, Context.MODE_PRIVATE);
    }


    @Override
    public Flowable<UserInfo> getUserData(String apiKey) {
        Flowable<UserInfo> authenticationSingle = localUserRepositoryService.getUserInfo(apiKey, apiKey);

        return authenticationSingle;
    }

    @Override
    public Boolean saveUserDataSharedPref(User user) {
        try{
            String jsonUser = gson.toJson(user);
            sharedPreferences.edit().putString(storageName, jsonUser).apply();
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public User getuserDataSharedPref() {
        String jsonUser = sharedPreferences.getString(storageName, "");
        return gson.fromJson(jsonUser, User.class);
    }
}
