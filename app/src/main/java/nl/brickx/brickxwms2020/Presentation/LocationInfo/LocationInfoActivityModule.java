package nl.brickx.brickxwms2020.Presentation.LocationInfo;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuContract;
import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuPresenter;
import nl.brickx.brickxwms2020.Presentation.ProductInfo.ProductInfoActivity;

@Module
public interface LocationInfoActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    nl.brickx.brickxwms2020.Presentation.LocationInfo.LocationInfoActivityModule.Bindings.class
            }
    )
    LocationInfoActivity locationInfoActivity();

    @Module
    interface Bindings {

        @Binds
        Activity bindLocationInfoActivity(LocationInfoActivity locationInfoActivity);

        @Binds
        MainMenuContract.Presenter bindPresenter(MainMenuPresenter mainMenuPresenter);
    }
}
