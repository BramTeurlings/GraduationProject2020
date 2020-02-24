package nl.brickx.brickxwms2020.Presentation.Login;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface LoginActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    Bindings.class
            }
    )
    LoginActivity loginActivity();

    @Module
    interface Bindings {

        @Binds
        LoginContract.Presenter bindPresenter(LoginPresenter loginPresenter);
    }
}
