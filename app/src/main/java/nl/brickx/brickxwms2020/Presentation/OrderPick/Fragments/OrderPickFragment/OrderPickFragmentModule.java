package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
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
        OrderPickFragmentContract.Presenter bindPresenter(OrderPickFragmentPresenter orderPickFragmentPresenter);

    }
}
