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
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;

public class LoginActivity extends DaggerAppCompatActivity {

    @Inject
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        subscribeObservers();
    }

    private void subscribeObservers() {
        presenter.observeAuth().observe(this, new Observer<AuthenticationResult>() {
            @Override
            public void onChanged(AuthenticationResult authentication) {
                if(authentication.getAuthenticated()){
                    AuthenticationCompleted();
                }
            }
        });
    }

    public void onLogin(View view){
        List<Permission> permissions = new ArrayList<>();
        permissions.add(Permission.BATCH_PICK);
        permissions.add(Permission.GENERATE_LABEL);
        permissions.add(Permission.INCOMING_GOODS);
        permissions.add(Permission.LOCATION_INFO);
        permissions.add(Permission.ORDER_PICK);
        permissions.add(Permission.PRODUCT_INFO);
        permissions.add(Permission.SSCC);
        permissions.add(Permission.REPLENISHMENT);
        permissions.add(Permission.STOCK_COUNT);
        permissions.add(Permission.STOCK_MUTATION);
        permissions.add(Permission.STOCK_TRANSFER);

        //Todo: Save logged in user.

        presenter.authenticateUser(new User(0, "TestUser", "sudg276f17f6rdfctr2c6i7kuycu7x", permissions));
        //Todo: Feedback if authentication failed.
    }

    /**
     * Brings user to next screen once authentication is completed.
     */
    public void AuthenticationCompleted(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
