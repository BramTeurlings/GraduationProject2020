package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

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
        OrderPickOverviewFragmentContract.Presenter bindPresenter(OrderPickOverviewFragmentPresenter orderPickOverviewFragmentPresenter);

    }
}
