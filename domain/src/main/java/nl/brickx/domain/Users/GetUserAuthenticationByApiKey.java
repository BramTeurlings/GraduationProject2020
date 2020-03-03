package nl.brickx.domain.Users;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.Gson.ApiUserRightsEnum;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.Gson.GetUserInfoResult;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.Data.AuthenticationRepository;

public class GetUserAuthenticationByApiKey {

    private AuthenticationRepository.Api authenticationApiRepository;
    private AuthenticationRepository.GetUserData authenticationUserRepository;

    @Inject
    GetUserAuthenticationByApiKey(AuthenticationRepository.Api authenticationRepository, AuthenticationRepository.GetUserData authenticationUserRepository){
        this.authenticationApiRepository = authenticationRepository;
        this.authenticationUserRepository = authenticationUserRepository;
    }

    public Flowable<AuthenticationResult> invoke(User user) {
        //Todo: Add all the permissions.
        if(authenticationApiRepository.authenticateUser( user.getApiKey()).subscribeOn(Schedulers.io()).blockingFirst().getApiKey()){
            GetUserInfoResult userInfo = authenticationUserRepository.getUserData(user.getApiKey()).subscribeOn(Schedulers.io()).blockingFirst();
            List<Permission> enums = new ArrayList<>();
            for(int i = 0; i < userInfo.getApiUserRights().size(); i++){
                if(userInfo.getApiUserRights().get(i).getApiRight() == ApiUserRightsEnum.Product_Get){
                    if(!enums.contains(Permission.PRODUCT_INFO)){
                        enums.add(Permission.PRODUCT_INFO);
                    }
                    //Todo: Check if this has a different permission in Brickx.
                    if(!enums.contains(Permission.LOCATION_INFO)){
                        enums.add(Permission.LOCATION_INFO);
                    }
                }
            }
            User newUser = new User(userInfo.getId(), userInfo.getUserName(), user.getApiKey(), enums);
            return Flowable.just(new AuthenticationResult(newUser, true));
        }else{
            return Flowable.just(new AuthenticationResult(new User(), false));
        }
    }

}
