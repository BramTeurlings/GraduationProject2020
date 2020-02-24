package nl.brickx.brickxwms2020.Presentation.MainMenu;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainMenuActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    Bindings.class
            }
    )
    MainMenuActivity mainMenuActivity();

    @Module
    interface Bindings {

        @Binds
        MainMenuContract.Presenter bindPresenter(MainMenuPresenter mainMenuPresenter);
    }
}
