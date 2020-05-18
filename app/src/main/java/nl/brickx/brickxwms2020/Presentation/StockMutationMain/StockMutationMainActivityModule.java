package nl.brickx.brickxwms2020.Presentation.StockMutationMain;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface StockMutationMainActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    StockMutationMainActivityModule.Bindings.class
            }
    )
    StockMutationMainActivity productInfoActivity();

    @Module
    interface Bindings {

        @Binds
        Activity bindProductInfoActivity(StockMutationMainActivity productInfoActivity);

        @Binds
        StockMutationMainContract.Presenter bindProductInfoPresenter(StockMutationMainPresenter productInfoPresenter);

        @Binds
        StockMutationMainContract.View bindProductInfoView(StockMutationMainActivity productInfoActivity);
    }
}