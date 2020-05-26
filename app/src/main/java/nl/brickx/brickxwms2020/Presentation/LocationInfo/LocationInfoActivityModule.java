package nl.brickx.brickxwms2020.Presentation.LocationInfo;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

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
        LocationInfoContract.Presenter bindLocationInfoPresenter(LocationInfoPresenter locationInfoPresenter);

        @Binds
        LocationInfoContract.View bindLocationInfoView(LocationInfoActivity locationInfoActivity);
    }
}
