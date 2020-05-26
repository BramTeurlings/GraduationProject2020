package nl.brickx.brickxwms2020.Presentation.OrderPickLanding;

import android.app.Activity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface OrderPickLandingActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    OrderPickLandingActivityModule.Bindings.class,
            }
    )
    OrderPickLandingActivity orderPickLandingActivity();

    @Module
    interface Bindings {

        @Binds
        Activity bindOrderPickActivity(OrderPickLandingActivity orderPickActivity);

        @Binds
        OrderPickLandingContract.Presenter bindOrderPickLandingPresenter(OrderPickLandingPresenter orderPickLandingPresenter);

        @Binds
        OrderPickLandingContract.Navigator bindNavigator(OrderPickLandingNavigator orderPickLandingNavigator);

        @Binds
        OrderPickLandingContract.View bindView(OrderPickLandingActivity orderPickLandingActivity);

    }
}
