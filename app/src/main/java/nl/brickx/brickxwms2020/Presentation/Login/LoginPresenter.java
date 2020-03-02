package nl.brickx.brickxwms2020.Presentation.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.GetUserAuthenticationByApiKey;

public class LoginPresenter implements LoginContract.Presenter {

    private MediatorLiveData<AuthenticationResult> auth = new MediatorLiveData<>();

    GetUserAuthenticationByApiKey getUserAuthenticationByApiKey;

    @Inject
    LoginPresenter(GetUserAuthenticationByApiKey authenticationRepository){
        this.getUserAuthenticationByApiKey = authenticationRepository;
    }

    @Override
    public void authenticateUser(User user) {
        User returnedUser = new User(user.getId(), user.getUsername(), user.getApiKey(), user.getPermissions());

        final LiveData<AuthenticationResult> source = LiveDataReactiveStreams.fromPublisher(getUserAuthenticationByApiKey.invoke(user)
                                                                                .subscribeOn(Schedulers.io()));

        auth.addSource(source, new Observer<AuthenticationResult>() {
            @Override
            public void onChanged(AuthenticationResult authenticationResult) {
                auth.setValue(authenticationResult);
                auth.removeSource(source);
            }
        });
    }

    @Override
    public LiveData<AuthenticationResult> observeAuth(){
        return auth;
    }
}
