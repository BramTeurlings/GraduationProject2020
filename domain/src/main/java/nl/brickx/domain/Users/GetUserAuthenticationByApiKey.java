package nl.brickx.domain.Users;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.Gson.ApiUserRightsEnum;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.Gson.UserInfo.UserInfo;
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

    public Observable<Authentication> authenticateUser(User user){
        return authenticationApiRepository.authenticateUser( user.getApiKey())
                .subscribeOn(Schedulers.io());
    }

    public Observable<UserInfo> getUserInfo(String apiKey){
        return authenticationUserRepository.getUserData(apiKey)
                .subscribeOn(Schedulers.io());
    }

    public void saveUserData(User user){
        authenticationUserSaveRepository.saveUserDataSharedPref(user);
    }

}
