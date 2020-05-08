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
        Activity bindProductInfoActivity(StockTransferMainActivity productInfoActivity);

        @Binds
        StockTransferMainContract.Presenter bindProductInfoPresenter(StockTransferMainPresenter productInfoPresenter);

        @Binds
        StockTransferMainContract.View bindProductInfoView(StockTransferMainActivity productInfoActivity);
    }
}