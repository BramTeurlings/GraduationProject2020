package nl.brickx.brickxwms2020.Presentation.Login;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MediatorLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.Gson.ApiUserRightsEnum;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.Gson.UserInfo.UserInfo;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.GetUserAuthenticationByApiKey;

import static android.content.ContentValues.TAG;

public class LoginPresenter implements LoginContract.Presenter {

    private MediatorLiveData<AuthenticationResult> auth = new MediatorLiveData<>();

    private GetUserAuthenticationByApiKey authenticationManager;

    private Context context;
    private LoginContract.View view;

    @Inject
    LoginPresenter(GetUserAuthenticationByApiKey authenticationRepository, @DataContext Context context, LoginContract.View view){
        this.authenticationManager = authenticationRepository;
        this.context = context;
        this.view = view;
    }

    @Override
    public void authenticateUser(User user) {
        User returnedUser = new User(user.getId(), user.getUsername(), context.getString(R.string.api_key_default), user.getPermissions());
        final List<Authentication> result = new ArrayList<>();

       authenticationManager.authenticateUser(returnedUser)
            .doOnNext(c -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
               s -> result.add(s),
               Throwable::printStackTrace,
               () -> getUserData(result.get(0), returnedUser));
    }

    @Override
    public void getUserData(Authentication authentication, User user) {
        if(authentication.getApiKey()){
            List<UserInfo> userInfo = new ArrayList<>();

            authenticationManager.getUserInfo(user.getApiKey())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            s -> userInfo.add(s),
                            Throwable::printStackTrace,
                            () -> processUserData(userInfo.get(0), user.getApiKey())
                    );
        }else{
            //Todo: Show message on screen.
            Log.i(TAG, "Authentication failed, invalid credidentials.");
        }
    }

    private void processUserData(UserInfo userInfo, String apiKey){
        List<Permission> enums = new ArrayList<>();
        for(int i = 0; i < userInfo.getUserInfoResult().getApiUserRights().size(); i++){
            if(userInfo.getUserInfoResult().getApiUserRights().get(i).getApiRight() == ApiUserRightsEnum.Product_Get){
                if(!enums.contains(Permission.PRODUCT_INFO)){
                    enums.add(Permission.PRODUCT_INFO);
                }
                if(!enums.contains(Permission.LOCATION_INFO)){
                    enums.add(Permission.LOCATION_INFO);
                }
            }
            if(userInfo.getUserInfoResult().getApiUserRights().get(i).getApiRight() == ApiUserRightsEnum.Pickslip_Get){
                if(!enums.contains(Permission.ORDER_PICK)){
                    enums.add(Permission.ORDER_PICK);
                }
            }
            if(userInfo.getUserInfoResult().getApiUserRights().get(i).getApiRight() == ApiUserRightsEnum.Stockcount){
                if(!enums.contains(Permission.STOCK_COUNT)){
                    enums.add(Permission.STOCK_COUNT);
                }
            }
        }
        User newUser = new User(userInfo.getUserInfoResult().getId(), userInfo.getUserInfoResult().getUserName(), apiKey, enums);
        authenticationManager.saveUserData(newUser);
        onAuthenticationInfoFetched(new AuthenticationResult(newUser, true));
    }


    private void onAuthenticationInfoFetched(AuthenticationResult result){
        view.onAuthenticationDataReceived(result);
    }
}
