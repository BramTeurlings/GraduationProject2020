package nl.brickx.brickxwms2020.Presentation.Login;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.Presentation.Models.AuthenticationResult;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.Data.AuthenticationRepository;

public class LoginPresenter implements LoginContract.Presenter {

    AuthenticationRepository authenticationRepository;

    @Inject
    LoginPresenter(AuthenticationRepository authenticationRepository){
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public AuthenticationResult authenticateUser(User user) {
        User returnedUser = new User(user.getId(), user.getUsername(), user.getApiKey(), user.getPermissions());

        //Todo: Api call
        return new AuthenticationResult(returnedUser, authenticationRepository.authenticateUser(user.getApiKey()));
    }
}
