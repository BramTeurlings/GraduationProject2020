package nl.brickx.brickxwms2020.Presentation.StockMutation;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface StockMutationActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    StockMutationActivityModule.Bindings.class
            }
    )
    StockMutationActivity productInfoActivity();

    @Module
    interface Bindings {

        @Binds
        Activity bindProductInfoActivity(StockMutationActivity productInfoActivity);

        @Binds
        StockMutationContract.Presenter bindProductInfoPresenter(StockMutationPresenter productInfoPresenter);

        @Binds
        StockMutationContract.View bindProductInfoView(StockMutationActivity productInfoActivity);

        @Binds
        StockMutationContract.Navigator bindNavigator(StockMutationNavigator stockTransferNavigator);
    }
}