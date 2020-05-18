package nl.brickx.brickxwms2020.Presentation.Login;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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

    private GetUserAuthenticationByApiKey authenticationManager;
    private Context context;
    private LoginContract.View view;
    private List<Disposable> disposables = new ArrayList<>();
    private Boolean isLoading = false;

    @Inject
    LoginPresenter(GetUserAuthenticationByApiKey authenticationRepository, @DataContext Context context, LoginContract.View view){
        this.authenticationManager = authenticationRepository;
        this.context = context;
        this.view = view;
    }

    @Override
    public void authenticateUser(User user) {
        if(!isLoading){
            onApiRequestStarted();
            changeLoadingState();
            User returnedUser = new User(user.getId(), user.getUsername(), context.getString(R.string.api_key_default), user.getPermissions());
            final List<Authentication> result = new ArrayList<>();

            disposables.add(authenticationManager.authenticateUser(returnedUser)
                    .doOnNext(c -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            s -> result.add(s),
                            t -> onLoginFailed(t),
                            () -> getUserData(result.get(0), returnedUser)));
        }
    }

    @Override
    public void getUserData(Authentication authentication, User user) {
        if(authentication.getApiKey()){
            List<UserInfo> userInfo = new ArrayList<>();

            disposables.add(authenticationManager.getUserInfo(user.getApiKey())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            s -> userInfo.add(s),
                            (Throwable::printStackTrace),
                            () -> processUserData(userInfo.get(0), user.getApiKey())));
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
            if(userInfo.getUserInfoResult().getApiUserRights().get(i).getApiRight() == ApiUserRightsEnum.StockTransfer_Post){
                if(!enums.contains(Permission.STOCK_TRANSFER)){
                    enums.add(Permission.STOCK_TRANSFER);
                }
                if(!enums.contains(Permission.STOCK_MUTATION)){
                    enums.add(Permission.STOCK_MUTATION);
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

    private void onLoginFailed(Throwable throwable){
        throwable.printStackTrace();
        onApiRequestCompleted();
        changeLoadingState();
        view.setErrorMessage(context.getString(R.string.login_error_message));
    }

    private void onAuthenticationInfoFetched(AuthenticationResult result){
        //Release loadingstate
        onApiRequestCompleted();
        changeLoadingState();
        view.onAuthenticationDataReceived(result);
    }

    private void onApiRequestStarted(){
        isLoading = true;
    }

    private void onApiRequestCompleted(){
        isLoading = false;
    }

    private void changeLoadingState(){
        view.changeLoadingState(isLoading);
    }

    @Override
    public void dispose() {
        for(int i = 0; i < disposables.size(); i++){
            this.disposables.get(i).dispose();
        }
    }
}
