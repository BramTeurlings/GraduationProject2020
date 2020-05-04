package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.DaggerFragment;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment.OrderPickOverviewFragment;
import nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivity;

@Module
public interface OrderPickFragmentModule {

    @ContributesAndroidInjector(
            modules = {
                    OrderPickFragmentModule.Bindings.class
            }
    )
    OrderPickFragment orderPickFragment();

    @Module
    interface Bindings {

        @Binds
        DaggerFragment bindOrderPickActivity(OrderPickFragment orderPickActivity);

        @Binds
        OrderPickFragmentContract.Presenter bindPresenter(OrderPickFragmentPresenter orderPickFragmentPresenter);

        @Binds
        OrderPickFragmentContract.View bindView(OrderPickFragment orderPickFragment);

    }
}
