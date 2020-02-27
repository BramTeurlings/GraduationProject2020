package nl.brickx.brickxwms2020.Presentation.Login;

import nl.brickx.brickxwms2020.Presentation.Models.AuthenticationResult;
import nl.brickx.domain.Models.User;

public interface LoginContract {

    interface Presenter {

        //Todo: Presenter code.
        AuthenticationResult authenticateUser(User user);
    }

    interface Navigator {

        //Todo: Navigate to screens.
    }
}
