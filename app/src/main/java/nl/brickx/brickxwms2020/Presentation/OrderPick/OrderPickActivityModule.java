package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.app.Activity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickFragmentModule;
import nl.brickx.brickxwms2020.Presentation.OrderPickLanding.OrderPickLandingActivity;

@Module
public interface OrderPickActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivityModule.Bindings.class,
                    nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickFragmentModule.class,
                    nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment.OrderPickOverviewFragmentModule.class,
                    OrderPickFragmentModule.class
            }
    )
    OrderPickActivity orderPickActivity();

    @Module
    interface Bindings {

        @Binds
        Activity bindOrderPickActivity(OrderPickActivity orderPickActivity);

        @Binds
        OrderPickActivityContract.View bindView(OrderPickActivity orderPickActivity);

    }
}
