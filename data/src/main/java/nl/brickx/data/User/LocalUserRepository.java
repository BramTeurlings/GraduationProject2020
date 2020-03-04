package nl.brickx.data.User;

import javax.inject.Inject;

import io.reactivex.Flowable;
import nl.brickx.data.Authentication.LocalAuthenticationRepositoryService;
import nl.brickx.domain.Models.Gson.GetUserInfoResult;
import nl.brickx.domain.Models.Gson.UserInfo;
import nl.brickx.domain.Users.Data.AuthenticationRepository;
import retrofit2.Retrofit;

public class LocalUserRepository implements AuthenticationRepository.GetUserData {

    @Inject
    Retrofit retrofit;

    @Inject
    LocalUserRepositoryService localUserRepositoryService;

    @Inject
    public LocalUserRepository() {
    }


    @Override
    public Flowable<UserInfo> getUserData(String apiKey) {
        Flowable<UserInfo> authenticationSingle = localUserRepositoryService.getUserInfo(apiKey, apiKey);

        return authenticationSingle;
    }
}
