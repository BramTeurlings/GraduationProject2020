package nl.brickx.domain.Users;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.Data.AuthenticationRepository;

public class GetUserAuthenticationByApiKey {

    private AuthenticationRepository authenticationRepository;

    @Inject
    GetUserAuthenticationByApiKey(AuthenticationRepository authenticationRepository){
        this.authenticationRepository = authenticationRepository;
    }

    public Flowable<AuthenticationResult> invoke(User user) {
        //Todo: Call on data layer.
        if(authenticationRepository.authenticateUser( "test").subscribeOn(Schedulers.io()).blockingFirst().getTitle().equals("delectus aut autem")){
            return Flowable.just(new AuthenticationResult(new User(), true));
        }else{
            return Flowable.just(new AuthenticationResult(new User(), false));
        }
    }

}
