package nl.brickx.brickxwms2020.Presentation.Login;

import javax.inject.Inject;

public class LoginPresenter implements LoginContract.Presenter{

    @Inject LoginPresenter(){

    }

    @Override
    public boolean testMethod() {
        return false;
    }
}
