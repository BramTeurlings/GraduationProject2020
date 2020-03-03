package nl.brickx.brickxwms2020.Presentation.Login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.net.SocketTimeoutException;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.GetUserAuthenticationByApiKey;

public class LoginPresenter implements LoginContract.Presenter {

    private MediatorLiveData<AuthenticationResult> auth = new MediatorLiveData<>();

    private GetUserAuthenticationByApiKey getUserAuthenticationByApiKey;

    private Context context;

    @Inject
    LoginPresenter(GetUserAuthenticationByApiKey authenticationRepository, @DataContext Context context){
        this.getUserAuthenticationByApiKey = authenticationRepository;
        this.context = context;
    }

    @Override
    public void authenticateUser(User user) {
        User returnedUser = new User(user.getId(), user.getUsername(), context.getString(R.string.api_key_default), user.getPermissions());

        try{
            final LiveData<AuthenticationResult> source = LiveDataReactiveStreams.fromPublisher(getUserAuthenticationByApiKey.invoke(returnedUser)
                    .subscribeOn(Schedulers.io()));

            auth.addSource(source, new Observer<AuthenticationResult>() {
                @Override
                public void onChanged(AuthenticationResult authenticationResult) {
                    auth.setValue(authenticationResult);
                    auth.removeSource(source);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public LiveData<AuthenticationResult> observeAuth(){
        return auth;
    }
}
