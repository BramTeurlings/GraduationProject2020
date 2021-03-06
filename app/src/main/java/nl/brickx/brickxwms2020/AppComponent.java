package nl.brickx.brickxwms2020;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import nl.brickx.brickxwms2020.Presentation.LocationInfo.LocationInfoActivityModule;
import nl.brickx.brickxwms2020.Presentation.Login.LoginActivityModule;
import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuActivityModule;
import nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivityModule;
import nl.brickx.brickxwms2020.Presentation.OrderPickLanding.OrderPickLandingActivityModule;
import nl.brickx.brickxwms2020.Presentation.ProductInfo.ProductInfoActivityModule;
import nl.brickx.brickxwms2020.Presentation.StockMutation.StockMutationActivityModule;
import nl.brickx.brickxwms2020.Presentation.StockMutationMain.StockMutationMainActivityModule;
import nl.brickx.brickxwms2020.Presentation.StockTransfer.StockTransferActivityModule;
import nl.brickx.brickxwms2020.Presentation.StockTransferMain.StockTransferMainActivityModule;


@Singleton
@Component(
        modules = {
                AppModule.class,
                AndroidSupportInjectionModule.class,
                OrderPickActivityModule.class,
                MainMenuActivityModule.class,
                LoginActivityModule.class,
                ProductInfoActivityModule.class,
                LocationInfoActivityModule.class,
                OrderPickLandingActivityModule.class,
                StockTransferActivityModule.class,
                StockTransferMainActivityModule.class,
                StockMutationActivityModule.class,
                StockMutationMainActivityModule.class
        }
)
interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApplication>{

        abstract Builder appModule(AppModule appModule);
    }
}
