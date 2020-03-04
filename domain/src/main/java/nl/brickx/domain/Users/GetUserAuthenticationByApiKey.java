package nl.brickx.domain.Users;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.Gson.ApiUserRightsEnum;
import nl.brickx.domain.Models.Gson.UserInfo;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.Data.AuthenticationRepository;

public class GetUserAuthenticationByApiKey {

    private AuthenticationRepository.Api authenticationApiRepository;
    private AuthenticationRepository.GetUserDataFromApi authenticationUserRepository;
    private AuthenticationRepository.SaveUserDataSharedPref authenticationUserSaveRepository;

    @Inject
    GetUserAuthenticationByApiKey(AuthenticationRepository.Api authenticationRepository, AuthenticationRepository.GetUserDataFromApi authenticationUserRepository, AuthenticationRepository.SaveUserDataSharedPref authenticationUserSaveRepository){
        this.authenticationApiRepository = authenticationRepository;
        this.authenticationUserRepository = authenticationUserRepository;
        this.authenticationUserSaveRepository = authenticationUserSaveRepository;
    }

    public Flowable<AuthenticationResult> invoke(User user) {
        //Todo: Add all the permissions.
        if(authenticationApiRepository.authenticateUser( user.getApiKey()).subscribeOn(Schedulers.io()).blockingFirst().getApiKey()){
            UserInfo userInfo = authenticationUserRepository.getUserData(user.getApiKey()).subscribeOn(Schedulers.io()).blockingFirst();
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
            User newUser = new User(userInfo.getUserInfoResult().getId(), userInfo.getUserInfoResult().getUserName(), user.getApiKey(), enums);
            authenticationUserSaveRepository.saveUserDataSharedPref(newUser);
            return Flowable.just(new AuthenticationResult(newUser, true));
        }else{
            return Flowable.just(new AuthenticationResult(new User(), false));
        }
    }

}
