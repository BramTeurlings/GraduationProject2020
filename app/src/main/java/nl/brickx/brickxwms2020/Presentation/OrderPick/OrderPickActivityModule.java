package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.app.Activity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface OrderPickActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivityModule.Bindings.class
            }
    )
    OrderPickActivity orderPickActivity();

    @Module
    interface Bindings {

        @Binds
        Activity bindOrderPickActivity(OrderPickActivity orderPickActivity);

    }
}
