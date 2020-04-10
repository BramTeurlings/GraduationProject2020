package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface OrderPickOverviewFragmentModule {

    @ContributesAndroidInjector(
            modules = {
                    OrderPickOverviewFragmentModule.Bindings.class
            }
    )
    OrderPickOverviewFragment orderPickOverviewFragment();

    @Module
    interface Bindings {

        @Binds
        OrderPickOverviewFragmentContract.Presenter bindOrderPickOverviewPresenter(OrderPickOverviewFragmentPresenter orderPickOverviewFragmentPresenter);

        @Binds
        OrderPickOverviewFragmentContract.View bindOrderPickOverviewView(OrderPickOverviewFragment orderPickOverviewFragment);

    }
}
