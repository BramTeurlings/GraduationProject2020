package nl.brickx.brickxwms2020.Presentation.ProductInfo;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ProductInfoActivityModule {

    @ContributesAndroidInjector(
            modules = {
                    nl.brickx.brickxwms2020.Presentation.ProductInfo.ProductInfoActivityModule.Bindings.class
            }
    )
    ProductInfoActivity productInfoActivity();

    @Module
    interface Bindings {

        @Binds
        Activity bindProductInfoActivity(ProductInfoActivity productInfoActivity);

        @Binds
        ProductInfoContract.Presenter bindProductInfoPresenter(ProductInfoPresenter productInfoPresenter);

        @Binds
        ProductInfoContract.View bindProductInfoView(ProductInfoActivity productInfoActivity);
    }
}