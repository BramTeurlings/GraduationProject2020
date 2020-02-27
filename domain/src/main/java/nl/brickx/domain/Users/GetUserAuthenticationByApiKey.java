package nl.brickx.domain.Users;

import javax.inject.Inject;
import io.reactivex.Observable;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.Data.AuthenticationRepository;

public class GetUserAuthenticationByApiKey {

    private AuthenticationRepository authenticationRepository;

    @Inject
    GetUserAuthenticationByApiKey(AuthenticationRepository authenticationRepository){
        this.authenticationRepository = authenticationRepository;
    }

    public Observable<Boolean> invoke(User user) {
        //Todo: Call on data layer.
        if(authenticationRepository.authenticateUser(user.getApiKey())){
            return Observable.just(true);
        }else{
            return Observable.just(false);
        }
    }

}
