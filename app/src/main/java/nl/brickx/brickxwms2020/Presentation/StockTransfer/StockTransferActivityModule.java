package nl.brickx.brickxwms2020.Presentation.StockTransfer;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface StockTransferActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    StockTransferActivityModule.Bindings.class
            }
    )
    StockTransferActivity productInfoActivity();

    @Module
    interface Bindings {

        @Binds
        Activity bindProductInfoActivity(StockTransferActivity productInfoActivity);

        @Binds
        StockTransferContract.Presenter bindProductInfoPresenter(StockTransferPresenter productInfoPresenter);

        @Binds
        StockTransferContract.View bindProductInfoView(StockTransferActivity productInfoActivity);

        @Binds
        StockTransferContract.Navigator bindNavigator(StockTransferAdapter stockTransferAdapter);
    }
}