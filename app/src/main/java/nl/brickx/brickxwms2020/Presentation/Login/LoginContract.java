package nl.brickx.brickxwms2020.Presentation.Login;

import androidx.lifecycle.LiveData;

import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.User;

public interface LoginContract {

    interface Presenter {

        void authenticateUser(User user);
        void getUserData(Authentication authentication, User user);
        void dispose();
    }

    interface Navigator {

        //Todo: Navigate to screens.
    }

    interface View {

        void onAuthenticationDataReceived(AuthenticationResult result);
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }
}
