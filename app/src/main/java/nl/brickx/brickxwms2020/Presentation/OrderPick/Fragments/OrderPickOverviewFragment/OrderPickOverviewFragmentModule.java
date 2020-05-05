package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.DaggerFragment;

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
        DaggerFragment bindOrderPickActivity(OrderPickOverviewFragment orderPickActivity);

        @Binds
        OrderPickOverviewFragmentContract.Presenter bindOrderPickOverviewPresenter(OrderPickOverviewFragmentPresenter orderPickOverviewFragmentPresenter);

        @Binds
        OrderPickOverviewFragmentContract.View bindOrderPickOverviewView(OrderPickOverviewFragment orderPickOverviewFragment);

    }
}
