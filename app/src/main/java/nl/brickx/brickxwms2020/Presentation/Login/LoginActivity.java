package nl.brickx.brickxwms2020.Presentation.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuActivity;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.User;

public class LoginActivity extends DaggerAppCompatActivity implements LoginContract.View {

    private TextInputLayout loginTextLayout;
    private MaterialButton loginButton;
    private ProgressBar loadingProgressBar;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        loginButton = findViewById(R.id.LoginButton);
        loadingProgressBar = findViewById(R.id.loadingIcon);
        loginTextLayout = findViewById(R.id.login_textInputLayout);
    }

    public void onLogin(View view){
        //Todo: Remove test user
        loginTextLayout.setError(null);
        presenter.authenticateUser(new User(0, "TestUser", "sudg276f17f6rdfctr2c6i7kuycu7x", null));
    }

    /**
     * Brings user to next screen once authentication is completed.
     */
    public void authenticationCompleted(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void setErrorMessage(String message){
        loginTextLayout.setError(message);
    }

    @Override
    public void onAuthenticationDataReceived(AuthenticationResult result) {
        if(result.getAuthenticated()){
            authenticationCompleted();
        }
    }

    @Override
    public void changeLoadingState(Boolean isLoading) {
        if(isLoading){
            loginButton.setEnabled(false);
            loadingProgressBar.setVisibility(View.VISIBLE);
        }else{
            loginButton.setEnabled(true);
            loadingProgressBar.setVisibility(View.GONE);
        }
    }
}
