package nl.brickx.brickxwms2020.Presentation.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuActivity;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.Gson.ApiUserRightsEnum;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;

public class LoginActivity extends DaggerAppCompatActivity implements LoginContract.View {

    @Inject
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }

    public void onLogin(View view){
        //Todo: Save logged in user.

        presenter.authenticateUser(new User(0, "TestUser", "sudg276f17f6rdfctr2c6i7kuycu7x", null));
        //Todo: Feedback if authentication failed.
    }

    /**
     * Brings user to next screen once authentication is completed.
     */
    public void AuthenticationCompleted(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAuthenticationDataReceived(AuthenticationResult result) {
        if(result.getAuthenticated()){
            AuthenticationCompleted();
        }
    }
}
