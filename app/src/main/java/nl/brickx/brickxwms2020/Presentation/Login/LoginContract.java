package nl.brickx.brickxwms2020.Presentation.Login;

import androidx.lifecycle.LiveData;

import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.User;

public interface LoginContract {

    interface Presenter {

        //Todo: Presenter code.
        void authenticateUser(User user);

        LiveData<AuthenticationResult> observeAuth();
    }

    interface Navigator {

        //Todo: Navigate to screens.
    }
}
