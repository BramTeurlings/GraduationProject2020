package nl.brickx.brickxwms2020.Presentation.StockTransferMain;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface StockTransferMainActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    StockTransferMainActivityModule.Bindings.class
            }
    )
    StockTransferMainActivity productInfoActivity();

    @Module
    interface Bindings {

        @Binds
        Activity bindProductInfoActivity(StockTransferMainActivity stockTransferMainActivity);

        @Binds
        StockTransferMainContract.Presenter bindProductInfoPresenter(StockTransferMainPresenter stockTransferMainPresenter);

        @Binds
        StockTransferMainContract.View bindProductInfoView(StockTransferMainActivity stockTransferMainActivity);

        @Binds
        StockTransferMainContract.Navigator bindNavigator(StockTransferMainNavigator stockTransferMainNavigator);
    }
}